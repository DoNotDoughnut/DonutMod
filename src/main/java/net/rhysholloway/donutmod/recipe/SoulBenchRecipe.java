package net.rhysholloway.donutmod.recipe;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.ShapedRecipe;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.rhysholloway.donutmod.DonutMod;
import net.rhysholloway.donutmod.item.SoulItem;

public class SoulBenchRecipe extends ShapedRecipe {

	public static final Identifier recipeId = new Identifier(DonutMod.modId, "soul_crafting");
	public static final RecipeType<SoulBenchRecipe> TYPE = Registry.register(Registry.RECIPE_TYPE, recipeId, new RecipeType<SoulBenchRecipe>() {
		public String toString() {
			return recipeId.toString();
		}
	});
	
	public static final RecipeSerializer<SoulBenchRecipe> SERIALIZER = new Serializer();

	protected final DefaultedList<Integer> souls;

	public SoulBenchRecipe(Identifier id, int width, int height, String group, DefaultedList<Integer> souls, DefaultedList<Ingredient> ingredients, ItemStack output) {
		super(id, group, width, height, ingredients, output);
		this.souls = souls;
	}

	public DefaultedList<ItemStack> getRemainingStacks(CraftingInventory inventory) {
		DefaultedList<ItemStack> defaultedList = DefaultedList.ofSize(inventory.size(), ItemStack.EMPTY);

		for (int i = 0; i < defaultedList.size(); ++i) {
			Item item = inventory.getStack(i).getItem();
			if (i < SoulItem.COUNT) {
				if (souls.get(i) != 0) {
					inventory.removeStack(i, souls.get(i) - 1);
				} else {
					inventory.getStack(i).increment(1);
				}
			}
			if (item.hasRecipeRemainder()) {
				defaultedList.set(i, new ItemStack(item.getRecipeRemainder()));
			}
		}

		return defaultedList;
	}

	public boolean matches(CraftingInventory craftingInventory, World world) {
		for (int i = 0; i <= craftingInventory.getWidth() - this.getWidth(); ++i) {
			for (int j = 0; j <= craftingInventory.getHeight() - this.getHeight(); ++j) {
				if (this.matchesSmall(craftingInventory, i, j, true)) {
					return true;
				}

				if (this.matchesSmall(craftingInventory, i, j, false)) {
					return true;
				}
			}
		}

		return false;
	}

	private boolean matchesSmall(CraftingInventory inv, int offsetX, int offsetY, boolean bl) {
		for (int i = 0; i < inv.getWidth(); ++i) {
			for (int j = 0; j < inv.getHeight(); ++j) {
				int k = i - offsetX;
				int l = j - offsetY;
				Ingredient ingredient = Ingredient.EMPTY;
				if (k >= 0 && l >= 0 && k < this.getWidth() && l < this.getHeight()) {
					if (bl) {
						ingredient = (Ingredient) this.getPreviewInputs().get(this.getWidth() - k - 1 + l * this.getWidth());
					} else {
						ingredient = (Ingredient) this.getPreviewInputs().get(k + l * this.getWidth());
					}
				}

				if (!ingredient.test(inv.getStack(SoulItem.COUNT + i + j * inv.getWidth()))) {
					return false;
				}
			}
		}

		return (inv.getStack(0).getCount() >= souls.get(0)) && (inv.getStack(1).getCount() >= souls.get(1)) && (inv.getStack(2).getCount() >= souls.get(2)) && (inv.getStack(3).getCount() >= souls.get(3));
	}

	@Override
	public RecipeSerializer<SoulBenchRecipe> getSerializer() {
		return SERIALIZER;
	}

	@Override
	public RecipeType<SoulBenchRecipe> getType() {
		return TYPE;
	}

	public static class Serializer implements RecipeSerializer<SoulBenchRecipe> {

		public SoulBenchRecipe read(Identifier identifier, JsonObject jsonObject) {
			String string = JsonHelper.getString(jsonObject, "group", "");

			Map<String, Ingredient> map = SoulBenchRecipe.getComponents(JsonHelper.getObject(jsonObject, "key"));

			String[] strings = SoulBenchRecipe.combinePattern(SoulBenchRecipe.getPattern(JsonHelper.getArray(jsonObject, "pattern")));

			int width = strings[0].length();
			int height = strings.length;

			DefaultedList<Integer> souls = SoulBenchRecipe.getSouls(JsonHelper.getObject(jsonObject, "souls"));

			DefaultedList<Ingredient> inputs = SoulBenchRecipe.getIngredients(strings, map, width, height);

			ItemStack itemStack = ShapedRecipe.getItemStack(JsonHelper.getObject(jsonObject, "result"));

			return new SoulBenchRecipe(identifier, width, height, string, souls, inputs, itemStack);
		}

		public SoulBenchRecipe read(Identifier identifier, PacketByteBuf packetByteBuf) {

			int width = packetByteBuf.readVarInt();
			int height = packetByteBuf.readVarInt();
			String string = packetByteBuf.readString(32767); // group

			DefaultedList<Integer> souls = DefaultedList.ofSize(SoulItem.COUNT, 0);

			for (int i = 0; i < SoulItem.COUNT; i++) {
				souls.set(i, packetByteBuf.readVarInt());
			}

			DefaultedList<Ingredient> defaultedList = DefaultedList.ofSize(width * height, Ingredient.EMPTY);

			for (int k = 0; k < defaultedList.size(); ++k) {
				defaultedList.set(k, Ingredient.fromPacket(packetByteBuf));
			}

			ItemStack itemStack = packetByteBuf.readItemStack();
			return new SoulBenchRecipe(identifier, width, height, string, souls, defaultedList, itemStack);
		}

		public void write(PacketByteBuf packetByteBuf, SoulBenchRecipe shapedRecipe) {
			packetByteBuf.writeVarInt(shapedRecipe.getWidth());
			packetByteBuf.writeVarInt(shapedRecipe.getHeight());
			packetByteBuf.writeString(shapedRecipe.getGroup());

			for (int i = 0; i < shapedRecipe.souls.size(); i++) {
				packetByteBuf.writeVarInt(shapedRecipe.souls.get(i));
			}

			Iterator<Ingredient> var3 = shapedRecipe.getPreviewInputs().iterator();

			while (var3.hasNext()) {
				Ingredient ingredient = (Ingredient) var3.next();
				ingredient.write(packetByteBuf);
			}

			packetByteBuf.writeItemStack(shapedRecipe.getOutput());
		}

	}

	private static DefaultedList<Ingredient> getIngredients(String[] pattern, Map<String, Ingredient> key, int width, int height) {
		DefaultedList<Ingredient> defaultedList = DefaultedList.ofSize(width * height, Ingredient.EMPTY);
		Set<String> set = Sets.newHashSet(key.keySet());
		set.remove(" ");

		for (int i = 0; i < pattern.length; ++i) {
			for (int j = 0; j < pattern[i].length(); ++j) {
				String string = pattern[i].substring(j, j + 1);
				Ingredient ingredient = (Ingredient) key.get(string);
				if (ingredient == null) {
					throw new JsonSyntaxException("Pattern references symbol '" + string + "' but it's not defined in the key");
				}

				set.remove(string);
				defaultedList.set(j + width * i, ingredient);
			}
		}

		if (!set.isEmpty()) {
			throw new JsonSyntaxException("Key defines symbols that aren't used in pattern: " + set);
		} else {
			return defaultedList;
		}
	}

	private static DefaultedList<Integer> getSouls(JsonObject json) {
		DefaultedList<Integer> souls = DefaultedList.ofSize(SoulItem.COUNT, 0);

		for (int i = 0; i < SoulItem.souls.length; i++) {

			if (json.has(SoulItem.souls[i])) {
				souls.set(i, json.get(SoulItem.souls[i]).getAsInt());
			} else {
				souls.set(i, 0);
			}

		}

		return souls;
	}

	private static String[] combinePattern(String... lines) {
		int i = Integer.MAX_VALUE;
		int j = 0;
		int k = 0;
		int l = 0;

		for (int m = 0; m < lines.length; ++m) {
			String string = lines[m];
			i = Math.min(i, findNextIngredient(string));
			int n = findNextIngredientReverse(string);
			j = Math.max(j, n);
			if (n < 0) {
				if (k == m) {
					++k;
				}

				++l;
			} else {
				l = 0;
			}
		}

		if (lines.length == l) {
			return new String[0];
		} else {
			String[] strings = new String[lines.length - l - k];

			for (int o = 0; o < strings.length; ++o) {
				strings[o] = lines[o + k].substring(i, j + 1);
			}

			return strings;
		}
	}

	private static int findNextIngredient(String pattern) {
		int i;
		for (i = 0; i < pattern.length() && pattern.charAt(i) == ' '; ++i) {
		}

		return i;
	}

	private static int findNextIngredientReverse(String pattern) {
		int i;
		for (i = pattern.length() - 1; i >= 0 && pattern.charAt(i) == ' '; --i) {
		}

		return i;
	}

	private static String[] getPattern(JsonArray json) {
		String[] strings = new String[json.size()];
		if (strings.length > 3) {
			throw new JsonSyntaxException("Invalid pattern: too many rows, 3 is maximum");
		} else if (strings.length == 0) {
			throw new JsonSyntaxException("Invalid pattern: empty pattern not allowed");
		} else {
			for (int i = 0; i < strings.length; ++i) {
				String string = JsonHelper.asString(json.get(i), "pattern[" + i + "]");
				if (string.length() > 3) {
					throw new JsonSyntaxException("Invalid pattern: too many columns, 3 is maximum");
				}

				if (i > 0 && strings[0].length() != string.length()) {
					throw new JsonSyntaxException("Invalid pattern: each row must be the same width");
				}

				strings[i] = string;
			}

			return strings;
		}
	}

	private static Map<String, Ingredient> getComponents(JsonObject json) {
		Map<String, Ingredient> map = Maps.newHashMap();
		Iterator<Entry<String, JsonElement>> var2 = json.entrySet().iterator();

		while (var2.hasNext()) {
			Entry<String, JsonElement> entry = (Entry<String, JsonElement>) var2.next();
			if (((String) entry.getKey()).length() != 1) {
				throw new JsonSyntaxException("Invalid key entry: '" + (String) entry.getKey() + "' is an invalid symbol (must be 1 character only).");
			}

			if (" ".equals(entry.getKey())) {
				throw new JsonSyntaxException("Invalid key entry: ' ' is a reserved symbol.");
			}

			map.put(entry.getKey(), Ingredient.fromJson((JsonElement) entry.getValue()));
		}

		map.put(" ", Ingredient.EMPTY);
		return map;
	}

}
