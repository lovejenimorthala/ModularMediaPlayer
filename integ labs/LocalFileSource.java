public class LocalFileSource implements MediaSource {
    private String path;

    public LocalFileSource(String path) {
        this.path = path;
    }

    @Override
    public void play() {
        System.out.println("[LocalFileSource] Playing local file: " + path);
    }
}

