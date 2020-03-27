package net.rhysholloway.donutmod1.blocks.mythril_anvil;

import io.github.cottonmc.cotton.gui.CottonCraftingController;
import io.github.cottonmc.cotton.gui.client.CottonInventoryScreen;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WItemSlot;
import io.github.cottonmc.cotton.gui.widget.WLabel;
import io.github.cottonmc.cotton.gui.widget.WSprite;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.container.BlockContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.recipe.RecipeType;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.rhysholloway.donutmod1.DonutMod;

public class MythrilAnvilController extends CottonCraftingController {

	protected MythrilAnvilController(int syncId, PlayerInventory playerInventory, BlockContext context) {
		super(RecipeType.CRAFTING, syncId, playerInventory, getBlockInventory(context), getBlockPropertyDelegate(context)); // Since we didn't create a ContainerType, we will place null here.

		WGridPanel root = new WGridPanel();
		setRootPanel(root);

		root.setSize(144, 100);
		
		WLabel label = new WLabel(new TranslatableText("gui."+DonutMod.modId+".mythril_anvil.label"), 0x007700);
        root.add(label, 0, 0, 3, 1);
		
        WItemSlot craftingSlot1 = WItemSlot.of(blockInventory, 0);
		root.add(craftingSlot1, 2, 1);
		
		WItemSlot craftingSlot2 = WItemSlot.of(blockInventory, 1);
		root.add(craftingSlot2, 3, 1);
		
		WSprite crafting_sprite = new WSprite(new Identifier(DonutMod.modId+":textures/gui/mythril_anvil_crafting_sprite.png"));
        root.add(crafting_sprite, 4, 1, 2, 1);
		
		WItemSlot outputSlot = WItemSlot.of(blockInventory, 2).setModifiable(false);
		root.add(outputSlot, 6, 1);

		root.add(this.createPlayerInventoryPanel(), 0, 3);
		
		root.validate(this);
	}
	
	@Override
	public int getCraftingResultSlotIndex() {
		return 2;
	}

	@Override
	public int getCraftingWidth() {
		return 1;
	}

	@Override
	public int getCraftingHeight() {
		return 2;
	}

	@Override
	@Environment(EnvType.CLIENT)
	public int getCraftingSlotCount() {
		return 2;
	}
}

class MythrilAnvilScreen extends CottonInventoryScreen<MythrilAnvilController> {

	public MythrilAnvilScreen(MythrilAnvilController container, PlayerEntity player) {
		super(container, player);
	}
}