package net.rhysholloway.donutmod1.items.hallowed;

import java.util.Map;
import java.util.function.Consumer;

import com.google.common.collect.ImmutableMap.Builder;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.block.PillarBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.PickaxeItem;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.rhysholloway.donutmod1.items.DonutItems;
import net.rhysholloway.donutmod1.materials.DonutItemMaterials;

public class HallowedMultitoolPickaxeAxe extends PickaxeItem {
	
	protected static final Map<Block, Block> BLOCK_TRANSFORMATIONS_MAP = (new Builder<Block, Block>()).put(Blocks.OAK_WOOD, Blocks.STRIPPED_OAK_WOOD).put(Blocks.OAK_LOG, Blocks.STRIPPED_OAK_LOG).put(Blocks.DARK_OAK_WOOD, Blocks.STRIPPED_DARK_OAK_WOOD).put(Blocks.DARK_OAK_LOG, Blocks.STRIPPED_DARK_OAK_LOG).put(Blocks.ACACIA_WOOD, Blocks.STRIPPED_ACACIA_WOOD).put(Blocks.ACACIA_LOG, Blocks.STRIPPED_ACACIA_LOG).put(Blocks.BIRCH_WOOD, Blocks.STRIPPED_BIRCH_WOOD).put(Blocks.BIRCH_LOG, Blocks.STRIPPED_BIRCH_LOG).put(Blocks.JUNGLE_WOOD, Blocks.STRIPPED_JUNGLE_WOOD).put(Blocks.JUNGLE_LOG, Blocks.STRIPPED_JUNGLE_LOG).put(Blocks.SPRUCE_WOOD, Blocks.STRIPPED_SPRUCE_WOOD).put(Blocks.SPRUCE_LOG, Blocks.STRIPPED_SPRUCE_LOG).build();
	
	public HallowedMultitoolPickaxeAxe(int attackDamage, float attackSpeed) {
		super(DonutItemMaterials.hallowed, attackDamage, attackSpeed, new Item.Settings().group(DonutItems.group));
	}
	
	public float getMiningSpeed(ItemStack stack, BlockState state) {
        Material material = state.getMaterial();        	
        return material != Material.WOOD && material != Material.PLANT && material != Material.REPLACEABLE_PLANT && material != Material.BAMBOO ? super.getMiningSpeed(stack, state) : this.miningSpeed;
    }

	public ActionResult useOnBlock(ItemUsageContext itemUsageContext) {
		World world = itemUsageContext.getWorld();
		BlockPos blockPos = itemUsageContext.getBlockPos();
		BlockState blockState = world.getBlockState(blockPos);
		Block block = BLOCK_TRANSFORMATIONS_MAP.get(blockState.getBlock());
		if (block != null) {
			PlayerEntity playerEntity = itemUsageContext.getPlayer();
			world.playSound(playerEntity, blockPos, SoundEvents.ITEM_AXE_STRIP, SoundCategory.BLOCKS, 1.0F, 1.0F);
			if (!world.isClient) {
				world.setBlockState(blockPos, (BlockState) block.getDefaultState().with(PillarBlock.AXIS, blockState.get(PillarBlock.AXIS)), 11);
				if (playerEntity != null)
					itemUsageContext.getStack().damage(1, (LivingEntity) playerEntity, (Consumer<LivingEntity>) ((p) -> {
						p.sendToolBreakStatus(itemUsageContext.getHand());
					}));
			}
			return ActionResult.SUCCESS;
		} else {
			return ActionResult.PASS;
		}
	}
	
	public boolean isEffectiveOn(BlockState blockState) {
		Material material = blockState.getMaterial();
		if (material == Material.WOOD || material == Material.PLANT || material == Material.REPLACEABLE_PLANT || material == Material.BAMBOO || super.isEffectiveOn(blockState)) {
			return true;
		} else
			return false;
	}
	
}
