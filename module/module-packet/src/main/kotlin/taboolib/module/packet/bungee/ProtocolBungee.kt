package taboolib.module.packet.bungee

import io.netty.channel.Channel
import net.md_5.bungee.api.ProxyServer
import net.md_5.bungee.api.connection.ProxiedPlayer
import net.md_5.bungee.api.event.PlayerDisconnectEvent
import net.md_5.bungee.api.event.PostLoginEvent
import org.tabooproject.reflex.Reflex.Companion.getProperty
import taboolib.common.platform.Platform
import taboolib.common.platform.PlatformImplementation
import taboolib.common.platform.PlatformSide
import taboolib.common.platform.ProxyPlayer
import taboolib.common.platform.event.SubscribeEvent
import taboolib.common.platform.function.adaptPlayer
import taboolib.module.packet.ChannelExecutor
import taboolib.module.packet.PlatformProtocolHandler

/**
 * TabooLib
 * taboolib.module.packet.bungee.ProtocolBungee
 *
 * @author Score2
 * @since 2021/11/18 23:29
 */
@PlatformSide([Platform.BUNGEE])
@PlatformImplementation(Platform.BUNGEE)
object ProtocolBungee: PlatformProtocolHandler {

    @SubscribeEvent
    fun e(e: PostLoginEvent) {
        ChannelExecutor.initPlayer(adaptPlayer(e.player))
    }

    @SubscribeEvent
    fun e(e: PlayerDisconnectEvent) {

    }

    override fun getServerChannels(): List<Channel> {
        return ProxyServer.getInstance().getProperty<Collection<Channel>>("listeners")!!.toList()
    }

    override fun getChannel(player: ProxyPlayer): Channel {
        return player.cast<ProxiedPlayer>().getProperty<Channel>("ch/ch")!!
    }

}