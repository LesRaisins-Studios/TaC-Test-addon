package me.xjqsh.lesraisinsadd.item.grenades;

import me.xjqsh.lesraisinsadd.common.data.grenades.HolyGrenadeMeta;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.Color;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TranslationTextComponent;

public class SThrowableItem extends ThrowableItem<HolyGrenadeMeta>{
    private Style style = Style.EMPTY.withColor(Color.parseColor("#8B4513"));
    public SThrowableItem(Properties properties, ThrowableEntityFactory<HolyGrenadeMeta> factory) {
        super(properties, factory);
    }
    @Override
    public ITextComponent getName(ItemStack p_200295_1_) {
//        return super.getName(p_200295_1_);
        return new TranslationTextComponent(this.getDescriptionId(p_200295_1_)).withStyle(style);
    }
}
