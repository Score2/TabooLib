package taboolib.module.packet.bukkit

import io.netty.channel.Channel
import taboolib.common.platform.Platform
import taboolib.common.platform.PlatformSide

/**
 * TabooLib
 * taboolib.module.packet.bukkit.NMS
 *
 * @author Score2
 * @since 2022/10/02 9:57
 */
@PlatformSide([Platform.BUKKIT])
abstract class NMS {

    abstract fun getServerChannels(): List<Channel>

}