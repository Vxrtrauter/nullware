package com.Vxrtrauter.NullWare;

import com.Vxrtrauter.NullWare.brand.BrandSpoofHandler;
import com.Vxrtrauter.NullWare.command.CommandManager;
import com.Vxrtrauter.NullWare.command.commands.HelpCommand;
import com.Vxrtrauter.NullWare.command.commands.ServerCommand;
import com.Vxrtrauter.NullWare.command.commands.NullwareCommand;
import com.Vxrtrauter.NullWare.command.commands.CrashCommand;
import com.Vxrtrauter.NullWare.command.event.ChatListener;
import com.Vxrtrauter.NullWare.gui.GameOverlay;
import com.Vxrtrauter.NullWare.gui.GuiEventHandler;
import com.Vxrtrauter.NullWare.event.PacketListener;
import com.Vxrtrauter.NullWare.helper.TpsHelper;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = NullWare.MODID, name = NullWare.NAME, version = NullWare.VERSION)
public class NullWare {
    public static final String MODID = "nullware";
    public static final String NAME = "NullWare";
    public static final String VERSION = "1.0";

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new GuiEventHandler());
        MinecraftForge.EVENT_BUS.register(new BrandSpoofHandler());
        MinecraftForge.EVENT_BUS.register(new ChatListener());
        MinecraftForge.EVENT_BUS.register(new GameOverlay());
        MinecraftForge.EVENT_BUS.register(new PacketListener());
        MinecraftForge.EVENT_BUS.register(new TpsHelper());
        CommandManager.registerCommand(new HelpCommand());
        CommandManager.registerCommand(new ServerCommand());
        CommandManager.registerCommand(new NullwareCommand());
        CommandManager.registerCommand(new CrashCommand());
    }
}