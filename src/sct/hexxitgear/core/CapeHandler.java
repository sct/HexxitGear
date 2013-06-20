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

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import net.minecraft.entity.player.EntityPlayer;
import sct.hexxitgear.HexxitGear;
import sct.hexxitgear.net.PacketWrapper;
import sct.hexxitgear.net.Packets;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

public class CapeHandler {

    public static Map<String, String> capes = new HashMap<String, String>();

    public static void registerCape(String player, String capeUrl) {
        capes.put(player, capeUrl);
        sendCapeUpdate(player, capeUrl);
    }

    public static void removeCape(String playerName) {
        if (playerName != null) {
            capes.remove(playerName);
            sendCapeUpdate(playerName, null);
        }
    }

    public static String getCapeUrl(String player) {
        return capes.get(player);
    }

    public static void sendCapeUpdate(String player, String capeUrl) {
        if (capeUrl == null) {
            capeUrl = "";
        }
        Object[] data = new Object[] { player, capeUrl };

        PacketDispatcher.sendPacketToAllPlayers(PacketWrapper.createPacket(HexxitGear.modNetworkChannel, Packets.CapeChange, data));
    }

    public static void sendJoinUpdate(EntityPlayer player) {
        List<Object> tempList = new ArrayList<Object>();

        tempList.add(0, (byte) capes.size());

        for (String playerName : capes.keySet()) {
            tempList.add(playerName);
            tempList.add(capes.get(playerName));
        }

        PacketDispatcher.sendPacketToPlayer(PacketWrapper.createPacket(HexxitGear.modNetworkChannel, Packets.CapeJoin, tempList.toArray()), (Player) player);
    }

    public static void readCapeUpdate(String playerName, String capeUrl) {
        EntityPlayer player = HexxitGear.proxy.findPlayer(playerName);
        if (!capeUrl.equals("")) {
            capes.put(playerName, capeUrl);
            if (player != null) {
                player.cloakUrl = capes.get(playerName);
                FMLClientHandler.instance().getClient().renderEngine.obtainImageData(player.cloakUrl, null);
            }
        } else {
            capes.remove(playerName);
            if (player != null)
                player.cloakUrl = null;
        }
    }

    public static void readJoinUpdate(DataInputStream data) {
        try {
            capes = new HashMap<String, String>();

            int count = data.readByte();

            String playerName, capeUrl;
            for (int i = 0; i < count; i++) {
                playerName = data.readUTF();
                capeUrl = data.readUTF();
                capes.put(playerName, capeUrl);
                EntityPlayer player = HexxitGear.proxy.findPlayer(playerName);
                if (player != null) {
                    player.cloakUrl = capes.get(playerName);
                    FMLClientHandler.instance().getClient().renderEngine.obtainImageData(player.cloakUrl, null);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
