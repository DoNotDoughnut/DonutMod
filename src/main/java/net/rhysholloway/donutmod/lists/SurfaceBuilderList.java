package net.rhysholloway.donutmod.lists;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;
import net.rhysholloway.donutmod.DonutMod;
import net.rhysholloway.donutmod.world.gen.surfacebuilder.JungleSurfaceBuilder;

public class SurfaceBuilderList {
	
	public static SurfaceBuilder<TernarySurfaceConfig> JUNGLE;
	public static TernarySurfaceConfig MUD_CONFIG;

	private static Identifier id(String string) {
		return new Identifier(DonutMod.modId, string);
	}
	
	public static void register() {
		JUNGLE = Registry.register(Registry.SURFACE_BUILDER, id("jungle"), new JungleSurfaceBuilder(TernarySurfaceConfig.CODEC));
		MUD_CONFIG = new TernarySurfaceConfig(BlockList.muddy_grass.getDefaultState(), BlockList.mud_block.getDefaultState(), BlockList.mud_block.getDefaultState());
	}

}
