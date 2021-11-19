package taboolib.module.packet.bungee

import net.md_5.bungee.api.event.PlayerDisconnectEvent
import net.md_5.bungee.api.event.PostLoginEvent
import taboolib.common.platform.event.SubscribeEvent

/**
 * TabooLib
 * taboolib.module.packet.bungee.ProtocolBungee
 *
 * @author Score2
 * @since 2021/11/18 23:29
 */
object ProtocolBungee {

    @SubscribeEvent
    fun e(e: PostLoginEvent) {

    }

    @SubscribeEvent
    fun e(e: PlayerDisconnectEvent) {

    }

}