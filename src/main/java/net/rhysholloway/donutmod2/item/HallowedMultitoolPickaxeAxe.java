package net.rhysholloway.donutmod2.item;

import java.util.Map;

import com.google.common.collect.ImmutableMap.Builder;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.rhysholloway.donutmod2.item.material.DonutItemMaterials;
import net.rhysholloway.donutmod2.util.item.MultitoolItem;
import net.rhysholloway.donutmod2.util.item.MultitoolSettings;

public class HallowedMultitoolPickaxeAxe extends MultitoolItem {
	
	protected static final Map<Block, Block> BLOCK_TRANSFORMATIONS_MAP = (new Builder<Block, Block>()).put(Blocks.OAK_WOOD, Blocks.STRIPPED_OAK_WOOD).put(Blocks.OAK_LOG, Blocks.STRIPPED_OAK_LOG).put(Blocks.DARK_OAK_WOOD, Blocks.STRIPPED_DARK_OAK_WOOD).put(Blocks.DARK_OAK_LOG, Blocks.STRIPPED_DARK_OAK_LOG).put(Blocks.ACACIA_WOOD, Blocks.STRIPPED_ACACIA_WOOD).put(Blocks.ACACIA_LOG, Blocks.STRIPPED_ACACIA_LOG).put(Blocks.BIRCH_WOOD, Blocks.STRIPPED_BIRCH_WOOD).put(Blocks.BIRCH_LOG, Blocks.STRIPPED_BIRCH_LOG).put(Blocks.JUNGLE_WOOD, Blocks.STRIPPED_JUNGLE_WOOD).put(Blocks.JUNGLE_LOG, Blocks.STRIPPED_JUNGLE_LOG).put(Blocks.SPRUCE_WOOD, Blocks.STRIPPED_SPRUCE_WOOD).put(Blocks.SPRUCE_LOG, Blocks.STRIPPED_SPRUCE_LOG).build();
	
	public HallowedMultitoolPickaxeAxe(int attackDamage, float attackSpeed, Settings settings) {
		super(DonutItemMaterials.hallowed, attackDamage, attackSpeed, new MultitoolSettings(true, true, true, false), settings);
	}
	
}
