package net.pochi.pochimod.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.pochi.pochimod.item.ModItems;
import net.pochi.pochimod.util.PotteryDataExporter;

public class ExportPotteryCommand {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("exportpottery")
                .requires(source -> source.hasPermission(2))
                .executes(ExportPotteryCommand::execute));
    }

    private static int execute(CommandContext<CommandSourceStack> context) {
        var player = context.getSource().getPlayer();
        if (player == null) return 0;

        ItemStack held = player.getMainHandItem();
        if (held.is(ModItems.FIRED_POTTERY.get()) || held.is(ModItems.UNFIRED_POTTERY.get())) {
            String filename = "pottery_" + System.currentTimeMillis();
            PotteryDataExporter.exportToJson(held, filename);

            context.getSource().sendSuccess(
                    () -> Component.literal("Pottery data exported to " + filename + ".json"),
                    false
            );
            return 1;
        } else {
            context.getSource().sendFailure(Component.literal("Hold a pottery item!"));
            return 0;
        }
    }
}