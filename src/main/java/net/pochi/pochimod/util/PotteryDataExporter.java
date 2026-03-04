package net.pochi.pochimod.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

public class PotteryDataExporter {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static void exportToJson(ItemStack pottery, String filename) {
        if (!pottery.has(DataComponents.CUSTOM_DATA)) return;
        CompoundTag tag = pottery.get(DataComponents.CUSTOM_DATA).copyTag();

        PotteryData data = new PotteryData();
        data.height = tag.getInt("Height");
        data.diameter = tag.getInt("Diameter");
        data.wallThickness = tag.getInt("WallThickness");
        data.mouthWidth = tag.getInt("MouthWidth");
        data.shape = tag.getString("Shape");
        data.glazeColor = String.format("#%06X", tag.getInt("GlazeColor") & 0xFFFFFF);

        // 実寸換算（1ブロック = 10cm）
        data.realHeight = data.height * 10.0;
        data.realDiameter = data.diameter * 10.0;
        data.realWallThickness = data.wallThickness * 2.0;

        try (FileWriter writer = new FileWriter(
                Paths.get("pottery_exports", filename + ".json").toFile())) {
            GSON.toJson(data, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class PotteryData {
        int height;
        int diameter;
        int wallThickness;
        int mouthWidth;
        String shape;
        String glazeColor;
        double realHeight;
        double realDiameter;
        double realWallThickness;
    }
}
