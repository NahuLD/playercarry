package me.nahuld.playercarry;

import co.aikar.commands.BukkitCommandManager;
import me.nahuld.playercarry.commands.PickupCommand;
import me.nahuld.playercarry.commands.SetDownCommand;
import me.nahuld.playercarry.listeners.PlayerListener;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public class Main extends JavaPlugin {

    private final Map<UUID /* requested */, UUID /* requester */> requests = new HashMap<>();
    private final Set<UUID> onHold = new HashSet<>();

    private BukkitCommandManager commandManager;

    @Override
    public void onEnable() {
        commandManager = new BukkitCommandManager(this);
        Arrays.asList(new PickupCommand(this), new SetDownCommand())
                .forEach(commandManager::registerCommand);
        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
    }

    @Override
    public void onDisable() {
        //TODO: stuff
    }

    public Map<UUID, UUID> getRequests() {
        return requests;
    }

    public Set<UUID> getOnHold() {
        return onHold;
    }

    public static void message(Player requester, Player requested) {
        requested.sendMessage(color("&e" + requester.getDisplayName() + " &ahas requested to carry you!"));
        requested.sendRawMessage(
                "[\"\",{\"text\":\"[ACCEPT] \",\"color\":\"green\",\"bold\":true,\"clickEvent\":{\"action\":\"run_command\",\"value\":\"pickup accept\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"\",\"extra\":[{\"text\":\"Accept the pickup request!\",\"color\":\"gold\"}]}}}," +
                        "{\"text\":\"[DECLINE]\",\"color\":\"red\",\"bold\":true,\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/pickup decline\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"\",\"extra\":[{\"text\":\"Decline the pickup request!\",\"color\":\"gold\"}]}}}]"
        );
    }

    public static String color(String input) {
        return ChatColor.translateAlternateColorCodes('&', input);
    }
}
