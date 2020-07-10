package net.rhysholloway.donutmod.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FacingBlock;
import net.minecraft.block.dispenser.DispenserBehavior;
import net.minecraft.block.dispenser.ProjectileDispenserBehavior;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPointerImpl;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Position;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;

public class DartTrapBlock extends Block {

	public static final BooleanProperty TRIGGERED = Properties.TRIGGERED;
	public static final DirectionProperty FACING = FacingBlock.FACING;

	private final int waitTime;
	private long lastTimeUsed;

	public DartTrapBlock(int waitTime, Settings settings) {
		super(settings);
		this.waitTime = waitTime;
	}
	
	public BlockState rotate(BlockState state, BlockRotation rotation) {
		return (BlockState) state.with(FACING, rotation.rotate((Direction) state.get(FACING)));
	}

	public BlockState mirror(BlockState state, BlockMirror mirror) {
		return state.rotate(mirror.getRotation((Direction) state.get(FACING)));
	}

	@Override
	public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
		if (world.getTime() - waitTime > lastTimeUsed) {
			lastTimeUsed = world.getTime();
			behavior.dispense(new BlockPointerImpl(world, pos), new ItemStack(Items.TIPPED_ARROW));
		}
	}

	public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean notify) {
		boolean bl = world.isReceivingRedstonePower(pos) || world.isReceivingRedstonePower(pos.up());
		boolean bl2 = (Boolean) state.get(TRIGGERED);
		if (bl && !bl2) {
			world.getBlockTickScheduler().schedule(pos, this, 4);
			world.setBlockState(pos, (BlockState) state.with(TRIGGERED, true), 4);
		} else if (!bl && bl2) {
			world.setBlockState(pos, (BlockState) state.with(TRIGGERED, false), 4);
		}

	}

	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(FACING, TRIGGERED);
	}

	private static final DispenserBehavior behavior = new ProjectileDispenserBehavior() {
		protected ProjectileEntity createProjectile(World world, Position position, ItemStack stack) {
			ArrowEntity arrowEntity = new ArrowEntity(world, position.getX(), position.getY(), position.getZ());
			arrowEntity.addEffect(new StatusEffectInstance(StatusEffects.POISON, 3, 20 * getPoisonDifficulty(world.getDifficulty())));
			arrowEntity.pickupType = PersistentProjectileEntity.PickupPermission.DISALLOWED;
			return arrowEntity;
		}
	};

	public static int getPoisonDifficulty(Difficulty d) {
		switch (d) {
		case PEACEFUL: {
			return 0;
		}
		case EASY: {
			return 5;
		}
		case NORMAL: {
			return 10;
		}
		case HARD: {
			return 15;
		}
		default: {
			return 0;
		}
		}
	}

}
