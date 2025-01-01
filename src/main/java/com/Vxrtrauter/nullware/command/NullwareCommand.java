package com.Vxrtrauter.nullware.command;

public class NullwareCommand implements Command {

    @Override
    public String getName() {
        return "nullware";
    }

    @Override
    public String getDescription() {
        return "Displays information about NullWare.";
    }

    @Override
    public void execute(String[] args) {
        System.out.println("NullWare v1.0 - Created by Vxrtrauter!");
    }
}
