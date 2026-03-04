package net.pochi.pochimod.command;

import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.ferm.SoilNutrientHelper;
import net.pochi.pochimod.item.food.FoodQualityHelper;

@EventBusSubscriber(modid = PochiMod.MOD_ID)
public class SoilDebugCommand {

    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
        event.getDispatcher().register(
                Commands.literal("soil")
                        .requires(source -> source.hasPermission(2))
                        .then(Commands.literal("set")
                                .then(Commands.argument("nutrient", StringArgumentType.word())
                                        .suggests((ctx, builder) -> {
                                            builder.suggest("n");
                                            builder.suggest("p");
                                            builder.suggest("k");
                                            builder.suggest("all");
                                            return builder.buildFuture();
                                        })
                                        .then(Commands.argument("value", FloatArgumentType.floatArg(0f, 1f))
                                                .executes(ctx -> setSoil(ctx,
                                                        StringArgumentType.getString(ctx, "nutrient"),
                                                        FloatArgumentType.getFloat(ctx, "value"))))))
                        .then(Commands.literal("get")
                                .executes(SoilDebugCommand::getSoil))
        );
    }

    private static int setSoil(CommandContext<CommandSourceStack> ctx,
                               String nutrient, float value) throws CommandSyntaxException {
        ServerPlayer player = ctx.getSource().getPlayerOrException();
        Level level = player.level();

        // プレイヤーの足元1ブロック下を対象
        BlockPos farmlandPos = player.blockPosition().below();

        switch (nutrient) {
            case "n"   -> SoilNutrientHelper.set(level, farmlandPos,
                    SoilNutrientHelper.KEY_NITROGEN, value);
            case "p"   -> SoilNutrientHelper.set(level, farmlandPos,
                    SoilNutrientHelper.KEY_PHOSPHORUS, value);
            case "k"   -> SoilNutrientHelper.set(level, farmlandPos,
                    SoilNutrientHelper.KEY_POTASSIUM, value);
            case "all" -> {
                SoilNutrientHelper.set(level, farmlandPos,
                        SoilNutrientHelper.KEY_NITROGEN, value);
                SoilNutrientHelper.set(level, farmlandPos,
                        SoilNutrientHelper.KEY_PHOSPHORUS, value);
                SoilNutrientHelper.set(level, farmlandPos,
                        SoilNutrientHelper.KEY_POTASSIUM, value);
            }
        }

        ctx.getSource().sendSuccess(() ->
                Component.literal(String.format("足元の土壌[%s] → %.2f にセットしました (pos: %s)",
                        nutrient.toUpperCase(), value, farmlandPos)), false);
        return 1;
    }

    private static int getSoil(CommandContext<CommandSourceStack> ctx)
            throws CommandSyntaxException {
        ServerPlayer player = ctx.getSource().getPlayerOrException();
        Level level = player.level();
        BlockPos farmlandPos = player.blockPosition();

        float n = SoilNutrientHelper.get(level, farmlandPos, SoilNutrientHelper.KEY_NITROGEN);
        float p = SoilNutrientHelper.get(level, farmlandPos, SoilNutrientHelper.KEY_PHOSPHORUS);
        float k = SoilNutrientHelper.get(level, farmlandPos, SoilNutrientHelper.KEY_POTASSIUM);

        float quality = SoilNutrientHelper.calcQuality(n, p, k, 1f, 1f, 1f);
        FoodQualityHelper.QualityRank rank = FoodQualityHelper.getRank(quality);

        ctx.getSource().sendSuccess(() ->
                Component.literal(String.format(
                        "土壌データ (pos: %s)\n  N: %.2f  P: %.2f  K: %.2f\n  → 品質係数: %.2f [%s]",
                        farmlandPos, n, p, k, quality, rank.label)), false);
        return 1;
    }
}