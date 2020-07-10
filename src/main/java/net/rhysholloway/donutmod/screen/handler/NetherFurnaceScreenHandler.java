package net.rhysholloway.donutmod.screen.handler;

import java.util.Optional;

import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.AbstractCookingRecipe;
import net.minecraft.recipe.RecipeType;
import net.minecraft.screen.AbstractFurnaceScreenHandler;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.text.Text;
import net.rhysholloway.donutmod.lists.ScreenHandlerTypeList;
import net.rhysholloway.donutmod.recipe.NetherSmeltingRecipe;
import net.rhysholloway.donutmod.screen.NetherFurnaceScreen;

public class NetherFurnaceScreenHandler extends AbstractFurnaceScreenHandler {

	public NetherFurnaceScreenHandler(int syncId, PlayerInventory playerInventory) {
		super(ScreenHandlerTypeList.NETHER_FURNACE, NetherSmeltingRecipe.TYPE, syncId, playerInventory);
	}

	public NetherFurnaceScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate propertyDelegate) {
		super(ScreenHandlerTypeList.NETHER_FURNACE, NetherSmeltingRecipe.TYPE, syncId, playerInventory, inventory, propertyDelegate);
	}
	
	protected boolean isSmeltable(ItemStack itemStack) {
		Optional<? extends AbstractCookingRecipe> recipe;
		recipe = this.world.getRecipeManager().getFirstMatch(RecipeType.SMELTING, new SimpleInventory(new ItemStack[] { itemStack }), this.world);
		recipe = this.world.getRecipeManager().getFirstMatch(NetherSmeltingRecipe.TYPE, new SimpleInventory(new ItemStack[] { itemStack }), this.world);
		return recipe.isPresent();
	}

	public static class Factory implements ScreenRegistry.Factory<NetherFurnaceScreenHandler, NetherFurnaceScreen> {

		@Override
		public NetherFurnaceScreen create(NetherFurnaceScreenHandler handler, PlayerInventory inventory, Text title) {
			return new NetherFurnaceScreen(handler, inventory, title);
		}
		
	}
	
}
