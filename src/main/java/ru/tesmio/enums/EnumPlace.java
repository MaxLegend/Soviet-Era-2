package ru.tesmio.enums;


import net.minecraft.util.IStringSerializable;

public enum EnumPlace implements IStringSerializable {
    FLOOR("floor"),
    CEILING("ceiling");

    private final String name;
    EnumPlace(String name) {this.name = name;}
    @Override
    public String getString() {
        return this.name;
    }
}
