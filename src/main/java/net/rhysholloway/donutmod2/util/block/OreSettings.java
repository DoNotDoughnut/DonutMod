package net.rhysholloway.donutmod2.util.block;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;

public class OreSettings {
	
	private final Biome.Category[] biomeList;
	private final boolean blacklist;
	private final int miningLevel, minXP, maxXP, veinSize, veinsPerChunk, bottomOffset, minHeight, maxHeight;
	
	public OreSettings(int miningLevel, int minXP, int maxXP, int veinSize, int veinsPerChunk, int bottomOffset, int minHeight, int maxHeight, boolean blacklist, Biome.Category... biomeList) {
		this.miningLevel = miningLevel;
		this.minXP = minXP;
		this.maxXP = maxXP;
		this.veinSize = veinSize;
		this.veinsPerChunk = veinsPerChunk;
		this.bottomOffset = bottomOffset;
		this.minHeight = minHeight;
		this.maxHeight = maxHeight;
		this.blacklist = blacklist;
		this.biomeList = biomeList;
	}
	
	public OreSettings(int miningLevel, int veinSize, int veinsPerChunk, int bottomOffset, int minHeight, int maxHeight, boolean blacklist, Biome.Category... biomeList) {
		this(miningLevel, 0, 0, veinSize, veinsPerChunk, bottomOffset, minHeight, maxHeight, blacklist, biomeList);
	}

	public int getMiningLevel() {
		return miningLevel;
	}

	public int getMinXP() {
		return minXP;
	}

	public int getMaxXP() {
		return maxXP;
	}

	public int getVeinSize() {
		return veinSize;
	}

	public int getVeinsPerChunk() {
		return veinsPerChunk;
	}

	public int getBottomOffset() {
		return bottomOffset;
	}

	public int getMinHeight() {
		return minHeight;
	}

	public int getMaxHeight() {
		return maxHeight;
	}
	
	public boolean isBlacklist() {
		return blacklist;
	}
	
	public Category[] getBiomeList() {
		return biomeList;
	}
	
}
