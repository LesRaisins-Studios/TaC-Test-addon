package me.xjqsh.lesraisinsadd.item;

import me.xjqsh.lesraisinsadd.client.render.model.armor.BulletProofVest;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class BulletProofVestItem extends ArmorItem {
    private BulletProofVest<LivingEntity> model;
    public BulletProofVestItem(IArmorMaterial armorMaterial, EquipmentSlotType slot, Properties properties) {
        super(armorMaterial, slot, properties);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public final BipedModel getArmorModel(LivingEntity entity, ItemStack itemStack, EquipmentSlotType armorSlot, BipedModel defaultArmor) {
        if(model==null){
            model=new BulletProofVest<>();
        }
        this.model.copyPropertiesTo(defaultArmor);
        return model.applySlot(EquipmentSlotType.CHEST);
    }

}
