package net.rhysholloway.donutmod.util.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.feature.Feature;
import net.rhysholloway.donutmod.lists.BlockList;

@Mixin(Feature.class)
public class FeatureMixin {

	@Overwrite
	public static boolean isSoil(Block block) {
		return block == BlockList.mud_block || block == BlockList.muddy_grass || block == Blocks.DIRT || block == Blocks.GRASS_BLOCK || block == Blocks.PODZOL || block == Blocks.COARSE_DIRT || block == Blocks.MYCELIUM;
	}

}
