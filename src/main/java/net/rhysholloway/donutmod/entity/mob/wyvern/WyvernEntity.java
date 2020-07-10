package net.rhysholloway.donutmod.entity.mob.wyvern;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.FlyingEntity;
import net.minecraft.world.World;

public class WyvernEntity extends FlyingEntity {

	private final WyvernEntityPart head = new WyvernEntityPart(this, "head", 1, 1.375f);
	private final WyvernEntityPart front_legs = new WyvernEntityPart(this, "front_legs", 1, 1.375f);
	private final WyvernEntityPart body = new WyvernEntityPart(this, "body", 1, 1.375f);
	private final WyvernEntityPart back_legs = new WyvernEntityPart(this, "back_legs", 1, 1.375f);
	private final WyvernEntityPart tail = new WyvernEntityPart(this, "tail", 1, 1.375f);
	
	public WyvernEntity(EntityType<? extends FlyingEntity> type, World world) {
		super(type, world);
	}

	public boolean damagePart(WyvernEntityPart wyvernEntityPart, DamageSource source, float amount) {
		return false;
	}

}
