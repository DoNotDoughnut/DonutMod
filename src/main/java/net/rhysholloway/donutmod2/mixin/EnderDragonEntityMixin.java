package net.rhysholloway.donutmod2.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.rhysholloway.donutmod2.loot.LootHandler;

@Mixin(EnderDragonEntity.class)
public abstract class EnderDragonEntityMixin extends MobEntity {

	protected EnderDragonEntityMixin(EntityType<? extends MobEntity> entityType, World world) {
		super(entityType, world);
	}

	@Inject(method = "awardExperience", at = @At("HEAD"))
	private void onAwardExperience(int amount, CallbackInfo info) {
		world.spawnEntity(new ItemEntity(world, this.getX(), this.getY(), this.getZ(), LootHandler.getEnderDragonLoot()));
	}

	@SuppressWarnings("unused")
	private void spawnMany(int num) {
		while(num > 0) {
			int toGive = world.random.nextInt(4);
			if(num < toGive)
				toGive = num;
			num -= toGive;
			world.spawnEntity(new ItemEntity(world, this.getX(), this.getY(), this.getZ(), new ItemStack(LootHandler.getEnderDragonLoot().getItem(), toGive)));
		}
	}
}
