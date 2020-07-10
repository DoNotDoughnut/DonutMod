package net.rhysholloway.donutmod.util.block;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;

public class GenOreSettings extends OreSettings {

	private final Biome.Category[] biomeList;
	private final boolean blacklist;
	
	public GenOreSettings(int miningLevel, int minXP, int maxXP, int veinSize, int veinsPerChunk, int bottomOffset, int minHeight, int maxHeight, boolean blacklist, Category[] biomeList) {
		super(miningLevel, minXP, maxXP, veinSize, veinsPerChunk, bottomOffset, minHeight, maxHeight, blacklist, biomeList);
		this.blacklist = blacklist;
		this.biomeList = biomeList;
	}

	public GenOreSettings(int miningLevel, int veinSize, int veinsPerChunk, int bottomOffset, int minHeight, int maxHeight, boolean blacklist, Category... biomeList) {
		this(miningLevel, 0, 0, veinSize, veinsPerChunk, bottomOffset, minHeight, maxHeight, blacklist, biomeList);
	}
	
	public boolean isBlacklist() {
		return blacklist;
	}
	
	public Category[] getBiomeList() {
		return biomeList;
	}

}
