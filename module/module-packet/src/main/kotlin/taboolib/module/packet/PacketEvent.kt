package taboolib.module.packet

import taboolib.common.platform.ProxyPlayer
import taboolib.common.platform.event.ProxyEvent

/**
 * TabooLib
 * taboolib.module.packet.PacketEvent
 *
 * @author Score2
 * @since 2021/11/18 23:18
 */
abstract class PacketEvent(val player: ProxyPlayer, val packet: Packet): ProxyEvent() {

    class Send(player: ProxyPlayer, packet: Packet): PacketEvent(player, packet)
    class Receive(player: ProxyPlayer, packet: Packet): PacketEvent(player, packet)
}