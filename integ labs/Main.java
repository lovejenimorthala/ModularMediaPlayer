public class Main {
    public static void main(String[] args) {
        ModularPlayer player = new ModularPlayer();

        // Add sources — one local, two remote (with caching)
        player.addSource(new LocalFileSource("song.mp3"));
        player.addSource(new CachedMediaProxy("https://example.com/live.m3u8",
                        new HlsStreamSource("https://example.com/live.m3u8")));
        player.addSource(new CachedMediaProxy("https://api.example.com/media/77",
                        new ApiStreamSource("https://api.example.com/media/77")));

        // Add plugins
        player.addPlugin(new SubtitlePlugin());
        player.addPlugin(new EqualizerPlugin());

        // Set render strategy
        player.setRenderStrategy(new HardwareRenderStrategy());

        System.out.println(">>> First playback (caching in progress) <<<");
        player.playAll();

        System.out.println("\n>>> Second playback (should use cache) <<<");
        player.playAll();
    }
}
