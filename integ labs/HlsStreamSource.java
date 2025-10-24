public class HlsStreamSource implements MediaSource {
    private String url;

    public HlsStreamSource(String url) {
        this.url = url;
    }

    @Override
    public void play() {
        System.out.println("[HlsStreamSource] Streaming from HLS URL: " + url);
    }
}
