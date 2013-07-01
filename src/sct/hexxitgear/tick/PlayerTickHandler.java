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

package sct.hexxitgear.tick;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import net.minecraft.entity.player.EntityPlayer;
import sct.hexxitgear.core.ability.AbilityHandler;
import sct.hexxitgear.core.ArmorSet;

import java.util.EnumSet;

public class PlayerTickHandler implements ITickHandler {

    private int tickCounter = 0;

    @Override
    public void tickStart(EnumSet<TickType> type, Object... tickData) {
        if (type.equals(EnumSet.of(TickType.PLAYER))) {
            for (Object tick : tickData) {
                if (tick instanceof EntityPlayer) {
                    EntityPlayer player = (EntityPlayer) tick;
                    if (tickCounter > 20) {
                        ArmorSet.getMatchingSet(player);
                        tickCounter = 0;
                    }
                    tickCounter++;

                    if (ArmorSet.getPlayerArmorSet(player.username) != null) {
                        ArmorSet armorSet = ArmorSet.getPlayerArmorSet(player.username);
                        armorSet.applyBuffs(player);
                    }

                    // We run this outside of the check for an armorset just incase a player takes off armor mid ability
                    AbilityHandler bh = AbilityHandler.getPlayerAbilityHandler(player.username);
                    if (bh != null) {
                        bh.onTick(player);
                    }
                }
            }
        }
    }

    @Override
    public void tickEnd(EnumSet<TickType> type, Object... tickData) {
    }

    @Override
    public EnumSet<TickType> ticks() {
        return EnumSet.of(TickType.PLAYER);
    }

    @Override
    public String getLabel() {
        return "HGPlayerTicks";
    }
}
