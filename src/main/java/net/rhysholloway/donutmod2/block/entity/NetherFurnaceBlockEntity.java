package net.rhysholloway.donutmod2.block.entity;

import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.registry.Registry;
import net.rhysholloway.donutmod2.DonutMod;
import net.rhysholloway.donutmod2.lists.BlockEntityTypeList;
import net.rhysholloway.donutmod2.lists.BlockList;
import net.rhysholloway.donutmod2.recipe.NetherSmeltingRecipe;
import net.rhysholloway.donutmod2.screen.handler.NetherFurnaceScreenHandler;

public class NetherFurnaceBlockEntity extends AbstractFurnaceBlockEntity {

	public NetherFurnaceBlockEntity() {
		super(BlockEntityTypeList.nether_furnace, NetherSmeltingRecipe.TYPE);
	}

	@Override
	protected Text getContainerName() {
		return new TranslatableText("container." + DonutMod.modId + "." + Registry.BLOCK.getId(BlockList.nether_furnace).getPath());
	}

	protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
		return new NetherFurnaceScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
	}

}
