package com.Vxrtrauter.nullware.command;

public interface Command {
    String getName();
    String getDescription();
    void execute(String[] args);
}
