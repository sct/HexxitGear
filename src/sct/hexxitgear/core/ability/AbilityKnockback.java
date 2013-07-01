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

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;

import java.util.List;

public class AbilityKnockback extends Ability {


    public AbilityKnockback() {
        super("Knockback", 1 * 20, 10 * 20, true);
    }

    @Override
    public void start(EntityPlayer player) {
        List<EntityLiving> entities = player.worldObj.getEntitiesWithinAABB(EntityLiving.class, AxisAlignedBB.getBoundingBox(player.posX - 5, player.posY, player.posZ - 5, player.posX + 5, player.posY + 3, player.posZ + 5));

        for (EntityLiving entity : entities) {
            double relX = player.posX - entity.posX;
            double relZ = player.posZ - entity.posZ;

            if (!entity.equals(player)) {
                entity.attackEntityFrom(DamageSource.causePlayerDamage(player), 2);
                entity.addVelocity((relX * 0.25) * -1, 0.2, (relZ * 0.25) * -1);
            }
        }
    }
}
