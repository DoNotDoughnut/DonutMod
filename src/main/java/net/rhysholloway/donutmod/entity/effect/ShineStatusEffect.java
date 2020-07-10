package net.rhysholloway.donutmod.entity.effect;

import java.util.HashMap;

import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.property.Properties;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.rhysholloway.donutmod.lists.BlockList;

public class ShineStatusEffect extends StatusEffect {

	public static HashMap<String, BlockPos> uuidLastPosMap = new HashMap<String, BlockPos>();

	private final int tickRate;

	public ShineStatusEffect(StatusEffectType type, int color, int tickRate) {
		super(type, color);
		this.tickRate = tickRate;
	}	

	@Override
	public void applyUpdateEffect(LivingEntity entity, int amplifier) {
		
		if (!entity.world.isClient) {
			if (entity.age % tickRate == 0) {

				BlockPos pos = new BlockPos(MathHelper.floor(entity.getX()), MathHelper.floor(entity.getY()) + 1, MathHelper.floor(entity.getZ()));

				if (entity.getRootVehicle() != null) {
					pos = new BlockPos(MathHelper.floor(entity.getRootVehicle().getX()), MathHelper.floor(entity.getRootVehicle().getY()) + 1, MathHelper.floor(entity.getRootVehicle().getZ()));
				}

				if (entity.world.getBlockState(pos).isAir() || entity.world.getBlockState(pos).getFluidState().isIn(FluidTags.WATER)) {
					PlayerEntity player = entity.world.getClosestPlayer(entity, -1.0D);

					if (entity instanceof PlayerEntity && !entity.isSpectator()) {
						this.setProperBlock(entity.world, pos, true);
					} else if (player != null && entity.squaredDistanceTo(player) <= 20 && entity.canSee(player) || player != null && entity.squaredDistanceTo(player) <= 15) {
						this.setProperBlock(entity.world, pos, true);
					} else {
						this.setProperBlock(entity.world, pos, false);
					}
				}

				if (uuidLastPosMap.containsKey(entity.getUuidAsString())) {
					BlockPos pos2 = uuidLastPosMap.get(entity.getUuidAsString());

					if ((!entity.isAlive() || entity.getHealth() <= 0 || pos2.getX() != pos.getX() || pos2.getY() != pos.getY() || pos2.getZ() != pos.getZ()) && entity.world.getBlockState(pos2).getBlock() == BlockList.air_light) {
						this.setProperBlock(entity.world, pos2, false);
					}
				}

				uuidLastPosMap.put(entity.getUuidAsString(), pos);

			} /* else {
				if (uuidLastPosMap.containsKey(entity.getUuidAsString())) {
					BlockPos pos = uuidLastPosMap.get(entity.getUuidAsString());

					if (entity.world.getBlockState(pos).getBlock() == BlockList.air_light) {
						this.setProperBlock(entity.world, pos, false);
					}

					uuidLastPosMap.remove(entity.getUuidAsString());
				}
			} */
		}

	}

	@Override
	public boolean canApplyUpdateEffect(int duration, int amplifier) {
		return true;
	}

	@Override
	public void onApplied(LivingEntity entity, AttributeContainer attributes, int amplifier) {
	}

	@Override
	public void onRemoved(LivingEntity entity, AttributeContainer attributes, int amplifier) {
		if (uuidLastPosMap.containsKey(entity.getUuidAsString())) {
			this.setProperBlock(entity.world, uuidLastPosMap.get(entity.getUuidAsString()), false);
			uuidLastPosMap.remove(entity.getUuidAsString());
		}
	}

	private void setProperBlock(World world, BlockPos pos, boolean setting) {
		if (setting) {
			if (world.getBlockState(pos).isAir() && world.getBlockState(pos).getBlock() != BlockList.air_light) {
				world.setBlockState(pos, BlockList.air_light.getDefaultState().with(Properties.WATERLOGGED, false));
			} else if (world.getBlockState(pos) == Blocks.WATER.getDefaultState()) {
				world.setBlockState(pos, BlockList.air_light.getDefaultState().with(Properties.WATERLOGGED, true));
			}
		} else {
			if (world.getBlockState(pos).isAir() && world.getBlockState(pos) == BlockList.air_light.getDefaultState().with(Properties.WATERLOGGED, false)) {
				world.setBlockState(pos, Blocks.AIR.getDefaultState());

			} else if (world.getBlockState(pos) == Blocks.WATER.getDefaultState() || world.getBlockState(pos) == BlockList.air_light.getDefaultState().with(Properties.WATERLOGGED, true)) {
				world.setBlockState(pos, Blocks.WATER.getDefaultState());
			}
		}
	}

}
