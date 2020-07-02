package net.rhysholloway.donutmod2.lists;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.Feature;
import net.rhysholloway.donutmod2.DonutMod;
import net.rhysholloway.donutmod2.world.gen.feature.UndergroundCabinFeature;
import net.rhysholloway.donutmod2.world.gen.feature.config.UndergroundCabinFeatureConfig;

public class FeatureList {

	public static final Feature<UndergroundCabinFeatureConfig> underground_cabin = new UndergroundCabinFeature(UndergroundCabinFeatureConfig.CODEC);
	
	public static void register() {
		
		Registry.register(Registry.FEATURE, new Identifier(DonutMod.modId, "underground_cabin"), underground_cabin);
		StructureFeatureList.biomesWithDungeon.forEach(biome -> biome.addFeature(GenerationStep.Feature.UNDERGROUND_STRUCTURES, underground_cabin.configure(new UndergroundCabinFeatureConfig(DonutMod.config.underground_cabin.enabled, DonutMod.config.underground_cabin.chance))));
		
	}
	
}
