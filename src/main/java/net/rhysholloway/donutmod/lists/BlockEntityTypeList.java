package net.rhysholloway.donutmod.lists;

import net.minecraft.block.entity.BlockEntityType;
import net.rhysholloway.donutmod.block.entity.AdamantiteFurnaceBlockEntity;
import net.rhysholloway.donutmod.block.entity.NetherFurnaceBlockEntity;

public class BlockEntityTypeList {

	public static BlockEntityType<NetherFurnaceBlockEntity> nether_furnace = BlockEntityType.Builder.create(NetherFurnaceBlockEntity::new, BlockList.nether_furnace).build(null);
	public static BlockEntityType<AdamantiteFurnaceBlockEntity> adamantite_furnace = BlockEntityType.Builder.create(AdamantiteFurnaceBlockEntity::new, BlockList.adamantite_furnace).build(null);
	
}
