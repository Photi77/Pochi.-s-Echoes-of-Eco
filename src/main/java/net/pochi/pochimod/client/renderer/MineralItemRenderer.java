package net.pochi.pochimod.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.client.model.data.ModelData;
import net.neoforged.neoforge.client.event.ModelEvent;
import net.pochi.pochimod.item.ModItems;
import net.pochi.pochimod.mineral.MineralChunkItem;
import net.pochi.pochimod.mineral.MineralColorCalculator;
import net.pochi.pochimod.mineral.tools.ToolNBTHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * mineral 系アイテム共通 BEWLR
 *
 * builtin/entity モデルを持つ mineral アイテムが呼び出すレンダラー。
 * 事前に登録した "_base" standalone モデルを取得してレンダリングし、
 * NBT の color_hex を頂点カラーとして乗算することで着色する。
 */
public class MineralItemRenderer extends BlockEntityWithoutLevelRenderer {

    private static MineralItemRenderer instance;

    /** Item → standalone ベースモデルの ResourceLocation */
    private static final Map<Item, ModelResourceLocation> BASE_MODEL_MAP = new HashMap<>();

    private MineralItemRenderer(BlockEntityRenderDispatcher dispatcher, EntityModelSet models) {
        super(dispatcher, models);
    }

    /** IClientItemExtensions から呼ばれる遅延シングルトン */
    public static MineralItemRenderer getInstance() {
        if (instance == null) {
            Minecraft mc = Minecraft.getInstance();
            instance = new MineralItemRenderer(
                    mc.getBlockEntityRenderDispatcher(),
                    mc.getEntityModels()
            );
        }
        return instance;
    }

    /**
     * ModelEvent.RegisterAdditional ハンドラから呼ぶ。
     * ・event.register() でテクスチャをアトラスに登録（ベースモデルのベイク）
     * ・BASE_MODEL_MAP へアイテム→モデルのマッピングを追加
     */
    public static void registerModels(ModelEvent.RegisterAdditional event) {
        addModel(event, ModItems.MINERAL_CHUNK,      "block/large_goshenite_bud");
        addModel(event, ModItems.MINERAL_SWORD,      "item/mineral_sword_base");
        addModel(event, ModItems.MINERAL_AXE,        "item/mineral_axe_base");
        addModel(event, ModItems.MINERAL_PICKAXE,    "item/mineral_pickaxe_base");
        addModel(event, ModItems.MINERAL_SHOVEL,     "item/mineral_shovel_base");
        addModel(event, ModItems.MINERAL_HELMET,     "item/mineral_helmet_base");
        addModel(event, ModItems.MINERAL_CHESTPLATE, "item/mineral_chestplate_base");
        addModel(event, ModItems.MINERAL_LEGGINGS,   "item/mineral_leggings_base");
        addModel(event, ModItems.MINERAL_BOOTS,      "item/mineral_boots_base");
    }

    private static void addModel(
            ModelEvent.RegisterAdditional event,
            net.neoforged.neoforge.registries.DeferredHolder<Item, Item> holder,
            String path) {
        ResourceLocation rl = ResourceLocation.fromNamespaceAndPath("pochimod", path);
        ModelResourceLocation mrl = ModelResourceLocation.standalone(rl);
        event.register(mrl);  // standalone variant で登録（NeoForge 21.1.x 要件）
        BASE_MODEL_MAP.put(holder.get(), mrl);
    }

    // ==============================
    //  レンダリング
    // ==============================

    @Override
    public void renderByItem(ItemStack stack, ItemDisplayContext ctx,
                             PoseStack ps, MultiBufferSource buf,
                             int light, int overlay) {

        ModelResourceLocation baseRL = BASE_MODEL_MAP.get(stack.getItem());
        if (baseRL == null) return;

        BakedModel model = Minecraft.getInstance().getModelManager().getModel(baseRL);
        if (model == null) return;

        // NBT から色を解決 (0xRRGGBB)
        int color = resolveColor(stack);
        float r = ((color >> 16) & 0xFF) / 255f;
        float g = ((color >>  8) & 0xFF) / 255f;
        float b = ( color        & 0xFF) / 255f;

        var vc   = buf.getBuffer(RenderType.cutout());
        var rand = RandomSource.create();
        var pose = ps.last();

        // 方向付き面 + 方向なし面を順に描画
        for (Direction dir : Direction.values()) {
            List<BakedQuad> quads = model.getQuads(null, dir, rand, ModelData.EMPTY, null);
            for (BakedQuad quad : quads) {
                vc.putBulkData(pose, quad, r, g, b, 1f, light, overlay);
            }
        }
        for (BakedQuad quad : model.getQuads(null, null, rand, ModelData.EMPTY, null)) {
            vc.putBulkData(pose, quad, r, g, b, 1f, light, overlay);
        }
    }

    /** ItemStack から色を解決する */
    private static int resolveColor(ItemStack stack) {
        // ツール系 (tool_data)
        ToolNBTHelper.ToolData tool = ToolNBTHelper.read(stack);
        if (tool != null && tool.colorHex() != null && !tool.colorHex().isEmpty()) {
            return MineralColorCalculator.hexToInt(tool.colorHex());
        }
        // チャンク系 (mineral_data)
        return MineralChunkItem.getItemColor(stack, 0);
    }
}
