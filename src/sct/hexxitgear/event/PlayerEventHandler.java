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

package sct.hexxitgear.event;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingEvent;
import sct.hexxitgear.HexxitGear;
import sct.hexxitgear.core.CapeHandler;

import java.util.logging.Level;

public class PlayerEventHandler {

    private int ticks = 0;

    @ForgeSubscribe
    public void playerUpdate(LivingEvent.LivingUpdateEvent event) {
        if (ticks > 16) {
            if (event.entityLiving != null && event.entityLiving instanceof EntityPlayer) {
                if (!event.entityLiving.worldObj.isRemote)
                    return;

                EntityPlayer player = (EntityPlayer) event.entityLiving;
                String capeUrl = CapeHandler.getCapeUrl(player.username);
                if (capeUrl != null && !capeUrl.equals(player.cloakUrl)) {
                    player.cloakUrl = capeUrl;
                    FMLClientHandler.instance().getClient().renderEngine.obtainImageData(player.cloakUrl, null);
                }
            }
        ticks = 0;
        }
        ticks++;
    }
}
