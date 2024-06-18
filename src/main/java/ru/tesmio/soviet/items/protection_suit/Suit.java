package ru.tesmio.soviet.items.protection_suit;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class Suit extends ArmorItem {
    private static final String ARMOR_TEXTURE_PATTERN = "soviet:textures/suit/%s.png";

    public Suit(IArmorMaterial material, EquipmentSlotType slotType, Properties properties) {
        super(material, slotType, properties);
    }

    @Nullable
    @Override
    @OnlyIn(Dist.CLIENT)
    public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, @NotNull A _default) {
        SuitModel model = new SuitModel(armorSlot);
        model.isChild = _default.isChild;
        model.isSitting = _default.isSitting;
        model.isSneak = _default.isSneak;
        return (A) model;
    }

    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
        String textureName = "";
        switch (slot) {
            case HEAD:
                textureName = "gasmask";
                break;
            case CHEST:
                textureName = "jacket_texture";
                break;
            case LEGS:
                textureName = "leggins_texture";
                break;
            case FEET:
                textureName = "boots_texture";
                break;
        }
        return textureName.isEmpty() ? textureName : String.format(ARMOR_TEXTURE_PATTERN, textureName);
    }
}
