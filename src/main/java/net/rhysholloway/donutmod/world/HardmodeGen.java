package net.rhysholloway.donutmod.world;

import static net.rhysholloway.donutmod.lists.FeatureList.cobalt_gen;
import static net.rhysholloway.donutmod.lists.FeatureList.mythril_gen;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.reflect.FieldUtils;

import static net.rhysholloway.donutmod.lists.FeatureList.adamantite_gen;

import net.minecraft.entity.SpawnGroup;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biome.SpawnEntry;
import net.minecraft.world.gen.GenerationStep;
import net.rhysholloway.donutmod.lists.EntityTypeList;

public class HardmodeGen {

	public static void addBiomeFeatures() {
		
		Registry.BIOME.forEach( biome -> {
			
			if(biome.getCategory() != Category.NETHER || biome.getCategory() != Category.THEEND) {
				
				biome.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, cobalt_gen);
				biome.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, mythril_gen);
				biome.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, adamantite_gen);
				
				try {
					@SuppressWarnings("unchecked")
					Map<SpawnGroup, List<Biome.SpawnEntry>> spawns = (Map<SpawnGroup, List<SpawnEntry>>) FieldUtils.readDeclaredField(biome, "spawns", true);
					spawns.get(SpawnGroup.MONSTER).add(new Biome.SpawnEntry(EntityTypeList.POSSESSED_ARMOR, 95, 1, 1));
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		});
		
	}
	
	public static void retrogen(ServerWorld world) {
		
		
		
	}
	
}
