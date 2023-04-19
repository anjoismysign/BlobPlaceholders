package us.mytheria.blobplaceholder.director;

import us.mytheria.bloblib.managers.Manager;
import us.mytheria.bloblib.managers.ManagerDirector;
import us.mytheria.blobplaceholder.BlobPlaceholder;

public class PlaceholderManager extends Manager {
    public PlaceholderManager(ManagerDirector managerDirector) {
        super(managerDirector);
    }

    @Override
    public PlaceholderDirector getManagerDirector() {
        return (PlaceholderDirector) super.getManagerDirector();
    }

    @Override
    public BlobPlaceholder getPlugin() {
        return (BlobPlaceholder) super.getPlugin();
    }
}
