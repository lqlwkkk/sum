# Mevxi Client — Fabric starter

A client-side Fabric mod starter with a custom dark Mevxi Click GUI.

## Target
- Minecraft Java Edition 1.21.11
- Fabric Loader + Fabric API
- Java 21
- Open the menu with **Right Shift**

## Included starter modules
- **Fullbright** — raises gamma while enabled
- **Sprint Assist** — holds sprint while moving forward
- **Coordinates HUD** — placeholder module (HUD rendering not implemented yet)

## Build
1. Install a Java 21 JDK.
2. Open this folder in a terminal.
3. Run `gradle build` if Gradle is installed, or add a Gradle wrapper from a trusted Gradle installation.
4. The mod JAR should appear in `build/libs/`.
5. Put the JAR in `.minecraft/mods` together with Fabric API.

## Notes
This is source code, not a prebuilt or tested JAR. Dependency versions may need updating to versions published for your exact Minecraft/Fabric setup. The GUI is a functional starter, not a pixel-perfect copy of the concept image. Test in a separate game instance first.
