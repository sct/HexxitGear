package sct.hexxitgear.core;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import sct.hexxitgear.HexxitGear;

import java.util.*;

public class ArmorSet {

    public static ArmorSet tribalSet = new ArmorSet("Tribal", "https://dl.dropbox.com/u/57416963/Minecraft/Capes/kinglemmingcofh.png", Arrays.asList(HexxitGear.skullHelmet, HexxitGear.tribalChest, HexxitGear.tribalLeggings, HexxitGear.tribalShoes));

    private static List<ArmorSet>armorSets;
    private static Map<String, ArmorSet> playerMap = new HashMap<String, ArmorSet>();

    private List<Item> armors = new ArrayList<Item>();
    private String capeUrl;
    private String name;

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
        if (getPlayerArmorSet(playerName).getCapeUrl() != null) {
            CapeHandler.removeCape(playerName);
        }
        playerMap.remove(playerName);
    }

    public ArmorSet(String name, String capeUrl, List<Item>armor) {
        this.name = name;
        this.armors = armor;
        this.capeUrl = capeUrl;

        if (armorSets == null) armorSets = new ArrayList<ArmorSet>();

        armorSets.add(this);
    }

    public ArmorSet(String name, List<Item>armor) {
        this(name, null, armor);
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
}
