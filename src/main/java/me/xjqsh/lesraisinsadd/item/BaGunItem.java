package me.xjqsh.lesraisinsadd.item;

import com.tac.guns.interfaces.IGunModifier;
import com.tac.guns.item.transition.TimelessGunItem;
import com.tac.guns.util.Process;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.Color;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TranslationTextComponent;


public class BaGunItem extends TimelessGunItem {
    public BaGunItem(Process<Properties> properties, IGunModifier... modifiers) {
        super(properties,modifiers);
    }
    private final Style style = Style.EMPTY.withColor(Color.parseColor("#F8F8FF"));
    @Override
    public ITextComponent getName(ItemStack p_200295_1_) {
//        return super.getName(p_200295_1_);
        return new TranslationTextComponent(this.getDescriptionId(p_200295_1_)).withStyle(style);
    }

}
