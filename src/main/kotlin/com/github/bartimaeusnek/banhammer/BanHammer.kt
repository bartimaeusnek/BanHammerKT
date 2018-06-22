package com.github.bartimaeusnek.banhammer

import cpw.mods.fml.common.Mod
import cpw.mods.fml.common.Mod.EventHandler
import cpw.mods.fml.common.FMLCommonHandler
import cpw.mods.fml.common.eventhandler.SubscribeEvent
import cpw.mods.fml.common.event.FMLPreInitializationEvent
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

import com.github.bartimaeusnek.banhammer.BanLoader
import com.github.bartimaeusnek.banhammer.ktEventHandler
import com.mojang.authlib.GameProfile

import java.util.UUID
import java.lang.RuntimeException
import java.util.ArrayList
import net.minecraft.entity.Entity
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.server.MinecraftServer
import net.minecraft.server.management.UserListBansEntry

@Mod(modid = BanHammer.Companion.MODID, name = BanHammer.MODNAME, version = BanHammer.Companion.VERSION, acceptableRemoteVersions = "*")
class BanHammer {
     companion object {
         final const val MODID: String = "BanHammerJ"
         final const val MODNAME: String = "BanHammerKT"
         final const val VERSION: String = "0.0.1"
         final val logger : Logger = LogManager.getLogger(MODID)
         var bans:ArrayList<String> = ArrayList<String>()
      }
    
    @EventHandler
    fun preInit(event: FMLPreInitializationEvent) {
		BanHammer.bans = BanLoader().loadBans(event)
        FMLCommonHandler.instance().bus().register(ktEventHandler())
    }
}
