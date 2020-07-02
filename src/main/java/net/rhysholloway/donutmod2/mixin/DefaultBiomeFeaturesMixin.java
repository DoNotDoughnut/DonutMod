package net.rhysholloway.donutmod2.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.ChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.rhysholloway.donutmod2.DonutMod;
import net.rhysholloway.donutmod2.lists.StructureFeatureList;

@Mixin(DefaultBiomeFeatures.class)
public abstract class DefaultBiomeFeaturesMixin {
	
	//@Inject(method = "addDungeons", at = @At("HEAD"))
	@Overwrite
	public static void addDungeons(Biome biome) {
		StructureFeatureList.biomesWithDungeon.add(biome);
		biome.addFeature(GenerationStep.Feature.UNDERGROUND_STRUCTURES, Feature.MONSTER_ROOM.configure(FeatureConfig.DEFAULT).createDecoratedFeature(Decorator.DUNGEONS.configure(new ChanceDecoratorConfig(DonutMod.dungeon_count))));
	}
	
	

}
