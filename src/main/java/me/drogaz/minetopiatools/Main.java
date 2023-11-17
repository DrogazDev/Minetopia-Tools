package me.drogaz.minetopiatools;

import lombok.Getter;
import me.drogaz.minetopiatools.commands.tools;
import me.drogaz.minetopiatools.utils.ConfigurationFile;
import me.drogaz.minetopiatools.utils.GUIHolder;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
//    private static @Getter ConfigurationFile data;

    @Override
    public void onEnable() {
        GUIHolder.init(this);
        new tools(this);

        saveDefaultConfig();

//        data = new ConfigurationFile(this, "data.yml", true);
//        data.saveConfig();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
