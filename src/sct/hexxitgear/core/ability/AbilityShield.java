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

package sct.hexxitgear.core.ability;

import net.minecraft.entity.player.EntityPlayer;

public class AbilityShield extends Ability {


    public AbilityShield() {
        super("Shield", 5 * 20, 10 * 20, false);
    }

    @Override
    public void start(EntityPlayer player) {
        player.invulnerable = true;
    }

    @Override
    public void end(EntityPlayer player) {
        player.invulnerable = false;
    }
}
