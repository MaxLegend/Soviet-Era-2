package ru.tesmio.soviet.entity.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.MissingTextureSprite;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import ru.tesmio.soviet.entity.EntitySittableBlock;

@OnlyIn(Dist.CLIENT)
public class EntitySittableBlockRender extends EntityRenderer<EntitySittableBlock> {
    public EntitySittableBlockRender(EntityRendererManager manager) {
        super(manager);
    }

    @Override
    public @NotNull ResourceLocation getEntityTexture(@NotNull EntitySittableBlock seatEntity) {
        return MissingTextureSprite.getLocation();
    }

    @Override
    protected void renderName(@NotNull EntitySittableBlock entity, @NotNull ITextComponent name, @NotNull MatrixStack matrix, @NotNull IRenderTypeBuffer rtb, int packedLight) {
    }
}
