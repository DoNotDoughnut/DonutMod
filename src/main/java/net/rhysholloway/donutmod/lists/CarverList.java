package net.rhysholloway.donutmod.lists;

import static net.rhysholloway.donutmod.DonutMod.modId;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.ProbabilityConfig;
import net.minecraft.world.gen.carver.Carver;
import net.rhysholloway.donutmod.world.gen.carver.JungleCaveCarver;

public class CarverList {
	
	public static final Carver<ProbabilityConfig> JUNGLE_CAVE = register("jungle_cave", new JungleCaveCarver(ProbabilityConfig.CODEC, 256));
	
	private static Carver<ProbabilityConfig> register(String id, Carver<ProbabilityConfig> carver) {
		return Registry.register(Registry.CARVER, new Identifier(modId, id), carver);
	}

}
