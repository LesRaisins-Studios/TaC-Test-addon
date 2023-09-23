package me.xjqsh.lesraisinsadd.init;

import com.tac.guns.GunMod;
import com.tac.guns.common.GunModifiers;
import com.tac.guns.item.GunItem;
import com.tac.guns.item.TransitionalTypes.TimelessGunItem;
import me.xjqsh.lesraisinsadd.Reference;
import me.xjqsh.lesraisinsadd.common.item.RiotShieldItem;
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
            () -> new RiotShieldItem(new Item.Properties().tab(GunMod.SNIPER)));
}
