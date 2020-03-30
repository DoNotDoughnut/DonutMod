package net.rhysholloway.donutmod1.items;

import static net.minecraft.entity.EquipmentSlot.*;
import static net.rhysholloway.donutmod1.DonutMod.modId;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.rhysholloway.donutmod1.items.accessories.AccessoryAglet;
import net.rhysholloway.donutmod1.items.accessories.AccessoryObsidianSkull;
import net.rhysholloway.donutmod1.items.accessories.AccessoryShinyRedBalloon;
import net.rhysholloway.donutmod1.items.hallowed.HallowedMultitoolPickaxeAxe;
import net.rhysholloway.donutmod1.materials.DonutArmorMaterials;
import net.rhysholloway.donutmod1.materials.DonutItemMaterials;
import net.rhysholloway.donutmod1.util.DonutRegistries.ARMOR;
import net.rhysholloway.donutmod1.util.DonutRegistries.BLOCK;
import net.rhysholloway.donutmod1.util.DonutRegistries.ITEM;
import net.rhysholloway.donutmod1.util.DonutRegistries.SWORD;

public class DonutItems {	
	
	public static final ItemGroup group = FabricItemGroupBuilder.build(new Identifier(modId, "general"), () -> new ItemStack(DonutItems.hallowed_ingot));
	
	public static final Item.Settings itemDefaults = new Item.Settings().group(group);

	public static ITEM 
	
			hallowed_ingot = new ITEM(itemDefaults, "hallowed_ingot"),
			accessory_aglet = new AccessoryAglet(itemDefaults, "aglet"),
			accessory_obsidian_skull = new AccessoryObsidianSkull(40, itemDefaults, "obsidian_skull"),
			accessory_shiny_red_balloon = new AccessoryShinyRedBalloon(itemDefaults, "shiny_red_balloon");

	public static BLOCK 
	
			hallowed_ingot_block = new BLOCK(FabricBlockSettings.of(Material.METAL).build(), itemDefaults, "hallowed_ingot_block"),
			mythril_ore = new BLOCK(Block.Settings.copy(Blocks.DIAMOND_ORE), new Item.Settings().group(group), "mythril_ore");

	public static SWORD 
	
			hallowed_sword = new SWORD(DonutItemMaterials.hallowed, 5, -2.4f, itemDefaults, "hallowed_sword");

	public static ARMOR
	
			// change helmets to HallowedArmor once the class is fixed
	
			/* Hallowed Helmet (Armor type, Armor slot) */
			hallowed_ranged_helmet = new ARMOR(DonutArmorMaterials.hallowed_ranged, HEAD, itemDefaults),

			/* Hallowed Mask (Armor type, Armor slot) */
			hallowed_melee_helmet = new ARMOR(DonutArmorMaterials.hallowed_melee, HEAD, itemDefaults),

			/* Hallowed Headgear (Armor type, Armor slot) */
			hallowed_magic_helmet = new ARMOR(DonutArmorMaterials.hallowed_magic, HEAD, itemDefaults),

			/* Hallowed Plate Mail (Armor type, Armor slot) */
			hallowed_chestplate = new ARMOR(DonutArmorMaterials.hallowed_basic, CHEST, itemDefaults),

			/* Hallowed Leggings (Armor type, Armor slot) */
			hallowed_leggings = new ARMOR(DonutArmorMaterials.hallowed_basic, LEGS, itemDefaults),

			/* Hallowed Boots (Armor type, Armor slot) */
			hallowed_boots = new ARMOR(DonutArmorMaterials.hallowed_basic, FEET, itemDefaults);
	
	public static HallowedMultitoolPickaxeAxe hallowed_multitool_pickaxe_axe = new HallowedMultitoolPickaxeAxe(3, -2.8f);

	public static void register() {
		hallowed_ingot.register();
		accessory_aglet.register();
		accessory_obsidian_skull.register();
		accessory_shiny_red_balloon.register();
		hallowed_ingot_block.register();
		Registry.register(Registry.ITEM, new Identifier(modId, "hallowed_multitool_pickaxe_axe"), hallowed_multitool_pickaxe_axe);
		hallowed_sword.register();
		hallowed_ranged_helmet.register();
		hallowed_melee_helmet.register();
		hallowed_magic_helmet.register();
		hallowed_chestplate.register();
		hallowed_leggings.register();
		hallowed_boots.register();

	}
	
}
