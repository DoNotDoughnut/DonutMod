package net.rhysholloway.donutmod2.screen.handler;

import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.screen.AbstractFurnaceScreenHandler;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.text.Text;
import net.rhysholloway.donutmod2.lists.ScreenHandlerTypeList;
import net.rhysholloway.donutmod2.recipe.NetherSmeltingRecipe;
import net.rhysholloway.donutmod2.screen.NetherFurnaceScreen;

public class NetherFurnaceScreenHandler extends AbstractFurnaceScreenHandler {

	public NetherFurnaceScreenHandler(int syncId, PlayerInventory playerInventory) {
		super(ScreenHandlerTypeList.NETHER_FURNACE, NetherSmeltingRecipe.TYPE, syncId, playerInventory);
	}

	public NetherFurnaceScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate propertyDelegate) {
		super(ScreenHandlerTypeList.NETHER_FURNACE, NetherSmeltingRecipe.TYPE, syncId, playerInventory, inventory, propertyDelegate);
	}

	public static class Factory implements ScreenRegistry.Factory<NetherFurnaceScreenHandler, NetherFurnaceScreen> {

		@Override
		public NetherFurnaceScreen create(NetherFurnaceScreenHandler handler, PlayerInventory inventory, Text title) {
			return new NetherFurnaceScreen(handler, inventory, title);
		}
		
	}
	
}
