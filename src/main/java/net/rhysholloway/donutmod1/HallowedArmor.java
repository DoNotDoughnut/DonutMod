package net.rhysholloway.donutmod1;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.rhysholloway.donutmod1.items.DonutItems;
import net.rhysholloway.donutmod1.materials.DonutArmorMaterials;

public class HallowedArmor extends ArmorItem {
	
	public HallowedArmor(DonutArmorMaterials material, EquipmentSlot slot) {
		super(material, slot, new Item.Settings().group(DonutItems.group));
	}
	
	/*
	
	@Override
	public void onArmorTick(final ItemStack stack, final World world, final PlayerEntity player)
	{
		if(player instanceof PlayerEntity && player.age % 40 == 0)
		{
			Iterable<ItemStack> armorIterable = player.getArmorItems();
			ItemStack[] armor = new ItemStack[4];
			int count = 0;
			
			for(ItemStack item : armorIterable) {
				armor[count] = item;
				count++;
			}

		    if(armor[0].getItem() != null && armor[1].getItem() == DonutItems.hallowed_chestplate && armor[2].getItem() == DonutItems.hallowed_leggings && armor[3].getItem() == DonutItems.hallowed_boots)
		    	
		    	if(armor[0].getItem() == DonutItems.hallowed_magic_helmet) {
		    		
		    		player.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 80, 0, true, false));
		    		
		    	} else if(armor[0].getItem() == DonutItems.hallowed_melee_helmet) {
		    		
		    		player.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 80, 0, true, false));
		    		
		    	} else if(armor[0].getItem() == DonutItems.hallowed_ranged_helmet) {
		    		
		    		player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 80, 0, true, false));
		    		
		    	}
		}
	}
	
	*/

}