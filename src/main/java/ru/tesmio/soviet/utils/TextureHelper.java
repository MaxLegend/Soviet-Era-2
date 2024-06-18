package ru.tesmio.soviet.utils;

import net.minecraft.block.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public class TextureHelper {

    private static ResourceLocation loc(String nameSpace, String path) {
        return new ResourceLocation(nameSpace, path);
    }

    public static List<TextureAtlasSprite> getTextureListFromBlock(Block blockIn) {
        List<ResourceLocation> locationList = new ArrayList<>();
        String m = "minecraft";
        String n = blockIn.getRegistryName().getNamespace();
        String p = blockIn.getRegistryName().getPath();
        String b = "block/";
        locationList.add(loc(n,b+p));
        return getTextureFromLocation(locationList);
    }

    /**
     * Method needed to get the correct textures for a given block
     * @param locationList list of ResourceLocations for the texture of the block
     * @return list of TextureAtlasSprites
     */
    private static List<TextureAtlasSprite> getTextureFromLocation(List<ResourceLocation> locationList) {
        List<TextureAtlasSprite> textureList = new ArrayList<>();
        for(ResourceLocation location:locationList) {
            textureList.add(Minecraft.getInstance().getAtlasSpriteGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE).apply(location));
        }
        return textureList;
    }
}
