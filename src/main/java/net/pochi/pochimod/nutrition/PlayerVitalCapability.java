package net.pochi.pochimod.nutrition;

/**
 * 後方互換クラス。PlayerVitalDataへのアクセスは ModAttachments.PLAYER_VITAL を使用。
 * このクラスは参照が残っているため、ビルドエラー防止用に残している。
 */
public class PlayerVitalCapability {
    // 旧来のCapabilityシステムは削除済み。
    // 代わりに ModAttachments.PLAYER_VITAL を使用してください。
    // 例: player.getData(ModAttachments.PLAYER_VITAL)
}
