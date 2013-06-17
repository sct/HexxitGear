package sct.hexxitgear.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import sct.hexxitgear.HexxitGear;

public class ItemThiefArmor extends ItemArmor {

    public ItemThiefArmor(int id, int renderIndex, int slot) {
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
        if (stack.itemID == HexxitGear.thiefLeggings.itemID) {
            return "/textures/armor/thief2.png";
        }

        return "/textures/armor/thief.png";
    }
}
