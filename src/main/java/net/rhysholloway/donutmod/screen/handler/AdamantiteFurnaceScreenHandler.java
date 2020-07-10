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
import net.rhysholloway.donutmod.recipe.AdamantiteSmeltingRecipe;
import net.rhysholloway.donutmod.recipe.NetherSmeltingRecipe;
import net.rhysholloway.donutmod.screen.AdamantiteFurnaceScreen;

public class AdamantiteFurnaceScreenHandler extends AbstractFurnaceScreenHandler {

	public AdamantiteFurnaceScreenHandler(int syncId, PlayerInventory playerInventory) {
		super(ScreenHandlerTypeList.ADAMANTINE_FURNACE, AdamantiteSmeltingRecipe.TYPE, syncId, playerInventory);
	}

	public AdamantiteFurnaceScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate propertyDelegate) {
		super(ScreenHandlerTypeList.ADAMANTINE_FURNACE, AdamantiteSmeltingRecipe.TYPE, syncId, playerInventory, inventory, propertyDelegate);
	}

	protected boolean isSmeltable(ItemStack itemStack) {
		Optional<? extends AbstractCookingRecipe> recipe;
		recipe = this.world.getRecipeManager().getFirstMatch(RecipeType.SMELTING, new SimpleInventory(new ItemStack[] { itemStack }), this.world);
		recipe = this.world.getRecipeManager().getFirstMatch(NetherSmeltingRecipe.TYPE, new SimpleInventory(new ItemStack[] { itemStack }), this.world);
		recipe = this.world.getRecipeManager().getFirstMatch(AdamantiteSmeltingRecipe.TYPE, new SimpleInventory(new ItemStack[] { itemStack }), this.world);
		return recipe.isPresent();
	}

	public static class Factory implements ScreenRegistry.Factory<AdamantiteFurnaceScreenHandler, AdamantiteFurnaceScreen> {

		@Override
		public AdamantiteFurnaceScreen create(AdamantiteFurnaceScreenHandler handler, PlayerInventory inventory, Text title) {
			return new AdamantiteFurnaceScreen(handler, inventory, title);
		}

	}

}
