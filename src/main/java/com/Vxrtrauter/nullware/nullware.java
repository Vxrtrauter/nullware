package com.Vxrtrauter.nullware;

import com.Vxrtrauter.nullware.brand.BrandSpoofHandler;
import com.Vxrtrauter.nullware.command.CommandManager;
import com.Vxrtrauter.nullware.command.HelpCommand;
import com.Vxrtrauter.nullware.command.ServerCommand;
import com.Vxrtrauter.nullware.command.NullwareCommand;
import com.Vxrtrauter.nullware.command.event.ChatListener;
import com.Vxrtrauter.nullware.gui.GuiEventHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = nullware.MODID, name = nullware.NAME, version = nullware.VERSION)
public class nullware {
    public static final String MODID = "nullware";
    public static final String NAME = "NullWare";
    public static final String VERSION = "1.0";

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        // register event handlers
        MinecraftForge.EVENT_BUS.register(new GuiEventHandler());
        MinecraftForge.EVENT_BUS.register(new BrandSpoofHandler());
        MinecraftForge.EVENT_BUS.register(new ChatListener());

        // register commands for CommandManager
        CommandManager.registerCommand(new HelpCommand());
        CommandManager.registerCommand(new ServerCommand());
        CommandManager.registerCommand(new NullwareCommand());
    }

}
