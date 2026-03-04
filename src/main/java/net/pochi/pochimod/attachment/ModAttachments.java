package net.pochi.pochimod.attachment;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.pochi.pochimod.PochiMod;
import net.pochi.pochimod.ferm.SoilData;
import net.pochi.pochimod.nutrition.PlayerVitalData;

public class ModAttachments {

    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES =
            DeferredRegister.create(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, PochiMod.MOD_ID);

    /**
     * プレイヤーのバイタルデータ（栄養・水分）
     * copyOnDeath: 死亡時にも保持
     */
    public static final DeferredHolder<AttachmentType<?>, AttachmentType<PlayerVitalData>> PLAYER_VITAL =
            ATTACHMENT_TYPES.register("vital_data",
                    () -> AttachmentType.serializable(PlayerVitalData::new).copyOnDeath().build());

    /**
     * チャンクの土壌データ（農業システム）
     */
    public static final DeferredHolder<AttachmentType<?>, AttachmentType<SoilData>> SOIL_DATA =
            ATTACHMENT_TYPES.register("soil_data",
                    () -> AttachmentType.serializable(SoilData::new).build());

    public static void register(IEventBus eventBus) {
        ATTACHMENT_TYPES.register(eventBus);
    }
}
