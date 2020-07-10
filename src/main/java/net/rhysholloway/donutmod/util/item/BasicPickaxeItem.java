package net.rhysholloway.donutmod.util.item;

import static net.rhysholloway.donutmod.lists.ItemGroupList.tools;
import static net.rhysholloway.donutmod.util.item.DonutItemConstants.pickaxeAD;
import static net.rhysholloway.donutmod.util.item.DonutItemConstants.pickaxeAS;

import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;

public class BasicPickaxeItem extends PickaxeItem {

	public BasicPickaxeItem(ToolMaterial material) {
		super(material, pickaxeAD, pickaxeAS, new Settings().group(tools));
	}

}
