import java.util.ArrayList;
import java.util.List;

public class ModularPlayer {
    private List<MediaSource> playlist = new ArrayList<>();
    private List<PlayerPlugin> plugins = new ArrayList<>();
    private RenderStrategy renderStrategy;

    public void addSource(MediaSource source) {
        playlist.add(source);
    }

    public void addPlugin(PlayerPlugin plugin) {
        plugins.add(plugin);
    }

    public void setRenderStrategy(RenderStrategy strategy) {
        this.renderStrategy = strategy;
    }

    public void playAll() {
        System.out.println("[ModularPlayer] Playing all sources using: " + 
            (renderStrategy == null ? "No Renderer" : renderStrategy.getClass().getSimpleName()));

        for (MediaSource source : playlist) {
            source.play();

            if (renderStrategy != null) {
                renderStrategy.render(source.getClass().getSimpleName());
            }

            for (PlayerPlugin plugin : plugins) {
                plugin.apply(source.getClass().getSimpleName());
            }
        }
    }
}
