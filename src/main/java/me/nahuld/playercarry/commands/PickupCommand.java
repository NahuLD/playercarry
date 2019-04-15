package me.nahuld.playercarry.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Optional;
import co.aikar.commands.annotation.Subcommand;
import me.nahuld.playercarry.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

import static me.nahuld.playercarry.Main.message;

@CommandAlias("pickup")
public class PickupCommand extends BaseCommand {

    private Map<UUID, UUID> requests;
    private Set<UUID> onHold;

    public PickupCommand(Main main) {
        this.requests = main.getRequests();
        this.onHold = main.getOnHold();
    }

    @Default
    @Subcommand("request")
    public void request(Player player, @Optional Player target) {
        if (target != null) {
            requests.put(target.getUniqueId(), player.getUniqueId());
            message(player, target);
            return;
        }
        onHold.add(player.getUniqueId());
    }

    @Subcommand("accept")
    public void accept(Player player) {
        UUID requested = player.getUniqueId();
        if (!requests.containsKey(requested)) return;
        Bukkit.getPlayer(requests.get(requested)).addPassenger(player);

        requests.remove(requested);
    }

    @Subcommand("decline")
    public void decline(Player player) {
        requests.remove(player.getUniqueId());
    }
}
