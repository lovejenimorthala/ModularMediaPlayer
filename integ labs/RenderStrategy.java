public interface RenderStrategy {
    void render(String mediaName);
}

// Hardware Rendering Strategy
class HardwareRenderStrategy implements RenderStrategy {
    @Override
    public void render(String mediaName) {
        System.out.println("[HardwareRenderer] Rendering " + mediaName + " using GPU acceleration.");
    }
}

// Software Rendering Strategy
class SoftwareRenderStrategy implements RenderStrategy {
    @Override
    public void render(String mediaName) {
        System.out.println("[SoftwareRenderer] Rendering " + mediaName + " using CPU.");
    }
}
