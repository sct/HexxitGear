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

package sct.hexxitgear.core;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import sct.hexxitgear.HexxitGear;
import sct.hexxitgear.core.buff.BuffTribalSet;
import sct.hexxitgear.core.buff.IBuffHandler;

import java.util.*;

public class ArmorSet {

    public static ArmorSet tribalSet = new ArmorSet("Tribal", "http://dpf.sctgaming.com/capes/brownrags.png",
            Arrays.asList(HexxitGear.tribalHelmet, HexxitGear.tribalChest, HexxitGear.tribalLeggings, HexxitGear.tribalShoes), new BuffTribalSet());
    public static ArmorSet thiefSet = new ArmorSet("Thief", "http://dpf.sctgaming.com/capes/redcape.png",
            Arrays.asList(HexxitGear.thiefHelmet, HexxitGear.thiefChest, HexxitGear.thiefLeggings, HexxitGear.thiefBoots), new BuffTribalSet());
    public static ArmorSet scaleSet = new ArmorSet("Scale", "http://dpf.sctgaming.com/capes/purplecape.png",
            Arrays.asList(HexxitGear.scaleHelmet, HexxitGear.scaleChest, HexxitGear.scaleLeggings, HexxitGear.scaleBoots), new BuffTribalSet());

    private static List<ArmorSet>armorSets;
    private static Map<String, ArmorSet> playerMap = new HashMap<String, ArmorSet>();

    private List<Item> armors = new ArrayList<Item>();
    private String capeUrl;
    private String name;
    private IBuffHandler buffHandler;

    public static List<ArmorSet> getArmorSets() {
        return armorSets;
    }

    public static ArmorSet getArmorSetAtIndex(int index) {
        return armorSets.get(index);
    }

    public static void getMatchingSet(EntityPlayer player) {
        List<Item> playerSet = getPlayerArmors(player);

        boolean foundMatch = false;

        for (ArmorSet armorSet : getArmorSets()) {
            int matched = 0;
            for (Item armor : armorSet.getArmors()) {
                if (playerSet.contains(armor)) {
                    matched++;
                }
            }
            if (matched == 4) {
                if (getPlayerArmorSet(player.username) == null || !getPlayerArmorSet(player.username).equals(armorSet)) {
                    addPlayerArmorSet(player.username, armorSet);
                    player.sendChatToPlayer("Set complete: " + armorSet.getName());
                }
                foundMatch = true;
            }
        }

        if (!foundMatch && getPlayerArmorSet(player.username) != null) {
            removePlayerArmorSet(player.username);
        }
    }

    private static List<Item> getPlayerArmors(EntityPlayer player) {
        List<Item> playerSet = new ArrayList<Item>(4);

        for (int i = 0; i < 4; i++) {
            if (player.getCurrentArmor(i) != null) {
                playerSet.add(player.getCurrentArmor(i).getItem());
            }
        }

        return playerSet;
    }

    public static ArmorSet getPlayerArmorSet(String playerName) {
        return playerMap.get(playerName);
    }

    public static void addPlayerArmorSet(String playerName, ArmorSet armorSet) {
        playerMap.put(playerName, armorSet);
        if (armorSet.getCapeUrl() != null) {
            CapeHandler.registerCape(playerName, armorSet.getCapeUrl());
        }
    }

    public static void removePlayerArmorSet(String playerName) {
        if (getPlayerArmorSet(playerName) != null && getPlayerArmorSet(playerName).getCapeUrl() != null) {
            CapeHandler.removeCape(playerName);
        }
        playerMap.remove(playerName);
    }

    public ArmorSet(String name, String capeUrl, List<Item>armor, IBuffHandler buffHandler) {
        this.name = name;
        this.armors = armor;
        this.capeUrl = capeUrl;
        this.buffHandler = buffHandler;

        if (armorSets == null) armorSets = new ArrayList<ArmorSet>();

        armorSets.add(this);
    }

    public ArmorSet(String name, List<Item>armor, IBuffHandler buffHandler) {
        this(name, null, armor, buffHandler);
    }

    public String getName() {
        return name;
    }

    public List<Item> getArmors() {
        return armors;
    }

    public String getCapeUrl() {
        return capeUrl;
    }

    public void applyBuffs(EntityPlayer player) {
        buffHandler.applyPlayerBuffs(player);
    }
}
