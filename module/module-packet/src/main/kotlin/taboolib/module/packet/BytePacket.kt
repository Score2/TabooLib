package taboolib.module.packet

import io.netty.buffer.ByteBuf

/**
 * TabooLib
 *
 * @author Score2
 * @since 2021/11/18 23:14
 */
class BytePacket(id: Int, side: PacketFlow, byteBuf: ByteBuf) : Packet(id, side) {

    val byteBuf = byteBuf.copy()

    override fun write(byteBuf: ByteBuf) {
        byteBuf.writeBytes(this.byteBuf)
    }

}