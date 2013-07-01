/*
 * HexxitGear
 * Copyright (C) 2013  Ryan Cohen
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package sct.hexxitgear.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.ISpecialArmor;
import sct.hexxitgear.HexxitGear;
import sct.hexxitgear.gui.HGCreativeTab;
import sct.hexxitgear.model.ModelHoodHelmet;
import sct.hexxitgear.util.FormatCodes;

import java.util.List;

public class ItemThiefArmor extends ItemHexxitArmor {

    public ItemThiefArmor(int id, int renderIndex, int slot) {
        super(id, EnumArmorMaterial.DIAMOND, renderIndex, slot);
    }

    @Override
    public void registerIcons(IconRegister ir) {
        itemIcon = ir.registerIcon(getUnlocalizedName());
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot,
                                  int layer) {
        if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entity;
            if (player.isPotionActive(Potion.invisibility))
                return "/textures/armor/invisible.png";
        }

        // If the helmet slot, return helmet texture map
        if (slot == 0)
            return "/textures/maps/HoodHelmet.png";

        if (stack.itemID == HexxitGear.thiefLeggings.itemID)
            return "/textures/armor/thief2.png";

        return "/textures/armor/thief.png";
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ModelBiped getArmorModel(EntityLiving entityLiving, ItemStack itemStack, int armorSlot) {
        if (armorSlot == 0)
            return new ModelHoodHelmet();
        return null;
    }

    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List infoList, boolean par4) {
        infoList.add(FormatCodes.Indigo.format + "Thief Set");
    }
}
