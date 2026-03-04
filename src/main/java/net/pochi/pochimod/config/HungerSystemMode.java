package net.pochi.pochimod.config;

/**
 * 満腹度システムのモード（独立したEnum）
 */
public enum HungerSystemMode {
    VANILLA("vanilla"),
    COEXIST("coexist"),
    NUTRITION_ONLY("nutrition_only");

    private final String name;

    HungerSystemMode(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}