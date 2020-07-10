package net.rhysholloway.donutmod.util.item;

import static net.rhysholloway.donutmod.lists.ItemGroupList.tools;
import static net.rhysholloway.donutmod.util.item.DonutItemConstants.shovelAD;
import static net.rhysholloway.donutmod.util.item.DonutItemConstants.shovelAS;

import net.minecraft.item.ShovelItem;
import net.minecraft.item.ToolMaterial;

public class BasicShovelItem extends ShovelItem {

	public BasicShovelItem(ToolMaterial material) {
		super(material, shovelAD, shovelAS, new Settings().group(tools));
	}

}
