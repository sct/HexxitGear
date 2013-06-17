package sct.hexxitgear.item;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import sct.hexxitgear.model.SkullHelmet;

public class ItemSkullHelmet extends ItemArmor {

    public static final SkullHelmet modelHelmet = new SkullHelmet();

    public ItemSkullHelmet(int id) {
        super(id, EnumArmorMaterial.DIAMOND, 3, 0);
        setCreativeTab(CreativeTabs.tabMisc);
        setUnlocalizedName("hexxitgear.tribal.helmet");
    }

    @Override
    public void registerIcons(IconRegister ir) {
        itemIcon = ir.registerIcon(getUnlocalizedName());
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot,
                                  int layer) {
        return "/textures/maps/SkullHelmet.png";
    }

    @Override
    public ModelBiped getArmorModel(EntityLiving entityLiving, ItemStack itemStack, int armorSlot) {
        return modelHelmet;
    }
}
