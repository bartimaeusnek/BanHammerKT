package com.github.bartimaeusnek.banhammer

import java.util.Date
import java.util.UUID

import com.mojang.authlib.GameProfile

import cpw.mods.fml.common.FMLCommonHandler
import cpw.mods.fml.common.eventhandler.SubscribeEvent
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent
import net.minecraft.entity.player.EntityPlayerMP
import net.minecraft.server.MinecraftServer
import net.minecraft.server.management.UserListBansEntry
import net.minecraft.server.management.UserListEntry

class ktEventHandler {

    @SubscribeEvent
    fun onlogin(event: PlayerLoggedInEvent) {
        val id = event.player.getUniqueID()
        for (name: String in BanHammer.bans)
            if (id.toString() == name) {
                if (FMLCommonHandler.instance().getSide().isServer()) {
                    event.player.setDead()
                    var playername: String? = null
                    for (activename in MinecraftServer.getServer().getAllUsernames()) {
                        val id2 = MinecraftServer.getServer().getPlayerProfileCache().getGameProfileForUsername(activename).getId();
                        if (id2.toString().equals(name)) {
                            playername = activename;
                        }
                    }

                    val gameprofile: GameProfile = GameProfile(event.player.getUniqueID(), playername)
                    val userlistbansentry: UserListBansEntry = UserListBansEntry(gameprofile, null, "BanHammerModpackBan", null, BanLoader.banreason)
                    MinecraftServer.getServer().getConfigurationManager().getBannedPlayers().addEntry(userlistbansentry)
                    val entityplayermp = MinecraftServer.getServer().getConfigurationManager().getPlayerByUsername(playername);

                    if (entityplayermp != null) {
                        entityplayermp.playerNetServerHandler.kickPlayerFromServer("You have been banned from all of this Modpack's Servers.");
                    }

                }
            }
    }
}