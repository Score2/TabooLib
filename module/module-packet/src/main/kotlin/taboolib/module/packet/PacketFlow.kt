package taboolib.module.packet

/**
 * TabooLib
 * taboolib.module.packet.PacketFlow
 *
 * @author Score2
 * @since 2022/10/02 1:18
 */
enum class PacketFlow {
    SERVERBOUND,
    CLIENTBOUND;

    fun getOpposite(): PacketFlow {
        return if (this == CLIENTBOUND) SERVERBOUND else CLIENTBOUND
    }
}