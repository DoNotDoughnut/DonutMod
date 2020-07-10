package net.rhysholloway.donutmod.item.material;

import static net.minecraft.sound.SoundEvents.ITEM_ARMOR_EQUIP_GENERIC;
import static net.rhysholloway.donutmod.util.item.DonutItemConstants.*;
import static net.minecraft.item.Items.PHANTOM_MEMBRANE;

import java.util.function.Supplier;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Lazy;
import net.rhysholloway.donutmod.util.item.VariantArmorMaterial;

public enum DonutArmorMaterials implements VariantArmorMaterial {

	// Material Name, Durability (Is scaled later), Toughness, Damage Reduction
	// Amounts, Enchantability, Equip Sound, Repair Item

	cobalt_basic(cobalt_name, cobalt_armor_durability, cobalt_armor_toughness, cobalt_knockback_resistance, cobalt_damage_reduction_amounts, cobalt_enchantability, getSupplier(cobalt_repair_item)),

	mythril_basic(mythril_name, mythril_armor_durability, mythril_armor_toughness, mythril_knockback_resistance, mythril_damage_reduction_amounts, mythril_enchantability, getSupplier(mythril_repair_item)),

	adamantite_basic(adamantite_name, adamantite_armor_durability, adamantite_armor_toughness, adamantite_knockback_resistance, adamantite_damage_reduction_amounts, adamantite_enchantability, getSupplier(adamantite_repair_item)),

	hallowed_basic(hallowed_name, hallowed_armor_durability, hallowed_armor_toughness, hallowed_knockback_resistance, hallowed_damage_reduction_amounts, hallowed_enchantability, getSupplier(hallowed_repair_item)),

	// Base Material, Sub Armor Name, Difference In Durability, (Optional)
	// Toughness, (Optional) Damage Reduction Amounts, (Optional) Enchantability,
	// (Optional) Equip Sound

	cobalt_melee(cobalt_basic, meleeId, t2_melee_durability_diff), //
	cobalt_magic(cobalt_basic, magicId, t2_magic_durability_diff), //
	cobalt_ranged(cobalt_basic, rangedId, t2_ranged_durability_diff), //

	mythril_melee(mythril_basic, meleeId, t2_melee_durability_diff), //
	mythril_magic(mythril_basic, magicId, t2_magic_durability_diff), //
	mythril_ranged(mythril_basic, rangedId, t2_ranged_durability_diff), //

	adamantite_melee(adamantite_basic, meleeId, t2_melee_durability_diff), //
	adamantite_magic(adamantite_basic, magicId, t2_magic_durability_diff), //
	adamantite_ranged(adamantite_basic, rangedId, t2_ranged_durability_diff), //

	hallowed_melee(hallowed_basic, meleeId, hallowed_melee_durability_diff), //
	hallowed_ranged(hallowed_basic, rangedId, hallowed_ranged_durability_diff), //
	hallowed_magic(hallowed_basic, magicId, hallowed_magic_durability_diff), //

	possessed("possessed", 500, 1.5f, 0.3f, new int[] { 2, 4, 5, 2}, 5, getSupplier(PHANTOM_MEMBRANE)); //

	private final String materialName;
	private final String armorVariantName;
	private final int durability;
	private final float toughness;
	private final float knockbackResistance;
	private final int enchantability;
	private final int[] damageReductionAmounts;
	private final SoundEvent equipSound;
	private final Lazy<Ingredient> repairIngredient;

	@Override
	public String getMaterialName() {
		return materialName;
	}

	@Override
	public String getVariantName() {
		return armorVariantName;
	}

	@Override
	public int getDurability(EquipmentSlot slot) {
		return baseArmorDurability[slot.getEntitySlotId()] * this.durability;
	}

	@Override
	public float getToughness() {
		return this.toughness;
	}

	@Override
	public float getKnockbackResistance() {
		return knockbackResistance;
	}

	@Override
	public int getEnchantability() {
		return this.enchantability;
	}

	@Override
	public int getProtectionAmount(EquipmentSlot slot) {
		return this.damageReductionAmounts[slot.getEntitySlotId()];
	}

	@Override
	public SoundEvent getEquipSound() {
		return equipSound;
	}

	@Override
	public Ingredient getRepairIngredient() {
		return repairIngredient.get();
	}

	@Override
	@Environment(EnvType.CLIENT)
	public String getName() {
		if (this.armorVariantName.equals(baseArmorName))
			return this.materialName + "_armor";
		else
			return this.materialName + "_" + this.armorVariantName + "_armor";
	}

	// Sub armor constructors

	private DonutArmorMaterials(DonutArmorMaterials material, String subArmorName, int durability) {
		this(material, subArmorName, durability, 0);
	}

	private DonutArmorMaterials(DonutArmorMaterials material, String subArmorName, int durability, float toughness) {
		this(material, subArmorName, durability, toughness, material.knockbackResistance);
	}

	private DonutArmorMaterials(DonutArmorMaterials material, String subArmorName, int durability, float toughness, float knockbackResistance) {
		this(material, subArmorName, durability, toughness, knockbackResistance, material.damageReductionAmounts);
	}

	private DonutArmorMaterials(DonutArmorMaterials material, String subArmorName, int durability, float toughness, float knockbackResistance, int[] damageReductionAmounts) {
		this(material, subArmorName, durability, toughness, knockbackResistance, damageReductionAmounts, material.enchantability);
	}

	private DonutArmorMaterials(DonutArmorMaterials material, String subArmorName, int durability, float toughness, float knockbackResistance, int[] damageReductionAmounts, int enchantability) {
		this(material, subArmorName, durability, toughness, knockbackResistance, damageReductionAmounts, enchantability, material.equipSound);
	}

	private DonutArmorMaterials(DonutArmorMaterials material, String subArmorName, int durability, float toughness, float knockbackResistance, int[] damageReductionAmounts, int enchantability, SoundEvent equipSound) {
		this(material.materialName, material.durability + durability, toughness, knockbackResistance, damageReductionAmounts, enchantability, equipSound, () -> material.getRepairIngredient());
	}

	private DonutArmorMaterials(DonutArmorMaterials material, String subArmorName, int durability, float toughness, float knockbackResistance, int[] damageReductionAmounts, int enchantability, Supplier<Ingredient> repairIngredient) {
		this(material.materialName, material.durability + durability, toughness, knockbackResistance, damageReductionAmounts, enchantability, material.equipSound, repairIngredient);
	}

	private DonutArmorMaterials(DonutArmorMaterials material, String subArmorName, int durability, float toughness, float knockbackResistance, int[] damageReductionAmounts, int enchantability, SoundEvent equipSound, Supplier<Ingredient> repairIngredient) {
		this(material.materialName, subArmorName, material.durability + durability, material.toughness + toughness, knockbackResistance, damageReductionAmounts, enchantability, equipSound, repairIngredient);
	}

	// Basic armor constructors

	private DonutArmorMaterials(String materialName, String armorVariantName, int durability, float toughness, float knockbackResistance, int[] damageReductionAmounts, int enchantability, SoundEvent equipSound, Supplier<Ingredient> repairIngredient) {
		this.materialName = materialName;
		this.armorVariantName = armorVariantName;
		this.durability = durability;
		this.toughness = toughness;
		this.knockbackResistance = knockbackResistance;
		this.enchantability = enchantability;
		this.damageReductionAmounts = damageReductionAmounts;
		this.equipSound = equipSound;
		this.repairIngredient = new Lazy<Ingredient>(repairIngredient);
	}

	private DonutArmorMaterials(String materialName, int durability, float toughness, float knockbackResistance, int[] damageReductionAmounts, int enchantability, SoundEvent equipSound, Supplier<Ingredient> repairIngredient) {
		this(materialName, baseArmorName, durability, toughness, knockbackResistance, damageReductionAmounts, enchantability, equipSound, repairIngredient);
	}

	private DonutArmorMaterials(String materialName, int durability, float toughness, float knockbackResistance, int[] damageReductionAmounts, int enchantability, Supplier<Ingredient> repairIngredient) {
		this(materialName, durability, toughness, knockbackResistance, damageReductionAmounts, enchantability, ITEM_ARMOR_EQUIP_GENERIC, repairIngredient);
	}

	private static Supplier<Ingredient> getSupplier(Item... item) {
		return () -> Ingredient.ofItems(item);
	}

}
