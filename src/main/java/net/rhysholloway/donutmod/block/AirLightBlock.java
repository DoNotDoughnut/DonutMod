package net.rhysholloway.donutmod.block;

import net.minecraft.block.AirBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager.Builder;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldAccess;
import net.rhysholloway.donutmod.util.block.Itemless;

public class AirLightBlock extends AirBlock implements Itemless {

	private static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

	public AirLightBlock(Settings settings) {
		super(settings);
	}

	@Override
	public FluidState getFluidState(BlockState state) {
		return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : Fluids.EMPTY.getDefaultState();
	}

	@Override
	public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState newState, WorldAccess world, BlockPos pos, BlockPos posFrom) {

		if (state.get(WATERLOGGED)) {
			world.getFluidTickScheduler().schedule(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
		}

		return super.getStateForNeighborUpdate(state, direction, newState, world, pos, posFrom);
	}

	@Override
	public BlockState getPlacementState(ItemPlacementContext context) {
		BlockPos blockpos = context.getBlockPos();
		BlockState blockstate = context.getWorld().getBlockState(blockpos);
		if (blockstate.getBlock() == this) {
			return blockstate.with(WATERLOGGED, Boolean.valueOf(false));
		} else {
			FluidState ifluidstate = context.getWorld().getFluidState(blockpos);
			BlockState blockstate1 = this.getDefaultState().with(WATERLOGGED, Boolean.valueOf(ifluidstate.getFluid() == Fluids.WATER));
			return blockstate1;
		}
	}
	
	@Override
	protected void appendProperties(Builder<Block, BlockState> builder) {
		builder.add(WATERLOGGED);
	}

	@Override
	public BlockRenderType getRenderType(BlockState state) {
		return BlockRenderType.INVISIBLE;
	}

}
