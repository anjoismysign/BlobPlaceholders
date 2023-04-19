package us.mytheria.blobplaceholder.entities;

import org.bukkit.entity.Player;

public class DeathCauseParser {
    private final String message;

    public DeathCauseParser(String message) {
        this.message = message;
    }

    public String parse(Player player) {
        return message.replace("%player%", player.getName());
    }
}
