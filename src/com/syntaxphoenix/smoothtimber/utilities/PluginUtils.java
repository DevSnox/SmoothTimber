package com.syntaxphoenix.smoothtimber.utilities;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

import com.syntaxphoenix.smoothtimber.SmoothTimber;
import com.syntaxphoenix.smoothtimber.config.CutterConfig;
import com.syntaxphoenix.smoothtimber.listener.BlockBreakListener;
import com.syntaxphoenix.smoothtimber.stats.SyntaxPhoenixStats;
import com.syntaxphoenix.smoothtimber.version.manager.VersionChanger;
import com.syntaxphoenix.smoothtimber.version.manager.VersionExchanger;

import net.md_5.bungee.api.ChatColor;

public class PluginUtils {

	public static PluginUtils utils;
	public static SmoothTimber m;
	public static VersionChanger changer;
	public static SyntaxPhoenixStats stats;

	public static void setUp(SmoothTimber main) {
		m = main;
		utils = new PluginUtils();
	}

	public PluginUtils() {
		changer = VersionExchanger.getVersionChanger(VersionExchanger.getMinecraftVersion());
		if (changer != null) {
			CutterConfig.load();
			registerListener();
			stats = new SyntaxPhoenixStats("7vTfe4hf", SmoothTimber.m);
		}
	}

	/*
	 * 
	 */

	private void checkPlugins(PluginManager pm) {
		if(pm.getPlugin("BlockyLog") != null) {
			if(CutterConfig.extension_blocky) {
				Locator.blockylog = true;
			}
		}
	}

	private void registerListener() {
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new BlockBreakListener(), SmoothTimber.m);
		
		checkPlugins(pm);
	}

	/*
	 * Console Util
	 */

	public static void sendConsoleMessage(String message) {
		Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', message));
	}

}
