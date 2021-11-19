package taboolib.module.packet

import io.netty.buffer.ByteBuf
import io.netty.channel.Channel
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToMessageDecoder
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

    private val id = "taboolib_${pluginId}_packet_handler"
    private val addChannelService = Executors.newSingleThreadExecutor()
    private val removeChannelService = Executors.newSingleThreadExecutor()


    fun addPlayerChannel(channel: Channel) {
        addChannelService.submit {
            try {
                channel.pipeline().addBefore("packet_handler", id, object : MessageToMessageDecoder<ByteBuf>() {
                        override fun decode(channelHandlerContext: ChannelHandlerContext, byteBuf: ByteBuf, list: MutableList<Any>) {
                            TODO("Not yet implemented")
                        }
                    }
                )
            } catch (ex: Throwable) {
                ex.printStackTrace()
            }
        }
    }
}