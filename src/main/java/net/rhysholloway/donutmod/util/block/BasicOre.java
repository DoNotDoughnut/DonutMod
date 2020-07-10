package net.rhysholloway.donutmod.util.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.OreBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.stat.Stats;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class BasicOre extends OreBlock {

	public final OreSettings oreSettings;
	
	public BasicOre(Block.Settings blockSettings, OreSettings oreSettings) {
		super(blockSettings);
		this.oreSettings = oreSettings;
	}

	@Override
	public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, BlockEntity blockEntity, ItemStack stack) {
		player.incrementStat(Stats.MINED.getOrCreateStat(this));
		player.addExhaustion(0.005F);
		if (stack.getItem() instanceof ToolItem) {
			if (((ToolItem) stack.getItem()).getMaterial().getMiningLevel() >= oreSettings.getMiningLevel() && stack.isEffectiveOn(state)) {
				dropStacks(state, world, pos, blockEntity, player, stack);
			}
		}

	}

	protected int getExperienceWhenMined(Random random) {
		return MathHelper.nextInt(random, oreSettings.getMinXP(), oreSettings.getMaxXP());
	}

	public void onStacksDropped(BlockState state, World world, BlockPos pos, ItemStack stack) {
		super.onStacksDropped(state, world, pos, stack);
		if (EnchantmentHelper.getLevel(Enchantments.SILK_TOUCH, stack) == 0) {
			int i = this.getExperienceWhenMined(world.random);
			if (i > 0) {
				this.dropExperience(world, pos, i);
			}
		}

	}

}
