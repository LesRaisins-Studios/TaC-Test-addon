package me.xjqsh.lesraisinsadd.item;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

import java.util.function.Supplier;

public enum CustomArmorMaterial implements IArmorMaterial {

    ARMOR_MATERIAL_FRANKSUIT("bulletproof_vest", 80, new int[] {8, 13, 20, 12}, 35, SoundEvents.ARMOR_EQUIP_IRON, 7.0F, 0.8F,
            () -> null);


    private static final int[] baseDurability = { 13, 15, 16, 11 };
    private final String name;
    private final int durabilityMultiplier;
    private final int[] armorVal;
    private final int enchantability;
    private final SoundEvent equipSound;
    private final float toughness;
    private final float knockbackResistance;
    private Ingredient repairIngredient;

    /**
     * Constructor that adds the armormaterial
     * @param name Armor name
     * @param durabilityMultiplier Armor durability multiplier
     * @param armorVal Armor values per body part
     * @param enchantability Armor enchantability
     * @param equipSound Armor equipping sound
     * @param toughness Armor toughness points
     * @param knockbackResistance Armor knockback resistance
     * @param repairIngredient Armor repair item
     */
    CustomArmorMaterial(String name, int durabilityMultiplier, int[] armorVal, int enchantability, SoundEvent equipSound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.armorVal = armorVal;
        this.enchantability = enchantability;
        this.equipSound = equipSound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = repairIngredient.get();
    }

    /**
     * Gets the durability for a certain slot
     * @param slot Armor slot
     * @return Base durability times multiplier for that slot
     */
    @Override
    public int getDurabilityForSlot(EquipmentSlotType slot) {
        return baseDurability[slot.getIndex()] * durabilityMultiplier;
    }

    /**
     * Gets the defense for a certain slot
     * @param slot Armor slot
     * @return Armor value for that slot
     */
    @Override
    public int getDefenseForSlot(EquipmentSlotType slot) {
        return this.armorVal[slot.getIndex()];
    }

    /**
     * Gets the enchantability for a certain armor
     * @return Enchantability value
     */
    @Override
    public int getEnchantmentValue() {
        return this.enchantability;
    }

    /**
     * Gets the equip sound for a certain armor
     * @return Equip sound
     */
    @Override
    public SoundEvent getEquipSound() {
        return this.equipSound;
    }

    /**
     * Gets the repair item for a certain armor
     * @return Repair item
     */
    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient;
    }

    /**
     * Gets the name for a certain armor
     * @return Name
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Gets the toughness value for a certain armor
     * @return Toughness value
     */
    @Override
    public float getToughness() {
        return this.toughness;
    }

    /**
     * Gets the knockback resistance for a certain armor
     * @return Knockback resistance value
     */
    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}

