package net.rhysholloway.donutmod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.rhysholloway.donutmod.util.ListRegistry;
import net.rhysholloway.donutmod.util.ModConfig;

public class DonutMod implements ModInitializer, ClientModInitializer {

	public static final String modId = "donutmod", fancyId = "Donut Mod";

	public static Logger logger = LogManager.getLogger(fancyId);

	@Override
	public void onInitialize() {

		ModConfig.register();

		ListRegistry.register();

	}

	@Override
	public void onInitializeClient() {

		ListRegistry.registerClient();

	}

}
