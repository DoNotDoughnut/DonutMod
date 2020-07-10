package net.rhysholloway.donutmod.inventory;

import net.minecraft.inventory.CraftingInventory;
import net.minecraft.screen.ScreenHandler;
import net.rhysholloway.donutmod.item.SoulItem;

public class SoulBenchInventory extends CraftingInventory {

	public SoulBenchInventory(ScreenHandler handler, int width, int height) {
		super(handler, 1, width * height + SoulItem.COUNT);
	}
	
	public int getHeight() {
		return 3;
	}

	public int getWidth() {
		return 3;
	}
	
}
