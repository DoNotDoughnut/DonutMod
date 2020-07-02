package net.rhysholloway.donutmod2.lists;

import net.minecraft.block.AbstractBlock.Settings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.world.biome.Biome;
import net.rhysholloway.donutmod2.DonutMod;
import net.rhysholloway.donutmod2.block.DartTrapBlock;
import net.rhysholloway.donutmod2.block.NetherFurnaceBlock;
import net.rhysholloway.donutmod2.block.SoulBenchBlock;
import net.rhysholloway.donutmod2.util.block.BasicOre;
import net.rhysholloway.donutmod2.util.block.BlockHelper;
import net.rhysholloway.donutmod2.util.block.OreSettings;

public class BlockList {

	public static Block

	hallowed_ingot_block = new Block(Block.Settings.of(Material.METAL)), 
	mythril_ingot_block = new Block(Block.Settings.of(Material.METAL)), 
	nether_furnace = new NetherFurnaceBlock(Block.Settings.copy(Blocks.BLAST_FURNACE).lightLevel(BlockHelper.createLightLevelFromBlockState(13))), 
	soul_bench = new SoulBenchBlock(Settings.of(Material.REPAIR_STATION).lightLevel((blockstate) -> 5)), 
	dart_trap = new DartTrapBlock(50, Settings.copy(Blocks.DISPENSER)),
	mythril_ore = new BasicOre(Block.Settings.copy(Blocks.DIAMOND_ORE), new OreSettings(3, DonutMod.config.mythril_ore.veinSize, DonutMod.config.mythril_ore.veinsPerChunk, DonutMod.config.mythril_ore.bottomOffset, DonutMod.config.mythril_ore.minHeight, DonutMod.config.mythril_ore.maxHeight, true, Biome.Category.NETHER, Biome.Category.THEEND));

}
