package me.xjqsh.lesraisinsadd.item;

import net.minecraft.item.Item;

public class TestBeamItem extends Item {
    public TestBeamItem(Properties properties) {
        super(properties);
    }

//    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
//        ItemStack itemstack = player.getItemInHand(hand);
//        if(!world.isClientSide()){
//            Color c = new Color(0, 208, 255);
//            BeamEntity.BeamInfo info = new BeamEntity.BeamInfo();
//            info.setColor(c.getRGB());
////            info.setRotateSpeed(5.0f);
////            info.setEndDisappear(true);
//            LaserUtil.createBeams(player,1,info);
////            LaserUtil.rayTrace(player,50);
//        }
//        return ActionResult.success(itemstack);
//    }

}
