package ru.tesmio.reg;

import net.minecraft.client.renderer.RenderState;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexFormat;

public class RegRenderTypes  extends RenderType {
    private static RenderType TRANSLUCENT_NS;
    public static void init() {
        TRANSLUCENT_NS = makeType("translucent_ns", DefaultVertexFormats.BLOCK, 7, 262144, true, true, getTranslucentNSState());
    }
    public RegRenderTypes(String nameIn, VertexFormat formatIn, int drawModeIn, int bufferSizeIn, boolean useDelegateIn, boolean needsSortingIn, Runnable setupTaskIn, Runnable clearTaskIn) {
        super(nameIn, formatIn, drawModeIn, bufferSizeIn, useDelegateIn, needsSortingIn, setupTaskIn, clearTaskIn);
    }

    private static RenderType.State getTranslucentNSState() {
        return RenderType.State.getBuilder().shadeModel(RenderState.SHADE_DISABLED).lightmap(RenderState.LIGHTMAP_DISABLED).texture(BLOCK_SHEET_MIPPED).transparency(TRANSLUCENT_TRANSPARENCY).target(TRANSLUCENT_TARGET).build(true);
    }

    public static RenderType getTranslucentNS() {
        return TRANSLUCENT_NS;
    }
}