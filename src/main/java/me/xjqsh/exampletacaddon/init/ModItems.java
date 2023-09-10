package me.xjqsh.exampletacaddon.init;

import com.tac.guns.GunMod;
import com.tac.guns.common.GunModifiers;
import com.tac.guns.item.GunItem;
import com.tac.guns.item.TransitionalTypes.TimelessGunItem;
import me.xjqsh.exampletacaddon.Reference;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {
    public static final DeferredRegister<Item> REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, Reference.MOD_ID);
    public static final RegistryObject<GunItem> HCAR = REGISTER.register("hcar",
            () -> new TimelessGunItem(properties -> properties.tab(GunMod.RIFLE), GunModifiers.AK47_MOD));
}
