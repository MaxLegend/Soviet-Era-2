package ru.tesmio.utils;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.List;
import java.util.regex.Pattern;

public class Tooltip {

    private static final Pattern COMPILE = Pattern.compile("@", Pattern.LITERAL);

    public static void showInfoShift(List<ITextComponent> tooltip)
    {
        if(Screen.hasShiftDown())
        {
            int generation = 10;
            int transfer = generation * 2;
            int capacity = generation * 1000;

            addInformationLocalized(tooltip, "message.soviet.shift_info", generation, transfer, capacity);
        }
        else
            addInformationLocalized(tooltip, "message.soviet.hold_shift");
    }

    public static void showInfoCtrl(int energy, List<ITextComponent> tooltip)
    {
        if(Screen.hasControlDown())
            addInformationLocalized(tooltip, "message.soviet.ctrl_info", energy);
        else
            addInformationLocalized(tooltip, "message.soviet.hold_ctrl");
    }

    private static void addInformationLocalized(List<ITextComponent> tooltip, String key, Object... parameters)
    {
        String translated = I18n.format(key, parameters);
        translated = COMPILE.matcher(translated).replaceAll("\u00a7");
        String[] formatted = translated.split("\n");
        for(String line : formatted)
            tooltip.add(new TranslationTextComponent(line));
    }
}
