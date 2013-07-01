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
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import sct.hexxitgear.HexxitGear;
import sct.hexxitgear.gui.HGCreativeTab;
import sct.hexxitgear.model.ModelScaleHelmet;

public class ItemScaleArmor extends ItemHexxitArmor {

    public ItemScaleArmor(int id, int renderIndex, int slot) {
        super(id, EnumArmorMaterial.DIAMOND, renderIndex, slot);
    }

    @Override
    public void registerIcons(IconRegister ir) {
        itemIcon = ir.registerIcon(getUnlocalizedName());
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, int layer) {
        if (slot == 0)
            return "/textures/maps/ScaleHelmet.png";

        if (stack.itemID == HexxitGear.scaleLeggings.itemID)
            return "/textures/armor/scale2.png";

        return "/textures/armor/scale.png";
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ModelBiped getArmorModel(EntityLiving entityLiving, ItemStack itemStack, int armorSlot) {
        if (armorSlot == 0)
            return new ModelScaleHelmet();
        return null;
    }
}
