package taboolib.module.packet

import io.netty.buffer.ByteBuf

/**
 * TabooLib
 * taboolib.module.packet.ByteBufExtension
 *
 * @author Score2
 * @since 2022/09/30 0:08
 */

fun ByteBuf.readVarInt(maxBytes: Int = 5): Int {
    var out = 0
    var bytes = 0
    var `in`: Byte
    while (true) {
        `in` = this.readByte()
        out = out or (`in`.toInt() and 0x7F shl bytes++) * 7
        if (bytes > maxBytes) {
            throw RuntimeException("VarInt too big")
        }
        if (`in`.toInt() and 0x80 != 0x80) {
            break
        }
    }
    return out
}

fun ByteBuf.writeVarInt(value: Int) {
    var value = value
    var part: Int
    while (true) {
        part = value and 0x7F
        value = value ushr 7
        if (value != 0) {
            part = part or 0x80
        }
        this.writeByte(part)
        if (value == 0) {
            break
        }
    }
}