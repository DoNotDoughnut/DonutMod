package net.rhysholloway.donutmod2.lists;

import net.minecraft.util.registry.Registry;
import net.rhysholloway.donutmod2.recipe.NetherSmeltingRecipe;
import net.rhysholloway.donutmod2.recipe.SoulBenchRecipe;

public class RecipeSerializerList {

	public static void register() {
		
		Registry.register(Registry.RECIPE_SERIALIZER, SoulBenchRecipe.recipeId, SoulBenchRecipe.SERIALIZER);
		Registry.register(Registry.RECIPE_SERIALIZER, NetherSmeltingRecipe.recipeId, NetherSmeltingRecipe.SERIALIZER);
		
	}
	
}
