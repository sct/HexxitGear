package sct.hexxitgear.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import sct.hexxitgear.HexxitGear;

public class ItemTribalArmor extends ItemArmor {

    public ItemTribalArmor(int id, int renderIndex, int slot) {
        super(id, EnumArmorMaterial.DIAMOND, renderIndex, slot);
        setCreativeTab(CreativeTabs.tabMisc);
    }

    @Override
    public void registerIcons(IconRegister ir) {
        itemIcon = ir.registerIcon(getUnlocalizedName());
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot,
                                  int layer) {
        if (stack.itemID == HexxitGear.tribalLeggings.itemID) {
            return "/textures/armor/tribal2.png";
        }

        return "/textures/armor/tribal.png";
    }
}
