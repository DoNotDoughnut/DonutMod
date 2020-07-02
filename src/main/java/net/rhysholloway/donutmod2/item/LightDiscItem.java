package net.rhysholloway.donutmod2.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.rhysholloway.donutmod2.entity.projectile.LightDiscEntity;
import net.rhysholloway.donutmod2.lists.SoundEventsList;

public class LightDiscItem extends Item {

	public LightDiscItem(Settings settings) {
		super(settings);
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
		if (!world.isClient) {
			LightDiscEntity entity = new LightDiscEntity(world, user, user.getStackInHand(hand));
			entity.setProperties(user, user.pitch, user.yaw, 0.0F, 2.5f, 1);

			if (user.abilities.creativeMode) {
				entity.pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;
			}

			world.spawnEntity(entity);
			world.playSoundFromEntity(null, entity, SoundEventsList.LIGHT_DISC_THROW, SoundCategory.PLAYERS, 1, 1);
			user.incrementStat(Stats.USED.getOrCreateStat(this));
			return TypedActionResult.consume(user.getStackInHand(hand));
		}
		return TypedActionResult.pass(user.getStackInHand(hand));
	}

	public void onStoppedUsing2(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
		if (user instanceof PlayerEntity) {
			PlayerEntity playerEntity = (PlayerEntity) user;
			if (!world.isClient) {
				TridentEntity tridentEntity = new TridentEntity(world, playerEntity, stack);
				tridentEntity.setProperties(playerEntity, playerEntity.pitch, playerEntity.yaw, 0.0F, 2.5F + 0.5F, 1.0F);
				if (playerEntity.abilities.creativeMode) {
					tridentEntity.pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;
				}

				world.spawnEntity(tridentEntity);
				world.playSoundFromEntity((PlayerEntity) null, tridentEntity, SoundEvents.ITEM_TRIDENT_THROW, SoundCategory.PLAYERS, 1.0F, 1.0F);
				if (!playerEntity.abilities.creativeMode) {
					playerEntity.inventory.removeOne(stack);
				}

				playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));

			}
		}
	}

}
