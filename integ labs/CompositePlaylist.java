import java.util.ArrayList;
import java.util.List;

public class CompositePlaylist implements MediaSource {
    private String name;
    private List<MediaSource> sources = new ArrayList<>();

    public CompositePlaylist(String name) {
        this.name = name;
    }

    public void add(MediaSource source) {
        sources.add(source);
    }

    public void remove(MediaSource source) {
        sources.remove(source);
    }

    @Override
    public void play() {
        System.out.println("[CompositePlaylist] Playing playlist: " + name);
        for (MediaSource source : sources) {
            source.play();
        }
    }
}

