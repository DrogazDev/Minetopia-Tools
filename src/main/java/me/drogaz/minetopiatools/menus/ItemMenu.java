package me.drogaz.minetopiatools.menus;

import me.drogaz.minetopiatools.utils.GUIHolder;
import me.drogaz.minetopiatools.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ItemMenu extends GUIHolder {

    public ItemMenu(Player speler) {

        this.inventory = Bukkit.createInventory(this, 6 * 9, Utils.chat("&7Item Menu"));



        speler.openInventory(this.inventory);

    }

    @Override
    public void onClick(InventoryClickEvent event) {

    }
}
