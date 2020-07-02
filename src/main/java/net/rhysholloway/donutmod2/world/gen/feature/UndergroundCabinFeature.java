package net.rhysholloway.donutmod2.world.gen.feature;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import org.apache.commons.lang3.mutable.MutableBoolean;

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
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.rhysholloway.donutmod2.DonutMod;
import net.rhysholloway.donutmod2.loot.LootHandler;
import net.rhysholloway.donutmod2.world.gen.feature.config.UndergroundCabinFeatureConfig;

public class UndergroundCabinFeature extends Feature<UndergroundCabinFeatureConfig> {

	private final int

	maxX = 7, maxY = 6, maxZ = 7;

	private final BlockState

	outer1 = Blocks.OAK_PLANKS.getDefaultState(), outer2 = Blocks.COBBLESTONE.getDefaultState(), beam = Blocks.STRIPPED_OAK_LOG.getDefaultState(), door = Blocks.OAK_DOOR.getDefaultState();

	public UndergroundCabinFeature(Codec<UndergroundCabinFeatureConfig> codec) {
		super(codec);
	}

	@Override
	public boolean generate(ServerWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator generator, Random random, BlockPos blockPos, UndergroundCabinFeatureConfig config) {

		if (!DonutMod.config.underground_cabin.enabled) {
			return false;
		}

		MutableBoolean bool = new MutableBoolean();

		IntStream.range(0, config.chance).mapToObj((ix) -> {
			int j = random.nextInt(16) + blockPos.getX();
			int k = random.nextInt(16) + blockPos.getZ();
			int l = random.nextInt(generator.getMaxY());
			return new BlockPos(j, l, k);
		}).forEach((pos) -> {

			int xStart = 0, yStart = 0, zStart = 0;
			int xEnd = this.maxX - 1, yEnd = this.maxY - 1, zEnd = this.maxZ - 1;
			BlockState outer1 = this.outer1, outer2 = this.outer2, beam = this.beam, door = this.door;

			int closeAirBlocks = 0;

			int xScan;
			int yScan;
			int zScan;

			BlockPos currentPos;

			List<BlockPos> doorPos = new ArrayList<BlockPos>();
			Direction doorDirection = null;

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
								if (doorDirection == null || doorDirection == Direction.NORTH) {
									doorDirection = Direction.NORTH;
									doorPos.add(currentPos);
								}
							}
							if (xScan == xEnd - 1) { //
								closeAirBlocks++;
								if (doorDirection == null || doorDirection == Direction.SOUTH) {
									doorDirection = Direction.SOUTH;
									doorPos.add(currentPos);
								}

							}

							if (zScan == zStart + 1) { // south
								closeAirBlocks++;
								if (doorDirection == null || doorDirection == Direction.EAST) {
									doorDirection = Direction.EAST;
									doorPos.add(currentPos);
								}
							}

							if (zScan == zEnd - 1) { // north
								closeAirBlocks++;
								if (doorDirection == null || doorDirection == Direction.WEST) {
									doorDirection = Direction.WEST;
									doorPos.add(currentPos);
								}
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

				// create empty room with plank walls
				
				ArrayList<BlockPos> availableDoorPoses = new ArrayList<BlockPos>();

				for (xScan = xStart; xScan <= xEnd; xScan++) { // go through each x value
					for (yScan = yStart; yScan >= yEnd; yScan++) { // go through each y value
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
								if (doorPos.contains(currentPos)) {
									availableDoorPoses.add(currentPos);
								}
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
				createTorches(world, pos.add(0, yEnd - 2, 0), maxX, maxZ);
				createDoor(world, door, availableDoorPoses.size() > 0 ? availableDoorPoses.get(0) : doorPos.get(0), doorDirection);

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

	private DoorSpot findBestDoorPosition(BlockPos rootPos, List<BlockPos> posList, int startX, int startZ, int endX, int endZ) {
		BlockPos bestPos = posList.get(0);

		ArrayList<BlockPos> southPos = new ArrayList<BlockPos>(), westPos = new ArrayList<BlockPos>(), northPos = new ArrayList<BlockPos>(), eastPos = new ArrayList<BlockPos>();

		posList.forEach(pos -> {

			if (pos.getX() == startX) { // west
				westPos.add(pos);
			}

			if (pos.getX() == endX) { // east
				eastPos.add(pos);
			}

			if (pos.getZ() == startZ) { // north
				northPos.add(pos);
			}

			if (pos.getZ() == endZ) { // south
				southPos.add(pos);
			}

		});

		return new DoorSpot(bestPos, Direction.WEST);
	}

	@SuppressWarnings("unused")
	private static class DoorSpot {

		public final BlockPos pos;

		public final Direction direction;

		public DoorSpot(BlockPos pos, Direction direction) {
			this.pos = pos;
			this.direction = direction;
		}

	}

	private void createDoor(ServerWorldAccess world, BlockState door, BlockPos pos, Direction direction) {
		world.setBlockState(pos, door.with(DoorBlock.FACING, direction), 2);
		world.setBlockState(pos.up(), door.with(DoorBlock.FACING, direction).with(DoorBlock.HALF, DoubleBlockHalf.UPPER), 2);
	}

}
