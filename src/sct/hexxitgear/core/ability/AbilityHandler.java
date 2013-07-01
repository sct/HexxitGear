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
import sct.hexxitgear.core.ArmorSet;

import java.util.HashMap;
import java.util.Map;

public class AbilityHandler {

    public static Map<String, AbilityHandler> buffHandlers = new HashMap<String, AbilityHandler>();

    private int activeTime = 0;
    private int cooldownTime = 0;
    private String playerName;
    private Ability ability;

    public static Map<String, AbilityHandler> getBuffHandlers() {
        return buffHandlers;
    }

    public static AbilityHandler getPlayerAbilityHandler(String playerName) {
        return buffHandlers.get(playerName);
    }

    public static void removePlayer(String playerName) {
        buffHandlers.remove(playerName);
    }

    public static void readAbilityPacket(String playerName) {
        if (playerName != null && !buffHandlers.containsKey(playerName)) {
            buffHandlers.put(playerName, new AbilityHandler(playerName));
        }
    }

    public AbilityHandler(String playerName) {
        this.playerName = playerName;
        this.ability = ArmorSet.getPlayerArmorSet(playerName).getAbility();
        this.activeTime = ability.getActive();
        this.cooldownTime = ability.getCooldown();
    }

    public void onTick(EntityPlayer player) {
        if (activeTime > 0) {
            if (ability != null) {
                if (ability.getActive() == activeTime)
                    player.sendChatToPlayer(ability.getName() + " activated!");

                ability.start(player);
                if (ability.isInstant())
                    activeTime = 0;
            }
            activeTime--;
        } else if (cooldownTime > 0) {
            if (ability != null) {
                ability.end(player);
            }
            cooldownTime--;
        } else {
            player.sendChatToPlayer("Ability refreshed");
            removePlayer(playerName);
        }
    }
}
