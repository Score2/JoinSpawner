package me.scoretwo.joinspawner.bukkit.spawn

import org.apache.commons.lang3.StringUtils
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.World

class Spawn(var locations: MutableList<Location> = mutableListOf(Location(null,0.0,0.0,0.0, 0.0F,0.0F)), var selection: String = "random") {

    companion object {

        fun readStringsToLocations(strings: MutableList<String>): MutableList<Location> {
            val locations = mutableListOf<Location>()

            for (s in strings) {
                locations.add(readStringToLocation(s))
            }

            return locations
        }

        fun readStringToLocation(string: String): Location {
            val args = string.split(",").toMutableList()

            lateinit var world: World
            var x = 0.0
            var y = 0.0
            var z = 0.0
            var yaw = 0.0F
            var pitch = 0.0F

            when (args.size) {
                5 -> {
                    world = Bukkit.getWorld(args[5])!!
                    x = args[4].toDouble()
                    y = args[3].toDouble()
                    z = args[2].toDouble()
                    yaw = args[1].toDouble().toFloat()
                    pitch = args[0].toDouble().toFloat()
                }
                4 -> {
                    x = args[4].toDouble()
                    y = args[3].toDouble()
                    z = args[2].toDouble()
                    yaw = args[1].toDouble().toFloat()
                    pitch = args[0].toDouble().toFloat()
                }
                3 -> {
                    world = Bukkit.getWorld(args[3])!!
                    x = args[2].toDouble()
                    y = args[1].toDouble()
                    z = args[0].toDouble()
                }
                2 -> {
                    x = args[2].toDouble()
                    y = args[1].toDouble()
                    z = args[0].toDouble()
                }
            }


            return Location(world, x, y, z, yaw, pitch)
        }

    }

}