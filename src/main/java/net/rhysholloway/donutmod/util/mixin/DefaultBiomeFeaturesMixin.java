package net.rhysholloway.donutmod.util.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.ProbabilityConfig;
import net.minecraft.world.gen.carver.Carver;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.rhysholloway.donutmod.lists.CarverList;
import net.rhysholloway.donutmod.world.gen.feature.UndergroundCabinFeature;

@Mixin(DefaultBiomeFeatures.class)
public abstract class DefaultBiomeFeaturesMixin {

	@Overwrite
	public static void addLandCarvers(Biome biome) {
		if (biome.getCategory() == Biome.Category.JUNGLE) {
			biome.addCarver(GenerationStep.Carver.AIR, Biome.configureCarver(CarverList.JUNGLE_CAVE, new ProbabilityConfig(0.24285715F)));
		} else {
			biome.addCarver(GenerationStep.Carver.AIR, Biome.configureCarver(Carver.CAVE, new ProbabilityConfig(0.14285715F)));

		}
		biome.addCarver(GenerationStep.Carver.AIR, Biome.configureCarver(Carver.CANYON, new ProbabilityConfig(0.02F)));
	}

	@Overwrite
	public static void addMineables(Biome biome) {
		if (biome.getCategory() == Biome.Category.JUNGLE) {
			biome.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Target.NATURAL_STONE, Blocks.STONE.getDefaultState(), 33)).createDecoratedFeature(Decorator.COUNT_RANGE.configure(new RangeDecoratorConfig(10, 0, 0, 80))));
			biome.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Target.NATURAL_STONE, Blocks.COBBLESTONE.getDefaultState(), 33)).createDecoratedFeature(Decorator.COUNT_RANGE.configure(new RangeDecoratorConfig(10, 0, 0, 80))));
		} else {
			biome.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Target.NATURAL_STONE, Blocks.GRANITE.getDefaultState(), 33)).createDecoratedFeature(Decorator.COUNT_RANGE.configure(new RangeDecoratorConfig(10, 0, 0, 80))));
			biome.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Target.NATURAL_STONE, Blocks.DIORITE.getDefaultState(), 33)).createDecoratedFeature(Decorator.COUNT_RANGE.configure(new RangeDecoratorConfig(10, 0, 0, 80))));
			biome.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Target.NATURAL_STONE, Blocks.ANDESITE.getDefaultState(), 33)).createDecoratedFeature(Decorator.COUNT_RANGE.configure(new RangeDecoratorConfig(10, 0, 0, 80))));
		}
		biome.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Target.NATURAL_STONE, Blocks.DIRT.getDefaultState(), 33)).createDecoratedFeature(Decorator.COUNT_RANGE.configure(new RangeDecoratorConfig(10, 0, 0, 256))));
		biome.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Target.NATURAL_STONE, Blocks.GRAVEL.getDefaultState(), 33)).createDecoratedFeature(Decorator.COUNT_RANGE.configure(new RangeDecoratorConfig(8, 0, 0, 256))));
		biome.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Target.NATURAL_STONE, Blocks.CLAY.getDefaultState(), 33)).createDecoratedFeature(Decorator.COUNT_RANGE.configure(new RangeDecoratorConfig(8, 0, 0, 256))));
	}

	@Inject(method = "addDungeons(Lnet/minecraft/world/biome/Biome;)V", at = @At("HEAD"))
	private static void onAddDungeons(Biome biome, CallbackInfo info) {
		UndergroundCabinFeature.biomesWithDungeon.add(biome);
	}

}
