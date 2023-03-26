package ru.tesmio.blocks.decorative.props.stillage;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import ru.tesmio.blocks.baseblock.BlockSideUpDownCM;

public class StillageTER extends TileEntityRenderer<StillageTileEntity> {

    private Minecraft mc = Minecraft.getInstance();

    public StillageTER(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(StillageTileEntity te, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        if (te.getItem().equals(ItemStack.EMPTY) || te.getItem().getItem().equals(Items.AIR))
            return;
        int lightLevel = getLightLevel(te.getWorld(), te.getPos().up());
        float angle = 0;
       if(te.getBlockState().get(BlockSideUpDownCM.FACING).getAxis() == Direction.Axis.X) angle = te.getBlockState().get(BlockSideUpDownCM.FACING).getHorizontalAngle();
       else angle = te.getBlockState().get(BlockSideUpDownCM.FACING).getOpposite().getHorizontalAngle();


        if(te.getBlockState().get(BlockSideUpDownCM.PART) == BlockSideUpDownCM.EnumPart.UP) {

            renderItem(te.getItem(), new double[] { 0.5d, -0.15d, 0.5d },
                    Vector3f.YP.rotationDegrees(angle), matrixStackIn, bufferIn, partialTicks,
                    combinedOverlayIn, lightLevel, 3.1f);
        } else {
            renderItem(te.getItem(), new double[]{0.5d, 0.05d, 0.5d},
                    Vector3f.YP.rotationDegrees(angle), matrixStackIn, bufferIn, partialTicks,
                    combinedOverlayIn, lightLevel, 3.1f);
        }
    }

    private void renderItem(ItemStack stack, double[] translation, Quaternion rotation, MatrixStack matrixStack, IRenderTypeBuffer buffer, float partialTicks, int combinedOverlay, int lightLevel, float scale) {
        matrixStack.push();
        matrixStack.translate(translation[0], translation[1], translation[2]);
        matrixStack.rotate(rotation);
        matrixStack.scale(scale, scale, scale);

        IBakedModel model = mc.getItemRenderer().getItemModelWithOverrides(stack, null, null);
        mc.getItemRenderer().renderItem(stack, ItemCameraTransforms.TransformType.GROUND, true, matrixStack, buffer,
                lightLevel, combinedOverlay, model);
        matrixStack.pop();
    }


    private int getLightLevel(World world, BlockPos pos) {
        int bLight = world.getLightFor(LightType.BLOCK, pos);
        int sLight = world.getLightFor(LightType.SKY, pos);
        return LightTexture.packLight(bLight, sLight);
    }
}
