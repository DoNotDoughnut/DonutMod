package net.rhysholloway.donutmod2.lists;

import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.loot.ConstantLootTableRange;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.entry.ItemEntry;

public class LootTableList {

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
