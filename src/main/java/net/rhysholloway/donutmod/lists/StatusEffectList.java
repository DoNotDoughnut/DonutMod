package net.rhysholloway.donutmod.lists;

import static net.minecraft.entity.effect.StatusEffectType.*;

import net.rhysholloway.donutmod.entity.effect.FlyStatusEffect;
import net.rhysholloway.donutmod.entity.effect.ShineStatusEffect;

public class StatusEffectList {

	public static final FlyStatusEffect fly = new FlyStatusEffect(NEUTRAL, 0x9ADDF4);
	public static final ShineStatusEffect shine = new ShineStatusEffect(NEUTRAL, 0xF0EB72, 2);

}
