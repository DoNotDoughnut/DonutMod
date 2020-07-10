package net.rhysholloway.donutmod.world.gen.feature;

import static net.rhysholloway.donutmod.util.ModConfig.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.IntStream;

import org.apache.commons.lang3.mutable.MutableBoolean;

import com.google.common.collect.Sets;
import com.mojang.serialization.Codec;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.Material;
import net.minecraft.block.WallTorchBlock;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.structure.StructurePiece;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BirchForestBiome;
import net.minecraft.world.biome.DarkForestBiome;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.rhysholloway.donutmod.loot.LootHandler;

public class UndergroundCabinFeature extends Feature<DefaultFeatureConfig> {

	public static final Set<Biome> biomesWithDungeon = Sets.newLinkedHashSet();

	private final int

	width = 7, height = 6, depth = 7;

	public UndergroundCabinFeature(Codec<DefaultFeatureConfig> codec) {
		super(codec);
	}

	@Override
	public boolean generate(ServerWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator generator, Random random, BlockPos blockPos, DefaultFeatureConfig featureConfig) {

		if (!config.underground_cabin.enabled) {
			return false;
		}

		MutableBoolean bool = new MutableBoolean();

		IntStream.range(0, config.underground_cabin.chance).mapToObj((ix) -> {
			int j = random.nextInt(16) + blockPos.getX();
			int k = random.nextInt(16) + blockPos.getZ();
			int l = random.nextInt(generator.getMaxY());
			return new BlockPos(j, l, k);
		}).forEach((pos) -> {

			int xStart = 0, yStart = 0, zStart = 0;
			int xEnd = this.width - 1, yEnd = this.height - 1, zEnd = this.depth - 1;

			int closeAirBlocks = 0;

			int xScan;
			int yScan;
			int zScan;

			BlockPos currentPos;

			List<DoorSpot> doorPositions = new ArrayList<DoorSpot>();

			// check for empty area to put cabin

			for (xScan = xStart; xScan <= xEnd; ++xScan) {
				for (yScan = yStart; yScan <= yEnd; ++yScan) {
					for (zScan = zStart; zScan <= zEnd; ++zScan) {

						currentPos = pos.add(xScan, yScan, zScan);

						// check if blocks in spawn pos are solid

						Material material = world.getBlockState(currentPos).getMaterial();
						boolean solid = material.isSolid();

						if (yScan == yStart && !solid) {
							bool.setFalse(); // if blocks are not solid at floor, dont generate
							return;
						}

						if (yScan == yEnd && !solid) {
							bool.setFalse(); // if blocks are not solid at roof, dont generate
							return;
						}

						if (yScan == 1 && world.isAir(currentPos) && world.isAir(currentPos.up())) {

							if (xScan == xStart + 1) { //
								closeAirBlocks++;
								doorPositions.add(new DoorSpot(currentPos, Direction.SOUTH));
							}
							if (xScan == xEnd - 1) { //
								closeAirBlocks++;
								doorPositions.add(new DoorSpot(currentPos, Direction.NORTH));
							}

							if (zScan == zStart + 1) { // south
								closeAirBlocks++;
								doorPositions.add(new DoorSpot(currentPos, Direction.WEST));
							}

							if (zScan == zEnd - 1) { // north
								closeAirBlocks++;
								doorPositions.add(new DoorSpot(currentPos, Direction.EAST));
							}

						}

						if ((xScan == xStart || xScan == xEnd || zScan == zStart || zScan == zEnd) // if on edge
								&& yScan == 0 // if on chest level
								&& world.isAir(currentPos) && world.isAir(currentPos.up())) // if 2 blocks of air space on edge
						{
							closeAirBlocks++;
						}
					}
				}
			}

			if (closeAirBlocks >= 1 && closeAirBlocks <= 5) {

				Biome biome = world.getBiome(pos);

				BlockState outer1, outer2, beam, door;

				outer2 = Blocks.COBBLESTONE.getDefaultState();

				switch (biome.getCategory()) {

				case TAIGA: {
					outer1 = Blocks.SPRUCE_PLANKS.getDefaultState();
					beam = Blocks.STRIPPED_SPRUCE_LOG.getDefaultState();
					door = Blocks.SPRUCE_DOOR.getDefaultState();
					break;
				}

				case SAVANNA: {
					outer1 = Blocks.ACACIA_PLANKS.getDefaultState();
					beam = Blocks.STRIPPED_ACACIA_LOG.getDefaultState();
					door = Blocks.ACACIA_DOOR.getDefaultState();
					break;
				}
				
				case JUNGLE: {
					outer1 = Blocks.JUNGLE_PLANKS.getDefaultState();
					beam = Blocks.STRIPPED_JUNGLE_LOG.getDefaultState();
					door = Blocks.JUNGLE_DOOR.getDefaultState();
					break;
				}

				case FOREST: {
					if (biome instanceof DarkForestBiome) {
						outer1 = Blocks.DARK_OAK_PLANKS.getDefaultState();
						beam = Blocks.STRIPPED_DARK_OAK_LOG.getDefaultState();
						door = Blocks.DARK_OAK_DOOR.getDefaultState();
						break;
					} else if (biome instanceof BirchForestBiome) {
						outer1 = Blocks.BIRCH_PLANKS.getDefaultState();
						beam = Blocks.STRIPPED_BIRCH_LOG.getDefaultState();
						door = Blocks.BIRCH_DOOR.getDefaultState();
						break;
					} else {
						outer1 = Blocks.OAK_PLANKS.getDefaultState();
						beam = Blocks.STRIPPED_OAK_LOG.getDefaultState();
						door = Blocks.OAK_DOOR.getDefaultState();
						break;
					}
				}

				default: {
					outer1 = Blocks.OAK_PLANKS.getDefaultState();
					beam = Blocks.STRIPPED_OAK_LOG.getDefaultState();
					door = Blocks.OAK_DOOR.getDefaultState();
					break;
				}

				}

				// create empty room with plank walls

				for (xScan = xStart; xScan <= xEnd; xScan++) { // go through each x value
					for (yScan = yStart; yScan <= yEnd; yScan++) { // go through each y value
						for (zScan = zStart; zScan <= zEnd; zScan++) { // go through each z value

							currentPos = pos.add(xScan, yScan, zScan);

							// create hollow box

							if (xScan != xStart && xScan != xEnd && yScan != yStart && yScan != yEnd && zScan != zStart && zScan != zEnd) {

								world.setBlockState(currentPos, Blocks.CAVE_AIR.getDefaultState(), 2);

								if (yScan == yEnd - 1) {
									if (random.nextInt(8) == 0) {
										world.setBlockState(currentPos, Blocks.COBWEB.getDefaultState(), 2);
									}
								}
							} else if (yScan % 2 == 1) {
								world.setBlockState(currentPos, outer2, 2);
							} else {
								world.setBlockState(currentPos, outer1, 2);

							}

							// create beams
							createBeams(world, currentPos, xScan, zScan, xStart, xEnd, zStart, zEnd, beam);
						}
					}
				}

				// create chest

				createChest(world, random, pos.add(xEnd / 2, 1, zEnd / 2));
				createTorches(world, pos.add(0, yEnd - 2, 0), xEnd, zEnd);

				createDoor(world, door, doorPositions);

				bool.setTrue();
			} else {
				bool.setFalse();
			}
		});
		return bool.isTrue();
	}

	private void createBeams(ServerWorldAccess world, BlockPos currentPos, int xScan, int zScan, int xStart, int xEnd, int zStart, int zEnd, BlockState beam) {
		if (xScan == xStart || xScan == xEnd) {
			if (zScan == zStart || zScan == zEnd) {
				world.setBlockState(currentPos, beam, 2);
			}
		}
	}

	private void createChest(ServerWorldAccess world, Random random, BlockPos chestPos) {
		world.setBlockState(chestPos, StructurePiece.method_14916(world, chestPos, Blocks.CHEST.getDefaultState()), 2);
		LootableContainerBlockEntity.setLootTable(world, random, chestPos, LootHandler.getUndergroundCabinLoot(random));
	}

	private void createTorches(ServerWorldAccess world, BlockPos pos, int maxX, int maxZ) {
		world.setBlockState(pos.add(maxX / 2, 0, maxZ / 2 + 2), Blocks.WALL_TORCH.getDefaultState(), 2); // south
		world.setBlockState(pos.add(maxX / 2, 0, maxZ / 2 - 2), Blocks.WALL_TORCH.getDefaultState().with(WallTorchBlock.FACING, Direction.SOUTH), 2);
		world.setBlockState(pos.add(maxX / 2 + 2, 0, maxZ / 2), Blocks.WALL_TORCH.getDefaultState().with(WallTorchBlock.FACING, Direction.WEST), 2);
		world.setBlockState(pos.add(maxX / 2 - 2, 0, maxZ / 2), Blocks.WALL_TORCH.getDefaultState().with(WallTorchBlock.FACING, Direction.EAST), 2);
	}

	private void createDoor(ServerWorldAccess world, BlockState door, List<DoorSpot> doorSpots) {
		for (int i = 0; i < doorSpots.size(); i++) {
			if (!world.getBlockState(doorSpots.get(i).pos).isAir()) {
				world.setBlockState(doorSpots.get(i).pos, door.with(DoorBlock.FACING, doorSpots.get(i).direction), 2);
				world.setBlockState(doorSpots.get(i).pos.up(), door.with(DoorBlock.FACING, doorSpots.get(i).direction).with(DoorBlock.HALF, DoubleBlockHalf.UPPER), 2);
				break;
			}
		}
	}
	
	private static class DoorSpot {

		public final BlockPos pos;

		public final Direction direction;

		public DoorSpot(BlockPos pos, Direction direction) {
			this.pos = pos;
			this.direction = direction;
		}

	}

}
