package ru.tesmio.entity.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import ru.tesmio.entity.EntitySittableBlock;
//краш рендера нулл при попытке сесть
public class EntitySittableBlockRender extends EntityRenderer<EntitySittableBlock>
{
    public EntitySittableBlockRender(EntityRendererManager manager)
    {
        super(manager);
    }

    @Override
    public ResourceLocation getEntityTexture(EntitySittableBlock seatEntity)
    {
        return null;
    }

    @Override
    protected void renderName(EntitySittableBlock p_225629_1_, ITextComponent p_225629_2_, MatrixStack p_225629_3_, IRenderTypeBuffer p_225629_4_, int p_225629_5_) {}
}
