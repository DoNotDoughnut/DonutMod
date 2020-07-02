package net.rhysholloway.donutmod2.lists;


import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.registry.Registry;
import net.rhysholloway.donutmod2.screen.handler.NetherFurnaceScreenHandler;
import net.rhysholloway.donutmod2.screen.handler.SoulBenchScreenHandler;

public class ScreenHandlerTypeList {

	public static ScreenHandlerType<NetherFurnaceScreenHandler> NETHER_FURNACE;
	public static ScreenHandlerType<SoulBenchScreenHandler> SOUL_BENCH;

	public static void register() {

		NETHER_FURNACE = ScreenHandlerRegistry.registerSimple(Registry.BLOCK.getId(BlockList.nether_furnace), (syncId, inventory) -> new NetherFurnaceScreenHandler(syncId, inventory));
		SOUL_BENCH = ScreenHandlerRegistry.registerSimple(Registry.BLOCK.getId(BlockList.soul_bench), (syncId, inventory) -> new SoulBenchScreenHandler(syncId, inventory));
		
	}

}
