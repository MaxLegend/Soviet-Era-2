package ru.tesmio.enums;

import net.minecraft.util.IStringSerializable;

public enum EnumStatus implements IStringSerializable {
    DISABLED("disabled"),
    POWERED("powered");
    private final String name;

    EnumStatus(String name) {
        this.name = name;
    }

    @Override
    public String getString() {
        return name;
    }


}