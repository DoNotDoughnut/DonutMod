package net.rhysholloway.donutmod.util.item;

import static net.rhysholloway.donutmod.lists.ItemGroupList.tools;
import static net.rhysholloway.donutmod.util.item.DonutItemConstants.hoeAD;
import static net.rhysholloway.donutmod.util.item.DonutItemConstants.hoeAS;

import net.minecraft.item.HoeItem;
import net.minecraft.item.ToolMaterial;

public class BasicHoeItem extends HoeItem {

	public BasicHoeItem(ToolMaterial material) {
		super(material, hoeAD, hoeAS, new Settings().group(tools));
	}

}
