import java.util.HashMap;
import java.util.Map;

public class CachedMediaProxy implements MediaSource {
    private MediaSource realSource;
    private static Map<String, String> cache = new HashMap<>();
    private String sourceId;

    public CachedMediaProxy(String sourceId, MediaSource realSource) {
        this.sourceId = sourceId;
        this.realSource = realSource;
    }

    @Override
    public void play() {
        if (cache.containsKey(sourceId)) {
            System.out.println("[ProxyCache] Loaded from cache: " + cache.get(sourceId));
        } else {
            System.out.println("[ProxyCache] Caching new stream: " + sourceId);
            realSource.play();
            cache.put(sourceId, sourceId);
        }
    }
}
