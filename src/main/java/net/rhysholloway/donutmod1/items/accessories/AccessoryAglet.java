package net.rhysholloway.donutmod1.items.accessories;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.rhysholloway.donutmod1.util.DonutRegistries.ACCESSORY;

public class AccessoryAglet extends ACCESSORY {

	public AccessoryAglet(Item.Settings settings, String registryName) {
		super(settings, registryName);
	}

	@Override
	public void tick(PlayerEntity livingEntity, ItemStack stack) {
		if (!livingEntity.getEntityWorld().isClient && livingEntity.age % 40 == 0)
			livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 80, 0, true, false));
	}
	
	/*
	
	@Override
	public void addInformation(ItemStack stack, World world, List<ITextComponent> list, ITooltipFlag flag) {
		super.addInformation(stack, world, list, flag);
		list.add(new StringTextComponent(TextFormatting.ITALIC + "Increased movement speed"));
	}
	
	*/

}
