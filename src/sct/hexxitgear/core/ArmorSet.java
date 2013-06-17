package sct.hexxitgear.core;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import sct.hexxitgear.HexxitGear;

import java.util.*;

public class ArmorSet {

    public static ArmorSet tribalSet = new ArmorSet("Tribal", "http://dpf.sctgaming.com/capes/brownrags.png",
            Arrays.asList(HexxitGear.skullHelmet, HexxitGear.tribalChest, HexxitGear.tribalLeggings, HexxitGear.tribalShoes));
    public static ArmorSet thiefSet = new ArmorSet("Thief", "http://dpf.sctgaming.com/capes/redcape.png",
            Arrays.asList(HexxitGear.hoodHelmet, HexxitGear.thiefChest, HexxitGear.thiefLeggings, HexxitGear.thiefBoots));
    public static ArmorSet scaleSet = new ArmorSet("Scale", "http://dpf.sctgaming.com/capes/purplecape.png",
            Arrays.asList(HexxitGear.scaleHelmet, HexxitGear.scaleChest, HexxitGear.scaleLeggings, HexxitGear.scaleBoots));

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
        if (getPlayerArmorSet(playerName) != null && getPlayerArmorSet(playerName).getCapeUrl() != null) {
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
