package ru.tesmio.soviet.types;

import net.minecraft.util.IStringSerializable;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public enum PartType implements IStringSerializable {
    DEF,
    DOWN,
    MID,
    UP,
    WEST,
    EAST,
    NORTH,
    SOUTH,
    FULL;

    @Override
    public @NotNull String getString() {
        return name().toLowerCase(Locale.ROOT);
    }
}
