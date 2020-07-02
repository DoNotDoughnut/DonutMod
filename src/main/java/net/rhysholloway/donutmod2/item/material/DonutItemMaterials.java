package net.rhysholloway.donutmod2.item.material;

import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.rhysholloway.donutmod2.lists.ItemList;

public enum DonutItemMaterials implements ToolMaterial {

	// Harvest Level, Durability, Efficiency, Attack Damage, Enchantability, Repair
	// Material

	hallowed("hallowed", 3, 576, 9F, 3.5F, 13, ItemList.hallowed_ingot);

	private Item repairMaterial;
	private int enchantability, harvestLevel, durability;
	private float efficiency;
	private float attackDamage;
	private String type;

	private DonutItemMaterials(String type, int harvestLevel, int durability, float efficiency, float attackDamage, int enchantability, Item repairMaterial) {
		this.type = type;
		this.harvestLevel = harvestLevel;
		this.durability = durability;
		this.efficiency = efficiency;
		this.attackDamage = attackDamage;
		this.enchantability = enchantability;
		this.repairMaterial = repairMaterial;
	}

	@Override
	public float getAttackDamage() {
		return this.attackDamage;
	}

	@Override
	public int getEnchantability() {
		return this.enchantability;
	}

	public String getType() {
		return type;
	}

	@Override
	public int getDurability() {
		return this.durability;
	}

	@Override
	public int getMiningLevel() {
		return this.harvestLevel;
	}

	public float getMiningSpeed() {
		return this.efficiency;
	}

	@Override
	public Ingredient getRepairIngredient() {
		return Ingredient.ofItems(this.repairMaterial);
	}

	public float getMiningSpeedMultiplier() {
		return getMiningSpeed();
	}
	
}
