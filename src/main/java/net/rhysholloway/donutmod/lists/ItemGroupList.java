package net.rhysholloway.donutmod.lists;

import static net.rhysholloway.donutmod.DonutMod.modId;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public final class ItemGroupList {
	
	public static final ItemGroup general = FabricItemGroupBuilder.build(new Identifier(modId, "general"), () -> new ItemStack(ItemList.hallowed_ingot));
	public static final ItemGroup tools = FabricItemGroupBuilder.build(new Identifier(modId, "tools"), () -> new ItemStack(ItemList.pickaxe_axe));
	public static final ItemGroup melee_weapons = FabricItemGroupBuilder.build(new Identifier(modId, "melee_weapons"), () -> new ItemStack(ItemList.hallowed_sword));
	public static final ItemGroup ranged_weapons = FabricItemGroupBuilder.build(new Identifier(modId, "ranged_weapons"), () -> new ItemStack(ItemList.hallowed_repeater));
	public static final ItemGroup magic_weapons = FabricItemGroupBuilder.build(new Identifier(modId, "magic_weapons"), () -> new ItemStack(ItemList.magic_placeholder));
	public static final ItemGroup armor = FabricItemGroupBuilder.build(new Identifier(modId, "armor"), () -> new ItemStack(ItemList.hallowed_chestplate));
	public static final ItemGroup accessories = FabricItemGroupBuilder.build(new Identifier(modId, "accessories"), () -> new ItemStack(ItemList.accessory_angel_ring));
	public static final ItemGroup misc = FabricItemGroupBuilder.build(new Identifier(modId, "misc"), () -> new ItemStack(ItemList.wyvern_spawn_egg));
	
}
