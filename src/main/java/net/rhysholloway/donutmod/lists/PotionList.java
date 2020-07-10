package net.rhysholloway.donutmod.lists;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.potion.Potion;

public class PotionList {

	public static Potion flight = new Potion(new StatusEffectInstance(StatusEffectList.fly, 1200));
	public static Potion shine = new Potion(new StatusEffectInstance(StatusEffectList.shine, 3600));
	
}
