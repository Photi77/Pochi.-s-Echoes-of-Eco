package net.pochi.pochimod.pottery;

public enum PotteryShape {
    BOWL("bowl"),           // 碗
    PLATE("plate"),         // 皿
    CUP("cup"),            // カップ
    VASE("vase"),          // 壺
    TEAPOT("teapot");      // 急須

    private final String name;

    PotteryShape(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
