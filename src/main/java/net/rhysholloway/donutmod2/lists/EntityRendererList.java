package net.rhysholloway.donutmod2.lists;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.minecraft.entity.EntityType;
import net.rhysholloway.donutmod2.client.render.entity.LightDiscEntityRenderer;
import net.rhysholloway.donutmod2.client.render.entity.RedstoneCubeEntityRenderer;
import net.rhysholloway.donutmod2.client.render.entity.WyvernEntityRenderer;

@Environment(EnvType.CLIENT)
public class EntityRendererList {
	
	public static void register() {
		
		register(EntityTypeList.WYVERN, (dispatcher, context) -> new WyvernEntityRenderer(dispatcher));
		register(EntityTypeList.REDSTONE_CUBE, (dispatcher, context) -> new RedstoneCubeEntityRenderer(dispatcher));
		register(EntityTypeList.LIGHT_DISC, (dispatcher, context) -> new LightDiscEntityRenderer(dispatcher));
		
	}
	
	private static void register(EntityType<?> type, EntityRendererRegistry.Factory factory) {
		EntityRendererRegistry.INSTANCE.register(type, factory);
	}

}
