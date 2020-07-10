package net.rhysholloway.donutmod.block.entity;

import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.registry.Registry;
import net.rhysholloway.donutmod.DonutMod;
import net.rhysholloway.donutmod.lists.BlockEntityTypeList;
import net.rhysholloway.donutmod.lists.BlockList;
import net.rhysholloway.donutmod.recipe.AdamantiteSmeltingRecipe;
import net.rhysholloway.donutmod.screen.handler.AdamantiteFurnaceScreenHandler;

public class AdamantiteFurnaceBlockEntity extends AbstractFurnaceBlockEntity {

	public AdamantiteFurnaceBlockEntity() {
		super(BlockEntityTypeList.adamantite_furnace, AdamantiteSmeltingRecipe.TYPE);
	}

	@Override
	protected Text getContainerName() {
		return new TranslatableText("container." + DonutMod.modId + "." + Registry.BLOCK.getId(BlockList.adamantite_furnace).getPath());
	}

	protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
		return new AdamantiteFurnaceScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
	}

}
