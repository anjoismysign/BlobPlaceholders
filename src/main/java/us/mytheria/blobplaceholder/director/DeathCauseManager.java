package us.mytheria.blobplaceholder.director;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import us.mytheria.bloblib.managers.ManagerDirector;
import us.mytheria.blobplaceholder.entities.DeathCauseParser;

import java.util.HashMap;
import java.util.Map;

public class DeathCauseManager extends PlaceholderManager implements Listener {
    private final Map<String, EntityDamageEvent.DamageCause> map;
    private Map<EntityDamageEvent.DamageCause, DeathCauseParser> parserMap;

    public DeathCauseManager(ManagerDirector managerDirector) {
        super(managerDirector);
        this.map = new HashMap<>();
        Bukkit.getPluginManager().registerEvents(this, getPlugin());
        reload();
    }

    @Override
    public void reload() {
        parserMap = new HashMap<>();
        ConfigManager configManager = getManagerDirector().getConfigManager();
        ConfigurationSection deathCauseParserSection = configManager.getDeathCauseParserSection();
        deathCauseParserSection.getKeys(false).forEach(key -> {
            EntityDamageEvent.DamageCause cause;
            try {
                cause = EntityDamageEvent.DamageCause.valueOf(key);
            } catch (IllegalArgumentException e) {
                getPlugin().getAnjoLogger().singleError("Invalid DamageCause: " + key);
                return;
            }
            if (!deathCauseParserSection.isString(deathCauseParserSection.getString(key)))
                return;
            String message = deathCauseParserSection.getString(key);
            parserMap.put(cause, new DeathCauseParser(message));
        });
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        EntityDamageEvent.DamageCause cause = event.getEntity().getLastDamageCause().getCause();
        map.put(event.getEntity().getName(), cause);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        map.remove(event.getPlayer().getName());
    }

    /**
     * Parse the death cause of the player
     *
     * @param player the player
     * @return the parsed death cause
     */
    public String parse(Player player) {
        EntityDamageEvent.DamageCause cause = map.get(player.getName());
        if (cause == null)
            return "";
        DeathCauseParser parser = parserMap.get(cause);
        if (parser == null)
            throw new IllegalStateException("No parser for " + cause.name());
        return parser.parse(player);
    }
}
