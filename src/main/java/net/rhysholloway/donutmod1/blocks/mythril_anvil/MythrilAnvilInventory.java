package net.rhysholloway.donutmod1.blocks.mythril_anvil;

import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeFinder;
import net.minecraft.util.DefaultedList;

public class MythrilAnvilInventory extends CraftingInventory {
	
	private DefaultedList<ItemStack> items;
	private MythrilAnvilContainer container;

	public MythrilAnvilInventory(MythrilAnvilContainer container, int width, int height) {
		super(container, width, height);
		this.items = DefaultedList.ofSize((width * height), ItemStack.EMPTY);
		this.container = container;
	}
	
	@Override
	public int getInvSize() {
		return items.size();
	}

	@Override
	public boolean isInvEmpty() {
		for (ItemStack stack : items) {
			if (!stack.isEmpty()) return false;
		}
		return true;
	}

	@Override
	public ItemStack getInvStack(int slot) {
		return items.get(slot);
	}

	@Override
	public ItemStack removeInvStack(int slot) {
		return Inventories.removeStack(items, slot);
	}

	@Override
	public ItemStack takeInvStack(int slot, int amount) {
		ItemStack stack = Inventories.splitStack(this.items, slot, amount);
		if (!stack.isEmpty()) {
			this.container.onContentChanged(this);
		}

		return stack;
	}

	@Override
	public void setInvStack(int slot, ItemStack stack) {
		this.items.set(slot, stack);
		this.container.onContentChanged(this);
	}

	@Override
	public void clear() {
		this.items.clear();
	}

	@Override
	public void provideRecipeInputs(RecipeFinder finder) {
		for (ItemStack stack : items) {
			finder.addNormalItem(stack);
		}
	}

}
