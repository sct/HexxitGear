package sct.hexxitgear.core;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import net.minecraft.entity.player.EntityPlayer;

import java.util.EnumSet;

public class HGTickHandler implements ITickHandler {

    private int tickCounter = 0;

    @Override
    public void tickStart(EnumSet<TickType> type, Object... tickData) {
        if (type.equals(EnumSet.of(TickType.PLAYER))) {
            for (Object tick : tickData) {
                if (tick instanceof EntityPlayer) {
                    if (tickCounter > 20) {
                        EntityPlayer player = (EntityPlayer) tick;
                        ArmorSet.getMatchingSet(player);
                        tickCounter = 0;
                    }
                    tickCounter++;
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
        return "MAPlayerTicks";
    }
}
