package com.mevxi.client;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public final class MevxiScreen extends Screen {
    private final List<ButtonWidget> moduleButtons = new ArrayList<>();
    private String category = "Combat";
    private int panelX, panelY, panelW, panelH;

    public MevxiScreen() {
        super(Text.literal("Mevxi Client"));
    }

    @Override protected void init() {
        clearChildren();
        moduleButtons.clear();
        panelX = Math.max(20, width / 2 - 340);
        panelY = Math.max(20, height / 2 - 210);
        panelW = Math.min(680, width - 40);
        panelH = Math.min(420, height - 40);
        int sidebarW = 150;
        String[] categories = {"Combat", "Misc", "Macros", "Donut", "Render", "Client"};
        for (int i = 0; i < categories.length; i++) {
            final String cat = categories[i];
            addDrawableChild(ButtonWidget.builder(Text.literal((cat.equals(category) ? "▎ " : "") + cat),
                b -> { category = cat; rebuild(); })
                .dimensions(panelX + 12, panelY + 62 + i * 38, sidebarW - 24, 30).build());
        }
        int x = panelX + sidebarW + 18;
        int y = panelY + 62;
        int w = panelW - sidebarW - 36;
        int idx = 0;
        for (ClientModule module : MevxiClient.MODULES.all()) {
            final ClientModule m = module;
            addDrawableChild(ButtonWidget.builder(
                Text.literal((m.enabled() ? "●  " : "○  ") + m.name() + "   —   " + m.description()),
                b -> { m.toggle(); rebuild(); })
                .dimensions(x, y + idx * 42, Math.max(100, w), 34).build());
            idx++;
        }
        addDrawableChild(ButtonWidget.builder(Text.literal("Close"), b -> close())
            .dimensions(panelX + panelW - 82, panelY + panelH - 38, 68, 26).build());
    }

    @Override public void renderBackground(DrawContext ctx, int mouseX, int mouseY, float delta) {
        ctx.fill(0, 0, width, height, 0x99040A14);
        ctx.fill(panelX, panelY, panelX + panelW, panelY + panelH, 0xF20B101B);
        ctx.fill(panelX, panelY, panelX + 2, panelY + panelH, 0xFF7B83FF);
        ctx.fill(panelX + 160, panelY + 48, panelX + panelW - 12, panelY + 49, 0xFF293247);
    }

    @Override public void render(DrawContext ctx, int mouseX, int mouseY, float delta) {
        super.render(ctx, mouseX, mouseY, delta);
        ctx.drawText(textRenderer, "Me", panelX + 16, panelY + 16, 0xFFEAECFF, true);
        ctx.drawText(textRenderer, "Mevxi", panelX + 48, panelY + 14, 0xFFF4F5FF, true);
        ctx.drawText(textRenderer, "MINECRAFT CLIENT", panelX + 48, panelY + 27, 0xFF929CB8, false);
        ctx.drawText(textRenderer, category, panelX + 178, panelY + 20, 0xFFE6E9FF, true);
        ctx.drawText(textRenderer, "Modules  \u2014  " + MevxiClient.MODULES.all().size(),
            panelX + 178, panelY + 34, 0xFF929CB8, false);
    }

    private void rebuild() { init(); }

    @Override public boolean shouldPause() { return false; }
    @Override public void close() { if (client != null) client.setScreen(null); }
}
