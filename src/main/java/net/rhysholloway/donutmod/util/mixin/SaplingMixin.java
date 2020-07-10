package net.rhysholloway.donutmod.util.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PlantBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.rhysholloway.donutmod.lists.BlockList;

@Mixin(PlantBlock.class)
public abstract class SaplingMixin {

	@Overwrite
	public boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
		return floor.isOf(BlockList.muddy_grass) || floor.isOf(BlockList.mud_block) || floor.isOf(Blocks.GRASS_BLOCK) || floor.isOf(Blocks.DIRT) || floor.isOf(Blocks.COARSE_DIRT) || floor.isOf(Blocks.PODZOL) || floor.isOf(Blocks.FARMLAND);
	}
	
}
