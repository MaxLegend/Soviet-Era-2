package ru.tesmio.soviet.blocks.rasty_frame;

import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockModelShapes;
import net.minecraft.client.renderer.model.*;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.data.IDynamicBakedModel;
import net.minecraftforge.client.model.data.IModelData;
import ru.tesmio.soviet.utils.ModelHelper;
import ru.tesmio.soviet.utils.TextureHelper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RastyFrameBakedModel implements IDynamicBakedModel {
    public static final ResourceLocation TEXTURE = new ResourceLocation("minecraft", "block/oak_planks");

    @Nonnull
    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, @Nonnull Random rand, @Nonnull IModelData extraData) {
        //    Integer texIndex = extraData.getData(TexturableBlockTile.TEXTURE);
        BlockState north = extraData.getData(RastyFrameTile.NORTH_CONTAINS);
        BlockState bottom = extraData.getData(RastyFrameTile.DOWN_CONTAINS);
        BlockState south = extraData.getData(RastyFrameTile.SOUTH_CONTAINS);
        BlockState top = extraData.getData(RastyFrameTile.UP_CONTAINS);
        BlockState east = extraData.getData(RastyFrameTile.EAST_CONTAINS);
        BlockState west = extraData.getData(RastyFrameTile.WEST_CONTAINS);
        List<BakedQuad> northPlane = new ArrayList<>();
        List<BakedQuad> topPlane = new ArrayList<>();
        List<BakedQuad> southPlane = new ArrayList<>();
        List<BakedQuad> bottomPlane = new ArrayList<>();
        List<BakedQuad> westPlane = new ArrayList<>();
        List<BakedQuad> eastPlane = new ArrayList<>();
        if (north != null && !(north.getBlock() instanceof RastyFrameBlock)) {
            List<TextureAtlasSprite> textureNorth = TextureHelper.getTextureListFromBlock(north.getBlock());
            ModelResourceLocation location = BlockModelShapes.getModelLocation(north);
            if (location != null && state != null) {
                IBakedModel model = Minecraft.getInstance().getModelManager().getModel(location);
                if (model != null) {
                    northPlane = ModelHelper.createPlaneNorth(textureNorth.get(0), -1);
                }
            }
        }
        if (top != null && !(top.getBlock() instanceof RastyFrameBlock)) {
            List<TextureAtlasSprite> textureTop = TextureHelper.getTextureListFromBlock(top.getBlock());
            ModelResourceLocation location = BlockModelShapes.getModelLocation(top);
            if (location != null && state != null) {
                IBakedModel model = Minecraft.getInstance().getModelManager().getModel(location);
                if (model != null) {
                    topPlane = ModelHelper.createPlaneTop(textureTop.get(0), -1);
                }
            }
        }
        if (bottom != null && !(bottom.getBlock() instanceof RastyFrameBlock)) {
            List<TextureAtlasSprite> textureBottom = TextureHelper.getTextureListFromBlock(bottom.getBlock());
            ModelResourceLocation location = BlockModelShapes.getModelLocation(bottom);
            if (location != null && state != null) {
                IBakedModel model = Minecraft.getInstance().getModelManager().getModel(location);
                if (model != null) {
                    bottomPlane = ModelHelper.createPlaneBottom(textureBottom.get(0), -1);
                }
            }
        }
        if (south != null && !(south.getBlock() instanceof RastyFrameBlock)) {
            List<TextureAtlasSprite> textureSouth = TextureHelper.getTextureListFromBlock(south.getBlock());
            ModelResourceLocation location = BlockModelShapes.getModelLocation(south);
            if (location != null && state != null) {
                IBakedModel model = Minecraft.getInstance().getModelManager().getModel(location);
                if (model != null) {
                    southPlane = ModelHelper.createPlaneSouth(textureSouth.get(0), -1);
                }
            }
        }
        if (west != null && !(west.getBlock() instanceof RastyFrameBlock)) {
            List<TextureAtlasSprite> textureWest = TextureHelper.getTextureListFromBlock(west.getBlock());
            ModelResourceLocation location = BlockModelShapes.getModelLocation(west);
            if (location != null && state != null) {
                IBakedModel model = Minecraft.getInstance().getModelManager().getModel(location);
                if (model != null) {
                    westPlane = ModelHelper.createPlaneWest(textureWest.get(0), -1);
                }
            }
        }
        if (east != null && !(east.getBlock() instanceof RastyFrameBlock)) {
            List<TextureAtlasSprite> textureEast = TextureHelper.getTextureListFromBlock(east.getBlock());
            ModelResourceLocation location = BlockModelShapes.getModelLocation(east);
            if (location != null && state != null) {
                IBakedModel model = Minecraft.getInstance().getModelManager().getModel(location);
                if (model != null) {
                    eastPlane = ModelHelper.createPlaneEast(textureEast.get(0), -1);
                }
            }
        }
        return ModelHelper.createCuboidFromPlane(topPlane, bottomPlane, northPlane, southPlane, westPlane, eastPlane);
    }

    private TextureAtlasSprite getTexture() {
        return Minecraft.getInstance().getAtlasSpriteGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE).apply(TEXTURE);
    }

    @Override
    public boolean isAmbientOcclusion() {
        return true;
    }

    @Override
    public boolean isGui3d() {
        return false;
    }

    @Override
    public boolean isSideLit() {
        return false;
    }

    @Override
    public boolean isBuiltInRenderer() {
        return false;
    }

    @Override
    public TextureAtlasSprite getParticleTexture() {
        return getTexture();
    }

    @Override
    public ItemOverrideList getOverrides() {
        return ItemOverrideList.EMPTY;
    }

    @Override
    public ItemCameraTransforms getItemCameraTransforms() {
        return ItemCameraTransforms.DEFAULT;
    }


}
