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

package sct.hexxitgear.render;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import sct.hexxitgear.item.ItemSkullHelmet;
import sct.hexxitgear.model.ModelSkullHelmet;

public class HelmetRenderer implements IItemRenderer {

    private static final ModelSkullHelmet SKULL_HELMET = new ModelSkullHelmet();

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return item.getItem() instanceof ItemSkullHelmet && type.equals(ItemRenderType.EQUIPPED);
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
                                         ItemRendererHelper helper) {
        switch (helper) {
            case ENTITY_ROTATION:
                return true;
            case ENTITY_BOBBING:
                return true;
            case EQUIPPED_BLOCK:
                return true;
            default:
                return false;
        }
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
        Entity player = null;
        for (Object obj : data) {
            if (obj instanceof EntityLiving) {
                player = (EntityLiving) obj;
            }
        }

        if (player != null) {
            FMLClientHandler.instance().getClient().renderEngine.bindTexture("/textures/maps/SkullHelmet.png");
            SKULL_HELMET.render(player, 0, 0, 0, 0, 0, 0.10F);
        }

    }
}