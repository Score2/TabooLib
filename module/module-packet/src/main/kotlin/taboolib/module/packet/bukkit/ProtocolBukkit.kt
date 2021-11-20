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
import taboolib.common.reflect.Reflex.Companion.getProperty
import taboolib.module.nms.MinecraftVersion
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

    override val DECODER_BASE_NAME = "decoder"
    override val ENCODER_BASE_NAME = "encoder"

    @SubscribeEvent
    fun e(e: PlayerJoinEvent) {
        ChannelExecutor.addPlayerChannel(adaptPlayer(e.player), e.player.getChannel())
    }
    @SubscribeEvent
    fun e(e: PlayerQuitEvent) {
        ChannelExecutor.removePlayerChannel(adaptPlayer(e.player), e.player.getChannel())
    }

    private fun Player.getChannel(): Channel {
        return if (MinecraftVersion.isUniversal) {
            getProperty<Channel>("entity/connection/connection/channel")!!
        } else {
            getProperty<Channel>("entity/playerConnection/networkManager/channel")!!
        }
    }
}