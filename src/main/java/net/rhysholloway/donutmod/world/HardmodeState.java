package net.rhysholloway.donutmod.world;

import static net.rhysholloway.donutmod.DonutMod.logger;

import java.util.UUID;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.MessageType;
import net.minecraft.server.MinecraftServer;
import net.minecraft.text.TranslatableText;
import net.minecraft.world.PersistentState;

public class HardmodeState extends PersistentState {
	
	private boolean hardmode;

	public static final String id = "hardmode";

	public HardmodeState() {
		super(id);
		this.hardmode = false;
	}
	
	public boolean isHardmode() {
		return hardmode;
	}

	public void startHardmode(MinecraftServer server) {
		if(this.hardmode != true) {
			logger.info("Enabling hardmode in world.");
			server.getPlayerManager().broadcastChatMessage(new TranslatableText("text.donutmod.starthardmode"), MessageType.CHAT, UUID.randomUUID());
			this.hardmode = true;
			onStartHardmode(server);
			markDirty();
		} else {
			logger.info("Hardmode is already active in world.");
		}
		
	}

	private void onStartHardmode(MinecraftServer server) {
		HardmodeGen.addBiomeFeatures();
		HardmodeGen.retrogen(server.getOverworld());
	}
	

	@Override
	public void fromTag(CompoundTag tag) {
		hardmode = tag.getBoolean(id);
	}

	@Override
	public CompoundTag toTag(CompoundTag tag) {
		tag.putBoolean(id, hardmode);
		return tag;
	}

}
