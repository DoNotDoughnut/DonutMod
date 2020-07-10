package net.rhysholloway.donutmod.lists.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.minecraft.entity.EntityType;
import net.rhysholloway.donutmod.client.render.entity.LightDiscEntityRenderer;
import net.rhysholloway.donutmod.client.render.entity.PossessedArmorEntityRenderer;
import net.rhysholloway.donutmod.client.render.entity.LivingRedstoneEntityRenderer;
import net.rhysholloway.donutmod.client.render.entity.WyvernEntityRenderer;
import net.rhysholloway.donutmod.lists.EntityTypeList;

@Environment(EnvType.CLIENT)
public class EntityRendererList {
	
	public static void register() {
		
		register(EntityTypeList.POSSESSED_ARMOR, (dispatcher, context) -> new PossessedArmorEntityRenderer(dispatcher));
		register(EntityTypeList.WYVERN, (dispatcher, context) -> new WyvernEntityRenderer(dispatcher));
		register(EntityTypeList.LIVING_REDSTONE, (dispatcher, context) -> new LivingRedstoneEntityRenderer(dispatcher));
		register(EntityTypeList.LIGHT_DISC, (dispatcher, context) -> new LightDiscEntityRenderer(dispatcher));
		
	}
	
	private static void register(EntityType<?> type, EntityRendererRegistry.Factory factory) {
		EntityRendererRegistry.INSTANCE.register(type, factory);
	}

}
