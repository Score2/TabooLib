package taboolib.module.packet.bungee

import io.netty.channel.Channel
import net.md_5.bungee.api.connection.ProxiedPlayer
import net.md_5.bungee.api.event.PlayerDisconnectEvent
import net.md_5.bungee.api.event.PostLoginEvent
import taboolib.common.platform.Platform
import taboolib.common.platform.PlatformImplementation
import taboolib.common.platform.PlatformSide
import taboolib.common.platform.ProxyPlayer
import taboolib.common.platform.event.SubscribeEvent
import taboolib.common.platform.function.adaptPlayer
import taboolib.common.reflect.Reflex.Companion.getProperty
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

    override val DECODER_BASE_NAME = "frame-decoder"
    override val ENCODER_BASE_NAME = "frame-prepender"

    @SubscribeEvent
    fun e(e: PostLoginEvent) {
        ChannelExecutor.addPlayerChannel(adaptPlayer(e.player), e.player.getChannel())
    }

    @SubscribeEvent
    fun e(e: PlayerDisconnectEvent) {
        ChannelExecutor.removePlayerChannel(adaptPlayer(e.player), e.player.getChannel())
    }
    private fun ProxiedPlayer.getChannel(): Channel {
        return getProperty<Channel>("ch/ch")!!
    }

}