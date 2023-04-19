package us.mytheria.blobplaceholder;

import us.mytheria.bloblib.managers.BlobPlugin;
import us.mytheria.blobplaceholder.director.PlaceholderDirector;

public final class BlobPlaceholder extends BlobPlugin {
    private PlaceholderDirector director;

    @Override
    public void onEnable() {
        this.director = new PlaceholderDirector(this);
    }

    @Override
    public void onDisable() {
        director.unload();
    }

    @Override
    public PlaceholderDirector getManagerDirector() {
        return this.director;
    }
}
