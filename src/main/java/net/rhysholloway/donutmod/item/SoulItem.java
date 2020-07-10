package net.rhysholloway.donutmod.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SoulItem extends Item {

	public static final String[] souls = { "flight", "might", "fright", "sight" };
	
	public static final int COUNT = 4;
	
	public SoulItem(Settings settings) {
		super(settings);
	}

	@Override
	public boolean hasGlint(ItemStack stack) {
		return false;
	}
	
}
