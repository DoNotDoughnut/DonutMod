package net.rhysholloway.donutmod.lists;

import static net.rhysholloway.donutmod.lists.ItemGroupList.*;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.rhysholloway.donutmod.item.HallowedMultitoolPickaxeAxe;
import net.rhysholloway.donutmod.item.LightDiscItem;
import net.rhysholloway.donutmod.item.SoulItem;
import net.rhysholloway.donutmod.item.accessory.AccessoryObsidianSkull;
import net.rhysholloway.donutmod.item.accessory.AngelRingItem;
import net.rhysholloway.donutmod.item.material.DonutArmorMaterials;
import net.rhysholloway.donutmod.item.material.DonutItemMaterials;
import net.rhysholloway.donutmod.util.item.BasicAxeItem;
import net.rhysholloway.donutmod.util.item.BasicHoeItem;
import net.rhysholloway.donutmod.util.item.BasicPickaxeItem;
import net.rhysholloway.donutmod.util.item.BasicShovelItem;
import net.rhysholloway.donutmod.util.item.BasicSwordItem;

public class ItemList {

	// Items in general tab, ordered

	public static Item soul_flight = new SoulItem(new Item.Settings().group(general));
	public static Item soul_might = new SoulItem(new Item.Settings().group(general));
	public static Item soul_fright = new SoulItem(new Item.Settings().group(general));
	public static Item soul_sight = new SoulItem(new Item.Settings().group(general));
	
	public static Item cobalt_ingot = new Item(new Item.Settings().group(general));
	public static Item mythril_ingot = new Item(new Item.Settings().group(general));
	public static Item adamantite_ingot = new Item(new Item.Settings().group(general));
	public static Item hallowed_ingot = new Item(new Item.Settings().group(general));

	// Items in Tools tab, ordered

	public static Item cobalt_pickaxe = new BasicPickaxeItem(DonutItemMaterials.cobalt);
	public static Item cobalt_shovel = new BasicShovelItem(DonutItemMaterials.cobalt);
	public static Item cobalt_axe = new BasicAxeItem(DonutItemMaterials.cobalt);
	public static Item cobalt_hoe = new BasicHoeItem(DonutItemMaterials.cobalt);
	public static Item mythril_pickaxe = new BasicPickaxeItem(DonutItemMaterials.mythril);
	public static Item mythril_shovel = new BasicShovelItem(DonutItemMaterials.mythril);
	public static Item mythril_axe = new BasicAxeItem(DonutItemMaterials.mythril);
	public static Item mythril_hoe = new BasicHoeItem(DonutItemMaterials.mythril);
	public static Item adamantite_pickaxe = new BasicPickaxeItem(DonutItemMaterials.adamantite);
	public static Item adamantite_shovel = new BasicShovelItem(DonutItemMaterials.adamantite);
	public static Item adamantite_axe = new BasicAxeItem(DonutItemMaterials.adamantite);
	public static Item adamantite_hoe = new BasicHoeItem(DonutItemMaterials.adamantite);
	public static Item pickaxe_axe = new HallowedMultitoolPickaxeAxe();
	public static Item hallowed_hoe = new BasicHoeItem(DonutItemMaterials.hallowed);

	// Items in Melee Weapons tab, ordered

	public static Item cobalt_sword = new BasicSwordItem(DonutItemMaterials.hallowed);
	public static Item mythril_sword = new BasicSwordItem(DonutItemMaterials.hallowed);
	public static Item adamantite_sword = new BasicSwordItem(DonutItemMaterials.hallowed);
	public static Item hallowed_sword = new BasicSwordItem(DonutItemMaterials.hallowed);
	public static Item light_disc = new LightDiscItem(new Item.Settings().group(melee_weapons).maxCount(3));

	// Items in Ranged Weapons tab, ordered

	public static Item hallowed_repeater = new Item(new Item.Settings().group(ranged_weapons));

	// Items in Magic Weapons tab, ordered

	public static Item magic_placeholder = new Item(new Item.Settings().group(magic_weapons));

	// Items in Accessories tab, ordered

	public static Item accessory_obsidian_skull = new AccessoryObsidianSkull(40, new Item.Settings().group(accessories).maxCount(1));
	public static Item accessory_angel_ring = new AngelRingItem(new Item.Settings().group(accessories).maxCount(1));

	// Items in Armor tab, ordered
	
	public static Item cobalt_boots = new ArmorItem(DonutArmorMaterials.cobalt_basic, EquipmentSlot.FEET, new Item.Settings().group(armor));
	public static Item cobalt_leggings = new ArmorItem(DonutArmorMaterials.cobalt_basic, EquipmentSlot.LEGS, new Item.Settings().group(armor));
	public static Item cobalt_chestplate = new ArmorItem(DonutArmorMaterials.cobalt_basic, EquipmentSlot.CHEST, new Item.Settings().group(armor));
	
	public static Item cobalt_melee_helmet = new ArmorItem(DonutArmorMaterials.cobalt_melee, EquipmentSlot.HEAD, new Item.Settings().group(armor));
	public static Item cobalt_ranged_helmet = new ArmorItem(DonutArmorMaterials.cobalt_ranged, EquipmentSlot.HEAD, new Item.Settings().group(armor));
	public static Item cobalt_magic_helmet = new ArmorItem(DonutArmorMaterials.cobalt_magic, EquipmentSlot.HEAD, new Item.Settings().group(armor));

	public static Item mythril_boots = new ArmorItem(DonutArmorMaterials.mythril_basic, EquipmentSlot.FEET, new Item.Settings().group(armor));
	public static Item mythril_leggings = new ArmorItem(DonutArmorMaterials.mythril_basic, EquipmentSlot.LEGS, new Item.Settings().group(armor));
	public static Item mythril_chestplate = new ArmorItem(DonutArmorMaterials.mythril_basic, EquipmentSlot.CHEST, new Item.Settings().group(armor));

	public static Item mythril_melee_helmet = new ArmorItem(DonutArmorMaterials.mythril_melee, EquipmentSlot.HEAD, new Item.Settings().group(armor));
	public static Item mythril_ranged_helmet = new ArmorItem(DonutArmorMaterials.mythril_ranged, EquipmentSlot.HEAD, new Item.Settings().group(armor));
	public static Item mythril_magic_helmet = new ArmorItem(DonutArmorMaterials.mythril_magic, EquipmentSlot.HEAD, new Item.Settings().group(armor));

	public static Item adamantite_boots = new ArmorItem(DonutArmorMaterials.adamantite_basic, EquipmentSlot.FEET, new Item.Settings().group(armor));
	public static Item adamantite_leggings = new ArmorItem(DonutArmorMaterials.adamantite_basic, EquipmentSlot.LEGS, new Item.Settings().group(armor));
	public static Item adamantite_chestplate = new ArmorItem(DonutArmorMaterials.adamantite_basic, EquipmentSlot.CHEST, new Item.Settings().group(armor));

	public static Item adamantite_melee_helmet = new ArmorItem(DonutArmorMaterials.adamantite_melee, EquipmentSlot.HEAD, new Item.Settings().group(armor));
	public static Item adamantite_ranged_helmet = new ArmorItem(DonutArmorMaterials.adamantite_ranged, EquipmentSlot.HEAD, new Item.Settings().group(armor));
	public static Item adamantite_magic_helmet = new ArmorItem(DonutArmorMaterials.adamantite_magic, EquipmentSlot.HEAD, new Item.Settings().group(armor));

	public static Item hallowed_boots = new ArmorItem(DonutArmorMaterials.hallowed_basic, EquipmentSlot.FEET, new Item.Settings().group(armor));
	public static Item hallowed_leggings = new ArmorItem(DonutArmorMaterials.hallowed_basic, EquipmentSlot.LEGS, new Item.Settings().group(armor));
	public static Item hallowed_chestplate = new ArmorItem(DonutArmorMaterials.hallowed_basic, EquipmentSlot.CHEST, new Item.Settings().group(armor));

	public static Item hallowed_melee_helmet = new ArmorItem(DonutArmorMaterials.hallowed_melee, EquipmentSlot.HEAD, new Item.Settings().group(armor));
	public static Item hallowed_ranged_helmet = new ArmorItem(DonutArmorMaterials.hallowed_ranged, EquipmentSlot.HEAD, new Item.Settings().group(armor));
	public static Item hallowed_magic_helmet = new ArmorItem(DonutArmorMaterials.hallowed_magic, EquipmentSlot.HEAD, new Item.Settings().group(armor));
	
	public static Item possessed_boots = new ArmorItem(DonutArmorMaterials.possessed, EquipmentSlot.FEET, new Item.Settings().group(armor));
	public static Item possessed_leggings = new ArmorItem(DonutArmorMaterials.possessed, EquipmentSlot.LEGS, new Item.Settings().group(armor));
	public static Item possessed_chestplate = new ArmorItem(DonutArmorMaterials.possessed, EquipmentSlot.CHEST, new Item.Settings().group(armor));
	public static Item possessed_helmet = new ArmorItem(DonutArmorMaterials.possessed, EquipmentSlot.HEAD, new Item.Settings().group(armor));

	// Items in Miscellaneous tab, ordered

	public static Item wyvern_spawn_egg = new SpawnEggItem(EntityTypeList.WYVERN, 0xF4FFFF, 0x71BBA2, new Item.Settings().group(misc));
	//public static Item potion_of_avian_grace = PotionUtil.setPotion(new ItemStack(Items.POTION), PotionList.flight).getItem();
	//public static Item shine_potion = PotionUtil.setPotion(new ItemStack(Items.POTION), PotionList.shine).getItem();
	
}
