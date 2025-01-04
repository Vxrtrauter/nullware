package com.Vxrtrauter.NullWare.gui;

import com.Vxrtrauter.NullWare.event.PacketListener;
import com.Vxrtrauter.NullWare.helper.TpsHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;

import java.lang.reflect.Field;

public class GameOverlay {

    private static final Minecraft mc = Minecraft.getMinecraft();
    private static final FontRenderer fontRenderer = mc.fontRendererObj;
    private static boolean enabled = true;
    private static final String watermark = "§cNullWare";

    @SubscribeEvent
    public void onRenderGameOverlay(RenderGameOverlayEvent.Post event) {
        if (!enabled) {
            return;
        }

        int color = calculateDynamicColor();

        // Draw watermark and FPS
        String fpsText = "FPS: " + Minecraft.getDebugFPS();
        int watermarkWidth = fontRenderer.getStringWidth(watermark);
        int fpsTextWidth = fontRenderer.getStringWidth(fpsText);
        int separatorWidth = fontRenderer.getStringWidth(" | ");
        int totalWidth = watermarkWidth + separatorWidth + fpsTextWidth;

        fontRenderer.drawStringWithShadow(watermark, 6, 6, color);
        fontRenderer.drawStringWithShadow(" | ", 6 + watermarkWidth, 6, 0xA9A9A9); // Dark-grey separator
        fontRenderer.drawStringWithShadow(fpsText, 6 + watermarkWidth + separatorWidth, 6, 0xFFFFFF); // White FPS text

        if (mc.isSingleplayer()) {
            return;
        }

        // Draw server IP
        String serverIP = mc.getCurrentServerData() != null ? mc.getCurrentServerData().serverIP : "UNKNOWN";
        fontRenderer.drawStringWithShadow("Server: " + serverIP, 6F, 20F, 0xFFFFFF);

        // Draw server brand
        String clientBrand = mc.thePlayer.getClientBrand();
        if (clientBrand != null) {
            String serverBrand = clientBrand.contains("<- ") ?
                    clientBrand.split(" ")[0] + " -> " + clientBrand.split("<- ")[1] :
                    clientBrand.split(" ")[0];
            fontRenderer.drawStringWithShadow("Engine: " + serverBrand, 6F, 30F, 0xFFFFFF);
        }

        // Draw time since last packet
        long timeSinceLastPacket = System.currentTimeMillis() - PacketListener.getLastPacketTime();
        String latestPacketText = "Last Packet: ";
        fontRenderer.drawStringWithShadow(latestPacketText, 6, 42, 0xFFFFFF);
        fontRenderer.drawStringWithShadow("§a" + timeSinceLastPacket + " ms§r", 6 + fontRenderer.getStringWidth(latestPacketText), 42, 0xFFFFFF);

        // Draw TPS
        TpsHelper.updateTps();
        String tpsText = "TPS: ";
        fontRenderer.drawStringWithShadow(tpsText, 6, 54, 0xFFFFFF);
        fontRenderer.drawStringWithShadow(formatTps(TpsHelper.tps) + "§r", 6 + fontRenderer.getStringWidth(tpsText), 54, 0xFFFFFF);
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.START) {
            initializePacketListener();
        }
    }

    public static void initializePacketListener() {
        try {
            NetHandlerPlayClient netHandler = mc.getNetHandler();
            Field netManagerField = NetHandlerPlayClient.class.getDeclaredField("netManager");
            netManagerField.setAccessible(true);
            Object netManager = netManagerField.get(netHandler);

            Field channelField = netManager.getClass().getDeclaredField("field_150735_g");
            channelField.setAccessible(true);
            Object channel = channelField.get(netManager);

            if (channel instanceof io.netty.channel.Channel) {
                io.netty.channel.ChannelPipeline pipeline = ((io.netty.channel.Channel) channel).pipeline();
                if (pipeline.get("custom_packet_listener") == null) {
                    pipeline.addBefore("packet_handler", "custom_packet_listener", new PacketListener());
                }
            }
        } catch (Exception ignored) {
        }
    }

    private int calculateDynamicColor() {
        long lastPacketTime = System.currentTimeMillis() - PacketListener.getLastPacketTime();

        if (lastPacketTime < 1000L) {
            return 0x00FF00;
        } else if (lastPacketTime < 7000L) {
            return 0xFFFF00;
        } else if (lastPacketTime < 15000L) {
            return 0xFFA500;
        } else {
            return 0xFF0000;
        }
    }

    private String formatTps(double tps) {
        if (tps > 15.0) {
            return "§a" + String.format("%.2f", tps);
        } else if (tps > 10.0) {
            return "§e" + String.format("%.2f", tps);
        } else if (tps > 5.0) {
            return "§6" + String.format("%.2f", tps);
        } else {
            return "§c" + String.format("%.2f", tps);
        }
    }
}