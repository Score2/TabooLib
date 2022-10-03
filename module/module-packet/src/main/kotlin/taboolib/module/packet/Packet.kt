package taboolib.module.packet

import io.netty.buffer.ByteBuf

/**
 * TabooLib
 * taboolib.module.packet.Packet
 *
 * @author Score2
 * @since 2022/10/02 1:46
 */
abstract class Packet(val id: Int, val side: PacketFlow) {

    abstract fun write(byteBuf: ByteBuf)

    companion object {

        /*fun create(): Packet {

        }*/

    }
}