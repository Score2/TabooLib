package taboolib.module.packet.velocity

import com.velocitypowered.api.event.connection.DisconnectEvent
import com.velocitypowered.api.event.connection.PostLoginEvent
import com.velocitypowered.api.proxy.Player
import io.netty.channel.Channel
import taboolib.common.platform.event.SubscribeEvent
import taboolib.common.platform.function.adaptPlayer
import taboolib.common.reflect.Reflex.Companion.getProperty
import taboolib.module.packet.ChannelExecutor

/**
 * TabooLib
 * taboolib.module.packet.velocity.ProtocolVelocity
 *
 * @author Score2
 * @since 2021/11/19 15:34
 */
object ProtocolVelocity {

    @SubscribeEvent
    fun e(e: PostLoginEvent) {
        ChannelExecutor.addPlayerChannel(adaptPlayer(e.player), e.player.getChannel())
    }

    @SubscribeEvent
    fun e(e: DisconnectEvent) {
        ChannelExecutor.removePlayerChannel(adaptPlayer(e.player), e.player.getChannel())
    }
    private fun Player.getChannel(): Channel {
        return getProperty<Channel>("connection/channel")!!
    }

}