package net.rhysholloway.donutmod1.materials;

import static net.minecraft.sound.SoundEvents.ITEM_ARMOR_EQUIP_GENERIC;

import java.util.function.Supplier;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Lazy;
import net.rhysholloway.donutmod1.items.DonutItems;
import net.rhysholloway.donutmod1.lib.DonutArmorMaterial;

public enum DonutArmorMaterials implements DonutArmorMaterial {

	// Armor material name, Name, Durability (Is scaled later), Toughness, Enchantability, Damage
	// Reduction Amounts, Equip Sound, Repair Item

	hallowed_basic("hallowed", 51, 1.0f, 15, new int[] { 3, 6, 7, 3 }, ITEM_ARMOR_EQUIP_GENERIC, () -> { return Ingredient.ofItems(DonutItems.hallowed_ingot);}),

	hallowed_melee(hallowed_basic.getMaterialName(), "melee", 64, 1.0f, 15, new int[] { 3, 6, 7, 3 }, ITEM_ARMOR_EQUIP_GENERIC, () -> { return Ingredient.ofItems(DonutItems.hallowed_ingot);}),
	
	hallowed_magic(hallowed_basic.getMaterialName(), "magic", 20, 1.0f, 15, new int[] { 3, 6, 7, 3 }, ITEM_ARMOR_EQUIP_GENERIC, () -> { return Ingredient.ofItems(DonutItems.hallowed_ingot);}), 
	
	hallowed_ranged(hallowed_basic.getMaterialName(), "ranged", 36, 1.0f, 15, new int[] { 3, 6, 7, 3 }, ITEM_ARMOR_EQUIP_GENERIC, () -> { return Ingredient.ofItems(DonutItems.hallowed_ingot);});

	private String materialName;
	private String armorVariantName;
	private int durability;
	private float toughness;
	private int enchantability;
	private int[] damageReductionAmounts;
	private SoundEvent equipSound;
	private Lazy<Ingredient> repairIngredient;

	private DonutArmorMaterials(String materialName, String subArmorName, int durability, float toughness, int enchantability, int[] damageReductionAmounts, SoundEvent equipSound, Supplier<Ingredient> repairIngredient) {
		this.materialName = materialName;
		this.armorVariantName = subArmorName;
		this.durability = durability;
		this.toughness = toughness;
		this.enchantability = enchantability;
		this.damageReductionAmounts = damageReductionAmounts;
		this.equipSound = equipSound;
		this.repairIngredient = new Lazy<Ingredient>(repairIngredient);
	}

	private DonutArmorMaterials(String type, int durability, float toughness, int enchantability, int[] damageReductionAmounts, SoundEvent equipSound, Supplier<Ingredient> repairIngredient) {
		this(type, "", durability, toughness, enchantability, damageReductionAmounts, equipSound, repairIngredient);
	}

	public String getMaterialName() {
		return materialName;
	}

	public String getVariantName() {
		return armorVariantName;
	}
	
    private static final int[] baseDurability = {13, 15, 16, 11};
	
	@Override
	public int getDurability(EquipmentSlot slot) {
		return baseDurability[slot.getEntitySlotId()] * this.durability;
	}
	
	@Override
	public float getToughness() {
		return this.toughness;
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
		if (this.armorVariantName.equals(""))
			return this.materialName + "_armor";
		else
			return this.materialName + "_" + this.armorVariantName + "_armor";
	}

}
