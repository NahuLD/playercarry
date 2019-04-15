package me.nahuld.playercarry.listeners;

import me.nahuld.playercarry.Main;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

import static me.nahuld.playercarry.Main.message;

public class PlayerListener implements Listener {

    private Map<UUID, UUID> requests;
    private Set<UUID> onHold;

    public PlayerListener(Main main) {
        this.requests = main.getRequests();
        this.onHold = main.getOnHold();
    }

    @EventHandler
    public void onPlayerInteractAtEntity(PlayerInteractAtEntityEvent event) {
        UUID requester = event.getPlayer().getUniqueId();
        Entity entity = event.getRightClicked();
        if (!(entity instanceof Player) || !onHold.contains(requester)) return;
        requests.put(entity.getUniqueId(), requester);
        message((Player) entity, event.getPlayer());
    }
}
