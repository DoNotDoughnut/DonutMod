package net.rhysholloway.donutmod.lists;

import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.rhysholloway.donutmod.server.command.HardmodeCommand;

public class CommandList {

	public static void register() {

		CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {

			// BloodmoonCommand.register(dispatcher);
			HardmodeCommand.register(dispatcher);

		});

	}

}
