package net.rhysholloway.donutmod1.lib;

import static net.rhysholloway.donutmod1.DonutMod.modId;

import net.minecraft.block.Block;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class DonutRegistries {

	public static class ITEM extends Item implements DonutRegistry {

		public final Identifier id;

		public ITEM(Item.Settings settings, String name) {
			super(settings);
			id = new Identifier(modId, name);
		}

		public void register() {
			Registry.register(Registry.ITEM, id, this);
		}

	}

	public static class BLOCK extends Block implements DonutRegistry {

		public final Identifier id;
		private Item.Settings itemSettings;

		public BLOCK(Block.Settings blockSettings, Item.Settings itemSettings, String name) {
			super(blockSettings);
			this.itemSettings = itemSettings;
			id = new Identifier(modId, name);
		}

		public void register() {
			Registry.register(Registry.BLOCK, id, this);
			Registry.register(Registry.ITEM, id, new BlockItem(this, itemSettings));
		}

	}
	
	public static class ARMOR extends ArmorItem implements DonutRegistry {

		public final Identifier id;
		
		public ARMOR(DonutArmorMaterial material, EquipmentSlot slot, Settings settings) {
			super(material, slot, settings);
			String slotName = "";
			switch(getSlotType().getEntitySlotId()) {
			case 0:
				slotName = "boots";
				break;
			case 1:
				slotName = "leggings";
				break;
			case 2:
				slotName = "chestplate";
				break;
			case 3:
				slotName = "helmet";
				break;
			}			
			if (((DonutArmorMaterial) getMaterial()).getVariantName() == null || ((DonutArmorMaterial) getMaterial()).getVariantName() == "")
				id = new Identifier(modId, ((DonutArmorMaterial) getMaterial()).getMaterialName() + "_" + slotName);
			else
				id = new Identifier(modId, ((DonutArmorMaterial) getMaterial()).getMaterialName () + "_" + ((DonutArmorMaterial) getMaterial()).getVariantName() + "_" + slotName);
		}
		
		public void register() {
			Registry.register(Registry.ITEM, id, this);
		}
		
	}

	public static class SWORD extends SwordItem implements DonutRegistry {

		private Identifier id;
		
		public SWORD(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings, String name) {
			super(material, attackDamage, attackSpeed, settings);
			id = new Identifier(modId, name);
		}

		public void register() {
			Registry.register(Registry.ITEM, id, this);
		}
		
	}
}

interface DonutRegistry {
	
	public void register();
	
}
