package net.pochi.pochimod.util;

import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class PotteryEffectCalculator {

    public record EffectData(int amplifier, int duration) {}

    @SuppressWarnings("unchecked")
    private static final Holder<MobEffect>[] ALL_EFFECTS = new Holder[] {
            MobEffects.MOVEMENT_SPEED,
            MobEffects.MOVEMENT_SLOWDOWN,
            MobEffects.DIG_SPEED,
            MobEffects.DIG_SLOWDOWN,
            MobEffects.DAMAGE_BOOST,
            MobEffects.DAMAGE_RESISTANCE,
            MobEffects.JUMP,
            MobEffects.CONFUSION,
            MobEffects.REGENERATION,
            MobEffects.FIRE_RESISTANCE,
            MobEffects.WATER_BREATHING,
            MobEffects.INVISIBILITY,
            MobEffects.BLINDNESS,
            MobEffects.NIGHT_VISION,
            MobEffects.HUNGER,
            MobEffects.WEAKNESS,
            MobEffects.POISON,
            MobEffects.WITHER,
            MobEffects.HEALTH_BOOST,
            MobEffects.ABSORPTION,
            MobEffects.SATURATION,
            MobEffects.GLOWING,
            MobEffects.LEVITATION,
            MobEffects.LUCK,
            MobEffects.UNLUCK,
            MobEffects.SLOW_FALLING,
            MobEffects.CONDUIT_POWER,
            MobEffects.DOLPHINS_GRACE,
            MobEffects.BAD_OMEN,
            MobEffects.HERO_OF_THE_VILLAGE
    };

    public static Map<Holder<MobEffect>, EffectData> calculateEffects(CompoundTag tag) {
        Map<Holder<MobEffect>, EffectData> effects = new HashMap<>();

        int height = tag.getInt("Height");
        int diameter = tag.getInt("Diameter");
        int thickness = tag.getInt("WallThickness");
        int mouth = tag.getInt("MouthWidth");
        int firingTime = tag.getInt("FiringTime");
        int color = tag.getInt("GlazeColor");

        long seed = generateSeed(height, diameter, thickness, mouth, firingTime, color);
        Random random = new Random(seed);

        int optimalTimeHeight = calculateOptimalTime(height, 50);
        int optimalTimeDiameter = calculateOptimalTime(diameter, 100);
        int optimalTimeThickness = calculateOptimalTime(thickness, 30);
        int optimalTimeMouth = calculateOptimalTime(mouth, 80);

        int complexity = height + diameter + thickness + mouth;
        int effectCount = Math.min(5, Math.max(1, complexity / 7));

        for (int i = 0; i < effectCount; i++) {
            Holder<MobEffect> effect = selectEffect(random, effects, i);

            int parameterType = (int) ((seed + i) % 4);
            int primaryParam, optimalTime;

            switch ((int)parameterType) {
                case 0 -> { primaryParam = height; optimalTime = optimalTimeHeight; }
                case 1 -> { primaryParam = diameter; optimalTime = optimalTimeDiameter; }
                case 2 -> { primaryParam = thickness; optimalTime = optimalTimeThickness; }
                default -> { primaryParam = mouth; optimalTime = optimalTimeMouth; }
            }

            float quality = calculateQuality(firingTime, optimalTime);

            int amplifier = calculateAmplifier(random, primaryParam, quality, i);
            int duration = calculateDuration(random, complexity, quality, i);

            if (amplifier >= 0 && duration > 0) {
                effects.put(effect, new EffectData(amplifier, duration));
            }
        }

        applyColorBonus(effects, color, firingTime, random);
        applyComboBonus(effects, height, diameter, thickness, mouth, firingTime, random);

        return effects;
    }

    private static long generateSeed(int h, int d, int t, int m, int f, int c) {
        long seed = 0;
        seed = seed * 31 + h;
        seed = seed * 31 + d;
        seed = seed * 31 + t;
        seed = seed * 31 + m;
        seed = seed * 31 + (f / 10);
        seed = seed * 31 + (c & 0xFFFFFF);
        return seed;
    }

    private static int calculateOptimalTime(int paramValue, int baseTime) {
        return baseTime * paramValue;
    }

    private static float calculateQuality(int actualTime, int optimalTime) {
        if (optimalTime == 0) return 0.5f;

        float deviation = Math.abs(actualTime - optimalTime) / (float)optimalTime;

        if (deviation <= 0.1f) {
            return 0.95f + (1.0f - deviation * 10) * 0.05f;
        } else if (deviation <= 0.3f) {
            return 0.7f + (0.3f - deviation) / 0.2f * 0.25f;
        } else if (deviation <= 0.6f) {
            return 0.4f + (0.6f - deviation) / 0.3f * 0.3f;
        } else if (deviation <= 1.0f) {
            return 0.2f + (1.0f - deviation) / 0.4f * 0.2f;
        } else {
            return Math.max(0.1f, 0.2f - deviation / 10f);
        }
    }

    private static Holder<MobEffect> selectEffect(Random random, Map<Holder<MobEffect>, EffectData> existing, int index) {
        int maxAttempts = 50;
        for (int attempt = 0; attempt < maxAttempts; attempt++) {
            int effectIndex = (random.nextInt(ALL_EFFECTS.length) + index * 13) % ALL_EFFECTS.length;
            Holder<MobEffect> effect = ALL_EFFECTS[effectIndex];

            if (!existing.containsKey(effect)) {
                return effect;
            }
        }

        return ALL_EFFECTS[random.nextInt(ALL_EFFECTS.length)];
    }

    private static int calculateAmplifier(Random random, int primaryParam, float quality, int index) {
        float paramFactor = primaryParam / 10f;
        float qualityBonus = quality;
        float baseLevel = (paramFactor * 2 + qualityBonus * 3) / 2;
        baseLevel *= (1.0f - index * 0.1f);
        baseLevel += (random.nextFloat() - 0.5f) * 0.4f;
        int level = (int)(baseLevel * 2);
        return Math.max(0, Math.min(4, level));
    }

    private static int calculateDuration(Random random, int complexity, float quality, int index) {
        float complexityFactor = complexity / 30f;
        float qualityBonus = quality / 10;
        float baseDuration = 30 + (complexityFactor) + (qualityBonus * 3);
        baseDuration *= (1.0f - index * 0.08f);
        baseDuration *= (0.85f + random.nextFloat() * 0.3f);
        return (int)(baseDuration * 20);
    }

    private static void applyColorBonus(Map<Holder<MobEffect>, EffectData> effects, int color, int firingTime, Random random) {
        int r = (color >> 16) & 0xFF;
        int g = (color >> 8) & 0xFF;
        int b = color & 0xFF;

        Random colorRandom = new Random(r * 1000L + g * 100L + b * 10L);

        int optimalForColor = (r + g + b) * 2;
        float colorQuality = calculateQuality(firingTime, optimalForColor);

        int duration = (int)((60 + colorQuality * 120) * 20);
        int level = (int)(colorQuality * 2);

        if (r > 200 && r > g && r > b && colorRandom.nextFloat() < colorQuality) {
            effects.put(MobEffects.FIRE_RESISTANCE, new EffectData(0, duration));
        }
        if (b > 200 && b > r && b > g && colorRandom.nextFloat() < colorQuality) {
            effects.put(MobEffects.WATER_BREATHING, new EffectData(0, duration));
        }
        if (g > 200 && g > r && g > b && colorRandom.nextFloat() < colorQuality) {
            effects.put(MobEffects.ABSORPTION, new EffectData(level, duration));
        }
        if (r < 30 && g < 30 && b < 30 && colorRandom.nextFloat() < colorQuality) {
            effects.put(MobEffects.NIGHT_VISION, new EffectData(0, duration * 2));
        }
        if (r > 240 && g > 240 && b > 240 && colorQuality > 0.8f) {
            effects.replaceAll((effect, data) ->
                    new EffectData(data.amplifier(), (int)(data.duration() * (1 + colorQuality * 0.3f)))
            );
        }
        if (r > 150 && b > 150 && g < 100 && colorRandom.nextFloat() < colorQuality) {
            effects.put(MobEffects.SLOW_FALLING, new EffectData(0, duration));
        }
        if (r > 200 && g > 200 && b < 100 && colorRandom.nextFloat() < colorQuality) {
            effects.put(MobEffects.GLOWING, new EffectData(0, duration));
        }
        if (g > 200 && b > 200 && r < 100 && colorRandom.nextFloat() < colorQuality) {
            effects.put(MobEffects.DOLPHINS_GRACE, new EffectData(0, duration));
        }
    }

    private static void applyComboBonus(Map<Holder<MobEffect>, EffectData> effects,
                                        int h, int d, int t, int m, int f,
                                        Random random) {
        if (h == 7 && d == 7 && t == 7 && m == 7) {
            effects.put(MobEffects.LUCK, new EffectData(7, 777));
        }
        if (h == d && d == t && t == m && h >= 5) {
            int optimalCircle = h * 100;
            float quality = calculateQuality(f, optimalCircle);
            int level = Math.min(4, (int)(quality * 5));
            effects.put(MobEffects.HERO_OF_THE_VILLAGE, new EffectData(level, (int)(300 * quality * 20)));
        }
        if ((h == 1 && d == 1 && t == 2 && m == 3) ||
                (h == 1 && d == 2 && t == 3 && m == 5) ||
                (h == 2 && d == 3 && t == 5 && m == 8)) {
            effects.put(MobEffects.LUCK, new EffectData(3, 600));
        }
        if (h < d && d < m && m - h >= 4) {
            int optimalPyramid = (h + d + m) * 30;
            float quality = calculateQuality(f, optimalPyramid);
            effects.put(MobEffects.SLOW_FALLING, new EffectData((int)(quality * 2), (int)(200 * quality * 20)));
        }
        if (h > d && d > m && h - m >= 4) {
            int optimalPyramid = (h + d + m) * 25;
            float quality = calculateQuality(f, optimalPyramid);
            effects.put(MobEffects.JUMP, new EffectData((int)(quality * 3), (int)(150 * quality * 20)));
        }
        float ratio = d > 0 ? (float)h / d : 0;
        if (ratio >= 1.5f && ratio <= 1.7f) {
            int optimalGolden = 314;
            float quality = calculateQuality(f, optimalGolden);
            effects.put(MobEffects.GLOWING, new EffectData(0, (int)(180 * quality * 20)));
        }
        if (h == 10 && d == 10 && t == 5 && m == 10) {
            int optimalMax = 350;
            float quality = calculateQuality(f, optimalMax);
            if (quality > 0.9f) {
                effects.replaceAll((effect, data) ->
                        new EffectData(Math.min(4, data.amplifier() + 2), data.duration() * 2)
                );
            }
        }
        if (h == 1 && d == 1 && t == 1 && m == 1) {
            int optimalMin = 40;
            float quality = calculateQuality(f, optimalMin);
            if (quality > 0.95f) {
                effects.put(MobEffects.LUCK, new EffectData(3, 600));
            }
        }
        if (isPrime(h) && isPrime(d) && isPrime(t) && isPrime(m)) {
            effects.put(MobEffects.LUCK, new EffectData(2, 400));
        }
    }

    private static boolean isPrime(int n) {
        if (n <= 1) return false;
        if (n <= 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;
        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) return false;
        }
        return true;
    }

    public static String getEffectsSummary(Map<Holder<MobEffect>, EffectData> effects, CompoundTag tag) {
        int h = tag.getInt("Height");
        int d = tag.getInt("Diameter");
        int t = tag.getInt("WallThickness");
        int m = tag.getInt("MouthWidth");
        int f = tag.getInt("FiringTime");
        int c = tag.getInt("GlazeColor");

        long seed = generateSeed(h, d, t, m, f, c);

        int optH = calculateOptimalTime(h, 50);
        int optD = calculateOptimalTime(d, 100);
        int optT = calculateOptimalTime(t, 30);
        int optM = calculateOptimalTime(m, 80);

        float qH = calculateQuality(f, optH);
        float qD = calculateQuality(f, optD);
        float qT = calculateQuality(f, optT);
        float qM = calculateQuality(f, optM);

        StringBuilder sb = new StringBuilder();
        sb.append("=== Pottery Analysis ===\n");
        sb.append(String.format("Parameters: H:%d D:%d T:%d M:%d\n", h, d, t, m));
        sb.append(String.format("Firing Time: %d ticks (%.1fs)\n\n", f, f / 20f));
        sb.append("Optimal Times:\n");
        sb.append(String.format("  Height:    %d ticks (quality: %.2f)\n", optH, qH));
        sb.append(String.format("  Diameter:  %d ticks (quality: %.2f)\n", optD, qD));
        sb.append(String.format("  Thickness: %d ticks (quality: %.2f)\n", optT, qT));
        sb.append(String.format("  Mouth:     %d ticks (quality: %.2f)\n\n", optM, qM));
        sb.append(String.format("Color: #%06X\n", c));
        sb.append(String.format("Seed: %d\n\n", seed));
        sb.append("Effects:\n");
        for (Map.Entry<Holder<MobEffect>, EffectData> entry : effects.entrySet()) {
            Holder<MobEffect> effect = entry.getKey();
            EffectData data = entry.getValue();
            sb.append(String.format("  * %s Level %d for %ds\n",
                    effect.value().getDescriptionId().replace("effect.minecraft.", ""),
                    data.amplifier() + 1, data.duration() / 20));
        }

        return sb.toString();
    }
}
