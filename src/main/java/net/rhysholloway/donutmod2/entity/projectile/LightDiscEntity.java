package net.rhysholloway.donutmod2.entity.projectile;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.rhysholloway.donutmod2.lists.EntityTypeList;
import net.rhysholloway.donutmod2.lists.ItemList;

public class LightDiscEntity extends PersistentProjectileEntity {

	private static int maxFlightTime = 80;

	private ItemStack stack;
	public int flightTime, returnTimer;
	private boolean dealtDamage;

	private static final TrackedData<Boolean> ENCHANTED = DataTracker.registerData(LightDiscEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

	public LightDiscEntity(EntityType<? extends PersistentProjectileEntity> type, World world) {
		super(type, world);
		stack = new ItemStack(ItemList.light_disc);
		setPierceLevel((byte) 100);

	}

	public LightDiscEntity(World world, LivingEntity owner, ItemStack stack) {
		super(EntityTypeList.LIGHT_DISC, owner, world);
		this.stack = new ItemStack(ItemList.light_disc);
		this.stack = stack.copy();
		this.dataTracker.set(ENCHANTED, stack.hasGlint());
	}

	protected void initDataTracker() {
		super.initDataTracker();
		this.dataTracker.startTracking(ENCHANTED, false);
	}

	public void tick() {

		flightTime++;

		if (this.inGroundTime > 4) {
			this.dealtDamage = true;
		}

		int i = 0;

		if (flightTime >= maxFlightTime) {
			i = 3;
		}

		Entity entity = this.getOwner();
		if ((this.dealtDamage || this.isNoClip()) && entity != null) {
			if (i > 0 && !this.isOwnerAlive()) {
				if (!this.world.isClient && this.pickupType == PersistentProjectileEntity.PickupPermission.ALLOWED) {
					this.dropStack(this.asItemStack(), 0.1F);
				}

				this.remove();
			} else if (i > 0) {
				this.setNoClip(true);
				Vec3d vec3d = new Vec3d(entity.getX() - this.getX(), entity.getEyeY() - this.getY(), entity.getZ() - this.getZ());
				this.setPos(this.getX(), this.getY() + vec3d.y * 0.015D * (double) i, this.getZ());
				if (this.world.isClient) {
					this.lastRenderY = this.getY();
				}

				double d = 0.05D * (double) i;
				this.setVelocity(this.getVelocity().multiply(0.95D).add(vec3d.normalize().multiply(d)));
				if (this.returnTimer == 0) {
					this.playSound(SoundEvents.ITEM_TRIDENT_RETURN, 10.0F, 1.0F);
				}

				++this.returnTimer;
			}
		}

		super.tick();
	}

	private boolean isOwnerAlive() {
		Entity entity = this.getOwner();
		if (entity != null && entity.isAlive()) {
			return !(entity instanceof ServerPlayerEntity) || !entity.isSpectator();
		} else {
			return false;
		}
	}

	protected EntityHitResult getEntityCollision(Vec3d currentPosition, Vec3d nextPosition) {
		return this.dealtDamage ? null : super.getEntityCollision(currentPosition, nextPosition);
	}

	/*
	 * 
	 * protected void onEntityHit(EntityHitResult entityHitResult) { Entity entity =
	 * entityHitResult.getEntity(); float f = 8.0F; if (entity instanceof
	 * LivingEntity) { LivingEntity livingEntity = (LivingEntity) entity; f +=
	 * EnchantmentHelper.getAttackDamage(this.stack, livingEntity.getGroup()); }
	 * 
	 * Entity entity2 = this.getOwner(); DamageSource damageSource =
	 * DamageSource.trident(this, (Entity) (entity2 == null ? this : entity2));
	 * this.dealtDamage = true; SoundEvent soundEvent =
	 * SoundEvents.ITEM_TRIDENT_HIT; if (entity.damage(damageSource, f)) { if
	 * (entity.getType() == EntityType.ENDERMAN) { return; }
	 * 
	 * if (entity instanceof LivingEntity) { LivingEntity livingEntity2 =
	 * (LivingEntity) entity; if (entity2 instanceof LivingEntity) {
	 * EnchantmentHelper.onUserDamaged(livingEntity2, entity2);
	 * EnchantmentHelper.onTargetDamaged((LivingEntity) entity2, livingEntity2); }
	 * 
	 * this.onHit(livingEntity2); } }
	 * 
	 * this.setVelocity(this.getVelocity().multiply(-0.01D, -0.1D, -0.01D)); float g
	 * = 1.0F; if (this.world instanceof ServerWorld && this.world.isThundering() &&
	 * EnchantmentHelper.hasChanneling(this.stack)) { BlockPos blockPos =
	 * entity.getBlockPos(); if (this.world.isSkyVisible(blockPos)) {
	 * LightningEntity lightningEntity = (LightningEntity)
	 * EntityType.LIGHTNING_BOLT.create(this.world);
	 * lightningEntity.method_29495(Vec3d.ofBottomCenter(blockPos));
	 * lightningEntity.setChanneler(entity2 instanceof ServerPlayerEntity ?
	 * (ServerPlayerEntity) entity2 : null);
	 * this.world.spawnEntity(lightningEntity); soundEvent =
	 * SoundEvents.ITEM_TRIDENT_THUNDER; g = 5.0F; } }
	 * 
	 * this.playSound(soundEvent, g, 1.0F); }
	 * 
	 */

	protected SoundEvent getHitSound() {
		return SoundEvents.ITEM_TRIDENT_HIT_GROUND;
	}

	@Override
	protected void onEntityHit(EntityHitResult entityHitResult) {
		// TODO Auto-generated method stub
		super.onEntityHit(entityHitResult);
	}

	@Override
	public byte getPierceLevel() {
		// TODO Auto-generated method stub
		return super.getPierceLevel();
	}

	public void onPlayerCollision(PlayerEntity player) {
		Entity entity = this.getOwner();
		if (entity == null || entity.getUuid() == player.getUuid()) {
			super.onPlayerCollision(player);
		}
	}

	@Environment(EnvType.CLIENT)
	public LightDiscEntity(World world, double x, double y, double z) {
		super(EntityTypeList.LIGHT_DISC, x, y, z, world);
		this.stack = new ItemStack(ItemList.light_disc);
	}

	@Override
	protected ItemStack asItemStack() {
		return stack;
	}

	@Environment(EnvType.CLIENT)
	public boolean isEnchanted() {
		return (Boolean) this.dataTracker.get(ENCHANTED);
	}

	public static class Builder extends FabricEntityTypeBuilder<LightDiscEntity> {

		public Builder(SpawnGroup spawnGroup) {
			super(spawnGroup, new Factory());
		}

		public static class Factory implements EntityType.EntityFactory<LightDiscEntity> {

			@Override
			public LightDiscEntity create(EntityType<LightDiscEntity> type, World world) {
				return new LightDiscEntity(type, world);
			}

		}

	}

}
