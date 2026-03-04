package net.pochi.pochimod.mineral.tools;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.crafting.*;
import net.pochi.pochimod.mineral.MineralChunkItem;
import net.pochi.pochimod.mineral.MineralData;
import net.pochi.pochimod.mineral.MineralNBTHelper;
import net.pochi.pochimod.recipe.ModRecipes;

import java.util.ArrayList;
import java.util.List;


public class MineralCraftingRecipe extends ShapedRecipe {

    private final ToolNBTHelper.ToolType toolType;
    // Store locally for codec access
    private final ItemStack result;

    public MineralCraftingRecipe(String group, CraftingBookCategory category,
                                 ShapedRecipePattern pattern, ItemStack result,
                                 ToolNBTHelper.ToolType toolType) {
        super(group, category, pattern, result);
        this.result = result;
        this.toolType = toolType;
    }

    // ==============================
    //  assemble: NBTコピーのみ追加
    // ==============================

    @Override
    public ItemStack assemble(CraftingInput container, HolderLookup.Provider access) {
        ItemStack result = super.assemble(container, access);

        // ★ デバッグログ（動作確認後に削除）
        org.apache.logging.log4j.LogManager.getLogger().info(
                "[MineralCrafting] assemble() called, toolType={}", toolType);

        List<MineralData> dataList = new ArrayList<>();
        for (int i = 0; i < container.size(); i++) {
            ItemStack ingredient = container.getItem(i);
            if (!(ingredient.getItem() instanceof MineralChunkItem)) continue;

            CustomData cd = ingredient.get(DataComponents.CUSTOM_DATA);
            if (cd == null) continue;
            CompoundTag tag = cd.copyTag();

            MineralData data = MineralNBTHelper.read(tag);
            if (data != null) {
                dataList.add(data);
                // ★ デバッグログ
                org.apache.logging.log4j.LogManager.getLogger().info(
                        "[MineralCrafting] chunk found: primary={} ratio={}",
                        data.getImpurities().isEmpty() ? "none" : data.getImpurities().get(0).getType().id,
                        data.getImpurities().isEmpty() ? 0 : data.getImpurities().get(0).getRatio());
            }
        }

        if (dataList.isEmpty()) {
            org.apache.logging.log4j.LogManager.getLogger().warn(
                    "[MineralCrafting] no MineralData found in container!");
            return result;
        }

        MineralData merged = MineralDataMerger.merge(dataList);
        if (merged == null) return result;

        // ★ デバッグログ
        org.apache.logging.log4j.LogManager.getLogger().info(
                "[MineralCrafting] merged: primary={} ratio={}",
                merged.getImpurities().isEmpty() ? "none" : merged.getImpurities().get(0).getType().id,
                merged.getImpurities().isEmpty() ? 0 : merged.getImpurities().get(0).getRatio());

        CompoundTag resultTag = result.has(DataComponents.CUSTOM_DATA)
                ? result.get(DataComponents.CUSTOM_DATA).copyTag()
                : new CompoundTag();
        ToolNBTHelper.writeFromMineralData(resultTag, merged, toolType);
        result.set(DataComponents.CUSTOM_DATA, CustomData.of(resultTag));
        return result;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.MINERAL_SHAPED.get();
    }



    // ==============================
    //  Serializer
    // ==============================

    public static class Serializer implements RecipeSerializer<MineralCraftingRecipe> {

        public static final MapCodec<MineralCraftingRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Codec.STRING.optionalFieldOf("group", "").forGetter(ShapedRecipe::getGroup),
                CraftingBookCategory.CODEC.fieldOf("category").orElse(CraftingBookCategory.MISC).forGetter(ShapedRecipe::category),
                ShapedRecipePattern.MAP_CODEC.forGetter(r -> r.pattern),
                ItemStack.STRICT_CODEC.fieldOf("result").forGetter(r -> r.result),
                Codec.STRING.fieldOf("tool_type").xmap(
                        ToolNBTHelper.ToolType::fromId,
                        t -> t.id()
                ).forGetter(r -> r.toolType)
        ).apply(inst, MineralCraftingRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, MineralCraftingRecipe> STREAM_CODEC =
                StreamCodec.of(
                        (buf, recipe) -> {
                            buf.writeUtf(recipe.getGroup());
                            buf.writeEnum(recipe.category());
                            ShapedRecipePattern.STREAM_CODEC.encode(buf, recipe.pattern);
                            ItemStack.STREAM_CODEC.encode(buf, recipe.result);
                            buf.writeUtf(recipe.toolType.id());
                        },
                        buf -> {
                            String group = buf.readUtf();
                            CraftingBookCategory category = buf.readEnum(CraftingBookCategory.class);
                            ShapedRecipePattern pattern = ShapedRecipePattern.STREAM_CODEC.decode(buf);
                            ItemStack result = ItemStack.STREAM_CODEC.decode(buf);
                            ToolNBTHelper.ToolType toolType = ToolNBTHelper.ToolType.fromId(buf.readUtf());
                            return new MineralCraftingRecipe(group, category, pattern, result, toolType);
                        }
                );

        @Override
        public MapCodec<MineralCraftingRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, MineralCraftingRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}
