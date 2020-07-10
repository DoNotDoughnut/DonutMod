package net.rhysholloway.donutmod.util.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FacingBlock;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.Direction;

public class FrontTexturedBlock extends Block {

	public static final DirectionProperty FACING = FacingBlock.FACING;
	
	public FrontTexturedBlock(Settings settings) {
		super(settings);
	}
	
	public BlockState rotate(BlockState state, BlockRotation rotation) {
		return (BlockState) state.with(FACING, rotation.rotate((Direction) state.get(FACING)));
	}

	public BlockState mirror(BlockState state, BlockMirror mirror) {
		return state.rotate(mirror.getRotation((Direction) state.get(FACING)));
	}
	
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}
	
}
