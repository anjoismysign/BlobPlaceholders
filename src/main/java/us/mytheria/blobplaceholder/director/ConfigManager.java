package us.mytheria.blobplaceholder.director;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import us.mytheria.blobplaceholder.BlobPlaceholder;

public class ConfigManager extends PlaceholderManager {
    private FileConfiguration configuration;
    private ConfigurationSection deathCauseParserSection;

    public ConfigManager(PlaceholderDirector managerDirector) {
        super(managerDirector);
        reload();
    }

    @Override
    public void reload() {
        BlobPlaceholder main = getPlugin();
        main.reloadConfig();
        main.saveDefaultConfig();
        main.getConfig().options().copyDefaults(true);
        main.saveConfig();
        configuration = main.getConfig();
        deathCauseParserSection = configuration.getConfigurationSection("DeathCauseParser");
    }

    public FileConfiguration getConfiguration() {
        return configuration;
    }

    public ConfigurationSection getDeathCauseParserSection() {
        return deathCauseParserSection;
    }
}