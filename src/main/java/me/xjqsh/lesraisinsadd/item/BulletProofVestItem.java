package me.xjqsh.lesraisinsadd.item;

import me.xjqsh.lesraisinsadd.client.render.model.armor.BulletProofVest;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;

public class BulletProofVestItem extends ArmorItem {
    private BulletProofVest<LivingEntity> model;
    public BulletProofVestItem(IArmorMaterial armorMaterial, EquipmentSlotType slot, Properties properties, BulletProofVest<LivingEntity> vest) {
        super(armorMaterial, slot, properties);
        this.model = vest;
    }

    @Override
    public final BipedModel getArmorModel(LivingEntity entity, ItemStack itemStack, EquipmentSlotType armorSlot, BipedModel defaultArmor) {
        this.model.copyPropertiesTo(defaultArmor);
        return model.applySlot(EquipmentSlotType.CHEST);
    }

}
