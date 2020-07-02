package net.rhysholloway.donutmod2.item.accessory;

import dev.emi.trinkets.api.Trinket;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class AngelRingItem extends Item implements Trinket {
	
	public AngelRingItem(Settings settings) {
		super(settings);
	}

	@Override
	public void onEquip(PlayerEntity player, ItemStack stack) {
		fly(player);
		Trinket.super.onEquip(player, stack);
	}

	@Override
	public void onUnequip(PlayerEntity player, ItemStack stack) {
		stopFly(player);
		Trinket.super.onUnequip(player, stack);
	}
	
	@Override
	public boolean canWearInSlot(String group, String slot) {
		return true;
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
