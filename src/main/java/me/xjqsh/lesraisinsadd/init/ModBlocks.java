package me.xjqsh.lesraisinsadd.init;

import me.xjqsh.lesraisinsadd.Reference;
import me.xjqsh.lesraisinsadd.block.Object15Block;
import net.minecraft.block.Block;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlocks {
     public static final DeferredRegister<Block> REGISTER = DeferredRegister.create(ForgeRegistries.BLOCKS, Reference.MOD_ID);
     public static final RegistryObject<Block> obj15Block = REGISTER.register("object_15", Object15Block::new);
//     public static final RegistryObject<Block> test = REGISTER.register("test", TestBlock::new);
}
