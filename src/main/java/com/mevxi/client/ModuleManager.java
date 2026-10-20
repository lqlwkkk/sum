package com.mevxi.client;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.MinecraftClient;

public final class ModuleManager {
    private final List<ClientModule> modules = new ArrayList<>();
    public void add(ClientModule module) { modules.add(module); }
    public List<ClientModule> all() { return List.copyOf(modules); }
    public void registerDefaults() {
        add(new ClientModule("Fullbright", "Raise gamma while enabled") {
            private double oldGamma = -1;
            @Override protected void onToggle(boolean value) {
                MinecraftClient mc = MinecraftClient.getInstance();
                if (mc.options == null) return;
                if (value) {
                    oldGamma = mc.options.getGamma().getValue();
                    mc.options.getGamma().setValue(16.0);
                } else if (oldGamma >= 0) {
                    mc.options.getGamma().setValue(oldGamma);
                    oldGamma = -1;
                }
            }
        });
        add(new ClientModule("Sprint Assist", "Hold sprint while moving forward") {
            @Override public void tick(MinecraftClient mc) {
                if (enabled() && mc.player != null && mc.options != null) {
                    mc.options.sprintKey.setPressed(mc.options.forwardKey.isPressed());
                }
            }
        });
        add(new ClientModule("Coordinates HUD", "Show your coordinates in the HUD") {});
    }
    public void tick(MinecraftClient client) {
        for (ClientModule module : modules) module.tick(client);
    }
}
