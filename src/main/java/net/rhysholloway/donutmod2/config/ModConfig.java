package net.rhysholloway.donutmod2.config;

import me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry.Gui.CollapsibleObject;
import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import net.rhysholloway.donutmod2.DonutMod;

@Config(name = DonutMod.modId)
public class ModConfig implements ConfigData {

	@CollapsibleObject
	public UndergroundCabin underground_cabin = new UndergroundCabin();
	
	public static class UndergroundCabin {
		
		public boolean enabled = true;
		public int chance = 80;
		
	}
	
	@CollapsibleObject
	public MythrilOre mythril_ore = new MythrilOre();
	
	public static class MythrilOre {
		
		public int veinSize = 4;
		public int veinsPerChunk = 4;
		public int bottomOffset = 0;
		public int minHeight = 0;
		public int maxHeight = 18;
		
	}
	
}
