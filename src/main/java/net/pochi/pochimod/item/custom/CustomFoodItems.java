package net.pochi.pochimod.item.custom;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PotionItem;
import net.pochi.pochimod.nutrition.FoodNutritionData;
import net.pochi.pochimod.nutrition.FoodNutritionRegistry;

/**
 * カスタム食べ物アイテム
 *
 * [設計方針]
 * - バニラ満腹度20/20相当の食事量で、各Mod栄養素100/100に到達できる設計
 * - 飲み物: alwaysEdible設定、水分補給メイン + 素材に応じた栄養素
 * - 食べ物: バニラ栄養値を食材の実態に合わせ設定（スパイス=1, 野菜=2-4, 料理=7-10）
 * - 栄養素は各食材の特性（実際の栄養成分）に基づいて付与
 */
public class CustomFoodItems {

    // =========================================================================
    // 飲み物 (PotionItem, alwaysEdible)
    // =========================================================================

    // --- 水・基本飲料 ---

    // 浄水: 純粋な水分補給
    public static class FilteredWaterItem extends PotionItem {
        public FilteredWaterItem() {
            super(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .alwaysEdible()
                            .build())
            );
            FoodNutritionRegistry.register(this, FoodNutritionData.builder()
                    .hydration(80)
                    .build());
        }
    }

    // 牛乳: タンパク質・カルシウム豊富
    public static class BottleMilkItem extends PotionItem {
        public BottleMilkItem() {
            super(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .alwaysEdible()
                            .build())
            );
            FoodNutritionRegistry.register(this, FoodNutritionData.builder()
                    .protein(20)
                    .lipid(15)
                    .mineral(25)
                    .hydration(50)
                    .build());
        }
    }

    // メープルウォーター: 微糖・ミネラル入り
    public static class MapleWaterItem extends PotionItem {
        public MapleWaterItem() {
            super(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .alwaysEdible()
                            .build())
            );
            FoodNutritionRegistry.register(this, FoodNutritionData.builder()
                    .carbohydrate(10)
                    .mineral(10)
                    .hydration(65)
                    .build());
        }
    }

    // メープルシロップ: 高糖質・ミネラル
    public static class MapleSyrupItem extends PotionItem {
        public MapleSyrupItem() {
            super(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .alwaysEdible()
                            .build())
            );
            FoodNutritionRegistry.register(this, FoodNutritionData.builder()
                    .carbohydrate(50)
                    .mineral(15)
                    .hydration(20)
                    .build());
        }
    }

    // --- コーヒー類 ---

    // コーヒー: ポリフェノール・抗酸化
    public static class CoffeeItem extends PotionItem {
        public CoffeeItem() {
            super(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .alwaysEdible()
                            .build())
            );
            FoodNutritionRegistry.register(this, FoodNutritionData.builder()
                    .vitamin(15)
                    .hydration(35)
                    .build());
        }
    }

    // コピ・ルアク: 高品質コーヒー、抗酸化物質豊富
    public static class KopiLuwakCoffeeItem extends PotionItem {
        public KopiLuwakCoffeeItem() {
            super(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .alwaysEdible()
                            .build())
            );
            FoodNutritionRegistry.register(this, FoodNutritionData.builder()
                    .vitamin(20)
                    .hydration(35)
                    .build());
        }
    }

    // --- アルコール類 ---

    // ウイスキー: アルコール脱水
    public static class WhiskeyItem extends PotionItem {
        public WhiskeyItem() {
            super(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .alwaysEdible()
                            .build())
            );
            FoodNutritionRegistry.register(this, FoodNutritionData.builder()
                    .carbohydrate(15)
                    .hydration(15)
                    .build());
        }
    }

    // 白酒: 蒸留酒
    public static class WhiteLiquorItem extends PotionItem {
        public WhiteLiquorItem() {
            super(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .alwaysEdible()
                            .build())
            );
            FoodNutritionRegistry.register(this, FoodNutritionData.builder()
                    .carbohydrate(10)
                    .hydration(15)
                    .build());
        }
    }

    // コーラ: 高糖質炭酸
    public static class ColaItem extends PotionItem {
        public ColaItem() {
            super(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .alwaysEdible()
                            .build())
            );
            FoodNutritionRegistry.register(this, FoodNutritionData.builder()
                    .carbohydrate(35)
                    .hydration(40)
                    .build());
        }
    }

    // 赤ワイン: ポリフェノール豊富
    public static class RedWineItem extends PotionItem {
        public RedWineItem() {
            super(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .alwaysEdible()
                            .build())
            );
            FoodNutritionRegistry.register(this, FoodNutritionData.builder()
                    .vitamin(25)
                    .carbohydrate(10)
                    .hydration(25)
                    .build());
        }
    }

    // 白ワイン
    public static class WhiteWineItem extends PotionItem {
        public WhiteWineItem() {
            super(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .alwaysEdible()
                            .build())
            );
            FoodNutritionRegistry.register(this, FoodNutritionData.builder()
                    .vitamin(10)
                    .carbohydrate(15)
                    .hydration(30)
                    .build());
        }
    }

    // 日本酒: 米由来
    public static class SakeItem extends PotionItem {
        public SakeItem() {
            super(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .alwaysEdible()
                            .build())
            );
            FoodNutritionRegistry.register(this, FoodNutritionData.builder()
                    .carbohydrate(20)
                    .protein(5)
                    .hydration(25)
                    .build());
        }
    }

    // --- 果実系リキュール ---

    // 桃リキュール
    public static class PeachLiquorItem extends PotionItem {
        public PeachLiquorItem() {
            super(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .alwaysEdible()
                            .build())
            );
            FoodNutritionRegistry.register(this, FoodNutritionData.builder()
                    .vitamin(15)
                    .carbohydrate(20)
                    .hydration(20)
                    .build());
        }
    }

    // 梅酒: ミネラル豊富
    public static class PlumLiquorItem extends PotionItem {
        public PlumLiquorItem() {
            super(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .alwaysEdible()
                            .build())
            );
            FoodNutritionRegistry.register(this, FoodNutritionData.builder()
                    .vitamin(20)
                    .mineral(10)
                    .hydration(20)
                    .build());
        }
    }

    // レモンリキュール: ビタミンC
    public static class LemonLiquorItem extends PotionItem {
        public LemonLiquorItem() {
            super(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .alwaysEdible()
                            .build())
            );
            FoodNutritionRegistry.register(this, FoodNutritionData.builder()
                    .vitamin(25)
                    .carbohydrate(15)
                    .hydration(20)
                    .build());
        }
    }

    // ミントリキュール
    public static class MintLiquorItem extends PotionItem {
        public MintLiquorItem() {
            super(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .alwaysEdible()
                            .build())
            );
            FoodNutritionRegistry.register(this, FoodNutritionData.builder()
                    .vitamin(15)
                    .carbohydrate(15)
                    .hydration(20)
                    .build());
        }
    }

    // りんごリキュール
    public static class AppleLiquorItem extends PotionItem {
        public AppleLiquorItem() {
            super(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .alwaysEdible()
                            .build())
            );
            FoodNutritionRegistry.register(this, FoodNutritionData.builder()
                    .vitamin(15)
                    .carbohydrate(20)
                    .hydration(20)
                    .build());
        }
    }

    // ハブ酒: ハブ由来タンパク質
    public static class HabuLiquorItem extends PotionItem {
        public HabuLiquorItem() {
            super(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .alwaysEdible()
                            .build())
            );
            FoodNutritionRegistry.register(this, FoodNutritionData.builder()
                    .protein(15)
                    .vitamin(10)
                    .hydration(15)
                    .build());
        }
    }

    // --- 発酵材料 ---

    // 麹: ビタミンB群・ミネラル
    public static class AspergillusItem extends PotionItem {
        public AspergillusItem() {
            super(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .alwaysEdible()
                            .build())
            );
            FoodNutritionRegistry.register(this, FoodNutritionData.builder()
                    .mineral(20)
                    .vitamin(25)
                    .hydration(5)
                    .build());
        }
    }

    // 酵母: ビタミンB群豊富
    public static class JYeastItem extends PotionItem {
        public JYeastItem() {
            super(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .alwaysEdible()
                            .build())
            );
            FoodNutritionRegistry.register(this, FoodNutritionData.builder()
                    .vitamin(30)
                    .mineral(15)
                    .protein(10)
                    .hydration(5)
                    .build());
        }
    }

    // --- フルーツジュース ---

    // ぶどうジュース: ポリフェノール
    public static class GrapeJuiceItem extends PotionItem {
        public GrapeJuiceItem() {
            super(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .alwaysEdible()
                            .build())
            );
            FoodNutritionRegistry.register(this, FoodNutritionData.builder()
                    .vitamin(30)
                    .carbohydrate(25)
                    .hydration(55)
                    .build());
        }
    }

    // りんごジュース
    public static class AppleJuiceItem extends PotionItem {
        public AppleJuiceItem() {
            super(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .alwaysEdible()
                            .build())
            );
            FoodNutritionRegistry.register(this, FoodNutritionData.builder()
                    .vitamin(25)
                    .carbohydrate(20)
                    .hydration(55)
                    .build());
        }
    }

    // レモンジュース: ビタミンC最強
    public static class LemonJuiceItem extends PotionItem {
        public LemonJuiceItem() {
            super(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .alwaysEdible()
                            .build())
            );
            FoodNutritionRegistry.register(this, FoodNutritionData.builder()
                    .vitamin(45)
                    .carbohydrate(10)
                    .hydration(60)
                    .build());
        }
    }

    // 桃ジュース
    public static class PeachJuiceItem extends PotionItem {
        public PeachJuiceItem() {
            super(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .alwaysEdible()
                            .build())
            );
            FoodNutritionRegistry.register(this, FoodNutritionData.builder()
                    .vitamin(25)
                    .carbohydrate(20)
                    .hydration(55)
                    .build());
        }
    }

    // 梅ジュース: ミネラル豊富
    public static class PlumJuiceItem extends PotionItem {
        public PlumJuiceItem() {
            super(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .alwaysEdible()
                            .build())
            );
            FoodNutritionRegistry.register(this, FoodNutritionData.builder()
                    .vitamin(25)
                    .mineral(15)
                    .hydration(55)
                    .build());
        }
    }

    // バナナジュース: カリウム豊富
    public static class BananaJuiceItem extends PotionItem {
        public BananaJuiceItem() {
            super(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .alwaysEdible()
                            .build())
            );
            FoodNutritionRegistry.register(this, FoodNutritionData.builder()
                    .carbohydrate(35)
                    .vitamin(20)
                    .mineral(15)
                    .hydration(45)
                    .build());
        }
    }

    // アーモンドジュース(アーモンドミルク): 植物性タンパク
    public static class AlmondJuiceItem extends PotionItem {
        public AlmondJuiceItem() {
            super(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .alwaysEdible()
                            .build())
            );
            FoodNutritionRegistry.register(this, FoodNutritionData.builder()
                    .protein(20)
                    .lipid(15)
                    .mineral(15)
                    .hydration(50)
                    .build());
        }
    }

    // ココナッツジュース: 電解質豊富
    public static class CoconutJuiceItem extends PotionItem {
        public CoconutJuiceItem() {
            super(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .alwaysEdible()
                            .build())
            );
            FoodNutritionRegistry.register(this, FoodNutritionData.builder()
                    .mineral(30)
                    .carbohydrate(10)
                    .hydration(70)
                    .build());
        }
    }

    // スムージー: 総合栄養
    public static class SmoothieJuiceItem extends PotionItem {
        public SmoothieJuiceItem() {
            super(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .alwaysEdible()
                            .build())
            );
            FoodNutritionRegistry.register(this, FoodNutritionData.builder()
                    .vitamin(30)
                    .carbohydrate(20)
                    .protein(10)
                    .hydration(50)
                    .build());
        }
    }

    // ミックスジュース
    public static class MixJuiceItem extends PotionItem {
        public MixJuiceItem() {
            super(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .alwaysEdible()
                            .build())
            );
            FoodNutritionRegistry.register(this, FoodNutritionData.builder()
                    .vitamin(30)
                    .carbohydrate(25)
                    .hydration(55)
                    .build());
        }
    }

    // ミックスオレ: 牛乳入りジュース
    public static class MixAuLaitJuiceItem extends PotionItem {
        public MixAuLaitJuiceItem() {
            super(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .alwaysEdible()
                            .build())
            );
            FoodNutritionRegistry.register(this, FoodNutritionData.builder()
                    .protein(20)
                    .carbohydrate(20)
                    .lipid(15)
                    .mineral(15)
                    .hydration(40)
                    .build());
        }
    }

    // チョコジュース
    public static class ChocoJuiceItem extends PotionItem {
        public ChocoJuiceItem() {
            super(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .alwaysEdible()
                            .build())
            );
            FoodNutritionRegistry.register(this, FoodNutritionData.builder()
                    .carbohydrate(30)
                    .lipid(20)
                    .mineral(10)
                    .hydration(35)
                    .build());
        }
    }

    // チョコミントジュース
    public static class ChocoMintJuiceItem extends PotionItem {
        public ChocoMintJuiceItem() {
            super(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .alwaysEdible()
                            .build())
            );
            FoodNutritionRegistry.register(this, FoodNutritionData.builder()
                    .carbohydrate(25)
                    .lipid(15)
                    .vitamin(10)
                    .hydration(40)
                    .build());
        }
    }


    // =========================================================================
    // 食べ物 (Item)
    // =========================================================================

    // --- 特別食材 ---

    // キャビア: 高タンパク・高ミネラルの高級食材
    public static class CaviarItem extends Item {
        public CaviarItem() {
            super(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(6)
                            .saturationModifier(1.5f)
                            .build())
            );
            FoodNutritionRegistry.register(this, FoodNutritionData.builder()
                    .protein(40)
                    .lipid(20)
                    .mineral(20)
                    .build());
        }
    }

    // --- 野菜 ---

    // キャベツ
    public static class CabbageItem extends Item {
        public CabbageItem() {
            super(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(3)
                            .saturationModifier(0.4f)
                            .build())
            );
            FoodNutritionRegistry.register(this, FoodNutritionData.builder()
                    .vitamin(25)
                    .mineral(10)
                    .hydration(15)
                    .build());
        }
    }

    // アスパラガス
    public static class AsparagusItem extends Item {
        public AsparagusItem() {
            super(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(2)
                            .saturationModifier(0.4f)
                            .build())
            );
            FoodNutritionRegistry.register(this, FoodNutritionData.builder()
                    .vitamin(35)
                    .mineral(15)
                    .hydration(10)
                    .build());
        }
    }

    // ぶどう
    public static class GrapeItem extends Item {
        public GrapeItem() {
            super(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(3)
                            .saturationModifier(0.5f)
                            .build())
            );
            FoodNutritionRegistry.register(this, FoodNutritionData.builder()
                    .vitamin(25)
                    .carbohydrate(20)
                    .build());
        }
    }

    // レモン: ビタミンC筆頭
    public static class LemonItem extends Item {
        public LemonItem() {
            super(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(2)
                            .saturationModifier(0.3f)
                            .build())
            );
            FoodNutritionRegistry.register(this, FoodNutritionData.builder()
                    .vitamin(50)
                    .carbohydrate(5)
                    .build());
        }
    }

    // シナモン（スパイス）
    public static class CinnamonItem extends Item {
        public CinnamonItem() {
            super(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(1)
                            .saturationModifier(0.2f)
                            .build())
            );
            FoodNutritionRegistry.register(this, FoodNutritionData.builder()
                    .mineral(10)
                    .vitamin(10)
                    .build());
        }
    }

    // 米: 炭水化物の主役
    public static class RiceItem extends Item {
        public RiceItem() {
            super(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(5)
                            .saturationModifier(0.7f)
                            .build())
            );
            FoodNutritionRegistry.register(this, FoodNutritionData.builder()
                    .carbohydrate(50)
                    .build());
        }
    }

    // コーラの実
    public static class ColaFruitsItem extends Item {
        public ColaFruitsItem() {
            super(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(3)
                            .saturationModifier(0.5f)
                            .build())
            );
            FoodNutritionRegistry.register(this, FoodNutritionData.builder()
                    .carbohydrate(20)
                    .vitamin(10)
                    .build());
        }
    }

    // トマト
    public static class TomatoItem extends Item {
        public TomatoItem() {
            super(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(2)
                            .saturationModifier(0.3f)
                            .build())
            );
            FoodNutritionRegistry.register(this, FoodNutritionData.builder()
                    .vitamin(30)
                    .carbohydrate(10)
                    .hydration(15)
                    .build());
        }
    }

    // コーン
    public static class CornItem extends Item {
        public CornItem() {
            super(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(4)
                            .saturationModifier(0.5f)
                            .build())
            );
            FoodNutritionRegistry.register(this, FoodNutritionData.builder()
                    .carbohydrate(30)
                    .vitamin(15)
                    .build());
        }
    }

    // タマネギ
    public static class OnionItem extends Item {
        public OnionItem() {
            super(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(2)
                            .saturationModifier(0.3f)
                            .build())
            );
            FoodNutritionRegistry.register(this, FoodNutritionData.builder()
                    .vitamin(20)
                    .mineral(15)
                    .hydration(10)
                    .build());
        }
    }

    // ショウガ（スパイス）
    public static class GingerItem extends Item {
        public GingerItem() {
            super(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(1)
                            .saturationModifier(0.2f)
                            .build())
            );
            FoodNutritionRegistry.register(this, FoodNutritionData.builder()
                    .vitamin(15)
                    .mineral(10)
                    .build());
        }
    }

    // ピーマン: ビタミンC豊富
    public static class GreenPepperItem extends Item {
        public GreenPepperItem() {
            super(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(2)
                            .saturationModifier(0.3f)
                            .build())
            );
            FoodNutritionRegistry.register(this, FoodNutritionData.builder()
                    .vitamin(40)
                    .mineral(10)
                    .build());
        }
    }

    // パプリカ: ビタミンC最高峰野菜
    public static class PaprikaItem extends Item {
        public PaprikaItem() {
            super(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(2)
                            .saturationModifier(0.3f)
                            .build())
            );
            FoodNutritionRegistry.register(this, FoodNutritionData.builder()
                    .vitamin(45)
                    .carbohydrate(5)
                    .build());
        }
    }

    // ナス
    public static class EggPlantItem extends Item {
        public EggPlantItem() {
            super(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(2)
                            .saturationModifier(0.3f)
                            .build())
            );
            FoodNutritionRegistry.register(this, FoodNutritionData.builder()
                    .vitamin(15)
                    .carbohydrate(10)
                    .mineral(10)
                    .hydration(10)
                    .build());
        }
    }

    // 大根
    public static class WhiteRadishItem extends Item {
        public WhiteRadishItem() {
            super(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(2)
                            .saturationModifier(0.3f)
                            .build())
            );
            FoodNutritionRegistry.register(this, FoodNutritionData.builder()
                    .vitamin(20)
                    .mineral(15)
                    .hydration(15)
                    .build());
        }
    }

    // 梅
    public static class PlumItem extends Item {
        public PlumItem() {
            super(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(3)
                            .saturationModifier(0.5f)
                            .build())
            );
            FoodNutritionRegistry.register(this, FoodNutritionData.builder()
                    .vitamin(20)
                    .carbohydrate(15)
                    .mineral(10)
                    .build());
        }
    }

    // チェリー
    public static class CherryItem extends Item {
        public CherryItem() {
            super(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(3)
                            .saturationModifier(0.5f)
                            .build())
            );
            FoodNutritionRegistry.register(this, FoodNutritionData.builder()
                    .vitamin(20)
                    .carbohydrate(20)
                    .build());
        }
    }

    // バナナ: カリウム豊富
    public static class BananaItem extends Item {
        public BananaItem() {
            super(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(4)
                            .saturationModifier(0.6f)
                            .build())
            );
            FoodNutritionRegistry.register(this, FoodNutritionData.builder()
                    .carbohydrate(40)
                    .vitamin(25)
                    .mineral(15)
                    .build());
        }
    }

    // ココナッツ
    public static class CoconutItem extends Item {
        public CoconutItem() {
            super(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(4)
                            .saturationModifier(0.7f)
                            .build())
            );
            FoodNutritionRegistry.register(this, FoodNutritionData.builder()
                    .lipid(25)
                    .carbohydrate(15)
                    .mineral(15)
                    .build());
        }
    }

    // 桃
    public static class PeachItem extends Item {
        public PeachItem() {
            super(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(3)
                            .saturationModifier(0.5f)
                            .build())
            );
            FoodNutritionRegistry.register(this, FoodNutritionData.builder()
                    .vitamin(20)
                    .carbohydrate(20)
                    .hydration(10)
                    .build());
        }
    }

    // キウイ: ビタミンC豊富
    public static class KiwiItem extends Item {
        public KiwiItem() {
            super(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(3)
                            .saturationModifier(0.5f)
                            .build())
            );
            FoodNutritionRegistry.register(this, FoodNutritionData.builder()
                    .vitamin(45)
                    .carbohydrate(15)
                    .mineral(10)
                    .build());
        }
    }

    // アーモンド: 脂質・タンパク質・ビタミンE
    public static class AlmondItem extends Item {
        public AlmondItem() {
            super(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(4)
                            .saturationModifier(0.8f)
                            .build())
            );
            FoodNutritionRegistry.register(this, FoodNutritionData.builder()
                    .lipid(30)
                    .protein(25)
                    .vitamin(20)
                    .mineral(15)
                    .build());
        }
    }

    // ドリアン: 高カロリーフルーツ
    public static class DurianItem extends Item {
        public DurianItem() {
            super(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(5)
                            .saturationModifier(0.7f)
                            .build())
            );
            FoodNutritionRegistry.register(this, FoodNutritionData.builder()
                    .carbohydrate(25)
                    .lipid(10)
                    .vitamin(15)
                    .build());
        }
    }

    // オリーブ: 良質な脂質
    public static class OliveItem extends Item {
        public OliveItem() {
            super(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(2)
                            .saturationModifier(0.5f)
                            .build())
            );
            FoodNutritionRegistry.register(this, FoodNutritionData.builder()
                    .lipid(35)
                    .vitamin(10)
                    .mineral(10)
                    .build());
        }
    }

    // ブルーベリー: アントシアニン
    public static class BlueBerryItem extends Item {
        public BlueBerryItem() {
            super(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(2)
                            .saturationModifier(0.5f)
                            .build())
            );
            FoodNutritionRegistry.register(this, FoodNutritionData.builder()
                    .vitamin(35)
                    .carbohydrate(15)
                    .build());
        }
    }

    // チリペッパー（スパイス）: カプサイシン
    public static class ChiliPepperItem extends Item {
        public ChiliPepperItem() {
            super(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(1)
                            .saturationModifier(0.2f)
                            .build())
            );
            FoodNutritionRegistry.register(this, FoodNutritionData.builder()
                    .vitamin(40)
                    .build());
        }
    }

    // バジル（ハーブ）
    public static class BasilItem extends Item {
        public BasilItem() {
            super(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(1)
                            .saturationModifier(0.2f)
                            .build())
            );
            FoodNutritionRegistry.register(this, FoodNutritionData.builder()
                    .vitamin(20)
                    .mineral(10)
                    .build());
        }
    }

    // レンコン: ミネラル・炭水化物
    public static class LotusRootItem extends Item {
        public LotusRootItem() {
            super(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(3)
                            .saturationModifier(0.5f)
                            .build())
            );
            FoodNutritionRegistry.register(this, FoodNutritionData.builder()
                    .carbohydrate(25)
                    .vitamin(15)
                    .mineral(20)
                    .build());
        }
    }

    // --- サンドイッチ類 ---

    // トマトサンド
    public static class TomatoSandItem extends Item {
        public TomatoSandItem() {
            super(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(6)
                            .saturationModifier(0.7f)
                            .build())
            );
            FoodNutritionRegistry.register(this, FoodNutritionData.builder()
                    .carbohydrate(30)
                    .vitamin(20)
                    .build());
        }
    }

    // バナナサンド
    public static class BananaSandItem extends Item {
        public BananaSandItem() {
            super(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(6)
                            .saturationModifier(0.7f)
                            .build())
            );
            FoodNutritionRegistry.register(this, FoodNutritionData.builder()
                    .carbohydrate(40)
                    .vitamin(15)
                    .mineral(10)
                    .build());
        }
    }

    // 桃サンド
    public static class PeachSandItem extends Item {
        public PeachSandItem() {
            super(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(6)
                            .saturationModifier(0.7f)
                            .build())
            );
            FoodNutritionRegistry.register(this, FoodNutritionData.builder()
                    .carbohydrate(30)
                    .vitamin(15)
                    .build());
        }
    }

    // りんごサンド
    public static class AppleSandItem extends Item {
        public AppleSandItem() {
            super(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(6)
                            .saturationModifier(0.7f)
                            .build())
            );
            FoodNutritionRegistry.register(this, FoodNutritionData.builder()
                    .carbohydrate(35)
                    .vitamin(15)
                    .build());
        }
    }

    // ぶどうサンド
    public static class GrapeSandItem extends Item {
        public GrapeSandItem() {
            super(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(6)
                            .saturationModifier(0.7f)
                            .build())
            );
            FoodNutritionRegistry.register(this, FoodNutritionData.builder()
                    .carbohydrate(35)
                    .vitamin(20)
                    .build());
        }
    }

    // --- 料理 ---

    // アスパラガスベーコン
    public static class AsparagusBaconItem extends Item {
        public AsparagusBaconItem() {
            super(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(8)
                            .saturationModifier(1.0f)
                            .build())
            );
            FoodNutritionRegistry.register(this, FoodNutritionData.builder()
                    .protein(30)
                    .lipid(25)
                    .vitamin(20)
                    .build());
        }
    }

    // 生姜焼き
    public static class GingerPorkItem extends Item {
        public GingerPorkItem() {
            super(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(8)
                            .saturationModifier(1.1f)
                            .build())
            );
            FoodNutritionRegistry.register(this, FoodNutritionData.builder()
                    .protein(40)
                    .lipid(30)
                    .vitamin(10)
                    .build());
        }
    }

    // 揚げナス
    public static class FriedEggplantItem extends Item {
        public FriedEggplantItem() {
            super(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(7)
                            .saturationModifier(0.9f)
                            .build())
            );
            FoodNutritionRegistry.register(this, FoodNutritionData.builder()
                    .vitamin(20)
                    .lipid(25)
                    .carbohydrate(15)
                    .build());
        }
    }

    // 青椒肉絲
    public static class ChinjaoloseeItem extends Item {
        public ChinjaoloseeItem() {
            super(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(8)
                            .saturationModifier(1.0f)
                            .build())
            );
            FoodNutritionRegistry.register(this, FoodNutritionData.builder()
                    .protein(35)
                    .vitamin(30)
                    .lipid(20)
                    .build());
        }
    }

    // ポップコーン
    public static class PopcornItem extends Item {
        public PopcornItem() {
            super(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(5)
                            .saturationModifier(0.6f)
                            .build())
            );
            FoodNutritionRegistry.register(this, FoodNutritionData.builder()
                    .carbohydrate(35)
                    .lipid(10)
                    .build());
        }
    }

    // ピザパン
    public static class PizzaBredItem extends Item {
        public PizzaBredItem() {
            super(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(8)
                            .saturationModifier(0.9f)
                            .build())
            );
            FoodNutritionRegistry.register(this, FoodNutritionData.builder()
                    .carbohydrate(40)
                    .protein(25)
                    .lipid(20)
                    .build());
        }
    }

    // 煮魚
    public static class BoiledFishItem extends Item {
        public BoiledFishItem() {
            super(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(8)
                            .saturationModifier(1.1f)
                            .build())
            );
            FoodNutritionRegistry.register(this, FoodNutritionData.builder()
                    .protein(45)
                    .mineral(20)
                    .hydration(10)
                    .build());
        }
    }

    // コーンスープ
    public static class CornSoupItem extends Item {
        public CornSoupItem() {
            super(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(7)
                            .saturationModifier(0.8f)
                            .build())
            );
            FoodNutritionRegistry.register(this, FoodNutritionData.builder()
                    .carbohydrate(30)
                    .vitamin(20)
                    .hydration(20)
                    .build());
        }
    }

    // ハンバーガー
    public static class HambugerItem extends Item {
        public HambugerItem() {
            super(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(9)
                            .saturationModifier(1.0f)
                            .build())
            );
            FoodNutritionRegistry.register(this, FoodNutritionData.builder()
                    .protein(35)
                    .carbohydrate(35)
                    .lipid(30)
                    .build());
        }
    }

    // ペペロンチーノ
    public static class PeperoncinoItem extends Item {
        public PeperoncinoItem() {
            super(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(8)
                            .saturationModifier(0.9f)
                            .build())
            );
            FoodNutritionRegistry.register(this, FoodNutritionData.builder()
                    .carbohydrate(40)
                    .vitamin(20)
                    .lipid(15)
                    .build());
        }
    }

    // 麻婆茄子
    public static class MaboNasuItem extends Item {
        public MaboNasuItem() {
            super(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(8)
                            .saturationModifier(1.0f)
                            .build())
            );
            FoodNutritionRegistry.register(this, FoodNutritionData.builder()
                    .protein(25)
                    .vitamin(20)
                    .lipid(15)
                    .carbohydrate(15)
                    .build());
        }
    }

    // 焼きとうもろこし
    public static class BakedCornItem extends Item {
        public BakedCornItem() {
            super(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(6)
                            .saturationModifier(0.8f)
                            .build())
            );
            FoodNutritionRegistry.register(this, FoodNutritionData.builder()
                    .carbohydrate(35)
                    .vitamin(15)
                    .build());
        }
    }

    // 大根のひき肉炒め
    public static class RadishMinciMeatItem extends Item {
        public RadishMinciMeatItem() {
            super(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(8)
                            .saturationModifier(1.0f)
                            .build())
            );
            FoodNutritionRegistry.register(this, FoodNutritionData.builder()
                    .protein(30)
                    .vitamin(20)
                    .mineral(15)
                    .build());
        }
    }

    // 鶏の卵
    public static class ChickenEggItem extends Item {
        public ChickenEggItem() {
            super(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(4)
                            .saturationModifier(0.8f)
                            .build())
            );
            FoodNutritionRegistry.register(this, FoodNutritionData.builder()
                    .protein(35)
                    .lipid(25)
                    .mineral(20)
                    .build());
        }
    }

    // ジェノベーゼ
    public static class GenoveseItem extends Item {
        public GenoveseItem() {
            super(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(8)
                            .saturationModifier(0.9f)
                            .build())
            );
            FoodNutritionRegistry.register(this, FoodNutritionData.builder()
                    .carbohydrate(40)
                    .vitamin(20)
                    .lipid(20)
                    .build());
        }
    }

    // 揚げアーモンド
    public static class FriedAlmondItem extends Item {
        public FriedAlmondItem() {
            super(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(6)
                            .saturationModifier(0.9f)
                            .build())
            );
            FoodNutritionRegistry.register(this, FoodNutritionData.builder()
                    .lipid(30)
                    .protein(20)
                    .carbohydrate(10)
                    .build());
        }
    }

    // グリーンカレー
    public static class GreenCarryItem extends Item {
        public GreenCarryItem() {
            super(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(9)
                            .saturationModifier(1.1f)
                            .build())
            );
            FoodNutritionRegistry.register(this, FoodNutritionData.builder()
                    .protein(25)
                    .vitamin(30)
                    .lipid(20)
                    .carbohydrate(20)
                    .build());
        }
    }

    // ピーマンのひき肉詰め
    public static class GreenPepperMincedMeatItem extends Item {
        public GreenPepperMincedMeatItem() {
            super(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(8)
                            .saturationModifier(1.0f)
                            .build())
            );
            FoodNutritionRegistry.register(this, FoodNutritionData.builder()
                    .protein(35)
                    .vitamin(25)
                    .lipid(20)
                    .build());
        }
    }

    // ぺぺキャベツ
    public static class PepeCabbageItem extends Item {
        public PepeCabbageItem() {
            super(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(5)
                            .saturationModifier(0.7f)
                            .build())
            );
            FoodNutritionRegistry.register(this, FoodNutritionData.builder()
                    .vitamin(30)
                    .carbohydrate(15)
                    .mineral(10)
                    .build());
        }
    }

    // 揚げレンコン
    public static class FriedLotusRootItem extends Item {
        public FriedLotusRootItem() {
            super(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(7)
                            .saturationModifier(0.9f)
                            .build())
            );
            FoodNutritionRegistry.register(this, FoodNutritionData.builder()
                    .carbohydrate(30)
                    .lipid(20)
                    .mineral(15)
                    .build());
        }
    }

    // レンコンのひき肉炒め
    public static class LotusRootMincedMeatItem extends Item {
        public LotusRootMincedMeatItem() {
            super(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(8)
                            .saturationModifier(1.0f)
                            .build())
            );
            FoodNutritionRegistry.register(this, FoodNutritionData.builder()
                    .protein(30)
                    .carbohydrate(20)
                    .mineral(15)
                    .build());
        }
    }

    // ガパオライス
    public static class GapraoItem extends Item {
        public GapraoItem() {
            super(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(9)
                            .saturationModifier(1.1f)
                            .build())
            );
            FoodNutritionRegistry.register(this, FoodNutritionData.builder()
                    .protein(35)
                    .vitamin(25)
                    .lipid(20)
                    .carbohydrate(15)
                    .build());
        }
    }

    // タコス
    public static class TacosItem extends Item {
        public TacosItem() {
            super(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(9)
                            .saturationModifier(1.0f)
                            .build())
            );
            FoodNutritionRegistry.register(this, FoodNutritionData.builder()
                    .protein(30)
                    .carbohydrate(35)
                    .lipid(20)
                    .vitamin(15)
                    .build());
        }
    }
}
