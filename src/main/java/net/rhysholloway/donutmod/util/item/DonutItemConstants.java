package net.rhysholloway.donutmod.util.item;

import net.minecraft.item.Item;
import net.rhysholloway.donutmod.lists.ItemList;

public class DonutItemConstants {

	// Constants

	public static final int[] baseArmorDurability = new int[] { 13, 15, 16, 11 };
	public static final String baseArmorName = "", meleeId = "melee", magicId = "magic", rangedId = "ranged";
	public static final int pickaxeAD = 1, swordAD = 3, axeAD = 5, hoeAD = -3;
	public static final float shovelAD = 1.5f, pickaxeAS = -2.8F, shovelAS = -3.0F, swordAS = -2.4F, axeAS = -3.0F, hoeAS = 0.0F;

	// Tier 2 Items

	public static final int t2_melee_durability_diff = 2;
	public static final int t2_ranged_durability_diff = 0;
	public static final int t2_magic_durability_diff = -3;

	// Cobalt Items

	public static final String cobalt_name = "cobalt";
	public static final int cobalt_mining_level = 5;
	public static final float cobalt_efficiency = 7.5f;
	public static final float cobalt_attack_damage = 3.0f;
	public static final int cobalt_item_durability = 1200;
	public static final int cobalt_enchantability = 9;
	public static final Item cobalt_repair_item = ItemList.cobalt_ingot;
	
	public static final int cobalt_armor_durability = 40;
	public static final float cobalt_armor_toughness = 2.3f;
	public static final float cobalt_knockback_resistance = 0;
	public static final int[] cobalt_damage_reduction_amounts = new int[] { 3, 5, 6, 3 };

	// Mythril Items

	public static final String mythril_name = "mythril";
	public static final int mythril_mining_level = cobalt_mining_level;
	public static final float mythril_efficiency = cobalt_efficiency + 0.5f;
	public static final float mythril_attack_damage = cobalt_attack_damage + 0.5f;
	public static final int mythril_item_durability = cobalt_item_durability + 300;
	public static final int mythril_enchantability = cobalt_enchantability + 2;
	public static final Item mythril_repair_item = ItemList.mythril_ingot;
	
	public static final int mythril_armor_durability = cobalt_armor_durability + 2;
	public static final float mythril_armor_toughness = cobalt_armor_toughness + 0.6f;
	public static final float mythril_knockback_resistance = cobalt_knockback_resistance + 0.1f;
	public static final int[] mythril_damage_reduction_amounts = new int[] { 3, 5, 7, 3 };

	// Adamantite Items

	public static final String adamantite_name = "adamantite";
	public static final int adamantite_mining_level = cobalt_mining_level;
	public static final float adamantite_efficiency = mythril_efficiency + 0.5f;
	public static final float adamantite_attack_damage = mythril_attack_damage + 0.5f;
	public static final int adamantite_item_durability = mythril_item_durability + 300;
	public static final int adamantite_enchantability = mythril_enchantability + 3;
	public static final Item adamantite_repair_item = ItemList.adamantite_ingot;
	
	public static final int adamantite_armor_durability = mythril_armor_durability + 3;
	public static final float adamantite_armor_toughness = mythril_armor_toughness + 0.6f;
	public static final float adamantite_knockback_resistance = mythril_knockback_resistance + 0.1f;
	public static final int[] adamantite_damage_reduction_amounts = new int[] { 3, 6, 8, 3 };

	// Hallowed Items

	public static final String hallowed_name = "hallowed";
	public static final int hallowed_mining_level = 6;
	public static final float hallowed_efficiency = 9f;
	public static final float hallowed_attack_damage = 4.5f;
	public static final int hallowed_item_durability = 2500;
	public static final int hallowed_enchantability = 18;
	public static final Item hallowed_repair_item = ItemList.hallowed_ingot;
	
	public static final int hallowed_armor_durability = 50;
	public static final float hallowed_armor_toughness = 4.0f;
	public static final float hallowed_knockback_resistance = 0.3f;
	public static final int[] hallowed_damage_reduction_amounts = new int[] { 4, 6, 8, 4 };
	public static final int hallowed_melee_durability_diff = 4;
	public static final int hallowed_ranged_durability_diff = 0;
	public static final int hallowed_magic_durability_diff = -2;

}
