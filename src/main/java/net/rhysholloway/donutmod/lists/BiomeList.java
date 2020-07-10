package net.rhysholloway.donutmod.lists;

import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.rhysholloway.donutmod.world.biome.DonutBambooJungleBiome;
import net.rhysholloway.donutmod.world.biome.DonutBambooJungleHillsBiome;
import net.rhysholloway.donutmod.world.biome.DonutJungleBiome;
import net.rhysholloway.donutmod.world.biome.DonutJungleEdgeBiome;
import net.rhysholloway.donutmod.world.biome.DonutJungleHillsBiome;
import net.rhysholloway.donutmod.world.biome.DonutModifiedJungleBiome;
import net.rhysholloway.donutmod.world.biome.DonutModifiedJungleEdgeBiome;

public class BiomeList {

	public static void register() {
		
		register(21, "jungle", new DonutJungleBiome());
		register(22, "jungle_hills", new DonutJungleHillsBiome());
		register(23, "jungle_edge", new DonutJungleEdgeBiome());
		register(149, "modified_jungle", new DonutModifiedJungleBiome());
		register(151, "modified_jungle_edge", new DonutModifiedJungleEdgeBiome());
		register(168, "bamboo_jungle", new DonutBambooJungleBiome());
		register(169, "bamboo_jungle_hills", new DonutBambooJungleHillsBiome());
		
	}
	
	private static void register(int rawId, String id, Biome biome) {
		Registry.register(Registry.BIOME, rawId, id, biome);
	}
	
}
