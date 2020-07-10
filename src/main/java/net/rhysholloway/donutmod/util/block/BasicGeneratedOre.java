package net.rhysholloway.donutmod.util.block;

public class BasicGeneratedOre extends BasicOre {

	public final GenOreSettings oreSettings;
	
	public BasicGeneratedOre(Settings blockSettings, GenOreSettings oreSettings) {
		super(blockSettings, oreSettings);
		this.oreSettings = oreSettings;
	}

}
