# Modular Media Streaming Suite

A refactored and modular version of a simple legacy media player that now supports multiple media sources, plugins, composite playlists, runtime rendering strategies, and proxy caching.

---

## Author
**Developed by:** [lovejenimorthala](https://github.com/lovejenimorthala)

---

## Overview
This project transforms a messy, monolithic media player into a **Modular Media Streaming Suite** using well-known software design patterns.  
It can play from different sources, apply plugins like subtitles or equalizers, switch between renderers (hardware/software), and cache remote streams for faster access.

---

## Features
**Multiple Media Sources:** Supports local files, HLS streams, and remote API sources.  
**Plugins:** Add features like subtitles, audio equalizer, or watermark without changing the core player.  
**Composite Playlists:** Create playlists that can contain other playlists or single media files.  
**Runtime Renderer Switching:** Easily switch between hardware and software rendering during playback.  
**Proxy Caching:** Automatically cache remote media streams for faster replays.  

---

## Design Patterns Used

### 1. Adapter
Used to make different media sources (Local, HLS, API) follow a common interface `MediaSource`.  
- Makes it easy to add new source types later.

### 2. Composite
Allows a playlist to contain both single media items and other playlists.  
- Helps manage nested or grouped playback.

### 3. Decorator
Used for plugins like subtitles, watermark, and audio equalizer.  
- Adds extra behavior to media rendering without changing the original player.

### 4. Strategy
Implements different rendering methods (hardware and software) that can be switched at runtime.  
- Improves flexibility and testing options.

### 5. Proxy
Caches remote media streams locally to reduce repeated fetching.  
- Saves bandwidth and speeds up second playback.

---

## Design Rationale (Simplified)
The project started with messy, repetitive code that handled local, HLS, and API sources separately.  
By refactoring with structural design patterns, we separated **concerns** and improved **extensibility**:
- **Adapter** simplified the addition of new source types.  
- **Composite** managed hierarchical playlists.  
- **Decorator** allowed easy plugin integration.  
- **Strategy** gave flexibility in rendering.  
- **Proxy** boosted performance by caching streams.

This made the player cleaner, easier to maintain, and more future-proof.

---

## Reflection
This project taught me how powerful software design patterns can be when refactoring messy code.  
The trade-off was spending more time structuring interfaces and classes, but the result was worth it because the system became more modular and readable.  
If I could improve it, I’d add a simple graphical user interface (GUI), better error handling, and real streaming functionality instead of simulated text output.  
Overall, I learned how patterns like Adapter, Composite, and Proxy help make flexible, scalable systems.

---

## How to Run
1. Compile all `.java` files in your folder.  
   ```bash
   javac *.java
