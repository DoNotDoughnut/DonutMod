package net.rhysholloway.donutmod1.blocks.mythril_anvil;

import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.ShapelessRecipe;
import net.minecraft.util.DefaultedList;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class MythrilAnvilRecipe extends ShapelessRecipe {

	public MythrilAnvilRecipe(Identifier id, String group, ItemStack output, DefaultedList<Ingredient> input) {
		super(id, group, output, input);
	}

	@Override
	public boolean isIgnoredInRecipeBook() {
		return true;
	}

	@Override
	public ItemStack craft(CraftingInventory inv) {
		return this.getOutput().copy();
	}

	@Override
	public boolean matches(CraftingInventory inventory, World world) {
		if (!(inventory instanceof MythrilAnvilInventory))
			return false;
		return super.matches(inventory, world);
	}

}
