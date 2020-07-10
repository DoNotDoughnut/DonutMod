package net.rhysholloway.donutmod.entity.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffectType;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;

public class FlyStatusEffect extends StatusEffect {

	public FlyStatusEffect(StatusEffectType type, int color) {
		super(type, color);
	}

	@Override
	public void onApplied(LivingEntity entity, AttributeContainer attributes, int amplifier) {
		super.onApplied(entity, attributes, amplifier);
		if (entity instanceof PlayerEntity) {
			fly((PlayerEntity) entity);
		}
	}

	@Override
	public void onRemoved(LivingEntity entity, AttributeContainer attributes, int amplifier) {
		super.onRemoved(entity, attributes, amplifier);
		if (entity instanceof PlayerEntity) {
			stopFly((PlayerEntity) entity);
		}
	}

	private void fly(PlayerEntity player) {
		if (!player.isCreative() || !player.isSpectator()) {
			player.abilities.allowFlying = true;
			player.sendAbilitiesUpdate();
		}
	}

	private void stopFly(PlayerEntity player) {
		if (!player.isCreative() || !player.isSpectator()) {
			player.abilities.flying = false;
			player.abilities.allowFlying = false;
			player.sendAbilitiesUpdate();
			if (!player.isOnGround()) {
				player.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, (int) (7.5 * 20), 0, false, false));
			}
		}
	}

}
