package net.rhysholloway.donutmod.util.item;

import static net.rhysholloway.donutmod.lists.ItemGroupList.tools;
import static net.rhysholloway.donutmod.util.item.DonutItemConstants.axeAD;
import static net.rhysholloway.donutmod.util.item.DonutItemConstants.axeAS;

import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;

public class BasicAxeItem extends AxeItem {

	public BasicAxeItem(ToolMaterial material) {
		super(material, axeAD, axeAS, new Item.Settings().group(tools));
	}

}
