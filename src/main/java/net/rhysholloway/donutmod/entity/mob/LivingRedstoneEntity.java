package net.rhysholloway.donutmod.entity.mob;

import java.util.EnumSet;
import java.util.Random;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.FlyingEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.rhysholloway.donutmod.lists.SoundEventList;

public class LivingRedstoneEntity extends FlyingEntity {

	public static final int maxSpawnHeight = 20, maxFlightHeight = 5;

	public LivingRedstoneEntity(EntityType<? extends FlyingEntity> type,World world) {
		super(type, world);
		this.moveControl = new LivingRedstoneMoveControl(this);
	}

	@Override
	protected void initGoals() {
		this.goalSelector.add(5, new FlyRandomlyGoal(this));
	}

	public static boolean canSpawn(EntityType<LivingRedstoneEntity> type, WorldAccess world, SpawnReason spawnReason, BlockPos pos, Random random) {
		return world.getDifficulty() != Difficulty.PEACEFUL && pos.getY() < maxSpawnHeight && random.nextInt(20) == 0 && canMobSpawn(type, world, spawnReason, pos, random);
	}

	protected boolean isDisallowedInPeaceful() {
		return true;
	}

	public SoundCategory getSoundCategory() {
		return SoundCategory.HOSTILE;
	}

	protected SoundEvent getAmbientSound() {
		return SoundEventList.ENTITY_LIVING_REDSTONE_AMBIENT;
	}

	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundEventList.ENTITY_LIVING_REDSTONE_HURT;
	}

	protected SoundEvent getDeathSound() {
		return SoundEventList.ENTITY_LIVING_REDSTONE_DEATH;
	}

	static class FlyRandomlyGoal extends Goal {
		private final LivingRedstoneEntity entity;

		public FlyRandomlyGoal(LivingRedstoneEntity entity) {
			this.entity = entity;
			this.setControls(EnumSet.of(Goal.Control.MOVE));
		}

		public boolean canStart() {
			
			if(entity.getRandom().nextFloat() > .1) {
				return false;
			}
			
			MoveControl moveControl = this.entity.getMoveControl();
			if (!moveControl.isMoving()) {
				
				return true;
			} else {
				double d = moveControl.getTargetX() - this.entity.getX();
				double e = moveControl.getTargetY() - this.entity.getY();
				double f = moveControl.getTargetZ() - this.entity.getZ();
				double g = d * d + e * e + f * f;
				return g < 1.0D || g > 3600.0D;
			}
		}

		public boolean shouldContinue() {
			return false;
		}

		public void start() {

			int distanceToGround;
			final BlockPos pos = this.entity.getBlockPos();

			for (distanceToGround = 1; distanceToGround <= maxFlightHeight; distanceToGround++) {
				if (!entity.world.isAir(pos.add(0, -distanceToGround, 0))) {
					break;
				}
			}

			Random random = this.entity.getRandom();
			double d = this.entity.getX() + (double) ((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
			double e = Math.min(this.entity.getY() + (maxFlightHeight - distanceToGround), this.entity.getY() + (double) ((random.nextFloat() * 2.0F - 1.0F) * 16.0F));
			double f = this.entity.getZ() + (double) ((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
			this.entity.getMoveControl().moveTo(d, e, f, 1.0D);
		}
	}

	static class LivingRedstoneMoveControl extends MoveControl {
		private final LivingRedstoneEntity entity;
		private int collisionCheckCooldown;

		public LivingRedstoneMoveControl(LivingRedstoneEntity entity) {
			super(entity);
			this.entity = entity;
		}

		public void tick() {
			if (this.state == MoveControl.State.MOVE_TO) {
				if (this.collisionCheckCooldown-- <= 0) {
					this.collisionCheckCooldown += this.entity.getRandom().nextInt(5) + 2;
					Vec3d vec3d = new Vec3d(this.targetX - this.entity.getX(), this.targetY - this.entity.getY(), this.targetZ - this.entity.getZ());
					double d = vec3d.length();
					vec3d = vec3d.normalize();
					if (this.willCollide(vec3d, MathHelper.ceil(d))) {
						this.entity.setVelocity(this.entity.getVelocity().add(vec3d.multiply(0.1D)));
					} else {
						this.state = MoveControl.State.WAIT;
					}
				}

			}
		}

		private boolean willCollide(Vec3d direction, int steps) {
			Box box = this.entity.getBoundingBox();

			for (int i = 1; i < steps; ++i) {
				box = box.offset(direction);
				if (!this.entity.world.doesNotCollide(this.entity, box)) {
					return false;
				}
			}

			return true;
		}
	}

}
