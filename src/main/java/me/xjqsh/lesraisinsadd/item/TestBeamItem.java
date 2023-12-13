package me.xjqsh.lesraisinsadd.item;

import me.xjqsh.lesraisinsadd.entity.BeamEntity;
import me.xjqsh.lesraisinsadd.util.LaserUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.awt.*;

public class TestBeamItem extends Item {
    public TestBeamItem(Properties properties) {
        super(properties);
    }

    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if(!world.isClientSide()){
            Color c = new Color(0, 208, 255);
            BeamEntity.BeamInfo info = new BeamEntity.BeamInfo();
            info.setColor(c.getRGB());
//            info.setRotateSpeed(5.0f);
//            info.setEndDisappear(true);
            LaserUtil.createBeams(player,1,info);
//            LaserUtil.rayTrace(player,50);
        }
        return ActionResult.success(itemstack);
    }

}
