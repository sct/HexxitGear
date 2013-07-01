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

public abstract class Ability {

    private String name;
    private int active;
    private int cooldown;
    private boolean instant;

    public Ability(String name, int active, int cooldown, boolean instant) {
        this.name = name;
        this.active = active;
        this.cooldown = cooldown;
        this.instant = instant;
    }

    public String getName() {
        return name;
    }

    public int getActive() {
        return active;
    }

    public int getCooldown() {
        return cooldown;
    }

    public boolean isInstant() {
        return instant;
    }

    public abstract void start(EntityPlayer player);

    public void end(EntityPlayer player) {

    }
}
