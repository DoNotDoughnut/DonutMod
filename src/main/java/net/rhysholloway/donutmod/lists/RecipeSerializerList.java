package net.rhysholloway.donutmod.lists;

import net.minecraft.util.registry.Registry;
import net.rhysholloway.donutmod.recipe.AdamantiteSmeltingRecipe;
import net.rhysholloway.donutmod.recipe.NetherSmeltingRecipe;
import net.rhysholloway.donutmod.recipe.SoulBenchRecipe;

public class RecipeSerializerList {

	public static void register() {
		
		Registry.register(Registry.RECIPE_SERIALIZER, SoulBenchRecipe.recipeId, SoulBenchRecipe.SERIALIZER);
		Registry.register(Registry.RECIPE_SERIALIZER, NetherSmeltingRecipe.recipeId, NetherSmeltingRecipe.SERIALIZER);
		Registry.register(Registry.RECIPE_SERIALIZER, AdamantiteSmeltingRecipe.recipeId, AdamantiteSmeltingRecipe.SERIALIZER);
		
	}
	
}
