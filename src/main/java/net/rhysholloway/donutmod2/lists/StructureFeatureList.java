package net.rhysholloway.donutmod2.lists;

import java.util.Set;

import com.google.common.collect.Sets;

import net.minecraft.world.biome.Biome;

public class StructureFeatureList {
	
	public static final Set<Biome> biomesWithDungeon = Sets.newLinkedHashSet();
	
	public static void register() {
		
		//LibStructure.registerStructure(new Identifier("newdungeon"), DungeonStructureFeature.STRUCTURE_FEATURE, GenerationStep.Feature.SURFACE_STRUCTURES, new StructureConfig(1, 4, 4537), DungeonStructureFeature.STRUCTURE_FEATURE.configure(DefaultFeatureConfig.DEFAULT));
		
		//biomesWithDungeon.forEach((biome) -> {
		//	biome.addStructureFeature(DungeonStructureFeature.STRUCTURE_FEATURE.configure(DefaultFeatureConfig.DEFAULT));
		//});
		
	}
	
}
