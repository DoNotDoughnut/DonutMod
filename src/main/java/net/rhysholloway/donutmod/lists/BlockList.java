package net.rhysholloway.donutmod.lists;

import static net.rhysholloway.donutmod.util.ModConfig.config;

import net.minecraft.block.AbstractBlock.Settings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.block.SlabBlock;
import net.minecraft.world.biome.Biome;
import net.rhysholloway.donutmod.block.AdamantiteFurnaceBlock;
import net.rhysholloway.donutmod.block.AirLightBlock;
import net.rhysholloway.donutmod.block.DartTrapBlock;
import net.rhysholloway.donutmod.block.MuddyGrassBlock;
import net.rhysholloway.donutmod.block.NetherFurnaceBlock;
import net.rhysholloway.donutmod.block.SoulBenchBlock;
import net.rhysholloway.donutmod.util.block.BlockHelper;
import net.rhysholloway.donutmod.util.block.FrontTexturedBlock;
import net.rhysholloway.donutmod.util.block.NoGenOre;
import net.rhysholloway.donutmod.util.block.OreSettings;
import net.rhysholloway.donutmod.util.block.StairBlock;

public class BlockList {
	
	// Blocks with no item

	public static Block air_light = new AirLightBlock(Block.Settings.copy(Blocks.AIR).lightLevel((blockstate) -> 12));
	
	// Building blocks
	
	public static Block muddy_grass = new MuddyGrassBlock(Block.Settings.copy(Blocks.GRASS_BLOCK));
	public static Block mud_block = new Block(Block.Settings.copy(Blocks.DIRT));
	public static Block temple_block = new Block(Block.Settings.copy(Blocks.STONE_BRICKS));
	public static Block chiseled_temple_block = new Block(Block.Settings.copy(Blocks.STONE_BRICKS));
	public static Block temple_block_slab = new SlabBlock(Block.Settings.copy(Blocks.STONE_BRICK_SLAB));
	public static Block temple_block_stairs = new StairBlock(temple_block.getDefaultState(), Block.Settings.copy(Blocks.STONE_BRICK_STAIRS));
	
	// Ores
	
	public static Block cobalt_ore = new NoGenOre(Block.Settings.copy(Blocks.DIAMOND_ORE), new OreSettings(3, config.cobalt_ore.veinSize, config.cobalt_ore.veinsPerChunk, config.cobalt_ore.bottomOffset, config.cobalt_ore.minHeight, config.cobalt_ore.maxHeight, true, Biome.Category.NETHER, Biome.Category.THEEND));
	public static Block mythril_ore = new NoGenOre(Block.Settings.copy(cobalt_ore), new OreSettings(3, config.mythril_ore.veinSize, config.mythril_ore.veinsPerChunk, config.mythril_ore.bottomOffset, config.mythril_ore.minHeight, config.mythril_ore.maxHeight, true, Biome.Category.NETHER, Biome.Category.THEEND));
	public static Block adamantite_ore = new NoGenOre(Block.Settings.copy(mythril_ore), new OreSettings(3, config.adamantite_ore.veinSize, config.adamantite_ore.veinsPerChunk, config.adamantite_ore.bottomOffset, config.adamantite_ore.minHeight, config.adamantite_ore.maxHeight, true, Biome.Category.NETHER, Biome.Category.THEEND));

	// Ingot / Gem blocks
	
	public static Block cobalt_ingot_block = new Block(Block.Settings.of(Material.METAL));
	public static Block mythril_ingot_block = new Block(Block.Settings.of(Material.METAL));
	public static Block adamantite_ingot_block = new Block(Block.Settings.of(Material.METAL));
	public static Block hallowed_ingot_block = new Block(Block.Settings.of(Material.METAL)); 
	
	
	// Crafting stations / containers
	
	public static Block nether_furnace = new NetherFurnaceBlock(Block.Settings.copy(Blocks.BLAST_FURNACE).lightLevel(NetherFurnaceBlock.createLightLevelFromBlockState()));
	public static Block adamantite_furnace = new AdamantiteFurnaceBlock(Block.Settings.copy(Blocks.BLAST_FURNACE).lightLevel(BlockHelper.createLightLevelFromBlockState(13)));
	public static Block soul_bench = new SoulBenchBlock(Settings.of(Material.REPAIR_STATION).lightLevel((blockstate) -> 5)); 
	
	// Activatable blocks
	
	public static Block dart_trap = new DartTrapBlock(50, Settings.copy(Blocks.DISPENSER));
	public static Block temple_dart_trap = new FrontTexturedBlock(Settings.copy(Blocks.DISPENSER));
	public static Block temple_spike_trap = new FrontTexturedBlock(Settings.copy(Blocks.DISPENSER));
	
}
