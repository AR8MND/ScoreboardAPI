package me.iwareq.scoreboard.packet;

import cn.nukkit.network.connection.util.HandleByteBuf;
import cn.nukkit.network.protocol.DataPacket;
import cn.nukkit.network.protocol.PacketHandler;
import lombok.Setter;

@Setter
public class RemoveObjectivePacket extends DataPacket {

	public static final byte NETWORK_ID = 0x6a;

	private String objectiveId;

	@Override
	public int pid() {
		return NETWORK_ID;
	}

	@Override
	public void decode(HandleByteBuf handleByteBuf) {

	}

	@Override
	public void handle(PacketHandler packetHandler) {

	}

	@Override
	public void encode(HandleByteBuf hBB) {
		hBB.resetWriterIndex();
		hBB.writeString(this.objectiveId);
	}
}
