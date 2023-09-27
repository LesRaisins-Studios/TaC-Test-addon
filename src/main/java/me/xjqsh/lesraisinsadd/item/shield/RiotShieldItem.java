package me.xjqsh.lesraisinsadd.item.shield;

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
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.UUID;

public class RiotShieldItem extends Item {
    private final Multimap<Attribute, AttributeModifier> defaultModifiers;
    protected final static UUID shield_movement= UUID.fromString("DE2D3BE7-E6FE-6002-C511-2CF3FAD33CD4");
    protected static Multimap<Attribute, AttributeModifier> createModifiers(float atk, float atk_speed, float movement_speed){
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", atk, AttributeModifier.Operation.ADDITION))
                .put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", atk_speed, AttributeModifier.Operation.ADDITION))
                .put(Attributes.MOVEMENT_SPEED, new AttributeModifier(shield_movement, "Shield slowness", movement_speed, AttributeModifier.Operation.MULTIPLY_TOTAL));
        return builder.build();
    }
    public RiotShieldItem(Properties properties, float atk, float atk_speed, float movement_speed) {
        this(properties, createModifiers(atk,atk_speed,movement_speed));
    }

    public RiotShieldItem(Properties properties, Multimap<Attribute, AttributeModifier> modifiers) {
        super(properties);
        this.defaultModifiers = modifiers;
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
