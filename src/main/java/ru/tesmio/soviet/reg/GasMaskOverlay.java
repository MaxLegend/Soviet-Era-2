package ru.tesmio.soviet.reg;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class GasMaskOverlay {
    private static final ResourceLocation GASMASK_GUI = new ResourceLocation("soviet:textures/gui/gasmask_gui.png");

    @SubscribeEvent
    public void onRenderGui(RenderGameOverlayEvent.Post e) {
        Minecraft mc = Minecraft.getInstance();
        if (!mc.gameSettings.getPointOfView().func_243192_a() || mc.player == null) return;
        if (e.getType() != RenderGameOverlayEvent.ElementType.VIGNETTE) return;
        int scaledWidth = e.getWindow().getScaledWidth();
        int scaledHeight = e.getWindow().getScaledHeight();

        ItemStack headStack = mc.player.inventory.armorItemInSlot(3);

        if (headStack.getItem() == RegItems.SUIT_GAS_MASK.get()) {
            RenderSystem.disableDepthTest();
            RenderSystem.depthMask(false);

            RenderSystem.defaultBlendFunc();
            RenderSystem.enableBlend();
            RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
            RenderSystem.disableAlphaTest();
            mc.getTextureManager().bindTexture(GASMASK_GUI);
            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder bufferbuilder = tessellator.getBuffer();
            bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
            bufferbuilder.pos(0.0D, scaledHeight, -90.0D).tex(0.0F, 1.0F).endVertex();
            bufferbuilder.pos(scaledWidth, scaledHeight, -90.0D).tex(1.0F, 1.0F).endVertex();
            bufferbuilder.pos(scaledWidth, 0.0D, -90.0D).tex(1.0F, 0.0F).endVertex();
            bufferbuilder.pos(0.0D, 0.0D, -90.0D).tex(0.0F, 0.0F).endVertex();
            tessellator.draw();
            RenderSystem.depthMask(true);
            RenderSystem.disableBlend();
            RenderSystem.enableDepthTest();
            RenderSystem.enableAlphaTest();
            RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        }
    }
}