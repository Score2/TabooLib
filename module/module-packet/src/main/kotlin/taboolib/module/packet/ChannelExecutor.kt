package taboolib.module.packet

import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelDuplexHandler
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelInboundHandlerAdapter
import io.netty.channel.ChannelOutboundHandlerAdapter
import io.netty.channel.ChannelPromise
import taboolib.common.LifeCycle
import taboolib.common.platform.Awake
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

    private val DECODER_NAME = "taboolib_decoder_${pluginId}"
    private val ENCODER_NAME = "taboolib_encoder_${pluginId}"

    private val HANDLER_NAME = "taboolib_packet_handler_${pluginId}"
    private val HANDLER_IMPL_NAME = "taboolib_packet_impl_handler_${pluginId}"

    private val pipelineService = Executors.newSingleThreadExecutor()

    // For server
    @Awake(LifeCycle.ENABLE)
    fun initChannels() {
        implementations<PlatformProtocolHandler>().getServerChannels().forEach { channel ->
//            channel.pipeline().addFirst(DECODER_NAME, PacketDecode(PacketFlow.SERVERBOUND))
//            channel.pipeline().addFirst(ENCODER_NAME, PacketEncode(PacketFlow.CLIENTBOUND))

            /*channel.pipeline().addFirst("taboolib_packet_out_$pluginId", object : ChannelDuplexHandler() {

            })*/

//            channel.pipeline().addLast(HANDLER_NAME, PacketHandler(PacketFlow.SERVERBOUND))

            channel.pipeline().addFirst("taboolib_packet_out_$pluginId", object : ChannelOutboundHandlerAdapter() {
                override fun write(ctx: ChannelHandlerContext, msg: Any, promise: ChannelPromise) {
                    if (msg !is ByteBuf) {
                        return super.write(ctx, msg, promise)
                    }
                    val slice = msg.copy().markReaderIndex()
                    val packetId = slice.readVarInt()

                    val packet = BytePacket(packetId, PacketFlow.CLIENTBOUND, slice)

                    if (PacketEvent.Receive(player, packet).call()) {
                        slice.markReaderIndex()
                        slice.markWriterIndex()
                        msg.clear()
                        msg.writeBytes(slice)

                        super.write(ctx, msg, promise)
                    }
                }
            })
        }
    }

    fun initPlayer(player: ProxyPlayer) {
        val channel = implementations<PlatformProtocolHandler>().getChannel(player)
        pipelineService.submit {
//            channel.pipeline().addFirst(DECODER_NAME, PacketDecode(PacketFlow.CLIENTBOUND))
//            channel.pipeline().addFirst(ENCODER_NAME, PacketEncode(PacketFlow.SERVERBOUND))

//            channel.pipeline().addLast(HANDLER_NAME, PacketHandler(PacketFlow.CLIENTBOUND))

            channel.pipeline().addFirst("taboolib_packet_in_$pluginId", object : ChannelOutboundHandlerAdapter() {
                override fun write(ctx: ChannelHandlerContext, msg: Any, promise: ChannelPromise) {
                    if (msg !is ByteBuf) {
                        return super.write(ctx, msg, promise)
                    }
                    val slice = msg.copy()
                    val packetId = slice.readVarInt()

                    val packet = BytePacket(packetId, PacketFlow.SERVERBOUND, slice)

                    if (PacketEvent.Send(player, packet).call()) {
                        slice.markReaderIndex()
                        slice.markWriterIndex()
                        msg.clear()
                        msg.writeBytes(slice)

                        super.write(ctx, msg, promise)
                    }
                }
            })
        }
    }


}