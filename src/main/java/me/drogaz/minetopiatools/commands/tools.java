package me.drogaz.minetopiatools.commands;

import io.github.bananapuncher714.nbteditor.NBTEditor;
import me.drogaz.minetopiatools.Main;
import me.drogaz.minetopiatools.menus.ItemMenu;
import me.drogaz.minetopiatools.utils.ItemBuilder;
import me.drogaz.minetopiatools.utils.Utils;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class tools implements CommandExecutor {

    Main main;
    public tools(Main plugin) {
        main = plugin;
        plugin.getCommand("tools").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            System.out.println("Dit commando kan niet gebruikt worden door de console!");
            return true;
        }

        Player speler = (Player) sender;

        if(args.length == 0) {
            speler.sendMessage(Utils.chat("&8&l&m--------------------------------------------"));
            speler.sendMessage(Utils.chat("&c/"+label+" &8- &7Dit commando."));
            speler.sendMessage(Utils.chat("&c/"+label+" lore &8- &7Zet de server lore op een item (config.yml)."));
            speler.sendMessage(Utils.chat("&c/"+label+" glow &8- &7Geef je item glow effect."));
            speler.sendMessage(Utils.chat("&c/"+label+" nbt &4nbtTag nbtValue &8- &7Zet custom NBT data op je item"));
            speler.sendMessage(Utils.chat(""));
            speler.sendMessage(Utils.chat("&cGemaakt door &aDrogazDevelopment (&283J&a)"));
            speler.sendMessage(Utils.chat("&cAliases: tools, mttools, mtt"));
            speler.sendMessage(Utils.chat("&cVersie: &av0.5"));
            speler.sendMessage(Utils.chat("&8&l&m--------------------------------------------"));
        }

        else if(args[0].equalsIgnoreCase("lore")) {
            if(speler.hasPermission("mttools.staff")) {
                ItemStack holdingItem = speler.getItemInHand();
                ItemMeta holdingMeta = holdingItem.getItemMeta();

                if(holdingItem.getType() == Material.AIR) {
                    speler.sendMessage(Utils.chat("&cJe houd geen item vast."));
                    return false;
                }

                List<String> lore = new ArrayList<String>();
                lore.add(Utils.chat(main.getConfig().getString("MinetopiaTools.serverLore")));
                holdingMeta.setLore(lore);
                holdingItem.setItemMeta(holdingMeta);

                speler.sendMessage(Utils.chat("&aItem Succesvol server lore gegeven!"));
            } else {
                speler.sendMessage(Utils.chat("&cJe hebt hier geen toegang tot!"));
            }
        }

        else if (args[0].equalsIgnoreCase("nbt")) {
            if(speler.hasPermission("mttools.staff")) {
                if (args.length != 3) {
                    speler.sendMessage(Utils.chat("&cSyntax: /tools &4nbt nbtTag nbtValue"));
                    return false;
                }
                ItemStack holdingItem = speler.getItemInHand();

                if(holdingItem.getType() == Material.AIR) {
                    speler.sendMessage(Utils.chat("&cJe houd geen item vast."));
                    return false;
                }

                ItemStack holdingItemUse = speler.getItemInHand();
                ItemStack cloneItemUse = holdingItemUse.clone();

                cloneItemUse = NBTEditor.set(cloneItemUse, args[2], args[1]);
                speler.getInventory().remove(holdingItemUse);
                speler.getInventory().addItem(cloneItemUse);

                speler.sendMessage(Utils.chat("&aItem Succesvol NBT: &2&l" + args[1] + " &a&l" + args[2] + " &aGegeven!"));

            } else {
                speler.sendMessage(Utils.chat("&cJe hebt hier geen toegang tot!"));
            }
        }

        else if(args[0].equalsIgnoreCase("glow")) {
            if(speler.hasPermission("mttools.staff")) {
                if (args.length != 1) {
                    speler.sendMessage(Utils.chat("&cSyntax: /tools &4glow"));
                    return false;
                }
            }

            ItemStack holdingItem = speler.getItemInHand();
            if(holdingItem.getType() == Material.AIR) {
                speler.sendMessage(Utils.chat("&cJe houd geen item vast."));
                return false;
            }

            holdingItem.addUnsafeEnchantment(Enchantment.LUCK, 1);
            ItemMeta holdingItemMeta = holdingItem.getItemMeta();

            holdingItemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

            holdingItem.setItemMeta(holdingItemMeta);

            speler.sendMessage(Utils.chat("&aItem Succesvol glow gegeven!"));
        }


        return false;
    }
}
