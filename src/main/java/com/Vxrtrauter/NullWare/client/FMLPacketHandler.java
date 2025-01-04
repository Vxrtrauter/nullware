package com.Vxrtrauter.NullWare.client;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import net.minecraftforge.fml.common.network.internal.FMLProxyPacket;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.network.PacketBuffer;

public class FMLPacketHandler extends ChannelDuplexHandler {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof FMLProxyPacket) {
            FMLProxyPacket packet = (FMLProxyPacket) msg;
            if ("FML|HS".equals(packet.channel())) {
                ByteBuf payload = packet.payload();
                int discriminator = payload.readByte();
                if (discriminator == 2) { 
                    ByteBuf newPayload = Unpooled.buffer();
                    newPayload.writeByte(2);
                    ByteBufUtils.writeVarInt(newPayload, 0, 5); 
                    FMLProxyPacket newPacket = new FMLProxyPacket(new PacketBuffer(newPayload), packet.channel());
                    ctx.fireChannelRead(newPacket);
                    return;
                }
            }
        }
        super.channelRead(ctx, msg);
    }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        if (msg instanceof FMLProxyPacket) {
            FMLProxyPacket packet = (FMLProxyPacket) msg;
            if ("FML|HS".equals(packet.channel())) {
                ByteBuf payload = packet.payload();
                int discriminator = payload.readByte();
                if (discriminator == 2) { // ModList packet
                    ByteBuf newPayload = Unpooled.buffer();
                    newPayload.writeByte(2);
                    ByteBufUtils.writeVarInt(newPayload, 0, 5); // Write empty mod list
                    FMLProxyPacket newPacket = new FMLProxyPacket(new PacketBuffer(newPayload), packet.channel());
                    ctx.write(newPacket, promise);
                    return;
                }
            }
        }
        super.write(ctx, msg, promise);
    }
}
