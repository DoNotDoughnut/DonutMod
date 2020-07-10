package net.rhysholloway.donutmod.entity.projectile;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.rhysholloway.donutmod.lists.EntityTypeList;
import net.rhysholloway.donutmod.lists.ItemList;
import net.rhysholloway.donutmod.lists.SoundEventList;

public class LightDiscEntity extends ThrownItemEntity {

	private static final TrackedData<Boolean> ENCHANTED = DataTracker.registerData(LightDiscEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
	private static int maxFlightTime = 80, baseDamage = 3, maxRicochetCount = 0;// 3;

	private int flightTime = 0, ricochets = 0;
	private boolean returning = false;
	private ItemStack stack;

	@Override
	public void tick() {
		
		flightTime++;
		
		if(flightTime > maxFlightTime && returning != true) {
			returning = true;
		}
		
		if (returning) {
			this.noClip = true;

			Vec3d vec3d = new Vec3d(this.getOwner().getX() - this.getX(), this.getOwner().getEyeY() - this.getY(), this.getOwner().getZ() - this.getZ());
			this.setPos(this.getX(), this.getY() + vec3d.y * 0.015D * 2, this.getZ());
			
			if (this.world.isClient) {
				this.lastRenderY = this.getY();
			}

			double d = 0.05D * 2;
			this.setVelocity(this.getVelocity().multiply(0.95D).add(vec3d.normalize().multiply(d)));
		}
		super.tick();
	}

	@Override
	protected void onBlockHit(BlockHitResult hitResult) {
		world.playSound(null, new BlockPos(hitResult.getPos()), SoundEventList.ITEM_LIGHT_DISC_BOUNCE, SoundCategory.PLAYERS, 0.5f, 1f);
		if (ricochets < maxRicochetCount) {
			ricochet(hitResult);
			ricochets++;
		} else {
			returning = true;
		}
		super.onBlockHit(hitResult);
	}

	@Override
	protected void onEntityHit(EntityHitResult hitResult) {

		if (hitResult.getEntity().equals(this.getOwner())) {
			world.sendEntityStatus(this, (byte) 3);
			this.dropStack(stack);
			this.remove();
		} else {
			hitResult.getEntity().damage(DamageSource.thrownProjectile(this, this.getOwner()), getDamage());
		}
	}

	// TO - DO

	private float getDamage() {
		return baseDamage;
	}

	private void ricochet(BlockHitResult hitResult) {
		
		Vec3d vel = this.getVelocity();
		
		switch(hitResult.getSide()) {	
		
		case DOWN: {
			this.setVelocity(vel.x, -vel.y, vel.z);
			break;
		}
			
		case EAST: {
			this.setVelocity(-vel.x, vel.y, vel.z);
			break;
		}
			
		case NORTH: {
			this.setVelocity(vel.x, vel.y, -vel.z);
			break;
		}
		case SOUTH: {
			this.setVelocity(vel.x, vel.y, -vel.z);
			break;
		}
		case UP: {
			this.setVelocity(vel.x, -vel.y, vel.z);
			break;
		}
		case WEST: {
			this.setVelocity(-vel.x, vel.y, vel.z);
			break;
		}
		
		default: {
			returning = true;
			break;
		}
			
		}
		
	}

	@Override
	protected Item getDefaultItem() {
		return ItemList.light_disc;
	}

	protected void initDataTracker() {
		super.initDataTracker();
		this.dataTracker.startTracking(ENCHANTED, false);
	}

	@Environment(EnvType.CLIENT)
	public boolean isEnchanted() {
		return (Boolean) this.dataTracker.get(ENCHANTED);
	}

	public LightDiscEntity(World world, double x, double y, double z) {
		super(EntityTypeList.LIGHT_DISC, x, y, z, world);
		this.stack = new ItemStack(ItemList.light_disc);
		this.setNoGravity(true);
	}

	public LightDiscEntity(LivingEntity livingEntity, World world, ItemStack stack) {
		super(EntityTypeList.LIGHT_DISC, livingEntity, world);
		this.stack = stack;
		this.dataTracker.set(ENCHANTED, stack.hasGlint());
		this.setNoGravity(true);
	}

	public LightDiscEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
		super(entityType, world);
		this.stack = new ItemStack(ItemList.light_disc);
		this.setNoGravity(true);
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
