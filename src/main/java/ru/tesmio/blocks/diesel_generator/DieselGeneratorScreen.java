package ru.tesmio.blocks.diesel_generator;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import ru.tesmio.Core;

public class DieselGeneratorScreen extends ContainerScreen<DieselGeneratorContainer> {

    private ResourceLocation GUI = new ResourceLocation(Core.MODID, "textures/gui/energy_generator.png");
    private TileDieselGenerator tile;
        public DieselGeneratorScreen(DieselGeneratorContainer container, PlayerInventory inv, ITextComponent name) {
        super(container, inv, name);
        if(container.tileEntity instanceof TileDieselGenerator) {
            this.tile = (TileDieselGenerator)container.tileEntity;
        }

        }

        @Override
        public void render(MatrixStack mStack, int mouseX, int mouseY, float partialTicks) {
            this.renderBackground(mStack);
            super.render(mStack, mouseX, mouseY, partialTicks);
            this.renderHoveredTooltip(mStack, mouseX, mouseY);
//            if(mouseX > getGuiLeft() + 7 && mouseX < getGuiLeft() + 29 && mouseY > getGuiTop() + 10 && mouseY < getGuiTop() + 77)
//                this.renderTooltip(mStack, new StringTextComponent("Energy: " + getPercent() + "%"), mouseX, mouseY);
        }

        @Override
        protected void drawGuiContainerForegroundLayer(MatrixStack matrixStack, int mouseX, int mouseY) {
            this.font.drawString(matrixStack,"Energy: " + container.getEnergy(), 10, 10, 0xffffff);
        }

        @Override
        protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY) {
            RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.minecraft.getTextureManager().bindTexture(GUI);
            this.blit(matrixStack, this.getGuiLeft(), this.getGuiTop(), 0, 0, this.width, this.height);
//
//            int relX = (this.width - this.width) / 2;
//            int relY = (this.height - this.height) / 2;
//            this.blit(matrixStack, relX, relY, 0, 0, this.width, this.height);
        }
    private int getPercent()
    {
        Long currentEnergy = new Long(container.getEnergy());
        int maxEnergy = container.getEnergy();

        long result = currentEnergy * 100 / maxEnergy;

        return (int) result;
    }
}

