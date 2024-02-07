package me.xjqsh.lesraisinsadd.mixin;

import com.tac.guns.common.attachments.CustomModifierData;
import com.tac.guns.common.attachments.NetworkModifierManager;
import com.tac.guns.item.attachment.IAttachment;
import com.tac.guns.item.attachment.impl.Attachment;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.thread.SidedThreadGroups;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static com.tac.guns.item.attachment.impl.Attachment.CUSTOM_MODIFIER;
import static com.tac.guns.item.attachment.impl.Attachment.hasCustomModifier;

@Mixin(Attachment.class)
public class AttachmentMixin {

    @Inject(method = "getCustomModifier",remap = false,at = @At("HEAD"), cancellable = true)
    private static void getCustomModifier(ItemStack stack, boolean isLocal, CallbackInfoReturnable<CustomModifierData> cir){
        if(!(stack.getItem() instanceof IAttachment)){
            cir.setReturnValue(null);
            return;
        }
        ResourceLocation loc = ((IAttachment<?>) stack.getItem()).getProperties().getDefaultModifier();
        if(hasCustomModifier(stack)) {
            if (stack.getTag() != null) {
                String raw = stack.getTag().getString(CUSTOM_MODIFIER);
                ResourceLocation tmp = ResourceLocation.tryParse(raw);
                if(tmp!=null) loc = tmp;
            }
        }

        if(loc!=null){
            boolean side = Thread.currentThread().getThreadGroup() != SidedThreadGroups.SERVER;
            if(side){
                cir.setReturnValue(NetworkModifierManager.getLocalCustomModifier(loc));
            }else {
                cir.setReturnValue(NetworkModifierManager.getCustomModifier(loc));
            }
            return;
        }
        cir.setReturnValue(null);
    }

}
