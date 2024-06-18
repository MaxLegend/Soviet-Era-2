package ru.tesmio.soviet.blocks.rasty_frame.texturable_block;

import com.mojang.datafixers.util.Pair;
import net.minecraft.client.renderer.model.*;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModelConfiguration;
import net.minecraftforge.client.model.geometry.IModelGeometry;
import ru.tesmio.soviet.blocks.rasty_frame.RastyFrameBakedModel;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.function.Function;

public class TexturableModelGeometry implements IModelGeometry<TexturableModelGeometry> {

    @Override
    public IBakedModel bake(IModelConfiguration owner, ModelBakery bakery, Function<RenderMaterial, TextureAtlasSprite> spriteGetter, IModelTransform modelTransform, ItemOverrideList overrides, ResourceLocation modelLocation) {
        return new RastyFrameBakedModel();
    }

    @Override
    public Collection<RenderMaterial> getTextures(IModelConfiguration owner, Function<ResourceLocation, IUnbakedModel> modelGetter, Set<Pair<String, String>> missingTextureErrors) {

        return Collections.singletonList(new RenderMaterial(AtlasTexture.LOCATION_BLOCKS_TEXTURE, RastyFrameBakedModel.TEXTURE));
    }
}