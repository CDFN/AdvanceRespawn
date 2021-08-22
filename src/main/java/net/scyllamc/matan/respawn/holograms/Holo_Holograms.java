package net.scyllamc.matan.respawn.holograms;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import java.util.UUID;

import net.scyllamc.matan.respawn.Config;

import net.scyllamc.matan.respawn.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Holo_Holograms implements Holo {
	
	
	public void spawnHolo(Player p) {
		
		if (!Config.SHOW_HOLOGRAMS.getBoolenValue()) 
			return;
		
		ItemStack skull = new ItemStack(Material.LEGACY_SKULL_ITEM, 1, (short) 3);
		SkullMeta meta = (SkullMeta) skull.getItemMeta();
		meta.setOwner(p.getName().toString());
		skull.setItemMeta(meta);

		if ((Bukkit.getPluginManager().isPluginEnabled("Holograms"))) {

			final Hologram hologram = HologramsAPI.createHologram(Main.plugin, p.getLocation().add(0.0D, 2D, 0.0D));

			hologram.appendItemLine(skull);
			hologram.appendTextLine(Config.HOLOGRAM_LINE_1.getFormattedValue(p, 0));
			hologram.appendTextLine(Config.HOLOGRAM_LINE_2.getFormattedValue(p, 0));
			new BukkitRunnable() {
				public void run() {
					hologram.delete();
				}
			}.runTaskLater(Bukkit.getPluginManager().getPlugin("AdvanceRespawn"), Config.HOLOGRAM_DURATION.getIntValue() * 20);
			return;
		}
	}
}