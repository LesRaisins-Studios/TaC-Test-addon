package me.xjqsh.lesraisinsadd.client.listener;


import me.xjqsh.lesraisinsadd.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.resources.IResourceManager;
import net.minecraft.resources.SimpleReloadableResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.resource.ISelectiveResourceReloadListener;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModelReg {
    private static final ResourceLocation beam = new ResourceLocation("lesraisinsadd", "entity/beam");
    private static IBakedModel cachedModel;

    @OnlyIn(Dist.CLIENT)
    public static IBakedModel getModel() {
        if (cachedModel == null) {
            IBakedModel model = Minecraft.getInstance().getModelManager().getModel(beam);
            if (model == Minecraft.getInstance().getModelManager().getMissingModel()) {
                return model;
            }
            cachedModel = model;
        }
        return cachedModel;
    }
    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void register(ModelRegistryEvent event) {
        ModelLoader.addSpecialModel(beam);
        IResourceManager manager = Minecraft.getInstance().getResourceManager();
        ((SimpleReloadableResourceManager) manager).registerReloadListener(
                (ISelectiveResourceReloadListener) (resourceManager, resourcePredicate) -> cachedModel=null
        );
    }
}
