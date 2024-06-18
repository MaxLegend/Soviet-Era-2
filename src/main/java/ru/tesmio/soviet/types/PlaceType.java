package ru.tesmio.soviet.types;


import net.minecraft.util.IStringSerializable;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public enum PlaceType implements IStringSerializable {
    FLOOR,
    CEILING;

    @Override
    public @NotNull String getString() {
        return name().toLowerCase(Locale.ROOT);
    }
}
