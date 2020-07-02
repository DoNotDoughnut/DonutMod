package net.rhysholloway.donutmod2.lists;

import net.minecraft.block.entity.BlockEntityType;
import net.rhysholloway.donutmod2.block.entity.NetherFurnaceBlockEntity;

public class BlockEntityTypeList {

	public static BlockEntityType<NetherFurnaceBlockEntity> nether_furnace = BlockEntityType.Builder.create(NetherFurnaceBlockEntity::new, BlockList.nether_furnace).build(null);
	
}
