package taboolib.platform

import org.bukkit.generator.ChunkGenerator
import org.bukkit.plugin.java.JavaPlugin
import taboolib.common.TabooLibCommon
import taboolib.common.io.findInstance
import taboolib.common.platform.execute
import taboolib.plugin.Plugin
import java.io.File

/**
 * TabooLib
 * taboolib.platform.BukkitPlugin
 *
 * @author sky
 * @since 2021/6/14 11:10 下午
 */
class BukkitPlugin : JavaPlugin() {

    val pluginInstance: Plugin?

    init {
        TabooLibCommon.init()
        pluginInstance = findInstance(Plugin::class.java)
    }

    override fun onLoad() {
        pluginInstance?.onLoad()
    }

    override fun onEnable() {
        pluginInstance?.onEnable()
        execute {
            pluginInstance?.onActive()
        }
    }

    override fun onDisable() {
        pluginInstance?.onDisable()
    }

    override fun getDefaultWorldGenerator(worldName: String, id: String?): ChunkGenerator? {
        return (pluginInstance as? BukkitWorldGenerator)?.getDefaultWorldGenerator(worldName, id) as? ChunkGenerator
    }

    public override fun getFile(): File {
        return super.getFile()
    }
}