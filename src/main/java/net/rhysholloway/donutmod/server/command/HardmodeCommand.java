package net.rhysholloway.donutmod.server.command;

import java.util.UUID;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.entity.Entity;
import net.minecraft.network.MessageType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.TranslatableText;
import net.rhysholloway.donutmod.world.HardmodeState;

public class HardmodeCommand {

	public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
		dispatcher.register(CommandManager.literal("hardmode").executes(context -> {

			Entity entity = context.getSource().getEntity();

			context.getSource().getMinecraftServer().getPlayerManager().getPlayer(entity.getUuid()).sendMessage(new TranslatableText(context.getSource().getMinecraftServer().getOverworld().getPersistentStateManager().getOrCreate(HardmodeState::new, HardmodeState.id).isHardmode() ? "text.donutmod.isHardmode" : "text.donutmod.isNotHardmode"), MessageType.CHAT, UUID.randomUUID());

			return 1;

		}));

		dispatcher.register(CommandManager.literal("starthardmode").executes(context -> {

			context.getSource().getMinecraftServer().getOverworld().getPersistentStateManager().getOrCreate(HardmodeState::new, HardmodeState.id).startHardmode(context.getSource().getMinecraftServer());

			return 1;

		}));

	}

}
