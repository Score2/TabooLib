package taboolib.module.packet.bukkit

import io.netty.channel.Channel
import net.minecraft.network.NetworkManager
import net.minecraft.server.MinecraftServer
import net.minecraft.server.dedicated.DedicatedServer
import net.minecraft.server.network.ServerConnection
import org.bukkit.Bukkit
import org.tabooproject.reflex.Reflex.Companion.getProperty
import org.tabooproject.reflex.Reflex.Companion.invokeMethod
import taboolib.common.platform.Platform
import taboolib.common.platform.PlatformImplementation
import taboolib.common.platform.PlatformSide
import taboolib.module.nms.MinecraftVersion

/**
 * TabooLib
 * taboolib.module.packet.bukkit.NMSImpl
 *
 * @author Score2
 * @since 2022/10/02 9:59
 */
@PlatformSide([Platform.BUKKIT])
class NMSImpl : NMS() {

    private val version = MinecraftVersion.majorLegacy

    override fun getServerChannels(): List<Channel> {
        val networks = when {
            version >= 11700 -> "connections"
            version >= 11500 -> "connectedChannels"
            version >= 11300 -> "g"
            else -> "h"
        }

        return Bukkit.getServer().invokeMethod<DedicatedServer>("getServer")!!
            .invokeMethod<Any>("getServerConnection")!!
            .getProperty<List<NetworkManager>>(networks)!!
            .map { it.getProperty<Channel>("channel")!! }
    }

}