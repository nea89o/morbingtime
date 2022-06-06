package moe.nea.morbing;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class MorbingTimeStatus extends StatusEffect {
    public MorbingTimeStatus() {
        super(StatusEffectCategory.NEUTRAL, 0x10_10_10);
    }

}
