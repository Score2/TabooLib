package taboolib.module.packet

import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.ByteToMessageDecoder

/**
 * TabooLib
 * taboolib.module.packet.PacketDecode
 *
 * @author Score2
 * @since 2022/10/01 12:55
 */
class PacketDecode(val side: PacketFlow) : ByteToMessageDecoder() {

    override fun decode(ctx: ChannelHandlerContext, `in`: ByteBuf, out: MutableList<Any>) {
        val slice = `in`.copy()
        val packetId = `in`.readVarInt()

        val packet = BytePacket(packetId, side, slice) // TODO 支持自适应 Packet 继承者


        out.add(packet)
    }


}