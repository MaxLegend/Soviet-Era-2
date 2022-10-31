package ru.tesmio.blocks.diesel_generator;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import ru.tesmio.Core;

public class DieselGeneratorScreen extends ContainerScreen<DieselGeneratorContainer> {

    private ResourceLocation GUI = new ResourceLocation(Core.MODID, "textures/gui/energy_generator.png");
    private TileDieselGenerator tile;
        public DieselGeneratorScreen(DieselGeneratorContainer container, PlayerInventory inv, ITextComponent name) {
        super(container, inv, name);
            if(container.tileEntity instanceof TileDieselGenerator) {
                this.tile = (TileDieselGenerator)container.tileEntity;
            }
            this.guiLeft = 0;
            this.guiTop = 0;
            this.xSize = 176;
            this.ySize = 166;
        }

        @Override
        public void render(MatrixStack mStack, int mouseX, int mouseY, float partialTicks) {
            this.renderBackground(mStack);
            super.render(mStack, mouseX, mouseY, partialTicks);
            this.renderHoveredTooltip(mStack, mouseX, mouseY);
        }

        @Override
        protected void drawGuiContainerForegroundLayer(MatrixStack matrixStack, int mouseX, int mouseY) {
            this.font.drawString(matrixStack,new TranslationTextComponent("se.energy").getString() + " " + container.getEnergy() + " FE", 10, 10, 0x404040);
        }

        @Override
        protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY) {
            RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.minecraft.getTextureManager().bindTexture(GUI);
            this.blit(matrixStack, this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
        }
}

