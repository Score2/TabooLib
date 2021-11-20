package taboolib.module.packet.sponge7

import org.spongepowered.api.Sponge
import org.spongepowered.api.event.network.ClientConnectionEvent
import taboolib.common.platform.Platform
import taboolib.common.platform.PlatformImplementation
import taboolib.common.platform.PlatformSide
import taboolib.common.platform.event.SubscribeEvent
import taboolib.module.packet.PlatformProtocolHandler
import taboolib.platform.Sponge7Plugin

/**
 * TabooLib
 * taboolib.module.packet.sponge.ProtocolSponge
 *
 * @author Score2
 * @since 2021/11/19 15:59
 */
@PlatformSide([Platform.SPONGE_API_7])
@PlatformImplementation(Platform.SPONGE_API_7)
object ProtocolSponge7 : PlatformProtocolHandler {

    override val DECODER_BASE_NAME = "decoder"
    override val ENCODER_BASE_NAME = "encoder"

    @SubscribeEvent
    fun e(e: ClientConnectionEvent.Join) {
        // TODO
    }

    @SubscribeEvent
    fun e(e: ClientConnectionEvent.Disconnect) {
        // TODO
    }
}