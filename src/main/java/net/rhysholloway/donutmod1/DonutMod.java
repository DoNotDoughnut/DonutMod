package net.rhysholloway.donutmod1;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.rhysholloway.donutmod1.blocks.mythril_anvil.MythrilAnvil;
import net.rhysholloway.donutmod1.items.DonutItems;

public class DonutMod implements ModInitializer, ClientModInitializer {
	
	public static final String modId = "donutmod1";

	@Override
	public void onInitialize() {
		DonutItems.register();
		MythrilAnvil.initialize();
	}
	
	@Override
	public void onInitializeClient() {
		MythrilAnvil.initializeClient();
	}

}
