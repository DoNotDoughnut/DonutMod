package net.rhysholloway.donutmod1.blocks.mythril_anvil;

import java.util.Optional;

import io.github.cottonmc.cotton.gui.CottonCraftingController;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WItemSlot;
import io.github.cottonmc.cotton.gui.widget.WLabel;
import io.github.cottonmc.cotton.gui.widget.WSprite;
import net.minecraft.client.network.packet.ContainerSlotUpdateS2CPacket;
import net.minecraft.container.BlockContext;
import net.minecraft.container.Slot;
import net.minecraft.container.SlotActionType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.CraftingResultInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.CraftingRecipe;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeFinder;
import net.minecraft.recipe.RecipeType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.rhysholloway.donutmod1.DonutMod;

public class MythrilAnvilContainer extends CottonCraftingController {

	private MythrilAnvilInventory craftingInv;
	private CraftingResultInventory resultInv;
	private PlayerEntity player;
	private BlockContext context;

	private final int resultSlot;
	private int firstPlayerInvSlot;
	private int firstPlayerHotbarSlot;
	private int lastSlot;

	public MythrilAnvilContainer(int syncId, PlayerEntity player, BlockContext context) {
		super(RecipeType.CRAFTING, syncId, player.inventory);
		craftingInv = new MythrilAnvilInventory(this, 2, 1);
		this.resultInv = new CraftingResultInventory();
		this.player = player;
		this.context = context;

		resultSlot = craftingInv.getInvSize();
		firstPlayerInvSlot = getCraftingWidth() * getCraftingHeight() + 1;
		firstPlayerHotbarSlot = firstPlayerInvSlot + 27;
		lastSlot = firstPlayerHotbarSlot + 9;

		buildGUI();
	}

	private void buildGUI() {
		WGridPanel root = new WGridPanel();
		setRootPanel(root);

		root.setSize(144, 100);

		WLabel label = new WLabel(new TranslatableText("gui." + DonutMod.modId + ".mythril_anvil.label"), 0x007700);
		root.add(label, 0, 0, 3, 1);

		WItemSlot craftingSlot1 = WItemSlot.of(craftingInv, 0);
		root.add(craftingSlot1, 2, 1);

		WItemSlot craftingSlot2 = WItemSlot.of(craftingInv, 1);
		root.add(craftingSlot2, 3, 1);

		WSprite crafting_sprite = new WSprite(new Identifier(DonutMod.modId + ":textures/gui/mythril_anvil_crafting_sprite.png"));
		root.add(crafting_sprite, 4, 1, 2, 1);

		WItemSlot outputSlot = WItemSlot.of(resultInv, resultSlot);
		root.add(outputSlot, 6, 1);

		root.add(this.createPlayerInventoryPanel(), 0, 3);

		root.validate(this);
	}

	public MythrilAnvilInventory getCraftInv() {
		return craftingInv;
	}

	public PlayerEntity getPlayer() {
		return player;
	}

	@Override
	public void close(PlayerEntity player) {
		super.close(player);
		this.context.run((world, pos) -> {
			dropInventory(player, world, craftingInv);
		});
	}

	@Override
	public int getCraftingResultSlotIndex() {
		return resultSlot;
	}

	@Override
	public int getCraftingWidth() {
		return 2;
	}

	@Override
	public int getCraftingHeight() {
		return 1;
	}

	@Override
	public boolean matches(Recipe<? super Inventory> recipe) {
		return recipe.matches(craftingInv, player.world);
	}

	@Override
	public void onContentChanged(Inventory inventory) {
		this.context.run((world, blockPos) -> {
			if (!world.isClient) {
				ServerPlayerEntity serverPlayer = (ServerPlayerEntity) player;
				ItemStack stack = ItemStack.EMPTY;
				Optional<CraftingRecipe> opt = world.getServer().getRecipeManager().getFirstMatch(RecipeType.CRAFTING, craftingInv, world);
				if (opt.isPresent()) {
					CraftingRecipe craftingRecipe = opt.get();
					if (resultInv.shouldCraftRecipe(world, serverPlayer, craftingRecipe)) {
						stack = craftingRecipe.craft(craftingInv);
					}
				}
				resultInv.setInvStack(0, stack);
				serverPlayer.networkHandler.sendPacket(new ContainerSlotUpdateS2CPacket(syncId, 0, stack));
			}
		});
	}

	@Override
	public ItemStack onSlotClick(int slotNumber, int button, SlotActionType action, PlayerEntity player) {
		if (action == SlotActionType.QUICK_MOVE) {
			return transferSlot(player, slotNumber);
		} else {
			return super.onSlotClick(slotNumber, button, action, player);
		}
	}

	@Override
	public void populateRecipeFinder(RecipeFinder finder) {
		this.craftingInv.provideRecipeInputs(finder);
	}

	@Override
	public void clearCraftingSlots() {
		this.craftingInv.clear();
		this.resultInv.clear();
	}

	@Override
	public boolean canInsertIntoSlot(ItemStack stack, Slot slot) {
		return slot.inventory != this.resultInv && super.canInsertIntoSlot(stack, slot);
	}

	@Override
	public ItemStack transferSlot(PlayerEntity player, int slotIndex) {
		ItemStack stack = ItemStack.EMPTY;
		Slot slot = this.slots.get(slotIndex);
		if (slot != null && slot.hasStack()) {
			ItemStack toTake = slot.getStack();
			stack = toTake.copy();
			if (slotIndex == resultSlot) {
				this.context.run((world, pos) -> {
					toTake.getItem().onCraft(toTake, world, player);
				});
				if (!this.insertItem(toTake, firstPlayerInvSlot, lastSlot, true)) {
					return ItemStack.EMPTY;
				}

				slot.onStackChanged(toTake, stack);
			} else if (slotIndex >= firstPlayerInvSlot && slotIndex < firstPlayerHotbarSlot) {
				if (!this.insertItem(toTake, firstPlayerHotbarSlot, lastSlot, false)) {
					return ItemStack.EMPTY;
				}
			} else if (slotIndex >= firstPlayerHotbarSlot && slotIndex < lastSlot) {
				if (!this.insertItem(toTake, firstPlayerInvSlot, firstPlayerHotbarSlot, false)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.insertItem(toTake, firstPlayerInvSlot, lastSlot, false)) {
				return ItemStack.EMPTY;
			}

			if (toTake.isEmpty()) {
				slot.setStack(ItemStack.EMPTY);
			} else {
				slot.markDirty();
			}

			if (toTake.getCount() == stack.getCount()) {
				return ItemStack.EMPTY;
			}

			ItemStack takenStack = slot.onTakeItem(player, toTake);
			if (slotIndex == resultSlot) {
				player.dropItem(takenStack, false);
			}
		}

		return stack;
	}

}