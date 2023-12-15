package me.xjqsh.lesraisinsadd.init;

import com.tac.guns.GunMod;
import com.tac.guns.common.GunModifiers;
import com.tac.guns.item.AmmoItem;
import com.tac.guns.item.GunItem;
import com.tac.guns.item.ScopeItem;
import com.tac.guns.item.TransitionalTypes.TimelessAmmoItem;
import com.tac.guns.item.TransitionalTypes.TimelessGunItem;
import com.tac.guns.item.TransitionalTypes.TimelessPistolGunItem;
import com.tac.guns.item.attachment.impl.Scope;
import com.tac.guns.item.attachment.impl.ScopeZoomData;
import me.xjqsh.lesraisinsadd.Reference;
import me.xjqsh.lesraisinsadd.item.AceItem;
import me.xjqsh.lesraisinsadd.item.armor.BulletProofVestItem;
import me.xjqsh.lesraisinsadd.item.armor.CustomArmorMaterial;
import me.xjqsh.lesraisinsadd.item.TestBeamItem;
import me.xjqsh.lesraisinsadd.item.grenades.EffectGrenadeItem;
import me.xjqsh.lesraisinsadd.item.grenades.SmokeGrenadeItem;
import me.xjqsh.lesraisinsadd.item.shield.FlashShieldItem;
import me.xjqsh.lesraisinsadd.item.shield.RiotShieldItem;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Rarity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {
    public static final DeferredRegister<Item> REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, Reference.MOD_ID);
    /*guns*/
    public static final RegistryObject<GunItem> MARLIN_1895 = REGISTER.register("marlin_1895",
            () -> new TimelessGunItem(properties -> properties.tab(GunMod.RIFLE)));
    public static final RegistryObject<GunItem> AUG = REGISTER.register("aug",
            () -> new TimelessGunItem(properties -> properties.tab(GunMod.RIFLE)));
    public static final RegistryObject<GunItem> SA58 = REGISTER.register("sa58",
            () -> new TimelessGunItem(properties -> properties.tab(GunMod.RIFLE)));
    public static final RegistryObject<GunItem> LOK1 = REGISTER.register("lok_1",
            () -> new TimelessGunItem(properties -> properties.tab(GunMod.RIFLE)));
    public static final RegistryObject<GunItem> FLINTLOCK = REGISTER.register("flintlock",
            () -> new TimelessGunItem(properties -> properties.tab(GunMod.RIFLE)));
    public static final RegistryObject<GunItem> FLINTLOCK_ROYAL = REGISTER.register("flintlock_royal",
            () -> new TimelessGunItem(properties -> properties.tab(GunMod.RIFLE)));
    public static final RegistryObject<GunItem> HK433 = REGISTER.register("hk433",
            () -> new TimelessGunItem(properties -> properties.tab(GunMod.RIFLE)));

    public static final RegistryObject<GunItem> SVD = REGISTER.register("svd",
            () -> new TimelessGunItem(properties -> properties.tab(GunMod.SNIPER)));
    public static final RegistryObject<GunItem> NTW20 = REGISTER.register("ntw20",
            () -> new TimelessGunItem(properties -> properties.tab(GunMod.SNIPER)));
    public static final RegistryObject<GunItem> CROSSBOW = REGISTER.register("crossbow",
            () -> new TimelessGunItem(properties -> properties.tab(GunMod.SNIPER)));

    public static final RegistryObject<GunItem> P90 = REGISTER.register("p90",
            () -> new TimelessGunItem(properties -> properties.tab(GunMod.SMG)));
    public static final RegistryObject<GunItem> PP19 = REGISTER.register("pp19",
            () -> new TimelessGunItem(properties -> properties.tab(GunMod.SMG)));
    public static final RegistryObject<GunItem> PPK20 = REGISTER.register("ppk20",
            () -> new TimelessGunItem(properties -> properties.tab(GunMod.SMG)));
    public static final RegistryObject<GunItem> MP18 = REGISTER.register("mp18",
            () -> new TimelessGunItem(properties -> properties.tab(GunMod.SMG)));


    public static final RegistryObject<GunItem> QSZ92 = REGISTER.register("qsz92",
            () -> new TimelessPistolGunItem(properties -> properties.tab(GunMod.PISTOL),GunModifiers.PISTOL_MOD));
    public static final RegistryObject<GunItem> ANGEL = REGISTER.register("angel",
            () -> new TimelessPistolGunItem(properties -> properties.tab(GunMod.PISTOL),GunModifiers.PISTOL_MOD));
    public static final RegistryObject<GunItem> ENCORE = REGISTER.register("encore",
            () -> new TimelessGunItem(properties -> properties.tab(GunMod.PISTOL)));
    public static final RegistryObject<GunItem> THE_LAST_WORD = REGISTER.register("the_last_word",
            () -> new TimelessGunItem(properties -> properties.tab(GunMod.PISTOL).rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<GunItem> THE_FIRST_CURSE = REGISTER.register("the_first_curse",
            () -> new TimelessGunItem(properties -> properties.tab(GunMod.PISTOL).rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<GunItem> ACE_OF_SPADES = REGISTER.register("ace_of_spades",
            () -> new AceItem(properties -> properties.tab(GunMod.PISTOL).rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<GunItem> BULLDOG = REGISTER.register("bulldog",
            () -> new TimelessPistolGunItem(properties -> properties.tab(GunMod.PISTOL)));

    public static final RegistryObject<GunItem> MG42 = REGISTER.register("mg42",
            () -> new TimelessGunItem(properties -> properties.tab(GunMod.HEAVY_MATERIAL)));

    /*launcher*/
    public static final RegistryObject<GunItem> M202 = REGISTER.register("m202",
            () -> new TimelessGunItem(properties -> properties.tab(GunMod.HEAVY_MATERIAL)));

    /*shield*/
    public static final RegistryObject<Item> RIOT_SHIELD = REGISTER.register("riot_shield",
            () -> new RiotShieldItem(new Item.Properties().durability(1000).tab(GunMod.GROUP),
                    3.0f,-2.5f,-0.15f));
    public static final RegistryObject<Item> FLASH_SHIELD = REGISTER.register("flash_shield",
            () -> new FlashShieldItem(new Item.Properties().durability(1000)
                    .tab(GunMod.GROUP).rarity(Rarity.EPIC),
                    3.0f,-2.5f,-0.15f));
    /*armor*/
    public static final RegistryObject<Item> BULLETPROOF_VEST = REGISTER.register("bulletproof_vest",
            ()-> new BulletProofVestItem(
                    CustomArmorMaterial.ARMOR_MATERIAL_FRANKSUIT,
                    EquipmentSlotType.CHEST,
                    new Item.Properties().durability(1000).tab(GunMod.GROUP)
            ));
    /*ammo*/
    public static final RegistryObject<Item> CROSSBOW_ARROW = REGISTER.register("crossbow_arrow",
            TimelessAmmoItem::new);

    public static final RegistryObject<Item> ORIGIN = REGISTER.register("origin_bullet",
            TimelessAmmoItem::new);
    public static final RegistryObject<Item> FLINTLOCK_BULLET = REGISTER.register("flintlock_bullet",
            TimelessAmmoItem::new);

    public static final RegistryObject<Item> M202_ROCKET = REGISTER.register("m202_rocket",
            () -> new AmmoItem(new Item.Properties().stacksTo(6).tab(GunMod.AMMO)));

    public static final RegistryObject<Item> AF6 = REGISTER.register("af6_scope",
            () -> new ScopeItem(Scope.create(new ScopeZoomData[]{new ScopeZoomData(0.00F, 0.00F)}, 1.875F, 0.325F, "coyote",
                    new ResourceLocation("lesraisins:af6")).viewFinderOffset(0.415).viewFinderOffsetSpecial(0.415), new Item.Properties().stacksTo(1).tab(GunMod.GROUP)));


    public static final RegistryObject<Item> SEAL = REGISTER.register("seal",
            () -> new Item(new Item.Properties().stacksTo(1).tab(GunMod.GROUP).rarity(Rarity.EPIC)));

    /*block*/
    public static final RegistryObject<Item> object15 = REGISTER.register("object_15",
            () -> new BlockItem(ModBlocks.obj15Block.get(), new Item.Properties().tab(GunMod.GROUP)));

    /*test*/
    public static final RegistryObject<Item> test = REGISTER.register("test_beam",
            () -> new TestBeamItem(new Item.Properties()));

    /*throwable*/
    public static final RegistryObject<Item> SMOKE_GRENADE = REGISTER.register("smoke_grenade",
            () -> new SmokeGrenadeItem(new Item.Properties().stacksTo(4).tab(GunMod.EXPLOSIVES), 20 * 7, 1.425f, 2.1f));
    public static final RegistryObject<Item> MOLOTOV = REGISTER.register("molotov",
            () ->{
                EffectGrenadeItem item = new EffectGrenadeItem(
                    new Item.Properties().stacksTo(4).tab(GunMod.EXPLOSIVES),  1.85f, EffectGrenadeItem.MOLOTOV);
                item.setExtinguishBySmoke(true);
                item.setMinRadius(2.0f);
                item.setMaxRadius(4.0f);
                item.setAreaDuration(300);
                item.setParticle(()-> ParticleTypes.FLAME);
                return item;} );
}
