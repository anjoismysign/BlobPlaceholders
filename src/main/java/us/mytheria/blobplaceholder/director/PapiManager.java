package us.mytheria.blobplaceholder.director;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;
import us.mytheria.bloblib.managers.ManagerDirector;

public class PapiManager extends PlaceholderManager {
    private PlaceholderExpansion expansion;

    public PapiManager(ManagerDirector managerDirector) {
        super(managerDirector);
        reload();
    }

    @Override
    public void reload() {
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") == null) {
            getPlugin().getAnjoLogger().log("PlaceholderAPI not found, not registering PlaceholderAPI expansion for " + getPlugin().getName());
        } else {
            expansion = new PlaceholderExpansion() {
                @Override
                public @NotNull String getIdentifier() {
                    return "blobplaceholder";
                }

                @Override
                public @NotNull String getAuthor() {
                    return "anjoismysign";
                }

                @Override
                public @NotNull String getVersion() {
                    return "1.0";
                }

                @Override
                public String onPlaceholderRequest(org.bukkit.entity.Player player, @NotNull String identifier) {
                    if (player == null) {
                        return "";
                    }
                    if (identifier.equals("last_death_cause")) {
                        return PapiManager.this.getPlugin().getManagerDirector()
                                .getCauseManager().parse(player);
                    }
                    return null;
                }
            };
            expansion.register();
        }
    }

    @Override
    public void unload() {
        if (expansion != null)
            expansion.unregister();
    }
}
