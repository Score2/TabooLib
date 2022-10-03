package taboolib.module.packet

import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler

/**
 * TabooLib
 * taboolib.module.packet.PacketHandler
 *
 * 无实义, Packet 最终监听方为服务端底层, TabooLib 无需额外触发并执行
 *
 * @author Score2
 * @since 2022/10/02 1:36
 */
@Deprecated("错误的决定")
class PacketHandler(val side: PacketFlow) : SimpleChannelInboundHandler<Packet>() {

    override fun channelRead0(ctx: ChannelHandlerContext, packet: Packet) {
        TODO("Not yet implemented")
    }

}