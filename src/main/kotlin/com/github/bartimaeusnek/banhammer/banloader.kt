package com.github.bartimaeusnek.banhammer

import cpw.mods.fml.common.event.FMLPreInitializationEvent
import net.minecraftforge.common.config.Configuration
import java.util.ArrayList
import java.io.File

class BanLoader {
    companion object {
        final val hardcoded: List<String> = listOf("UUID", "UUID2")
        var configbans = arrayOf("UUID1", "UUID2")
        var banreason = "BanHammerModpackBan"
    }

    fun loadBans(event: FMLPreInitializationEvent): ArrayList<String> {
        var bandir = File(event.modConfigurationDirectory, "Bans")
        var ret: ArrayList<String> = ArrayList<String>()
        val banconf = Configuration(event.getSuggestedConfigurationFile())

        configbans = banconf.getStringList("UUIDs", "Bans", configbans, "")
        banreason = banconf.getString("Ban Reason", "Bans", banreason, "")

        if (banconf.hasChanged()) {
            banconf.save()
        }
        if (!bandir.exists()) {
            bandir.mkdirs()
        }

        for (file: File in bandir.listFiles()) {
            var name: String = file.getName()
            if (name.contains(".dat"))
                name = name.substring(0, name.length - ".dat".length)
            ret.add(name)
        }

        for (s in configbans) {
            ret.add(s)
        }
        ret.addAll(hardcoded)
        return ret
    }

}