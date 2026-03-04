package net.pochi.pochimod.mineral;

import java.util.Random;

/**
 * ベースとなる無色宝石5種
 * バイオームコンボに関係なくランダムで決定される
 *
 * 色は不純物（ImpurityType）によって後から付与されるため、
 * ベース自体はすべて無色（白）で統一
 */
public enum BaseGem {

    CORUNDUM ("corundum",  "コランダム"),   // Al2O3 系（ルビー・サファイアの母石）
    CRYSTAL  ("crystal",   "水晶"),         // SiO2 系
    BERYL    ("beryl",     "ベリル"),        // Be3Al2Si6O18 系（エメラルド・アクアマリンの母石）
    ZIRCON   ("zircon",    "ジルコン"),      // ZrSiO4 系
    GROSSULAR("grossular", "グロッシュラー"); // Ca3Al2(SiO4)3 系（ガーネット族）

    public final String id;          // NBT・内部IDに使用
    public final String displayName; // ツールチップ表示名

    BaseGem(String id, String displayName) {
        this.id = id;
        this.displayName = displayName;
    }

    private static final BaseGem[] VALUES = values();

    /**
     * ランダムに1つ選択
     */
    public static BaseGem random(Random random) {
        return VALUES[random.nextInt(VALUES.length)];
    }

    /**
     * IDから復元
     */
    public static BaseGem fromId(String id) {
        for (BaseGem gem : VALUES) {
            if (gem.id.equals(id)) return gem;
        }
        return CRYSTAL; // フォールバック
    }
}