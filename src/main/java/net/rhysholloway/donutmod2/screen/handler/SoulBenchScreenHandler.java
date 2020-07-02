package net.rhysholloway.donutmod2.screen.handler;

import java.util.Optional;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.CraftingResultInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.s2c.play.ScreenHandlerSlotUpdateS2CPacket;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeFinder;
import net.minecraft.screen.AbstractRecipeScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import net.rhysholloway.donutmod2.inventory.SoulBenchInventory;
import net.rhysholloway.donutmod2.item.SoulItem;
import net.rhysholloway.donutmod2.lists.BlockList;
import net.rhysholloway.donutmod2.lists.ScreenHandlerTypeList;
import net.rhysholloway.donutmod2.recipe.SoulBenchRecipe;
import net.rhysholloway.donutmod2.screen.SoulBenchScreen;
import net.rhysholloway.donutmod2.screen.slot.SoulBenchResultSlot;
import net.rhysholloway.donutmod2.screen.slot.SoulSlots.FlightSoulSlot;
import net.rhysholloway.donutmod2.screen.slot.SoulSlots.FrightSoulSlot;
import net.rhysholloway.donutmod2.screen.slot.SoulSlots.MightSoulSlot;
import net.rhysholloway.donutmod2.screen.slot.SoulSlots.SightSoulSlot;

public class SoulBenchScreenHandler extends AbstractRecipeScreenHandler<SoulBenchInventory> {

	private final SoulBenchInventory input;
	private final CraftingResultInventory result;
	private final ScreenHandlerContext context;
	private final PlayerEntity player;

	public SoulBenchScreenHandler(int syncId, PlayerInventory playerInventory) {
		this(syncId, playerInventory, ScreenHandlerContext.EMPTY);
	}

	public SoulBenchScreenHandler(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
		super(ScreenHandlerTypeList.SOUL_BENCH, syncId);
		this.input = new SoulBenchInventory(this, getCraftingWidth(), getCraftingHeight());
		this.result = new CraftingResultInventory();
		this.context = context;
		this.player = playerInventory.player;

		// final crafting slot

		this.addSlot(new SoulBenchResultSlot(player, this.input, this.result, this.getCraftingResultSlotIndex(), 124, 35));

		int m;
		int l;

		// soul slots

		this.addSlot(new FlightSoulSlot(this.input, 0, 7, 8 + 0 * 18));
		this.addSlot(new MightSoulSlot(this.input, 1, 7, 8 + 1 * 18));
		this.addSlot(new FrightSoulSlot(this.input, 2, 7, 8 + 2 * 18));
		this.addSlot(new SightSoulSlot(this.input, 3, 7, 8 + 3 * 18));

		// crafting table

		for (m = 0; m < getCraftingHeight(); ++m) {
			for (l = 0; l < getCraftingWidth(); ++l) {
				this.addSlot(new Slot(this.input, SoulItem.COUNT + l + m * 3, 30 + l * 18, 17 + m * 18));
			}
		}

		// normal inventory

		for (m = 0; m < 3; ++m) {
			for (l = 0; l < 9; ++l) {
				this.addSlot(new Slot(playerInventory, l + m * 9 + 9, 8 + l * 18, 84 + m * 18));
			}
		}

		// hotbar

		for (m = 0; m < 9; ++m) {
			this.addSlot(new Slot(playerInventory, m, 8 + m * 18, 142));
		}

	}

	protected static void updateResult(int syncId, World world, PlayerEntity player, SoulBenchInventory craftingInventory, CraftingResultInventory resultInventory) {
		if (!world.isClient) {
			ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity) player;
			ItemStack itemStack = ItemStack.EMPTY;
			Optional<SoulBenchRecipe> optional = world.getServer().getRecipeManager().getFirstMatch(SoulBenchRecipe.TYPE, craftingInventory, world);
			if (optional.isPresent()) {
				SoulBenchRecipe craftingRecipe = (SoulBenchRecipe) optional.get();
				if (resultInventory.shouldCraftRecipe(world, serverPlayerEntity, craftingRecipe)) {
					itemStack = craftingRecipe.craft(craftingInventory);
				}
			}

			resultInventory.setStack(0, itemStack);
			serverPlayerEntity.networkHandler.sendPacket(new ScreenHandlerSlotUpdateS2CPacket(syncId, 0, itemStack));
		}
	}

	public void onContentChanged(Inventory inventory) {
		this.context.run((world, blockPos) -> {
			updateResult(this.syncId, world, this.player, this.input, this.result);
		});
	}

	public void populateRecipeFinder(RecipeFinder finder) {
		this.input.provideRecipeInputs(finder);
	}

	public void clearCraftingSlots() {
		this.input.clear();
		this.result.clear();
	}

	public boolean matches(Recipe<? super SoulBenchInventory> recipe) {
		return recipe.matches(this.input, this.player.world);
	}

	public void close(PlayerEntity player) {
		super.close(player);
		this.context.run((world, blockPos) -> {
			this.dropInventory(player, world, this.input);
		});
	}

	public boolean canUse(PlayerEntity player) {
		return canUse(this.context, player, BlockList.soul_bench);
	}

	public ItemStack transferSlot(PlayerEntity player, int index) {
		ItemStack itemStack = ItemStack.EMPTY;
		Slot slot = (Slot) this.slots.get(index);
		if (slot != null && slot.hasStack()) {
			ItemStack itemStack2 = slot.getStack();
			itemStack = itemStack2.copy();
			if (index == 0) {
				this.context.run((world, blockPos) -> {
					itemStack2.getItem().onCraft(itemStack2, world, player);
				});
				if (!this.insertItem(itemStack2, getCraftingSlotCount(), getCraftingSlotCount() + 27 + 9, true)) {
					return ItemStack.EMPTY;
				}

				slot.onStackChanged(itemStack2, itemStack);
			} else if (index >= 10 && index < 46) {
				if (!this.insertItem(itemStack2, 1, getCraftingSlotCount(), false)) {
					if (index < 37) {
						if (!this.insertItem(itemStack2, getCraftingSlotCount() + 27, getCraftingSlotCount() + 27 + 9, false)) {
							return ItemStack.EMPTY;
						}
					} else if (!this.insertItem(itemStack2, getCraftingSlotCount(), getCraftingSlotCount() + 27, false)) {
						return ItemStack.EMPTY;
					}
				}
			} else if (!this.insertItem(itemStack2, getCraftingSlotCount(), getCraftingSlotCount() + 27 + 9, false)) {
				return ItemStack.EMPTY;
			}

			if (itemStack2.isEmpty()) {
				slot.setStack(ItemStack.EMPTY);
			} else {
				slot.markDirty();
			}

			if (itemStack2.getCount() == itemStack.getCount()) {
				return ItemStack.EMPTY;
			}

			ItemStack itemStack3 = slot.onTakeItem(player, itemStack2);
			if (index == 0) {
				player.dropItem(itemStack3, false);
			}
		}

		onContentChanged(input);
		
		return itemStack;
	}

	public boolean canInsertIntoSlot(ItemStack stack, Slot slot) {
		return slot.inventory != this.result && super.canInsertIntoSlot(stack, slot);
	}

	public int getCraftingResultSlotIndex() {
		return 0;
	}

	public int getCraftingWidth() {
		return 3;
	}

	public int getCraftingHeight() {
		return 3;
	}

	@Environment(EnvType.CLIENT)
	public int getCraftingSlotCount() {
		return getCraftingWidth() * getCraftingHeight() + SoulItem.COUNT + 1;
	}

	public static class Factory implements ScreenRegistry.Factory<SoulBenchScreenHandler, SoulBenchScreen> {

		@Override
		public SoulBenchScreen create(SoulBenchScreenHandler handler, PlayerInventory inventory, Text title) {
			return new SoulBenchScreen(handler, inventory, title);
		}

	}

}
