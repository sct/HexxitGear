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

package sct.hexxitgear.net;

import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import sct.hexxitgear.core.ability.AbilityHandler;
import sct.hexxitgear.core.CapeHandler;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

public class HGPacketHandler implements IPacketHandler {
    @SuppressWarnings("rawtypes")
    @Override
    public void onPacketData(INetworkManager manager,
                             Packet250CustomPayload packet, Player player) {
        DataInputStream data = new DataInputStream(new ByteArrayInputStream(packet.data));
        int packetType = PacketWrapper.readPacketID(data);

        if (packetType == Packets.CapeChange) {
            Class[] decodeAs = { String.class, String.class };
            Object[] packetReadout = PacketWrapper.readPacketData(data, decodeAs);

            CapeHandler.readCapeUpdate((String)packetReadout[0], (String)packetReadout[1]);
        } else if (packetType == Packets.CapeJoin) {
            CapeHandler.readJoinUpdate(data);
        } else if (packetType == Packets.armorAbility) {
            Class[] decodeAs = { String.class };
            Object[] packetReadout = PacketWrapper.readPacketData(data, decodeAs);

            AbilityHandler.readAbilityPacket((String) packetReadout[0]);
        }

    }
}
