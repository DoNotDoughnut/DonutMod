package net.rhysholloway.donutmod.item.material;

import static net.rhysholloway.donutmod.util.item.DonutItemConstants.*;

import java.util.function.Supplier;

import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Lazy;

public enum DonutItemMaterials implements ToolMaterial {

	// Harvest Level, Durability, Efficiency, Attack Damage, Enchantability, Repair Material

	cobalt(cobalt_name, cobalt_mining_level, cobalt_item_durability, cobalt_efficiency, cobalt_attack_damage, cobalt_enchantability, cobalt_repair_item),
	mythril(mythril_name, mythril_mining_level, mythril_item_durability, mythril_efficiency, mythril_attack_damage, mythril_enchantability, mythril_repair_item),
	adamantite(adamantite_name, adamantite_mining_level, adamantite_item_durability, adamantite_efficiency, adamantite_attack_damage, adamantite_enchantability, adamantite_repair_item),
	hallowed(hallowed_name, hallowed_mining_level, hallowed_item_durability, hallowed_efficiency, hallowed_attack_damage, hallowed_enchantability, hallowed_repair_item);

	private String name;
	private int enchantability, harvestLevel, durability;
	private float efficiency;
	private float attackDamage;
	private Lazy<Ingredient> repairIngredient;

	private DonutItemMaterials(String name, int harvestLevel, int durability, float efficiency, float attackDamage, int enchantability, Lazy<Ingredient> repairIngredient) {
		this.name = name;
		this.harvestLevel = harvestLevel;
		this.durability = durability;
		this.efficiency = efficiency;
		this.attackDamage = attackDamage;
		this.enchantability = enchantability;
		this.repairIngredient = repairIngredient;
	}
	
	private DonutItemMaterials(String name, int harvestLevel, int durability, float efficiency, float attackDamage, int enchantability, Supplier<Ingredient> repairIngredient) {
		this(name, harvestLevel, durability, efficiency, attackDamage, enchantability, new Lazy<Ingredient>(repairIngredient));
	}
	
	private DonutItemMaterials(String name, int harvestLevel, int durability, float efficiency, float attackDamage, int enchantability, Item repairIngredient) {
		this(name, harvestLevel, durability, efficiency, attackDamage, enchantability, () -> Ingredient.ofItems(repairIngredient));
	}

	@Override
	public float getAttackDamage() {
		return this.attackDamage;
	}

	@Override
	public int getEnchantability() {
		return this.enchantability;
	}

	public String getName() {
		return name;
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
		return this.repairIngredient.get();
	}

	public float getMiningSpeedMultiplier() {
		return getMiningSpeed();
	}
	
}
