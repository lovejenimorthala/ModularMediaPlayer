public interface PlayerPlugin {
    void apply(String mediaName);
}

// Subtitle Plugin
class SubtitlePlugin implements PlayerPlugin {
    @Override
    public void apply(String mediaName) {
        System.out.println("[SubtitlePlugin] Rendering subtitles for " + mediaName);
    }
}

// Equalizer Plugin
class EqualizerPlugin implements PlayerPlugin {
    @Override
    public void apply(String mediaName) {
        System.out.println("[EqualizerPlugin] Adjusting audio frequencies for " + mediaName);
    }
}

// Watermark Plugin
class WatermarkPlugin implements PlayerPlugin {
    @Override
    public void apply(String mediaName) {
        System.out.println("[WatermarkPlugin] © 2025 My Media Player " + mediaName);
    }
}
