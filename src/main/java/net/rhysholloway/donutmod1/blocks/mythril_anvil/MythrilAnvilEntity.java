package net.rhysholloway.donutmod1.blocks.mythril_anvil;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.DefaultedList;
import net.rhysholloway.donutmod1.lib.ImplementedInventory;

public class MythrilAnvilEntity extends BlockEntity implements ImplementedInventory {

	private DefaultedList<ItemStack> items = DefaultedList.ofSize(3, ItemStack.EMPTY);
	
	public MythrilAnvilEntity() {
		super(MythrilAnvil.MYTHRIL_ANVIL_ENTITY);
	}

	@Override
	public DefaultedList<ItemStack> getItems() {
		return items;
	}
	
	@Override
	public boolean canPlayerUseInv(PlayerEntity player) {
		return pos.isWithinDistance(player.getBlockPos(), 4.5);
	}

	@Override
	public void fromTag(CompoundTag tag) {
		super.fromTag(tag);
		Inventories.fromTag(tag, items);
	}

	@Override
	public CompoundTag toTag(CompoundTag tag) {
		Inventories.toTag(tag, items);
		return super.toTag(tag);
	}

}