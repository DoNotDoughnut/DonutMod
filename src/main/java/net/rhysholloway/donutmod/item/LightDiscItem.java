package net.rhysholloway.donutmod.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.rhysholloway.donutmod.entity.projectile.LightDiscEntity;
import net.rhysholloway.donutmod.lists.SoundEventList;

public class LightDiscItem extends Item {

	public LightDiscItem(Settings settings) {
		super(settings);
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
		if (!world.isClient) {
			LightDiscEntity entity = new LightDiscEntity(user, world, user.getStackInHand(hand));
			entity.setProperties(user, user.pitch, user.yaw, 0.0F, 2.5f, 1);
			world.spawnEntity(entity);
			world.playSoundFromEntity(null, entity, SoundEventList.ITEM_LIGHT_DISC_THROW, SoundCategory.PLAYERS, 1, 1);
			user.incrementStat(Stats.USED.getOrCreateStat(this));
			return TypedActionResult.consume(user.getStackInHand(hand));
		}
		return TypedActionResult.pass(user.getStackInHand(hand));
	}

}
