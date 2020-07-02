package net.rhysholloway.donutmod2.loot;

import java.util.Random;

import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootTables;
import net.minecraft.util.Identifier;
import net.rhysholloway.donutmod2.lists.ItemList;

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
	
}
