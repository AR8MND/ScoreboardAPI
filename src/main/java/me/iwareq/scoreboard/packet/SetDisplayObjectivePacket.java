package me.iwareq.scoreboard.packet;

import cn.nukkit.network.connection.util.HandleByteBuf;
import cn.nukkit.network.protocol.DataPacket;
import cn.nukkit.network.protocol.PacketHandler;
import lombok.Setter;
import me.iwareq.scoreboard.packet.data.DisplaySlot;
import me.iwareq.scoreboard.packet.data.SortOrder;

@Setter
public class SetDisplayObjectivePacket extends DataPacket {

	public static final byte NETWORK_ID = 0x6b;

	private DisplaySlot displaySlot;
	private String objectiveId;
	private String displayName;
	private String criteria;
	private SortOrder sortOrder;

	@Override
	public int pid() {
		return NETWORK_ID;
	}

	@Override
	public void decode(HandleByteBuf handleByteBuf) {

	}

	@Override
	public void encode(HandleByteBuf hBB) {
		hBB.resetWriterIndex();

		hBB.writeString(this.displaySlot.getName());
		hBB.writeString(this.objectiveId);
		hBB.writeString(this.displayName);
		hBB.writeString(this.criteria);
		hBB.writeVarInt(this.sortOrder.ordinal());
	}

	@Override
	public void handle(PacketHandler packetHandler) {

	}
}
