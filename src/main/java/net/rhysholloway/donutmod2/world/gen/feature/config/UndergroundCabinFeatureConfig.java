package net.rhysholloway.donutmod2.world.gen.feature.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.world.gen.feature.FeatureConfig;

public class UndergroundCabinFeatureConfig implements FeatureConfig {

	public static final Codec<UndergroundCabinFeatureConfig> CODEC = 
			
	RecordCodecBuilder.create((instance) -> {	
		return instance.group(
				Codec.BOOL.fieldOf("enabled").forGetter((featureConfig) -> { return featureConfig.enabled; }),
				Codec.INT.fieldOf("chance").forGetter((featureConfig) -> { return featureConfig.chance; }))
				.apply(instance, UndergroundCabinFeatureConfig::new);
		}
	);
	
	public final boolean enabled;
	public final int chance;
	
	public UndergroundCabinFeatureConfig(boolean enabled, int chance) {
		this.enabled = enabled;
		this.chance = chance;
	}
	
	

}
