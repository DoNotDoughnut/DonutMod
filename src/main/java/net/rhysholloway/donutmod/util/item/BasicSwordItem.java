package net.rhysholloway.donutmod.util.item;

import static net.rhysholloway.donutmod.lists.ItemGroupList.melee_weapons;
import static net.rhysholloway.donutmod.util.item.DonutItemConstants.swordAD;
import static net.rhysholloway.donutmod.util.item.DonutItemConstants.swordAS;

import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;

public class BasicSwordItem extends SwordItem {

	public BasicSwordItem(ToolMaterial toolMaterial) {
		super(toolMaterial, swordAD, swordAS, new Settings().group(melee_weapons));
	}

}
