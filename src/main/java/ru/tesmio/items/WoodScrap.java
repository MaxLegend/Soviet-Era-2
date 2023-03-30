package ru.tesmio.items;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;

import javax.annotation.Nullable;

public class WoodScrap extends ItemInfo{
    public WoodScrap(Properties properties, String info) {
        super(properties, info);

    }

    @Override
    public int getBurnTime(ItemStack itemStack, @Nullable IRecipeType<?> recipeType) {
        return 190;
    }
}
