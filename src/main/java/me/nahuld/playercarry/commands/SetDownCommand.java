package me.nahuld.playercarry.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import org.bukkit.entity.Player;

@CommandAlias("setdown|sitdown")
public class SetDownCommand extends BaseCommand {

    @Default
    public void leave(Player player) {
        player.getPassengers().forEach(player::removePassenger);
    }
}
