package ru.tesmio.enums;

import net.minecraft.util.IStringSerializable;

public enum EnumStatus implements IStringSerializable {
    OFF("off"),
    CLOSE("close"),
    OPEN("open");
    private final String name;
    EnumStatus(String name) {this.name = name;}
    @Override
    public String getString() {
        return this.name;
    }
}