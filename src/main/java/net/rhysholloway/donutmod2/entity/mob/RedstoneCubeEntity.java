package net.rhysholloway.donutmod2.entity.mob;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.world.World;
import net.rhysholloway.donutmod2.lists.EntityTypeList;

public class RedstoneCubeEntity extends HostileEntity {

	public RedstoneCubeEntity(World world) {
		super(EntityTypeList.REDSTONE_CUBE, world);
	}
	
	public static class Builder extends FabricEntityTypeBuilder<RedstoneCubeEntity> {
		
		public Builder(SpawnGroup spawnGroup) {
			super(spawnGroup, new Factory());
		}

		public static class Factory implements EntityType.EntityFactory<RedstoneCubeEntity> {

			@Override
			public RedstoneCubeEntity create(EntityType<RedstoneCubeEntity> type, World world) {
				return new RedstoneCubeEntity(world);
			}
			
		}
		
	}
}
