package com.Vxrtrauter.NullWare.command.event;

import com.Vxrtrauter.NullWare.command.CommandManager;
import net.minecraft.network.play.client.C01PacketChatMessage;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelPromise;
import io.netty.channel.ChannelHandlerContext;

public class ChatListener {

    @SubscribeEvent
    public void onClientConnectedToServer(FMLNetworkEvent.ClientConnectedToServerEvent event) {
        event.manager.channel().pipeline().addBefore("packet_handler", "command_handler", new ChannelDuplexHandler() {
            @Override
            public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
                if (msg instanceof C01PacketChatMessage) {
                    C01PacketChatMessage packet = (C01PacketChatMessage) msg;
                    String message = packet.getMessage();

                    if (message.startsWith(".")) {
                   
                        if (CommandManager.executeCommand(message)) {
                            return; 
                        }
                    }
                }
                super.write(ctx, msg, promise);
            }
        });
    }
}
