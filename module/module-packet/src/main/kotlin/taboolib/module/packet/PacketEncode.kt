package taboolib.module.packet

import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToByteEncoder

/**
 * TabooLib
 * taboolib.module.packet.PacketEncode
 *
 * @author Score2
 * @since 2022/10/01 12:55
 */
class PacketEncode(val side: PacketFlow) : MessageToByteEncoder<Packet>() {

    override fun encode(ctx: ChannelHandlerContext, packet: Packet, out: ByteBuf) {
        out.writeVarInt(packet.id)
        packet.write(out)
    }

}