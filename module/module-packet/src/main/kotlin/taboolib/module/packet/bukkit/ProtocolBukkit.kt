package taboolib.module.packet.bukkit

import io.netty.channel.Channel
import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import taboolib.common.platform.Platform
import taboolib.common.platform.PlatformImplementation
import taboolib.common.platform.PlatformSide
import taboolib.common.platform.event.SubscribeEvent
import taboolib.common.platform.function.adaptPlayer
import org.tabooproject.reflex.Reflex.Companion.getProperty
import taboolib.common.platform.ProxyPlayer
import taboolib.module.nms.MinecraftVersion
import taboolib.module.nms.nmsProxy
import taboolib.module.packet.ChannelExecutor
import taboolib.module.packet.PlatformProtocolHandler

/**
 * TabooLib
 * taboolib.module.packet.bukkit.ProtocolBukkit
 *
 * @author Score2
 * @since 2021/11/19 11:40
 */
@PlatformSide([Platform.BUKKIT])
@PlatformImplementation(Platform.BUKKIT)
object ProtocolBukkit : PlatformProtocolHandler {

    @SubscribeEvent
    fun e(e: PlayerJoinEvent) {
        ChannelExecutor.initPlayer(adaptPlayer(e.player))
    }
    @SubscribeEvent
    fun e(e: PlayerQuitEvent) {

    }

    override fun getServerChannels(): List<Channel> {
        return nmsProxy<NMS>().getServerChannels()
    }

    override fun getChannel(player: ProxyPlayer): Channel {
        val playerConnection = if (MinecraftVersion.isUniversal) {
            player.cast<Player>().getProperty<Any>("entity/connection")!!
        } else {
            player.cast<Player>().getProperty<Any>("entity/playerConnection")!!
        }
        // playerConnection 被异常注入
        return try {
            if (MinecraftVersion.isUniversal) {
                playerConnection.getProperty<Channel>("connection/channel")!!
            } else {
                playerConnection.getProperty<Channel>("networkManager/channel")!!
            }
        } catch (ex: Throwable) {
            throw IllegalStateException("Unable to get player Channel from ${playerConnection.javaClass}", ex)
        }
    }
}