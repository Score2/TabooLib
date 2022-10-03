package taboolib.module.packet

/**
 * TabooLib
 * taboolib.module.packet.ConnectionProtocol
 *
 * @author Score2
 * @since 2022/09/29 22:21
 */
enum class ProtocolIntention(val id: Int) {
    HANDSHAKING(-1),
    PLAY(1),
    STATUS(2),
    LOGIN(3),
    ;
}