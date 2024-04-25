package me.iwareq.scoreboard.packet;

import cn.nukkit.network.connection.util.HandleByteBuf;
import cn.nukkit.network.protocol.DataPacket;
import cn.nukkit.network.protocol.PacketHandler;
import lombok.AllArgsConstructor;
import lombok.Getter;
import me.iwareq.scoreboard.packet.data.ScorerInfo;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class SetScorePacket extends DataPacket {

	public static final byte NETWORK_ID = 0x6c;

	private final Action action;
	@Getter
	private final List<ScorerInfo> infos = new ArrayList<>();

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

		hBB.writeByte((byte) this.action.ordinal());
		hBB.writeUnsignedVarInt(this.infos.size());
		for (ScorerInfo info : this.infos) {
			hBB.writeVarLong(info.getScoreboardId());
			hBB.writeString(info.getObjectiveId());
			hBB.writeIntLE(info.getScore());
			if (this.action == Action.SET) {
				hBB.writeByte((byte) info.getType().ordinal());
				switch (info.getType()) {
					case ENTITY:
					case PLAYER:
						hBB.writeUnsignedVarLong(info.getEntityId());
						break;
					case FAKE:
						hBB.writeString(info.getName());
						break;
					default:
						throw new IllegalArgumentException("Invalid score type received");
				}
			}
		}
	}

	@Override
	public void handle(PacketHandler packetHandler) {

	}

	public enum Action {

		SET,
		REMOVE
	}
}
