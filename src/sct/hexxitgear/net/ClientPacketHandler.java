package sct.hexxitgear.net;

import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import sct.hexxitgear.core.CapeHandler;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.util.HashMap;
import java.util.Map;

public class ClientPacketHandler implements IPacketHandler {
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
        }

    }
}
