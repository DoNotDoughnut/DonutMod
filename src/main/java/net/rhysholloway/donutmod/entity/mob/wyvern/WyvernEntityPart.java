package net.rhysholloway.donutmod.entity.mob.wyvern;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Packet;
import net.rhysholloway.donutmod.lists.EntityTypeList;

public class WyvernEntityPart extends Entity {

	public final WyvernEntity owner;
	public final String name;
	private final EntityDimensions partDimensions;

	public WyvernEntityPart(WyvernEntity owner, String name, float width, float height) {
		super(EntityTypeList.WYVERN, owner.world);
		this.owner = owner;
		this.name = name;
		this.partDimensions = EntityDimensions.changing(width, height);
	}

	public boolean damage(DamageSource source, float amount) {
		return this.isInvulnerableTo(source) ? false : this.owner.damagePart(this, source, amount);
	}

	public boolean isPartOf(Entity entity) {
		return this == entity || this.owner == entity;
	}

	public EntityDimensions getDimensions(EntityPose pose) {
		return this.partDimensions;
	}

	public boolean collides() {
		return true;
	}

	protected void initDataTracker() {

	}

	protected void readCustomDataFromTag(CompoundTag tag) {

	}

	protected void writeCustomDataToTag(CompoundTag tag) {

	}

	@Override
	public Packet<?> createSpawnPacket() {
		throw new UnsupportedOperationException();
	}

}
