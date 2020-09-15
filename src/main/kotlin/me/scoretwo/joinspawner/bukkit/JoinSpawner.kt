package me.scoretwo.joinspawner.bukkit

import me.scoretwo.joinspawner.bukkit.listeners.PlayerListeners
import me.scoretwo.joinspawner.bukkit.spawn.Spawn
import org.bukkit.Bukkit
import org.bukkit.command.CommandMap
import org.bukkit.command.SimpleCommandMap
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.java.JavaPlugin

class JoinSpawner: JavaPlugin() {

    init {
        instance = this
    }

    @Synchronized
    override fun onEnable() {
        saveDefaultConfig()
        reloadConfig()

        spawns.clear()
        for (key in config.getConfigurationSection("spawns")!!.getKeys(false)) {
            val section = config.getConfigurationSection("spawns.$key")!!

            spawns.add(Spawn(
                Spawn.readStringsToLocations(section.getStringList("locations")),
                if (config.contains("selection")) section.getString("selection")!! else "random")
            )

        }

        Bukkit.getPluginManager().registerEvents(PlayerListeners(), this)
    }

    companion object {
        lateinit var instance: JoinSpawner

        val spawns = mutableListOf<Spawn>()

        fun onReload() {
            instance.onDisable()
            instance.onEnable()
        }

        fun getCommandMap(): CommandMap {
            return Bukkit.getServer().javaClass.getDeclaredMethod("getCommandMap").invoke(Bukkit.getServer()) as SimpleCommandMap
        }
    }

}