package me.xjqsh.lesraisinsadd.init;

import com.tac.guns.GunMod;
import com.tac.guns.common.GunModifiers;
import com.tac.guns.item.GunItem;
import com.tac.guns.item.TransitionalTypes.TimelessGunItem;
import me.xjqsh.lesraisinsadd.Reference;
import me.xjqsh.lesraisinsadd.item.BulletProofVestItem;
import me.xjqsh.lesraisinsadd.item.CustomArmorMaterial;
import me.xjqsh.lesraisinsadd.item.shield.FlashShieldItem;
import me.xjqsh.lesraisinsadd.item.shield.RiotShieldItem;
import me.xjqsh.lesraisinsadd.client.render.model.armor.BulletProofVest;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {
    public static final DeferredRegister<Item> REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, Reference.MOD_ID);
    /*guns*/
    public static final RegistryObject<GunItem> HCAR = REGISTER.register("hcar",
            () -> new TimelessGunItem(properties -> properties.tab(GunMod.RIFLE), GunModifiers.AK47_MOD));
    public static final RegistryObject<GunItem> P90 = REGISTER.register("p90",
            () -> new TimelessGunItem(properties -> properties.tab(GunMod.RIFLE), GunModifiers.AK47_MOD));
    public static final RegistryObject<GunItem> PP19 = REGISTER.register("pp19",
            () -> new TimelessGunItem(properties -> properties.tab(GunMod.SMG), GunModifiers.AK47_MOD));
    public static final RegistryObject<GunItem> PPK20 = REGISTER.register("ppk20",
            () -> new TimelessGunItem(properties -> properties.tab(GunMod.SMG), GunModifiers.AK47_MOD));
    /*shield*/
    public static final RegistryObject<Item> RIOT_SHIELD = REGISTER.register("riot_shield",
            () -> new RiotShieldItem(new Item.Properties().durability(1000).tab(GunMod.GROUP),
                    3.0f,-2.5f,-0.15f));
    public static final RegistryObject<Item> FLASH_SHIELD = REGISTER.register("flash_shield",
            () -> new FlashShieldItem(new Item.Properties().durability(1000).tab(GunMod.GROUP),
                    3.0f,-2.5f,-0.15f));
    /*armor*/
    public static final RegistryObject<Item> vest = REGISTER.register("bulletproof_vest",
            ()-> new BulletProofVestItem(
                    CustomArmorMaterial.ARMOR_MATERIAL_FRANKSUIT,
                    EquipmentSlotType.CHEST,
                    new Item.Properties().durability(1000).tab(GunMod.GROUP),
                    new BulletProofVest<>()
            ));
}