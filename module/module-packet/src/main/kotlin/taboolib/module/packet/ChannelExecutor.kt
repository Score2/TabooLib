package taboolib.module.packet

import io.netty.buffer.ByteBuf
import io.netty.channel.Channel
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToMessageDecoder
import io.netty.handler.codec.MessageToMessageEncoder
import org.bukkit.entity.Player
import taboolib.common.platform.ProxyPlayer
import taboolib.common.platform.function.pluginId
import java.util.concurrent.Executors

/**
 * TabooLib
 * taboolib.module.packet.ChannelExecutor
 *
 * @author Score2
 * @since 2021/11/18 23:57
 */
object ChannelExecutor {

    private val decoderId = "taboolib_${pluginId}_packet_decoder_handler"
    private val encoderId = "taboolib_${pluginId}_packet_encoder_handler"
    private val addChannelService = Executors.newSingleThreadExecutor()
    private val removeChannelService = Executors.newSingleThreadExecutor()

    fun addPlayerChannel(player: ProxyPlayer, channel: Channel) {
        addChannelService.submit {
            try {
                channel.pipeline().addBefore("packet_handler", decoderId, object : MessageToMessageDecoder<ByteBuf>() {
                    override fun decode(channelHandlerContext: ChannelHandlerContext, byteBuf: ByteBuf, list: MutableList<Any>) {
                        if (PacketEvent.Receive(player, Packet(byteBuf)).call()) {
                            list.add(byteBuf)
                        }
                    }
                })
            } catch (ex: Throwable) {
                ex.printStackTrace()
            }
            try {
                channel.pipeline().addBefore("packet_handler", encoderId, object : MessageToMessageEncoder<ByteBuf>() {
                    override fun encode(channelHandlerContext: ChannelHandlerContext, byteBuf: ByteBuf, list: MutableList<Any>) {
                        if (PacketEvent.Send(player, Packet(byteBuf)).call()) {
                            list.add(byteBuf)
                        }
                    }
                })
            } catch (ex: Throwable) {
                ex.printStackTrace()
            }
        }
    }
    fun removePlayerChannel(player: ProxyPlayer, channel: Channel) {
        removeChannelService.submit {
            try {
                if (channel.pipeline()[decoderId] != null) {
                    channel.pipeline().remove(decoderId)
                }
            } catch (ex: Throwable) {
                ex.printStackTrace()
            }
            try {
                if (channel.pipeline()[encoderId] != null) {
                    channel.pipeline().remove(encoderId)
                }
            } catch (ex: Throwable) {
                ex.printStackTrace()
            }
        }
    }
}