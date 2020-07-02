package net.rhysholloway.donutmod2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.GsonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.rhysholloway.donutmod2.config.ModConfig;
import net.rhysholloway.donutmod2.lists.EntityRendererList;
import net.rhysholloway.donutmod2.lists.EntityTypeList;
import net.rhysholloway.donutmod2.lists.FeatureList;
import net.rhysholloway.donutmod2.lists.ItemList;
import net.rhysholloway.donutmod2.lists.LootTableList;
import net.rhysholloway.donutmod2.lists.RecipeSerializerList;
import net.rhysholloway.donutmod2.lists.ScreenHandlerTypeList;
import net.rhysholloway.donutmod2.lists.ScreenRegistryList;
import net.rhysholloway.donutmod2.lists.StructureFeatureList;
import net.rhysholloway.donutmod2.util.ListRegistry;

public class DonutMod implements ModInitializer, ClientModInitializer {

	public static final String modId = "donutmod2", fancyId = "DonutMod2";
	
	public static Logger logger = LogManager.getLogger(fancyId);
	
	public static final ItemGroup group = FabricItemGroupBuilder.build(new Identifier(modId, "general"), () -> new ItemStack(ItemList.hallowed_ingot));

	public static final int dungeon_count = 80;
	
	public static ModConfig config;
	
	@Override
	public void onInitialize() {
		
		logger.info("Registering config...");
		AutoConfig.register(ModConfig.class, GsonConfigSerializer::new);
		config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();
		
		RecipeSerializerList.register();

		ListRegistry.register();
		
		FeatureList.register();
		
		if(config.underground_cabin.enabled) {
			StructureFeatureList.register();
		}

		ScreenHandlerTypeList.register();
		
		EntityTypeList.register();
		
		LootTableList.register();
		
	}
	
	@Override
	public void onInitializeClient() {
		
		ScreenRegistryList.register();
		EntityRendererList.register();
		
	}
	
}
