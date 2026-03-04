package net.pochi.pochimod.mineral.tools;

/**
 * 不純物のデメリット種別
 *
 * 主不純物と副不純物が同じPenaltyTypeを持つ場合、
 * ペナルティが×1.5倍になりネザライト超えが阻害される。
 */
public enum PenaltyType {
    DURABILITY,    // 耐久値低下
    ATTACK,        // 攻撃力低下
    MINING_SPEED,  // 採掘速度低下
    DEFENSE,       // 防御値低下
    NONE           // ペナルティなし
}
