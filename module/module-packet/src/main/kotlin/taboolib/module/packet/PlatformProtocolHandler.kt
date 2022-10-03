package taboolib.module.packet

import io.netty.channel.Channel
import taboolib.common.platform.ProxyPlayer

/**
 * TabooLib
 * taboolib.module.packet.PlatformProtocolHandle
 *
 * @author Score2
 * @since 2021/11/20 15:18
 */
interface PlatformProtocolHandler {

    fun getServerChannels(): List<Channel>

    fun getChannel(player: ProxyPlayer): Channel

}