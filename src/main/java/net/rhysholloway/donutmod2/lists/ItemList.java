package net.rhysholloway.donutmod2.lists;

import static net.rhysholloway.donutmod2.DonutMod.group;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.SwordItem;
import net.rhysholloway.donutmod2.item.HallowedMultitoolPickaxeAxe;
import net.rhysholloway.donutmod2.item.LightDiscItem;
import net.rhysholloway.donutmod2.item.SoulItem;
import net.rhysholloway.donutmod2.item.accessory.AccessoryObsidianSkull;
import net.rhysholloway.donutmod2.item.accessory.AngelRingItem;
import net.rhysholloway.donutmod2.item.material.DonutArmorMaterials;
import net.rhysholloway.donutmod2.item.material.DonutItemMaterials;

public class ItemList {
	
	public static Item

			hallowed_ingot = new Item(new Item.Settings().group(group)),
			mythril_ingot = new Item(new Item.Settings().group(group)),
			mythril_chunk = new Item(new Item.Settings().group(group)),
			
			soul_flight = new SoulItem(new Item.Settings().group(group)),
			soul_might = new SoulItem(new Item.Settings().group(group)),
			soul_fright = new SoulItem(new Item.Settings().group(group)),
			soul_sight = new SoulItem(new Item.Settings().group(group)),
			
			wyvern_spawn_egg = new SpawnEggItem(EntityTypeList.WYVERN, 0xF4FFFF, 0x71BBA2, new Item.Settings().group(group)),
			
			accessory_obsidian_skull = new AccessoryObsidianSkull(40, new Item.Settings().group(group).maxCount(1)), 
			accessory_angel_ring = new AngelRingItem(new Item.Settings().group(group).maxCount(1)),
			
			hallowed_multitool_pickaxe_axe = new HallowedMultitoolPickaxeAxe(3, -2.8f, new Item.Settings().group(group)),
			
			hallowed_sword = new SwordItem(DonutItemMaterials.hallowed, 5, -2.4f, new Item.Settings().group(group)),
			light_disc = new LightDiscItem(new Item.Settings().group(group).maxCount(3)),

			hallowed_ranged_helmet = new ArmorItem(DonutArmorMaterials.hallowed_ranged, EquipmentSlot.HEAD, new Item.Settings().group(group)),
			hallowed_melee_helmet = new ArmorItem(DonutArmorMaterials.hallowed_melee, EquipmentSlot.HEAD, new Item.Settings().group(group)),
			hallowed_magic_helmet = new ArmorItem(DonutArmorMaterials.hallowed_magic, EquipmentSlot.HEAD, new Item.Settings().group(group)),

			hallowed_chestplate = new ArmorItem(DonutArmorMaterials.hallowed_basic, EquipmentSlot.CHEST, new Item.Settings().group(group)),
			hallowed_leggings = new ArmorItem(DonutArmorMaterials.hallowed_basic, EquipmentSlot.LEGS, new Item.Settings().group(group)),
			hallowed_boots = new ArmorItem(DonutArmorMaterials.hallowed_basic, EquipmentSlot.FEET, new Item.Settings().group(group));

}
