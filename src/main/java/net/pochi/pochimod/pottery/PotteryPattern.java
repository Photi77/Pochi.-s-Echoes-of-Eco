package net.pochi.pochimod.pottery;

public enum PotteryPattern {
    NONE("none"),
    STRIPES("stripes"),         // 縞模様
    DOTS("dots"),               // ドット
    WAVES("waves"),             // 波
    FLOWERS("flowers"),         // 花柄
    GEOMETRIC("geometric");     // 幾何学模様

    private final String name;

    PotteryPattern(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}