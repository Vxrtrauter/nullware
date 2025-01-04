package com.Vxrtrauter.NullWare.brand;

import com.Vxrtrauter.NullWare.client.FMLPacketHandler;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import io.netty.channel.ChannelPipeline;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.C17PacketCustomPayload;

public class BrandSpoofHandler {

    @SubscribeEvent
    public void onClientConnectedToServer(FMLNetworkEvent.ClientConnectedToServerEvent event) {
        String brand = BrandManager.getCurrentBrand();
        ByteBuf buf = Unpooled.buffer();
        PacketBuffer pb = new PacketBuffer(buf);
        pb.writeString(brand);
        C17PacketCustomPayload packet = new C17PacketCustomPayload("MC|Brand", pb);
        event.manager.sendPacket(packet);

       
        ChannelPipeline pipeline = event.manager.channel().pipeline();
        pipeline.addBefore("packet_handler", "fml_packet_handler", new FMLPacketHandler());
    }
}
