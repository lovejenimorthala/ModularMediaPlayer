// Intentionally messy "legacy" media player - starting point for refactor
// Single-file monolith demonstrating duplicated adaptation code and no plugin system.
// WARNING: this code is written to be messy on purpose to simulate a legacy codebase.

package com.example.legacyplayer;
import java.io.*;
import java.net.*;
import java.nio.file.*;
import java.util.*;

public class LegacyPlayer {
    public static void main(String[] args) throws Exception {
        LegacyPlayer p = new LegacyPlayer();
        // Build a simple playlist with different source types
        List<String> playlist = Arrays.asList(
            "file:./sample.mp3",
            "hls:https://example.com/stream.m3u8",
            "api:https://api.example.com/media/42"
        );
        p.playPlaylist(playlist);
    }

    // The whole playback lifecycle is shoved into this class.

    public void playPlaylist(List<String> items) {
        System.out.println("[LegacyPlayer] Playing playlist with " + items.size() + " items");
        for (String item : items) {
            try {
                if (item.startsWith("file:")) {
                    playLocal(item.substring(5));
                } else if (item.startsWith("hls:")) {
                    playHls(item.substring(4));
                } else if (item.startsWith("api:")) {
                    playRemoteApi(item.substring(4));
                } else {
                    System.out.println("[LegacyPlayer] Unknown scheme for " + item);
                }
            } catch (Exception e) {
                System.err.println("[LegacyPlayer] Error playing " + item + ": " + e.getMessage());
            }
        }
    }

    // --- Local playback (duplicated adaptation + inline features) ---
    private void playLocal(String path) throws Exception {
        System.out.println("[LegacyPlayer] Opening local file: " + path);
        // duplicated: adapt path -> InputStream
        InputStream in = null;
        try {
            // many concerns mixed: file IO, decoding, subtitles, watermarking
            Path p = Paths.get(path);
            in = Files.newInputStream(p);
            // duplicated decoding logic (pretend)
            byte[] buffer = new byte[1024];
            int read;
            long total = 0;
            while ((read = in.read(buffer)) != -1) {
                total += read;
                // inline feature: watermark
                if (total % 10000 == 0) {
                    System.out.println("[LegacyPlayer] (watermark) overlaying watermark on frame");
                }
                // inline feature: subtitles (very naive)
                if (total % 5000 == 0) {
                    System.out.println("[LegacyPlayer] (subtitle) showing subtitle line: 'Hello' ");
                }
                // pretend decoding/rendering
                Thread.sleep(1);
            }
            System.out.println("[LegacyPlayer] Finished local file, bytes=" + total);
        } finally {
            if (in != null) try { in.close(); } catch (IOException e) {}
        }
    }

    // --- HLS playback (duplicated adaptation + duplicated decoding) ---
    private void playHls(String url) throws Exception {
        System.out.println("[LegacyPlayer] Opening HLS URL: " + url);
        // duplicated adaptation code: HTTP download + streaming
        HttpURLConnection conn = null;
        InputStream in = null;
        try {
            URL u = new URL(url);
            conn = (HttpURLConnection) u.openConnection();
            conn.setRequestProperty("User-Agent", "LegacyPlayer/1.0");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            int code = conn.getResponseCode();
            if (code != 200) {
                System.out.println("[LegacyPlayer] HLS returned " + code);
                return;
            }
            in = conn.getInputStream();
            // duplicated decoding logic from playLocal
            byte[] buffer = new byte[512];
            int read; long total = 0;
            while ((read = in.read(buffer)) != -1) {
                total += read;
                // inline watermark again (duplicated feature code)
                if (total % 12000 == 0) {
                    System.out.println("[LegacyPlayer] (watermark) overlaying watermark on HLS frame");
                }
                // pretend decoding
                Thread.sleep(2);
            }
            System.out.println("[LegacyPlayer] Finished HLS, bytes=" + total);
        } finally {
            if (in != null) try { in.close(); } catch (IOException e) {}
            if (conn != null) conn.disconnect();
        }
    }

    // --- Remote API playback (duplicated again) ---
    private void playRemoteApi(String apiUrl) throws Exception {
        System.out.println("[LegacyPlayer] Querying remote API: " + apiUrl);
        // duplicated: talk to API and stream returned bytes
        HttpURLConnection conn = null;
        InputStream in = null;
        try {
            URL u = new URL(apiUrl);
            conn = (HttpURLConnection) u.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/octet-stream");
            conn.setConnectTimeout(3000);
            conn.setReadTimeout(3000);
            int code = conn.getResponseCode();
            if (code == 404) {
                System.out.println("[LegacyPlayer] Remote media not found: " + apiUrl);
                return;
            }
            in = conn.getInputStream();
            // duplicated decode/render loop again
            byte[] buffer = new byte[1024]; int read; long total = 0;
            while ((read = in.read(buffer)) != -1) {
                total += read;
                // repeated watermark logic
                if (total % 8000 == 0) System.out.println("[LegacyPlayer] (watermark) remote overlay");
                // pretend equalizer built in
                if (total % 7000 == 0) System.out.println("[LegacyPlayer] (equalizer) applying band boost");
                Thread.sleep(3);
            }
            System.out.println("[LegacyPlayer] Finished remote API, bytes=" + total);
        } finally {
            if (in != null) try { in.close(); } catch (IOException e) {}
            if (conn != null) conn.disconnect();
        }
    }

    // --- Helper methods duplicated across code ---
    private void log(String s) { System.out.println("[LegacyPlayer] " + s); }

    // --- No plugin mechanism, no clear separation of concerns, no tests ---
    // The code above mixes IO, decoding, rendering, and features in one class.
    // Also, there is no caching/proxy, no playlist composition, and the adaptation code
    // for different sources is duplicated rather than abstracted.
}
