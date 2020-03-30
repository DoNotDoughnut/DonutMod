package net.rhysholloway.donutmod1;

import dev.emi.trinkets.api.SlotGroups;
import dev.emi.trinkets.api.Slots;
import dev.emi.trinkets.api.TrinketSlots;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import net.rhysholloway.donutmod1.blocks.mythril_anvil.MythrilAnvil;
import net.rhysholloway.donutmod1.items.DonutItems;

public class DonutMod implements ModInitializer, ClientModInitializer {

	public static final String modId = "donutmod1";

	@Override
	public void onInitialize() {
		for (int i = 0; i < 6; i++)
			TrinketSlots.addSlot(SlotGroups.OFFHAND, Slots.CHARM, new Identifier("trinkets", "textures/item/empty_trinket_slot_charm.png"));
		DonutItems.register();
		MythrilAnvil.initialize();
	}

	@Override
	public void onInitializeClient() {
		MythrilAnvil.initializeClient();
	}

}
