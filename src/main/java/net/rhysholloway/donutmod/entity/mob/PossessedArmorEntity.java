package net.rhysholloway.donutmod.entity.mob;

import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.rhysholloway.donutmod.lists.ItemList;

public class PossessedArmorEntity extends HostileEntity {

	public PossessedArmorEntity(EntityType<PossessedArmorEntity> type, World world) {
		super(type, world);
	}

	@Override
	public boolean handleFallDamage(float fallDistance, float damageMultiplier) {
		return false;
	}

	@Override
	public EntityData initialize(WorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, EntityData entityData, CompoundTag entityTag) {

		this.equipStack(EquipmentSlot.HEAD, new ItemStack(ItemList.possessed_helmet));
		this.equipStack(EquipmentSlot.CHEST, new ItemStack(ItemList.possessed_chestplate));
		this.equipStack(EquipmentSlot.LEGS, new ItemStack(ItemList.possessed_leggings));
		this.equipStack(EquipmentSlot.FEET, new ItemStack(ItemList.possessed_boots));

		return super.initialize(world, difficulty, spawnReason, entityData, entityTag);
	}

}
