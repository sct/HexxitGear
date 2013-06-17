package sct.hexxitgear.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import sct.hexxitgear.HexxitGear;

public class ItemScaleArmor extends ItemArmor {

    public ItemScaleArmor(int id, int renderIndex, int slot) {
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
        if (stack.itemID == HexxitGear.scaleLeggings.itemID) {
            return "/textures/armor/scale2.png";
        }

        return "/textures/armor/scale.png";
    }
}
