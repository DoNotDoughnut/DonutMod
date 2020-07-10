package net.rhysholloway.donutmod.util.block;

import java.util.Arrays;
import java.util.function.ToIntFunction;

import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.minecraft.block.BlockState;
import net.minecraft.state.property.Properties;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;

public class BlockHelper {
	
	public static void registerOre(BasicGeneratedOre block) {
		Registry.BIOME.forEach((biome) -> {
			if (block.oreSettings.isBlacklist()) {
				if (!Arrays.asList(block.oreSettings.getBiomeList()).contains(biome.getCategory())) {
					addOreFeature(biome, block);
				}
			} else {
				if (Arrays.asList(block.oreSettings.getBiomeList()).contains(biome.getCategory())) {
					addOreFeature(biome, block);
				}
			}
		});

		RegistryEntryAddedCallback.event(Registry.BIOME).register((i, identifier, biome) -> {
			addOreFeature(biome, block);
		});
	}

	private static void addOreFeature(Biome biome, BasicOre block) {
		biome.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Target.NATURAL_STONE, block.getDefaultState(), block.oreSettings.getVeinSize())).createDecoratedFeature(Decorator.COUNT_RANGE.configure(new RangeDecoratorConfig(block.oreSettings.getVeinsPerChunk(), block.oreSettings.getBottomOffset(), block.oreSettings.getMinHeight(), block.oreSettings.getMaxHeight()))));
	}
	
	public static ToIntFunction<BlockState> createLightLevelFromBlockState(int litLevel) {
		return (blockState) -> {
			return (Boolean) blockState.get(Properties.LIT) ? litLevel : 0;
		};
	}

}
