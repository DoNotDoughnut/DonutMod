package net.rhysholloway.donutmod2.item.accessory;

import dev.emi.trinkets.api.Trinket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class AccessoryObsidianSkull extends Item implements Trinket {

	private int lastFireTimer = 0;

	private final int maxTime;

	public AccessoryObsidianSkull(float seconds, Settings settings) {
		super(settings);
		this.maxTime = (int) (20 * seconds);
	}

	@Override
	public void tick(PlayerEntity livingEntity, ItemStack stack) {
		if (!livingEntity.getEntityWorld().isClient) {
			if (livingEntity.isOnFire()) {
				if (livingEntity.getFireTicks() + (int) maxTime < lastFireTimer) {
					livingEntity.setFireTicks(0);
					lastFireTimer = 0;
				} else if (livingEntity.getFireTicks() > lastFireTimer) {
					lastFireTimer = livingEntity.getFireTicks();
				}
			}

		}
	}

	@Override
	public boolean canWearInSlot(String group, String slot) {
		return true;
	}
	
}
