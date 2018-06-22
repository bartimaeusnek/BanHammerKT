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

@Mod(modid = BanHammer.Companion.MODID, name = BanHammer.MODID, version = BanHammer.Companion.VERSION, acceptableRemoteVersions = "*")
class BanHammer {
     companion object {
         final const val MODID: String = "BanHammerKT"
         final const val VERSION: String = "0.0.1"
         final val logger : Logger = LogManager.getLogger(MODID)
         var bans:ArrayList<String> = ArrayList<String>()
      }
    
    @EventHandler
    fun preInit(event: FMLPreInitializationEvent) {
		BanHammer.bans = BanLoader().loadBans(event)
        FMLCommonHandler.instance().bus().register(ktEventHandler())
    }
    
   /* @SubscribeEvent
    fun onlogin(event: PlayerLoggedInEvent)
    {
        val id : UUID = (event.player as net.minecraft.entity.Entity).getUniqueID()
            for ( name:String in bans )
                if ( id.toString() == name ){
                    if (FMLCommonHandler.instance().getEffectiveSide().isClient())
                        throw RuntimeException("You have been banned from all Modpack's Servers")
                    else if (FMLCommonHandler.instance().getEffectiveSide().isServer()){
                    event.player.setDead()
                    val gameprofile : GameProfile = GameProfile(event.player.getUniqueID(),null)
                    val userlistbansentry : UserListBansEntry = UserListBansEntry(gameprofile, null, "BanHammerModpackBan", null, null)
                    MinecraftServer.getServer().getConfigurationManager().getBannedPlayers().addEntry(userlistbansentry)
                    }
                } 
    }
 */
}
