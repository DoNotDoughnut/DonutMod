package net.rhysholloway.donutmod.util;

import static net.rhysholloway.donutmod.DonutMod.logger;
import static net.rhysholloway.donutmod.DonutMod.modId;
import static net.rhysholloway.donutmod.lists.ItemGroupList.general;

import java.lang.reflect.Field;

import org.apache.commons.lang3.reflect.FieldUtils;

import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.rhysholloway.donutmod.DonutMod;
import net.rhysholloway.donutmod.lists.BiomeList;
import net.rhysholloway.donutmod.lists.BlockEntityTypeList;
import net.rhysholloway.donutmod.lists.BlockList;
import net.rhysholloway.donutmod.lists.CommandList;
import net.rhysholloway.donutmod.lists.EntityTypeList;
import net.rhysholloway.donutmod.lists.FeatureList;
import net.rhysholloway.donutmod.lists.ItemList;
import net.rhysholloway.donutmod.lists.PotionList;
import net.rhysholloway.donutmod.lists.RecipeSerializerList;
import net.rhysholloway.donutmod.lists.ScreenHandlerTypeList;
import net.rhysholloway.donutmod.lists.StatusEffectList;
import net.rhysholloway.donutmod.lists.SurfaceBuilderList;
import net.rhysholloway.donutmod.lists.client.EntityRendererList;
import net.rhysholloway.donutmod.lists.client.ScreenRegistryList;
import net.rhysholloway.donutmod.loot.LootHandler;
import net.rhysholloway.donutmod.util.block.BasicGeneratedOre;
import net.rhysholloway.donutmod.util.block.BlockHelper;
import net.rhysholloway.donutmod.util.block.Itemless;
import net.rhysholloway.donutmod.util.block.SpecialItemSettings;

public class ListRegistry {

	public static void register() {
		
		registerBlocks();
		registerPotions();
		registerItems();
		registerBlockEntityTypes();
		registerStatusEffects();
		
		SurfaceBuilderList.register();
		BiomeList.register();
		RecipeSerializerList.register();
		FeatureList.register();
		ScreenHandlerTypeList.register();
		EntityTypeList.register();
		LootHandler.register();
		CommandList.register();
		
	}
	
	public static void registerClient() {
		ScreenRegistryList.register();
		EntityRendererList.register();
	}

	private static void registerBlocks() {

		for (Field blockField : BlockList.class.getDeclaredFields()) {

			try {

				if (FieldUtils.readStaticField(blockField) instanceof Block) {

					Block block = (Block) FieldUtils.readStaticField(blockField);

					Registry.register(Registry.BLOCK, new Identifier(DonutMod.modId, blockField.getName()), block);

					if (!(block instanceof Itemless)) {

						if (block instanceof SpecialItemSettings) {
							Registry.register(Registry.ITEM, new Identifier(modId, blockField.getName()), new BlockItem(block, ((SpecialItemSettings) block).getItemSettings()));
						} else {
							Registry.register(Registry.ITEM, new Identifier(modId, blockField.getName()), new BlockItem(block, new Item.Settings().group(general)));
						}
						
					}

					if (block instanceof BasicGeneratedOre) {
						BlockHelper.registerOre((BasicGeneratedOre) block);
					}

				} else {
					logger.warn("Non-Block field " + blockField.getName() + " in Block List class!");
				}

			} catch (IllegalArgumentException | IllegalAccessException e) {
				logger.error("Block field " + blockField.getName() + " could not be used!");
			}

		}

	}
	
	private static void registerPotions() {
		
		for (Field potionField : PotionList.class.getDeclaredFields()) {

			try {

				if (FieldUtils.readStaticField(potionField) instanceof Potion) {

					Potion potion = (Potion) FieldUtils.readStaticField(potionField);

					Registry.register(Registry.POTION, new Identifier(DonutMod.modId, potionField.getName()), potion);

				} else {
					logger.warn("Non-Potion field " + potionField.getName() + " in Potion List class!");
				}

			} catch (IllegalArgumentException | IllegalAccessException e) {
				logger.error("Potion field " + potionField.getName() + " could not be used!");
			}
		}

	}

	private static void registerItems() {
		
		for (Field itemField : ItemList.class.getDeclaredFields()) {

			try {

				if (FieldUtils.readStaticField(itemField) instanceof Item) {

					Item item = (Item) FieldUtils.readStaticField(itemField);

					Registry.register(Registry.ITEM, new Identifier(DonutMod.modId, itemField.getName()), item);

				} else {
					logger.warn("Non-Item field " + itemField.getName() + " in Item List class!");
				}

			} catch (IllegalArgumentException | IllegalAccessException e) {
				logger.error("Item field " + itemField.getName() + " could not be used!");
			}
		}

	}

	private static void registerBlockEntityTypes() {

		for (Field blockEntityTypeField : BlockEntityTypeList.class.getDeclaredFields()) {

			try {

				if (FieldUtils.readStaticField(blockEntityTypeField) instanceof BlockEntityType) {

					BlockEntityType<?> blockEntityType = (BlockEntityType<?>) FieldUtils.readStaticField(blockEntityTypeField);

					Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(DonutMod.modId, blockEntityTypeField.getName()), blockEntityType);

				} else {
					logger.warn("Non-Block Entity Type field " + blockEntityTypeField.getName() + " in Block Entity Type List class!");
				}

			} catch (IllegalArgumentException | IllegalAccessException e) {
				logger.error("Block Entity Type field " + blockEntityTypeField.getName() + " could not be used!");
			}

		}

	}

	private static void registerStatusEffects() {
		for (Field statusEffectField : StatusEffectList.class.getDeclaredFields()) {

			try {

				if (FieldUtils.readStaticField(statusEffectField) instanceof StatusEffect) {

					StatusEffect statusEffect = (StatusEffect) FieldUtils.readStaticField(statusEffectField);

					Registry.register(Registry.STATUS_EFFECT, new Identifier(DonutMod.modId, statusEffectField.getName()), statusEffect);

				} else {
					logger.warn("Non-Status Effect field " + statusEffectField.getName() + " in Status Effect List class!");
				}

			} catch (IllegalArgumentException | IllegalAccessException e) {
				logger.error("StatusEffect field " + statusEffectField.getName() + " could not be used!");
			}

		}

	}



}
