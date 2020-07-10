package net.rhysholloway.donutmod.recipe;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.AbstractCookingRecipe;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.registry.Registry;
import net.rhysholloway.donutmod.DonutMod;
import net.rhysholloway.donutmod.lists.BlockList;

public class NetherSmeltingRecipe extends AbstractCookingRecipe {

	public static final Identifier recipeId = new Identifier(DonutMod.modId, "nether_smelting");
	public static RecipeType<NetherSmeltingRecipe> TYPE = Registry.register(Registry.RECIPE_TYPE, recipeId, new RecipeType<NetherSmeltingRecipe>() {
		public String toString() {
			return recipeId.toString();
		}
	});

	public static RecipeSerializer<NetherSmeltingRecipe> SERIALIZER = new NetherSmeltingRecipe.Serializer<NetherSmeltingRecipe>(new NetherSmeltingRecipe.Serializer.Factory(), 200);

	public NetherSmeltingRecipe(Identifier id, String group, Ingredient input, ItemStack output, float experience, int cookTime) {
		super(TYPE, id, group, input, output, experience, cookTime);
	}

	@Environment(EnvType.CLIENT)
	public ItemStack getRecipeKindIcon() {
		return new ItemStack(BlockList.nether_furnace);
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return SERIALIZER;
	}

	public static class Serializer<T extends AbstractCookingRecipe> implements RecipeSerializer<T> {
		private final int cookingTime;
		private final RecipeFactory<T> recipeFactory;

		public Serializer(RecipeFactory<T> recipeFactory, int cookingTime) {
			this.cookingTime = cookingTime;
			this.recipeFactory = recipeFactory;
		}

		public T read(Identifier identifier, JsonObject jsonObject) {
			String string = JsonHelper.getString(jsonObject, "group", "");
			JsonElement jsonElement = JsonHelper.hasArray(jsonObject, "ingredient") ? JsonHelper.getArray(jsonObject, "ingredient") : JsonHelper.getObject(jsonObject, "ingredient");
			Ingredient ingredient = Ingredient.fromJson((JsonElement) jsonElement);
			String string2 = JsonHelper.getString(jsonObject, "result");
			Identifier identifier2 = new Identifier(string2);
			ItemStack itemStack = new ItemStack((ItemConvertible) Registry.ITEM.getOrEmpty(identifier2).orElseThrow(() -> {
				return new IllegalStateException("Item: " + string2 + " does not exist");
			}));
			float f = JsonHelper.getFloat(jsonObject, "experience", 0.0F);
			int i = JsonHelper.getInt(jsonObject, "cookingtime", this.cookingTime);
			return this.recipeFactory.create(identifier, string, ingredient, itemStack, f, i);
		}

		public T read(Identifier identifier, PacketByteBuf packetByteBuf) {
			String string = packetByteBuf.readString(32767);
			Ingredient ingredient = Ingredient.fromPacket(packetByteBuf);
			ItemStack itemStack = packetByteBuf.readItemStack();
			float f = packetByteBuf.readFloat();
			int i = packetByteBuf.readVarInt();
			return this.recipeFactory.create(identifier, string, ingredient, itemStack, f, i);
		}

		public void write(PacketByteBuf packetByteBuf, T abstractCookingRecipe) {
			packetByteBuf.writeString(abstractCookingRecipe.getGroup());
			abstractCookingRecipe.getPreviewInputs().get(0).write(packetByteBuf);
			packetByteBuf.writeItemStack(abstractCookingRecipe.getOutput());
			packetByteBuf.writeFloat(abstractCookingRecipe.getExperience());
			packetByteBuf.writeVarInt(abstractCookingRecipe.getCookTime());
		}

		public static class Factory implements RecipeFactory<NetherSmeltingRecipe> {

			@Override
			public NetherSmeltingRecipe create(Identifier id, String group, Ingredient input, ItemStack output, float experience, int cookTime) {
				return new NetherSmeltingRecipe(id, group, input, output, experience, cookTime);
			}

		}

		interface RecipeFactory<T> {
			T create(Identifier id, String group, Ingredient input, ItemStack output, float experience, int cookTime);
		}
	}
}
