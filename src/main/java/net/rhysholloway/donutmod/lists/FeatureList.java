package net.rhysholloway.donutmod.lists;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.rhysholloway.donutmod.DonutMod;
import net.rhysholloway.donutmod.util.block.NoGenOre;
import net.rhysholloway.donutmod.world.gen.feature.UndergroundCabinFeature;

public class FeatureList {

	public static final ConfiguredFeature<?, ?> cobalt_gen = Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Target.NATURAL_STONE, ((NoGenOre) BlockList.cobalt_ore).getDefaultState(), ((NoGenOre) BlockList.cobalt_ore).oreSettings.getVeinSize())).createDecoratedFeature(Decorator.COUNT_RANGE.configure(new RangeDecoratorConfig(((NoGenOre) BlockList.cobalt_ore).oreSettings.getVeinsPerChunk(), ((NoGenOre) BlockList.cobalt_ore).oreSettings.getBottomOffset(), ((NoGenOre) BlockList.cobalt_ore).oreSettings.getMinHeight(), ((NoGenOre) BlockList.cobalt_ore).oreSettings.getMaxHeight())));
	public static final ConfiguredFeature<?, ?> mythril_gen = Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Target.NATURAL_STONE, ((NoGenOre) BlockList.mythril_ore).getDefaultState(), ((NoGenOre) BlockList.mythril_ore).oreSettings.getVeinSize())).createDecoratedFeature(Decorator.COUNT_RANGE.configure(new RangeDecoratorConfig(((NoGenOre) BlockList.mythril_ore).oreSettings.getVeinsPerChunk(), ((NoGenOre) BlockList.mythril_ore).oreSettings.getBottomOffset(), ((NoGenOre) BlockList.mythril_ore).oreSettings.getMinHeight(), ((NoGenOre) BlockList.mythril_ore).oreSettings.getMaxHeight())));
	public static final ConfiguredFeature<?, ?> adamantite_gen = Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Target.NATURAL_STONE, ((NoGenOre) BlockList.adamantite_ore).getDefaultState(), ((NoGenOre) BlockList.adamantite_ore).oreSettings.getVeinSize())).createDecoratedFeature(Decorator.COUNT_RANGE.configure(new RangeDecoratorConfig(((NoGenOre) BlockList.adamantite_ore).oreSettings.getVeinsPerChunk(), ((NoGenOre) BlockList.adamantite_ore).oreSettings.getBottomOffset(), ((NoGenOre) BlockList.adamantite_ore).oreSettings.getMinHeight(), ((NoGenOre) BlockList.adamantite_ore).oreSettings.getMaxHeight())));
	
	public static final Feature<DefaultFeatureConfig> underground_cabin = new UndergroundCabinFeature(DefaultFeatureConfig.CODEC);
	
	public static void register() {
		
		Registry.register(Registry.FEATURE, new Identifier(DonutMod.modId, "underground_cabin"), underground_cabin);
		UndergroundCabinFeature.biomesWithDungeon.forEach(biome -> biome.addFeature(GenerationStep.Feature.UNDERGROUND_STRUCTURES, underground_cabin.configure(DefaultFeatureConfig.INSTANCE)));
		
	}
	
}
