package taboolib.module.packet

import taboolib.common.platform.ProxyPlayer
import taboolib.common.platform.event.ProxyEvent

/**
 * TabooLib
 * taboolib.module.packet.PacketReceiveEvent
 *
 * @author Score2
 * @since 2021/11/18 23:18
 */
class PacketReceiveEvent(val player: ProxyPlayer, val packet: Packet): ProxyEvent()