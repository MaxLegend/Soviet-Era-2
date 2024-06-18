package ru.tesmio.soviet.items.protection_suit;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
@OnlyIn(Dist.CLIENT)
public class SuitModel extends BipedModel {
    protected final EquipmentSlotType slot;

    public ModelRenderer ozk_body = new ModelRenderer(this);
    public ModelRenderer ozk_left_sleeve = new ModelRenderer(this);
    public ModelRenderer ozk_right_sleeve = new ModelRenderer(this);
    public ModelRenderer mask = new ModelRenderer(this);
    public ModelRenderer cube_r1 = new ModelRenderer(this);
    public ModelRenderer left_leg = new ModelRenderer(this);
    public ModelRenderer right_leg = new ModelRenderer(this);
    public ModelRenderer left_boot = new ModelRenderer(this);
    public ModelRenderer right_boot = new ModelRenderer(this);
    @Override

    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        //head
        mask.copyModelAngles(bipedHeadwear);
        mask.render(matrixStack, buffer, packedLight, packedOverlay);
        //chestplate
        ozk_body.copyModelAngles(bipedBody);
        ozk_left_sleeve.copyModelAngles(bipedLeftArm);
        ozk_right_sleeve.copyModelAngles(bipedRightArm);
        ozk_body.render(matrixStack, buffer, packedLight, packedOverlay);
        ozk_left_sleeve.render(matrixStack, buffer, packedLight, packedOverlay);
        ozk_right_sleeve.render(matrixStack, buffer, packedLight, packedOverlay);
        //leggins
        left_leg.copyModelAngles(bipedLeftLeg);
        right_leg.copyModelAngles(bipedRightLeg);
        left_leg.render(matrixStack, buffer, packedLight, packedOverlay);
        right_leg.render(matrixStack, buffer, packedLight, packedOverlay);
        //boots
        left_boot.copyModelAngles(bipedLeftLeg);
        right_boot.copyModelAngles(bipedRightLeg);
        left_boot.render(matrixStack, buffer, packedLight, packedOverlay);
        right_boot.render(matrixStack, buffer, packedLight, packedOverlay);
    }
    public SuitModel(EquipmentSlotType slot) {
        super(1F);
        this.slot = slot;
        switch (slot) {
            case HEAD:
                ADCGasMaskModel();
                break;
            case CHEST:
                ADCJacketModel();
                break;
            case LEGS:
                ADCLegginsModel();
                break;
            case FEET:
                ADCBootsModel();
                break;
        }




    }

    public void ADCBootsModel() {
        textureWidth = 64;
        textureHeight = 64;

        left_boot = new ModelRenderer(this);
        left_boot.setRotationPoint(2.0625F, 16.5F, 1.0F);
        left_boot.setTextureOffset(22, 22).addBox(-2.0625F, 10.0F, -4.5F, 4.0F, 2.0F, 2.0F, 0.0F, false);
        left_boot.setTextureOffset(8, 10).addBox(1.9375F, 10.0F, -3.0F, 1.0F, 2.0F, 6.0F, 0.0F, false);
        left_boot.setTextureOffset(16, 22).addBox(1.9375F, 7.0F, -2.0F, 1.0F, 3.0F, 4.0F, 0.0F, false);
        left_boot.setTextureOffset(18, 15).addBox(-3.5625F, 7.0F, -2.0F, 1.0F, 3.0F, 4.0F, 0.0F, false);
        left_boot.setTextureOffset(28, 19).addBox(-2.0625F, 8.0F, -3.5F, 4.0F, 2.0F, 1.0F, 0.0F, false);
        left_boot.setTextureOffset(16, 10).addBox(-2.0625F, -1.0F, -2.0F, 4.0F, 1.0F, 4.0F, 0.0F, false);
        left_boot.setTextureOffset(8, 2).addBox(-3.0625F, 10.0F, -3.0F, 1.0F, 2.0F, 6.0F, 0.0F, false);
        left_boot.setTextureOffset(28, 9).addBox(-2.0625F, 10.0F, 3.0F, 4.0F, 2.0F, 1.0F, 0.0F, false);
        left_boot.setTextureOffset(0, 23).addBox(-2.0625F, 7.0F, 3.0F, 4.0F, 3.0F, 1.0F, 0.0F, false);
        left_boot.setTextureOffset(0, 26).addBox(-3.25F, -1.0F, -0.5F, 0.0F, 11.0F, 1.0F, 0.0F, false);
        left_boot.setTextureOffset(14, 24).addBox(3.0F, -1.0F, -0.5F, 0.0F, 11.0F, 1.0F, 0.0F, false);

        right_boot = new ModelRenderer(this);
        right_boot.setRotationPoint(7.0625F, 16.5F, 1.0F);
        right_boot.setTextureOffset(22, 5).addBox(-2.0625F, 10.0F, -4.5F, 4.0F, 2.0F, 2.0F, 0.0F, false);
        right_boot.setTextureOffset(0, 0).addBox(1.9375F, 10.0F, -3.0F, 1.0F, 2.0F, 6.0F, 0.0F, false);
        right_boot.setTextureOffset(10, 18).addBox(2.4375F, 7.0F, -2.0F, 1.0F, 3.0F, 4.0F, 0.0F, false);
        right_boot.setTextureOffset(0, 16).addBox(-3.0625F, 7.0F, -2.0F, 1.0F, 3.0F, 4.0F, 0.0F, false);
        right_boot.setTextureOffset(28, 0).addBox(-2.0625F, 8.0F, -3.5F, 4.0F, 2.0F, 1.0F, 0.0F, false);
        right_boot.setTextureOffset(16, 0).addBox(-2.0625F, -1.0F, -2.0F, 4.0F, 1.0F, 4.0F, 0.0F, false);
        right_boot.setTextureOffset(0, 8).addBox(-3.0625F, 10.0F, -3.0F, 1.0F, 2.0F, 6.0F, 0.0F, false);
        right_boot.setTextureOffset(26, 26).addBox(-2.0625F, 10.0F, 3.0F, 4.0F, 2.0F, 1.0F, 0.0F, false);
        right_boot.setTextureOffset(24, 15).addBox(-2.0625F, 7.0F, 3.0F, 4.0F, 3.0F, 1.0F, 0.0F, false);
        right_boot.setTextureOffset(12, 24).addBox(-3.125F, -1.0F, -0.5F, 0.0F, 11.0F, 1.0F, 0.0F, false);
        right_boot.setTextureOffset(10, 24).addBox(3.125F, -1.0F, -0.5F, 0.0F, 11.0F, 1.0F, 0.0F, false);


    }

    public void ADCJacketModel() {
        textureWidth = 64;
        textureHeight = 64;

        ozk_body = new ModelRenderer(this);
        ozk_body.setRotationPoint(0.0F, 15.1667F, 1.5F);
        ozk_body.setTextureOffset(0, 13).addBox(-4.0F, -0.1667F, -3.0F, 8.0F, 12.0F, 1.0F, 0.0F, false);
        ozk_body.setTextureOffset(0, 0).addBox(-4.0F, -0.1667F, 2.0F, 8.0F, 12.0F, 1.0F, 0.0F, false);
        ozk_body.setTextureOffset(20, 32).addBox(-4.0F, -0.1667F, 3.0F, 8.0F, 3.0F, 2.0F, 0.0F, false);
        ozk_body.setTextureOffset(38, 26).addBox(-4.0F, -1.1667F, 4.0F, 8.0F, 1.0F, 2.0F, 0.0F, false);
        ozk_body.setTextureOffset(18, 16).addBox(-4.0F, -0.1667F, -2.0F, 1.0F, 12.0F, 4.0F, 0.0F, false);
        ozk_body.setTextureOffset(18, 0).addBox(3.0F, -0.1667F, -2.0F, 1.0F, 12.0F, 4.0F, 0.0F, false);

        ozk_left_sleeve = new ModelRenderer(this);
        ozk_left_sleeve.setRotationPoint(0.0F, 24.0F, 0.0F);
        ozk_left_sleeve.setTextureOffset(38, 13)
                .addBox(-1.0F, -2.0F, -3F, 4.0F, 12.0F, 1.0F, 0.0F, false);
        ozk_left_sleeve.setTextureOffset(20, 37)
                .addBox(-1.0F, -2.0F, 2F, 4.0F, 12.0F, 1.0F, 0.0F, false);
        ozk_left_sleeve.setTextureOffset(28, 16)
                .addBox(3.0F, -2.0F, -2F, 1.0F, 12.0F, 4.0F, 0.0F, false);
        ozk_left_sleeve.setTextureOffset(10, 28)
                .addBox(-2.0F, -2.0F, -2F, 1.0F, 12.0F, 4.0F, 0.0F, false);
        ozk_left_sleeve.setTextureOffset(0, 44)
                .addBox(-1.0F, -3.0F, -2F, 4.0F, 1.0F, 4.0F, 0.0F, false);
        ozk_left_sleeve.setTextureOffset(40, 39)
                .addBox(-1.0F, 10.0F, -2F, 4.0F, 1.0F, 4.0F, 0.0F, false);

        ozk_right_sleeve = new ModelRenderer(this);
        ozk_right_sleeve.setRotationPoint(-12.0F, 24.0F, 0.0F);
        ozk_right_sleeve.setTextureOffset(40, 34)
                .addBox(-3.0F, -3.0F, -2F, 4.0F, 1.0F, 4.0F, 0.0F, false);
        ozk_right_sleeve.setTextureOffset(38, 29)
                .addBox(-3.0F, 10.0F, -2F, 4.0F, 1.0F, 4.0F, 0.0F, false);
        ozk_right_sleeve.setTextureOffset(30, 37)
                .addBox(-3.0F, -2.0F, 2F, 4.0F, 12.0F, 1.0F, 0.0F, false);
        ozk_right_sleeve.setTextureOffset(0, 26)
                .addBox(-4.0F, -2.0F, -2F, 1.0F, 12.0F, 4.0F, 0.0F, false);
        ozk_right_sleeve.setTextureOffset(38, 0)
                .addBox(-3.0F, -2.0F, -3F, 4.0F, 12.0F, 1.0F, 0.0F, false);
        ozk_right_sleeve.setTextureOffset(28, 0)
                .addBox(1.0F, -2.0F, -2F, 1.0F, 12.0F, 4.0F, 0.0F, false);

    }

    public void ADCLegginsModel() {
        textureWidth = 64;
        textureHeight = 64;

        left_leg = new ModelRenderer(this);
        left_leg.setRotationPoint(-2.0F, 24.0F, 2.0F);
        left_leg.setTextureOffset(30, 0).addBox(-2.0F, 0.0F, -3.0F, 4.0F, 12.0F, 1.0F, 0.0F, false);
        left_leg.setTextureOffset(0, 16).addBox(2.0F, 0.0F, -2.0F, 1.0F, 12.0F, 4.0F, 0.0F, false);
        left_leg.setTextureOffset(20, 26).addBox(-2.0F, 0.0F, 2.0F, 4.0F, 12.0F, 1.0F, 0.0F, false);
        left_leg.setTextureOffset(10, 16).addBox(-3.0F, 0.0F, -2.0F, 1.0F, 12.0F, 4.0F, 0.0F, false);

        right_leg = new ModelRenderer(this);
        right_leg.setRotationPoint(3.0F, 24.0F, 2.0F);
        right_leg.setTextureOffset(20, 13).addBox(-2.0F, 0.0F, -3.0F, 4.0F, 12.0F, 1.0F, 0.0F, false);
        right_leg.setTextureOffset(10, 0).addBox(2.0F, 0.0F, -2.0F, 1.0F, 12.0F, 4.0F, 0.0F, false);
        right_leg.setTextureOffset(20, 0).addBox(-2.0F, 0.0F, 2.0F, 4.0F, 12.0F, 1.0F, 0.0F, false);
        right_leg.setTextureOffset(0, 0).addBox(-3.0F, 0.0F, -2.0F, 1.0F, 12.0F, 4.0F, 0.0F, false);
    }


    public void ADCGasMaskModel() {

        textureWidth = 64;
        textureHeight = 64;

        mask = new ModelRenderer(this);
        mask.setRotationPoint(0.0F, 24.0F, 0.0F);
        mask.setTextureOffset(11, 21).addBox(-3.0F, -9.0F, -4.0F, 1.0F, 1.0F, 9.0F, 0.0F, false);
        mask.setTextureOffset(22, 6).addBox(-3.0F, -8.0F, -5.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        mask.setTextureOffset(22, 4).addBox(-3.0F, -7.0F, -5.0F, 6.0F, 1.0F, 1.0F, 0.0F, false);
        mask.setTextureOffset(22, 8).addBox(2.0F, -8.0F, -5.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        mask.setTextureOffset(0, 20).addBox(2.0F, -9.0F, -4.0F, 1.0F, 1.0F, 9.0F, 0.0F, false);
        mask.setTextureOffset(11, 11).addBox(4.0F, -6.0F, -4.0F, 1.0F, 1.0F, 9.0F, 0.0F, false);
        mask.setTextureOffset(0, 20).addBox(2.0F, -8.0F, 4.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
        mask.setTextureOffset(3, 22).addBox(3.0F, -6.0F, 4.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        mask.setTextureOffset(22, 11).addBox(-2.0F, -6.0F, 4.0F, 4.0F, 4.0F, 1.0F, 0.0F, false);
        mask.setTextureOffset(15, 21).addBox(3.0F, -2.0F, 4.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        mask.setTextureOffset(0, 0).addBox(4.0F, -2.0F, -4.0F, 1.0F, 1.0F, 9.0F, 0.0F, false);
        mask.setTextureOffset(3, 17).addBox(2.0F, -3.0F, 4.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        mask.setTextureOffset(16, 6).addBox(-3.0F, -3.0F, 4.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        mask.setTextureOffset(11, 21).addBox(-4.0F, -2.0F, 4.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        mask.setTextureOffset(0, 10).addBox(-5.0F, -2.0F, -4.0F, 1.0F, 1.0F, 9.0F, 0.0F, false);
        mask.setTextureOffset(11, 13).addBox(-4.0F, -6.0F, -5.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
        mask.setTextureOffset(10, -1).addBox(-1.0F, -6.0F, -6.0F, 2.0F, 3.0F, 2.0F, 0.0F, false);
        mask.setTextureOffset(0, 14).addBox(3.0F, -6.0F, -5.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
        mask.setTextureOffset(11, 7).addBox(1.0F, -6.0F, -5.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
        mask.setTextureOffset(4, 14).addBox(3.0F, -3.0F, -5.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
        mask.setTextureOffset(11, 1).addBox(-5.0F, -6.0F, -4.0F, 1.0F, 1.0F, 9.0F, 0.0F, false);
        mask.setTextureOffset(14, 16).addBox(-3.0F, -8.0F, 4.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
        mask.setTextureOffset(4, 20).addBox(-4.0F, -6.0F, 4.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        mask.setTextureOffset(15, 13).addBox(-4.0F, -3.0F, -5.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
        mask.setTextureOffset(22, 0).addBox(-3.0F, -3.0F, -5.0F, 6.0F, 3.0F, 1.0F, 0.0F, false);
        mask.setTextureOffset(12, 5).addBox(-1.0F, -4.0F, -6.0625F, 2.0F, 2.0F, 0.0F, 0.0F, false);
        mask.setTextureOffset(0, 10).addBox(-1.0F, -1.0F, -7.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
        mask.setTextureOffset(11, 11).addBox(-3.0F, -6.0F, -5.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
        mask.setTextureOffset(27, 15).addBox(1.0F, -5.0F, -4.6875F, 2.0F, 2.0F, 1.0F, 0.0F, false);
        mask.setTextureOffset(27, 15).addBox(-3.0F, -5.0F, -4.6875F, 2.0F, 2.0F, 1.0F, 0.0F, false);

        cube_r1 = new ModelRenderer(this);
        cube_r1.setRotationPoint(3.5581F, -1.75F, -5.2348F);
        mask.addChild(cube_r1);
        setRotationAngle(cube_r1, 0.2182F, -0.7854F, 0.0F);
        cube_r1.setTextureOffset(16, 3).addBox(-0.375F, -0.25F, -0.75F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        cube_r1.setTextureOffset(0, 4).addBox(-0.875F, -0.75F, -0.25F, 2.0F, 2.0F, 2.0F, 0.0F, false);
        cube_r1.setTextureOffset(0, 0).addBox(-1.625F, -1.25F, -1.5F, 3.0F, 3.0F, 1.0F, 0.0F, false);

    }


    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
