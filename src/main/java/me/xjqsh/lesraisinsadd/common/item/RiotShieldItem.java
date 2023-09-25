package me.xjqsh.lesraisinsadd.common.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.block.BlockState;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.UUID;

public class RiotShieldItem extends Item {
    private final Multimap<Attribute, AttributeModifier> defaultModifiers;
    private final static UUID shield_movement= UUID.fromString("DE2D3BE7-E6FE-6002-C511-2CF3FAD33CD4");
    public RiotShieldItem(Properties properties) {
        super(properties);
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", 3.0f, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", -2.5f, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(shield_movement, "Shield slowness", -0.15f, AttributeModifier.Operation.MULTIPLY_TOTAL));
        this.defaultModifiers = builder.build();
    }
    @Override
    public UseAction getUseAnimation(ItemStack itemStack) {
        return UseAction.NONE;
    }

    @Override
    public boolean canAttackBlock(BlockState blockState, World world, BlockPos blockPos, PlayerEntity player) {
        return false;
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlotType slot,ItemStack stack) {
        return slot == EquipmentSlotType.MAINHAND ? this.defaultModifiers : super.getAttributeModifiers(slot,stack);
    }
}
