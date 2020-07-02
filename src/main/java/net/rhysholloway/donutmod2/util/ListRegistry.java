package net.rhysholloway.donutmod2.util;

import static net.rhysholloway.donutmod2.DonutMod.*;

import java.lang.reflect.Field;

import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.rhysholloway.donutmod2.DonutMod;
import net.rhysholloway.donutmod2.lists.BlockEntityTypeList;
import net.rhysholloway.donutmod2.lists.BlockList;
import net.rhysholloway.donutmod2.lists.ItemList;
import net.rhysholloway.donutmod2.lists.StatusEffectList;
import net.rhysholloway.donutmod2.util.block.BasicOre;
import net.rhysholloway.donutmod2.util.block.BlockHelper;
import net.rhysholloway.donutmod2.util.block.Itemless;
import net.rhysholloway.donutmod2.util.block.SpecialItemSettings;

public class ListRegistry {
	
	public static void register() {
		registerBlocks();
		registerItems();
		registerBlockEntityTypes();
		registerStatusEffects();
	}

	private static void registerBlocks() {

		for (Field blockField : BlockList.class.getDeclaredFields()) {

			try {

				if (blockField.get(blockField.getName()) instanceof Block) {
					
					Block block = (Block) blockField.get(blockField.getName());

					Registry.register(Registry.BLOCK, new Identifier(DonutMod.modId, blockField.getName()), block);

					if (!(block instanceof Itemless)) {
						
						if(block instanceof SpecialItemSettings) {
							Registry.register(Registry.ITEM, new Identifier(modId, blockField.getName()), new BlockItem(block, ((SpecialItemSettings) block).getItemSettings()));
						} else {
							Registry.register(Registry.ITEM, new Identifier(modId, blockField.getName()), new BlockItem(block, new Item.Settings().group(group)));
						}						
					} else {
						logger.warn("Non-Block field " + blockField.getName() + " in Block List class!");
					}
					
					if(block instanceof BasicOre) {
						BlockHelper.registerOre((BasicOre) block);
					}

				}

			} catch (IllegalArgumentException | IllegalAccessException e) {
				logger.error("Block field " + blockField.getName() + " could not be used!");
			}

		}

	}

	private static void registerItems() {

		for (Field itemField : ItemList.class.getDeclaredFields()) {

			try {

				if (itemField.get(itemField.getName()) instanceof Item) {

					Item item = (Item) itemField.get(itemField.getName());
					
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

				if (blockEntityTypeField.get(blockEntityTypeField.getName()) instanceof BlockEntityType) {
					
					BlockEntityType<?> blockEntityType = (BlockEntityType<?>) blockEntityTypeField.get(blockEntityTypeField.getName());

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

				if (statusEffectField.get(statusEffectField.getName()) instanceof StatusEffect) {
					
					StatusEffect statusEffect = (StatusEffect) statusEffectField.get(statusEffectField.getName());
					
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
