public class ApiStreamSource implements MediaSource {
    private String apiUrl;

    public ApiStreamSource(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    @Override
    public void play() {
        System.out.println("[ApiStreamSource] Fetching from API: " + apiUrl);
    }
}
