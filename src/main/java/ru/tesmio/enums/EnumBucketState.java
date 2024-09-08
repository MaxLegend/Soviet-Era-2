package ru.tesmio.enums;

import net.minecraft.util.IStringSerializable;

public enum EnumBucketState implements IStringSerializable
{
    EMPTY("empty"),
    WATER("water"),

    //-----//
    //сделать все цвета кисточка готова, шпатель готов, скраппер для краски готов. Настроить
    ORANGE("orange"),
    BLUE("blue"),
    RED("red"),
    YELLOW("yellow"),
    WHITE("white"),
    BEIGE("beige"),
    GREEN("green"),
    FILLER("filler"), //it "gray" color
    BEIGE2("beige2");

    private final String name;

    EnumBucketState(String name)
    {
        this.name = name;
    }

    public String getString()
    {
        return this.name;
    }



}
