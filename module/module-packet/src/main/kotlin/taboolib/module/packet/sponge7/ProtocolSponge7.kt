package taboolib.module.packet.sponge7

import org.spongepowered.api.Sponge
import org.spongepowered.api.event.network.ClientConnectionEvent
import taboolib.common.platform.event.SubscribeEvent

/**
 * TabooLib
 * taboolib.module.packet.sponge.ProtocolSponge
 *
 * @author Score2
 * @since 2021/11/19 15:59
 */
object ProtocolSponge7 {

    @SubscribeEvent
    fun e(e: ClientConnectionEvent.Join) {
        e.targetEntity.connection
//        Sponge.getChannelRegistrar().getOrCreateRaw()
    }

    @SubscribeEvent
    fun e(e: ClientConnectionEvent.Disconnect) {

    }
}