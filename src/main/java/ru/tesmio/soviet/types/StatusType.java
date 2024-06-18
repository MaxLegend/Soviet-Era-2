package ru.tesmio.soviet.types;

import net.minecraft.util.IStringSerializable;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public enum StatusType implements IStringSerializable {
    OFF,
    CLOSE,
    OPEN;

    @Override
    public @NotNull String getString() {
        return name().toLowerCase(Locale.ROOT);
    }
}