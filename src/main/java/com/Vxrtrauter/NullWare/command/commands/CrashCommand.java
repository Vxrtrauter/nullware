
package com.Vxrtrauter.NullWare.command.commands;

import com.Vxrtrauter.NullWare.command.Command;
import com.Vxrtrauter.NullWare.command.commands.CrashHandler;
import com.Vxrtrauter.NullWare.command.commands.crashers.*;
import net.minecraft.client.Minecraft;

import java.util.HashMap;
import java.util.Map;

import static com.Vxrtrauter.NullWare.client.MessageHandler.sendMessage;

public class CrashCommand implements Command {

    private final Minecraft mc = Minecraft.getMinecraft();
    private final Map<String, CrashHandler> crashMethods = new HashMap<>();

    public CrashCommand() {
        crashMethods.put("ONEPACKET", new OnePacketCrash());
        crashMethods.put("NETTY", new NettyCrash());
        crashMethods.put("NETTY2", new Netty2Crash());
        crashMethods.put("MVCBYPASS", new MvcBypassCrash());
        crashMethods.put("RANDOMPACKETA", new RandomPacketACrash());
        crashMethods.put("RANDOMPACKETB", new RandomPacketBCrash());
        crashMethods.put("LPX", new LpxCrash());
        crashMethods.put("EXPLOITFIXER1", new ExploitFixer1Crash());
        crashMethods.put("EXPLOITFIXER2", new ExploitFixer2Crash());
        crashMethods.put("EXPLOITFIXER3", new ExploitFixer3Crash());
        crashMethods.put("CUSTOMPAYLOADFIXER", new CustomPayloadFixerCrash());
        crashMethods.put("CLICKEXC", new ClickExcCrash());
        crashMethods.put("TABNBT", new TabNbtCrash());
        crashMethods.put("GPT" , new GPTCrash());
        crashMethods.put("NPC", new NpcCrash());
        crashMethods.put("SKILLSPIGOT", new SkillSpigotCrash());
        crashMethods.put("FAWE", new FaweCrash());
        crashMethods.put("WORLDEDIT", new WorldEditCrash());
        crashMethods.put("BUNGEE", new BungeeCrash());
        crashMethods.put("BUNGEEREPORT", new BungeeReportCrash());
        crashMethods.put("VELOCITY", new VelocityCrash());
        crashMethods.put("SKILLPLUGIN", new SkillPluginCrash());
        crashMethods.put("CREATIVE", new CreativeCrash());
        crashMethods.put("CONSOLESPAM", new ConsoleSpamCrash());
        crashMethods.put("ATTACK", new AttackCrash());
        crashMethods.put("BOOKPLACE", new BookCrash());

    }

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

        CrashHandler crashMethod = crashMethods.get(mode);
        if (crashMethod == null) {
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

        sendMessage(String.format("Sending §8[§7Method: §c%s§7, Packets: §c%s§8]", mode, packets));

        crashMethod.execute(packets);
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
                "EXPLOITFIXER3 - Sends a large written book payload to the server.",
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
                "ONEPACKET - Floods the server with a large written book packet",
                "GPT - Sends a large written book payload containing 50 pages of a large payload to stress the server.",
                "ATTACK - Simple swingarm crasher that can provide lags on small servers",
                "BOOKPLACE - Sends a book packet to place a book in the player's inventory"
        };


        sendMessage("Available crash methods:");
        for (String method : methods) {
            sendMessage(method);
        }
    }
}