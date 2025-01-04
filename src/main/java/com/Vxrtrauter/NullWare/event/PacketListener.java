package com.Vxrtrauter.NullWare.event;

import net.minecraft.network.Packet;
import net.minecraft.network.NetworkManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.lang.reflect.Field;

public class PacketListener extends ChannelInboundHandlerAdapter {

    private static Packet<?> latestPacket;
    private static long lastPacketTime;

    public PacketListener() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onClientConnect(FMLNetworkEvent.ClientConnectedToServerEvent event) {
        try {
            NetworkManager networkManager = event.manager;
            injectPacketHandler(networkManager);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void injectPacketHandler(NetworkManager networkManager) throws Exception {
        Field[] fields = NetworkManager.class.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object value = field.get(networkManager);
            if (value instanceof io.netty.channel.Channel) {
                io.netty.channel.Channel channel = (io.netty.channel.Channel) value;
                io.netty.channel.ChannelPipeline pipeline = channel.pipeline();
                if (pipeline.get("custom_packet_listener") == null) {
                    pipeline.addBefore("packet_handler", "custom_packet_listener", this);
                }
                return;
            }
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof Packet<?>) {
            latestPacket = (Packet<?>) msg;
            lastPacketTime = System.currentTimeMillis();
        }
        super.channelRead(ctx, msg);
    }

    public static Packet<?> getLatestPacket() {
        return latestPacket;
    }

    public static long getLastPacketTime() {
        return lastPacketTime;
    }
}