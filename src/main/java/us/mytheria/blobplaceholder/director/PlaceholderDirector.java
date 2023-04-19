package us.mytheria.blobplaceholder.director;

import us.mytheria.bloblib.managers.ManagerDirector;
import us.mytheria.blobplaceholder.BlobPlaceholder;

public class PlaceholderDirector extends ManagerDirector {
    private final DeathCauseManager causeManager;
    private final ConfigManager configManager;
    private final PapiManager papiManager;

    public PlaceholderDirector(BlobPlaceholder plugin) {
        super(plugin);
        configManager = new ConfigManager(this);
        causeManager = new DeathCauseManager(this);

        papiManager = new PapiManager(this);
    }

    @Override
    public void unload() {
        papiManager.unload();
        causeManager.unload();
        configManager.unload();
    }

    @Override
    public void reload() {
        unload();
        configManager.reload();
        causeManager.reload();
        papiManager.reload();
    }

    @Override
    public BlobPlaceholder getPlugin() {
        return (BlobPlaceholder) super.getPlugin();
    }

    /**
     * Get the ConfigManager
     *
     * @return ConfigManager
     */
    public ConfigManager getConfigManager() {
        return configManager;
    }

    /**
     * Get the DeathCauseManager
     *
     * @return DeathCauseManager
     */
    public DeathCauseManager getCauseManager() {
        return causeManager;
    }
}
