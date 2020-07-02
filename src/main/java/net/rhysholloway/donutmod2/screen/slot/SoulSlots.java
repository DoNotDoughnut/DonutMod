package net.rhysholloway.donutmod2.screen.slot;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import net.rhysholloway.donutmod2.lists.ItemList;

public class SoulSlots {

	public static class FlightSoulSlot extends Slot {

		public FlightSoulSlot(Inventory inventory, int index, int x, int y) {
			super(inventory, index, x, y);
		}
		
		@Override
		public boolean canInsert(ItemStack stack) {
			return (stack.getItem()).equals(ItemList.soul_flight);
		}

	}

	public static class MightSoulSlot extends Slot {

		public MightSoulSlot(Inventory inventory, int index, int x, int y) {
			super(inventory, index, x, y);
		}
		
		@Override
		public boolean canInsert(ItemStack stack) {
			return (stack.getItem()).equals(ItemList.soul_might);
		}

	}

	public static class FrightSoulSlot extends Slot {

		public FrightSoulSlot(Inventory inventory, int index, int x, int y) {
			super(inventory, index, x, y);
		}
		
		@Override
		public boolean canInsert(ItemStack stack) {
			return (stack.getItem()).equals(ItemList.soul_fright);
		}

	}

	public static class SightSoulSlot extends Slot {

		public SightSoulSlot(Inventory inventory, int index, int x, int y) {
			super(inventory, index, x, y);
		}
		
		@Override
		public boolean canInsert(ItemStack stack) {
			return stack.getItem().equals(ItemList.soul_sight);
		}

	}

}
