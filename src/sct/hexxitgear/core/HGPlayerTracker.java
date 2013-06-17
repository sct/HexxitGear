package sct.hexxitgear.core;

import cpw.mods.fml.common.IPlayerTracker;
import net.minecraft.entity.player.EntityPlayer;

public class HGPlayerTracker implements IPlayerTracker {

    public static HGPlayerTracker instance = new HGPlayerTracker();

    @Override
    public void onPlayerLogin(EntityPlayer player) {
        ArmorSet.getMatchingSet(player);
        CapeHandler.sendJoinUpdate(player.username);
    }

    @Override
    public void onPlayerLogout(EntityPlayer player) {
        ArmorSet.removePlayerArmorSet(player.username);
    }

    @Override
    public void onPlayerChangedDimension(EntityPlayer player) {
    }

    @Override
    public void onPlayerRespawn(EntityPlayer player) {
    }
}
