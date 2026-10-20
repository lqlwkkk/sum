package com.mevxi.client;

public abstract class ClientModule {
    private final String name;
    private final String description;
    private boolean enabled;

    protected ClientModule(String name, String description) {
        this.name = name;
        this.description = description;
    }
    public String name() { return name; }
    public String description() { return description; }
    public boolean enabled() { return enabled; }
    public void toggle() { setEnabled(!enabled); }
    public void setEnabled(boolean value) {
        if (enabled == value) return;
        enabled = value;
        onToggle(value);
    }
    protected void onToggle(boolean value) {}
    public void tick(net.minecraft.client.MinecraftClient client) {}
}
