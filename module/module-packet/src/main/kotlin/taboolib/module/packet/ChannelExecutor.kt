package taboolib.module.packet

import io.netty.buffer.ByteBuf
import io.netty.channel.Channel
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToMessageDecoder
import io.netty.handler.codec.MessageToMessageEncoder
import taboolib.common.platform.ProxyPlayer
import taboolib.common.platform.function.implementations
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

    private val DECODER_BASE_NAME by lazy { implementations<PlatformProtocolHandler>().DECODER_BASE_NAME }
    private val ENCODER_BASE_NAME by lazy { implementations<PlatformProtocolHandler>().ENCODER_BASE_NAME }

    private val DECODER_NAME = "taboolib_${pluginId}_packet_decoder"
    private val ENCODER_NAME = "taboolib_${pluginId}_packet_encoder"

    private val addChannelService = Executors.newSingleThreadExecutor()
    private val removeChannelService = Executors.newSingleThreadExecutor()

    fun addPlayerChannel(player: ProxyPlayer, channel: Channel) {
        addChannelService.submit {
            try {
                channel.pipeline().addBefore(DECODER_BASE_NAME, DECODER_NAME, object : MessageToMessageDecoder<ByteBuf>() {
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
                channel.pipeline().addBefore(ENCODER_BASE_NAME, ENCODER_NAME, object : MessageToMessageEncoder<ByteBuf>() {
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
                if (channel.pipeline()[DECODER_NAME] != null) {
                    channel.pipeline().remove(DECODER_NAME)
                }
            } catch (ex: Throwable) {
                ex.printStackTrace()
            }
            try {
                if (channel.pipeline()[ENCODER_NAME] != null) {
                    channel.pipeline().remove(ENCODER_NAME)
                }
            } catch (ex: Throwable) {
                ex.printStackTrace()
            }
        }
    }
}