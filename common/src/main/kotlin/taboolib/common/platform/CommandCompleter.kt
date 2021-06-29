package taboolib.common.platform

import taboolib.common.io.Isolated

/**
 * TabooLib
 * taboolib.common.CommandTabCompleter
 *
 * @author sky
 * @since 2021/6/24 11:49 下午
 */
interface CommandCompleter {

    fun execute(sender: ProxyCommandSender, command: CommandStructure, name: String, args: Array<String>): List<String>?
}