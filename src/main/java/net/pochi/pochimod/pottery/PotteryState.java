package net.pochi.pochimod.pottery;

public enum PotteryState {
    SHAPING("shaping", 0),          // 成形中
    DRYING("drying", 1200),         // 乾燥中（60秒）
    GLAZEABLE("glazeable", 0),      // 釉薬塗布可能
    FIRING("firing", 2400),         // 焼成中（120秒）
    FINISHED("finished", 0);        // 完成

    private final String name;
    private final int duration; // ティック数（0は手動遷移）

    PotteryState(String name, int duration) {
        this.name = name;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    public PotteryState next() {
        return switch (this) {
            case SHAPING -> DRYING;
            case DRYING -> GLAZEABLE;
            case GLAZEABLE -> FIRING;
            case FIRING -> FINISHED;
            case FINISHED -> FINISHED;
        };
    }

    public boolean canProgress() {
        return this != FINISHED;
    }
}
