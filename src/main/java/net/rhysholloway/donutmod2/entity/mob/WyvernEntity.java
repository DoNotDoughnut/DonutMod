package net.rhysholloway.donutmod2.entity.mob;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.rhysholloway.donutmod2.lists.EntityTypeList;

public class WyvernEntity extends HostileEntity {

	public WyvernEntity(World world) {
		super(EntityTypeList.WYVERN, world);
	}	

	@Override
	protected void fall(double heightDifference, boolean onGround, BlockState landedState, BlockPos landedPosition) {
	}

	public static class Builder extends FabricEntityTypeBuilder<WyvernEntity> {

		public Builder(SpawnGroup spawnGroup) {
			super(spawnGroup, new WyvernEntity.Builder.Factory());
		}
		
		public static class Factory implements EntityType.EntityFactory<WyvernEntity> {

			@Override
			public WyvernEntity create(EntityType<WyvernEntity> type, World world) {
				return new WyvernEntity(world);
			}
			
		}
		
	}

}
