package me.xjqsh.lesraisinsadd.item;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.Constants;

/** a simple interface to mark an item has ammos
 */
public interface IAmmoable {
    String AMMO_TAG = "AmmoCount";
    default int getMaxAmmo(){
        return 1;
    }
    /**Get current ammo from the item stack.
     * @param stack the target item stack.
     * @return -1 when the item can't have ammo, or the current ammo count.
     * Set the ammo to 0 if the tag don't exist.
     */
    default int getAmmo(ItemStack stack){
        if(stack.getItem() instanceof IAmmoable){
            CompoundNBT nbt = stack.getOrCreateTag();
            if (nbt.contains(AMMO_TAG, Constants.NBT.TAG_INT)) {
                return nbt.getInt(AMMO_TAG);
            }
            nbt.putInt(AMMO_TAG,0);
            return 0;
        }else {
            return -1;
        }
    }

    default boolean setAmmo(ItemStack stack, int newAmmo){
        if(stack.getItem() instanceof IAmmoable){
            CompoundNBT nbt = stack.getOrCreateTag();
            nbt.putInt(AMMO_TAG,newAmmo);
            return true;
        }else {
            return false;
        }
    }

    default boolean consume(ItemStack stack, int i){
        int r = getAmmo(stack);
        if(r < i)return false;
        return setAmmo(stack, r-i);
    }

    default Item getAmmoItem(){
        return Items.AIR;
    }
    /**Count the reserve ammo in the given inventory
     * @param inventory The inv to search.
     * @return The number of reserve ammos.
     * */
    default int countAmmo(IInventory inventory){
        if(getAmmoItem().equals(Items.AIR)){
            return 0;
        } else {
            return inventory.countItem(getAmmoItem());
        }

    }

    default int filling(IInventory inventory, ItemStack stack){
        if(getAmmoItem().equals(Items.AIR)){
            return 0;
        }

        int i = getMaxAmmo()-getAmmo(stack);

        ItemStackHelper.clearOrCountMatchingItems(inventory,
                (s)-> s.getItem().equals(getAmmoItem()),i,false);

        return i;
    }
}
