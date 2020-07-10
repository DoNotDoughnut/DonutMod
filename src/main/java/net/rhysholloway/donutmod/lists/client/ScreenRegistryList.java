package net.rhysholloway.donutmod.lists.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.rhysholloway.donutmod.lists.ScreenHandlerTypeList;
import net.rhysholloway.donutmod.screen.handler.AdamantiteFurnaceScreenHandler;
import net.rhysholloway.donutmod.screen.handler.NetherFurnaceScreenHandler;
import net.rhysholloway.donutmod.screen.handler.SoulBenchScreenHandler;

@Environment(EnvType.CLIENT)
public class ScreenRegistryList {

	public static void register() {

		ScreenRegistry.register(ScreenHandlerTypeList.NETHER_FURNACE, new NetherFurnaceScreenHandler.Factory());
		ScreenRegistry.register(ScreenHandlerTypeList.ADAMANTINE_FURNACE, new AdamantiteFurnaceScreenHandler.Factory());
		ScreenRegistry.register(ScreenHandlerTypeList.SOUL_BENCH, new SoulBenchScreenHandler.Factory());

	}

}
