package net.rhysholloway.donutmod.loot;

import java.util.Random;

import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.ConstantLootTableRange;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.util.Identifier;
import net.rhysholloway.donutmod.lists.BlockList;
import net.rhysholloway.donutmod.lists.ItemList;

public class LootHandler {

	private static Random random = new Random();
	
	public static ItemStack getEnderDragonLoot() {
		return new ItemStack(ItemList.soul_might, random.nextInt(15) + 25);
	}
	
	public static Identifier getUndergroundCabinLoot(Random random) {
		int num = random.nextInt(4);
		switch(num) {
		
		case 0: {
			return LootTables.SIMPLE_DUNGEON_CHEST;
		}
		
		case 1: {
			return LootTables.DESERT_PYRAMID_CHEST;
		}
		
		case 2: {
			return LootTables.SHIPWRECK_TREASURE_CHEST;
		}
		
		case 3: {
			return LootTables.SPAWN_BONUS_CHEST;
		}
		
		default: {
			return LootTables.SIMPLE_DUNGEON_CHEST;
		}
		
		}
		
	}
	
	public static void register() {
		
		// Add nether furnaces to ruined portal pool
		
		LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, supplier, setter) -> {
			if(LootTables.RUINED_PORTAL_CHEST.equals(id)) {
				FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder().rolls(ConstantLootTableRange.create(1)).withEntry(ItemEntry.builder(BlockList.nether_furnace).build());
				supplier.pool(poolBuilder);
			}
		});
		
	}
	
}
