package ru.tesmio.soviet.utils;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormatElement;
import net.minecraft.util.Direction;
import net.minecraftforge.client.model.pipeline.BakedQuadBuilder;
import net.minecraft.util.math.vector.Vector3d;
import java.util.ArrayList;
import java.util.List;

public class ModelHelper {

    /**
     * This method is used to put all parameters for a vertex. A vertex in this context
     * is something like a point or a vector with special attributes. These include the
     * texture, the light level and so on. This method is needed for the quads (which
     * are something like the faces of a cuboid) and those are determined by 4 vertices
     * @param builder used to construct the quads, content is saved as int array afterwards
     * @param normal normal vector
     * @param x x component of the vertex
     * @param y y component of the vertex
     * @param z z component of the vertex
     * @param u u component of the texture, that means the horizontal axis
     * @param v v component of the texture, that means the vertical axis
     * @param sprite the texture for the vertex, sprite[u,v] will be displayed for this vertex
     * @param r red value
     * @param g green value
     * @param b blue value
     */
    private static void putVertex(BakedQuadBuilder builder, Vector3d normal,
                                  double x, double y, double z, float u, float v, TextureAtlasSprite sprite, float r, float g, float b) {

        ImmutableList<VertexFormatElement> elements = builder.getVertexFormat().getElements().asList();
        for (int j = 0 ; j < elements.size() ; j++) {
            VertexFormatElement e = elements.get(j);
            switch (e.getUsage()) {
                case POSITION:
                    builder.put(j, (float) x, (float) y, (float) z, 1.0f);
                    break;
                case COLOR:
                    builder.put(j, r, g, b, 1.0f);
                    break;
                case UV:
                    switch (e.getIndex()) {
                        case 0:
                            float iu = sprite.getInterpolatedU(u);
                            float iv = sprite.getInterpolatedV(v);
                            builder.put(j, iu, iv);
                            break;
                        case 2:
                            builder.put(j, (short) 0, (short) 0);
                            break;
                        default:
                            builder.put(j);
                            break;
                    }
                    break;
                case NORMAL:
                    builder.put(j, (float) normal.x, (float) normal.y, (float) normal.z);
                    break;
                default:
                    builder.put(j);
                    break;
            }
        }
        builder.setQuadTint(1);
    }

    /**
     * This method is used to create quads. Simply put, a quad is one face of a block
     * This method calls the putVertex method four times, because we need 4 vertices
     * to determine a rectangle, which is the face of the block. For this reason we
     * need 4 vectors, the texture, and 4 u and v values
     * @param v1 first vector/point of the face
     * @param v2 second vector/point of the face
     * @param v3 third vector/point of the face
     * @param v4 forth vector/point of the face
     * @param sprite texture of the block, saved as TextureAtlasSprite
     * @param ulow low u value, determines the left corners of the sprite
     * @param uhigh  high u value, determines the right corners of the sprite
     * @param vlow  low v value, determines the top corners of the sprite
     * @param vhigh  high v value, determines the bottom corners of the sprite
     * @param tintIndex only needed for tintable blocks like grass
     * @return Baked quad i.e. the completed face of a block
     */
    public static BakedQuad createQuad(Vector3d v1, Vector3d v2, Vector3d v3, Vector3d v4, TextureAtlasSprite sprite, float ulow, float uhigh, float vlow, float vhigh, int tintIndex) {
        Vector3d normal = v3.subtract(v2).crossProduct(v1.subtract(v2)).normalize();

        BakedQuadBuilder builder = new BakedQuadBuilder(sprite);
        builder.setQuadOrientation(Direction.getFacingFromVector(normal.x, normal.y, normal.z));
        builder.setApplyDiffuseLighting(true);
        if (tintIndex>-1) {
            builder.setQuadTint(tintIndex);
        }
        putVertex(builder, normal, v1.x, v1.y, v1.z, ulow, vlow, sprite, 1.0f, 1.0f, 1.0f);
        putVertex(builder, normal, v2.x, v2.y, v2.z, ulow, vhigh, sprite, 1.0f, 1.0f, 1.0f);
        putVertex(builder, normal, v3.x, v3.y, v3.z, uhigh, vhigh, sprite, 1.0f, 1.0f, 1.0f);
        putVertex(builder, normal, v4.x, v4.y, v4.z, uhigh, vlow, sprite, 1.0f, 1.0f, 1.0f);
        return builder.build();
    }

    public static List<BakedQuad> createEmpty() {
        List<BakedQuad> quads = new ArrayList<>();
        return quads;
    }

    public static List<BakedQuad> createPlaneTop(TextureAtlasSprite texture, int tintIndex) {
        float xl =0, xh = 1, yh = 1,  zl= 0,  zh = 1;
        List<BakedQuad> quads = new ArrayList<>();
        Vector3d NWU = v(xl,yh,zl);
        Vector3d NEU = v(xl,yh,zh);
        Vector3d SEU = v(xh,yh,zh);
        Vector3d SWU = v(xh,yh,zl);
        if(xl < 0) {
            xl++;
        }
        if(xh > 1) {
            xh--;
        }
        if(zl < 0) {
            zl++;
        }
        if(zh > 1) {
            zh--;
        }
        quads.add(createQuad(NWU, NEU, SEU, SWU, texture, xl*16, xh*16, zl*16, zh*16, tintIndex));
        return quads;
    }
    public static List<BakedQuad> createPlaneBottom( TextureAtlasSprite texture, int tintIndex) {
        float xl = 0,  xh = 1,  yl = 0,  zl = 0,  zh = 1;
        List<BakedQuad> quads = new ArrayList<>();
        Vector3d NWD = v(xl,yl,zl);
        Vector3d NED = v(xl,yl,zh);
        Vector3d SWD = v(xh,yl,zl);
        Vector3d SED = v(xh,yl,zh);
        if(xl < 0) {
            xl++;
        }
        if(xh > 1) {
            xh--;
        }
        if(zl < 0) {
            zl++;
        }
        if(zh > 1) {
            zh--;
        }
        quads.add(createQuad(SWD, SED, NED, NWD, texture, xl*16, xh*16, zl*16, zh*16, tintIndex));
        return quads;
    }
    public static List<BakedQuad> createPlaneNorth(TextureAtlasSprite texture, int tintIndex) {
        float xl = 0,  xh = 1,  yl = 0,  yh = 1,  zl = 0;
        Vector3d SWD = v(xh,yl,zl);
        Vector3d NWD = v(xl,yl,zl);
        Vector3d SWU = v(xh,yh,zl);
        Vector3d NWU = v(xl,yh,zl);
        List<BakedQuad> quads = new ArrayList<>();
        if(xl < 0) {
            xl++;
        }
        if(xh > 1) {
            xh--;
        }
        if(yl < 0) {
            yl++;
        }
        if(yh > 1) {
            yh--;
        }
        quads.add(createQuad(SWU, SWD, NWD, NWU, texture, xl*16, xh*16, 16-yh*16, 16-yl*16, tintIndex));
        return quads;
    }
    public static List<BakedQuad> createPlaneSouth(TextureAtlasSprite texture, int tintIndex) {
        List<BakedQuad> quads = new ArrayList<>();
        float xl =0, xh = 1, yl = 0, yh = 1, zh = 1;
        Vector3d NEU = v(xl,yh,zh);
        Vector3d NED = v(xl,yl,zh);
        Vector3d SEU = v(xh,yh,zh);
        Vector3d SED = v(xh,yl,zh);
        if(xl < 0) {
            xl++;
        }
        if(xh > 1) {
            xh--;
        }
        if(yl < 0) {
            yl++;
        }
        if(yh > 1) {
            yh--;
        }
        quads.add(createQuad(NEU, NED, SED, SEU, texture, xl*16, xh*16, 16-yh*16, 16-yl*16, tintIndex));
        return quads;
    }
    public static List<BakedQuad> createPlaneWest(TextureAtlasSprite texture, int tintIndex) {
        List<BakedQuad> quads = new ArrayList<>();
        float xl =0, yl = 0, yh = 1, zl = 0, zh = 1;
        Vector3d NWU = v(xl,yh,zl);
        Vector3d NEU = v(xl,yh,zh);
        Vector3d NWD = v(xl,yl,zl);
        Vector3d NED = v(xl,yl,zh);
        if(yl < 0) {
            yl++;
        }
        if(yh > 1) {
            yh--;
        }
        if(zl < 0) {
            zl++;
        }
        if(zh > 1) {
            zh--;
        }
        quads.add(createQuad(NWU, NWD, NED, NEU, texture, zl*16, zh*16, 16-yh*16, 16-yl*16, tintIndex));
        return quads;
    }
    public static List<BakedQuad> createPlaneEast(TextureAtlasSprite texture, int tintIndex) {
        List<BakedQuad> quads = new ArrayList<>();
        float xh = 1, yl = 0, yh = 1, zl = 0, zh = 1;
        Vector3d SWU = v(xh,yh,zl);
        Vector3d SEU = v(xh,yh,zh);
        Vector3d SWD = v(xh,yl,zl);
        Vector3d SED = v(xh,yl,zh);

        if(yl < 0) {
            yl++;
        }
        if(yh > 1) {
            yh--;
        }
        if(zl < 0) {
            zl++;
        }
        if(zh > 1) {
            zh--;
        }
        quads.add(createQuad(SEU, SED, SWD, SWU, texture, zl*16, zh*16, 16-yh*16, 16-yl*16, tintIndex));
        return quads;
    }

    public static List<BakedQuad> createBaseCuboid(TextureAtlasSprite texture) {
        float xl =0, xh = 1, yl = 0, yh = 1, zl = 0, zh = 1;
        int tintIndex = -1;
        List<BakedQuad> quads = new ArrayList<>();
        Vector3d NWU = v(xl,yh,zl);
        Vector3d NEU = v(xl,yh,zh);
        Vector3d NWD = v(xl,yl,zl);
        Vector3d NED = v(xl,yl,zh);
        Vector3d SWU = v(xh,yh,zl);
        Vector3d SEU = v(xh,yh,zh);
        Vector3d SWD = v(xh,yl,zl);
        Vector3d SED = v(xh,yl,zh);
        if(xl < 0) {
            xl++;
        }
        if(xh > 1) {
            xh--;
        }
        if(yl < 0) {
            yl++;
        }
        if(yh > 1) {
            yh--;
        }
        if(zl < 0) {
            zl++;
        }
        if(zh > 1) {
            zh--;
        }
        quads.add(createQuad(NWU, NEU, SEU, SWU, texture, xl*16, xh*16, zl*16, zh*16, tintIndex));
        quads.add(createQuad(SWD, SED, NED, NWD, texture, xl*16, xh*16, zl*16, zh*16, tintIndex));
        quads.add(createQuad(SWU, SWD, NWD, NWU, texture, xl*16, xh*16, 16-yh*16, 16-yl*16, tintIndex));
        quads.add(createQuad(NEU, NED, SED, SEU, texture, xl*16, xh*16, 16-yh*16, 16-yl*16, tintIndex));
        quads.add(createQuad(NWU, NWD, NED, NEU, texture, zl*16, zh*16, 16-yh*16, 16-yl*16, tintIndex));
        quads.add(createQuad(SEU, SED, SWD, SWU, texture, zl*16, zh*16, 16-yh*16, 16-yl*16, tintIndex));
        return quads;
    }
        public static List<BakedQuad> createCuboid(float xl, float xh, float yl, float yh, float zl, float zh, TextureAtlasSprite texture, int tintIndex) {
        List<BakedQuad> quads = new ArrayList<>();
        //Eight corners of the block
        Vector3d NWU = v(xl,yh,zl); //North-West-Up
        Vector3d NEU = v(xl,yh,zh); //...
        Vector3d NWD = v(xl,yl,zl);
        Vector3d NED = v(xl,yl,zh);
        Vector3d SWU = v(xh,yh,zl);
        Vector3d SEU = v(xh,yh,zh);
        Vector3d SWD = v(xh,yl,zl);
        Vector3d SED = v(xh,yl,zh); //South-East-Down
        if(xl < 0) {
            xl++;
        }
        if(xh > 1) {
            xh--;
        }
        if(yl < 0) {
            yl++;
        }
        if(yh > 1) {
            yh--;
        }
        if(zl < 0) {
            zl++;
        }
        if(zh > 1) {
            zh--;
        }
        quads.add(createQuad(NWU, NEU, SEU, SWU, texture, xl*16, xh*16, zl*16, zh*16, tintIndex));
        quads.add(createQuad(SWD, SED, NED, NWD, texture, xl*16, xh*16, zl*16, zh*16, tintIndex));
        quads.add(createQuad(SWU, SWD, NWD, NWU, texture, xl*16, xh*16, 16-yh*16, 16-yl*16, tintIndex));
        quads.add(createQuad(NEU, NED, SED, SEU, texture, xl*16, xh*16, 16-yh*16, 16-yl*16, tintIndex));
        quads.add(createQuad(NWU, NWD, NED, NEU, texture, zl*16, zh*16, 16-yh*16, 16-yl*16, tintIndex));
        quads.add(createQuad(SEU, SED, SWD, SWU, texture, zl*16, zh*16, 16-yh*16, 16-yl*16, tintIndex));
        return quads;
    }
    public static List<BakedQuad> createCuboidFromTexture(TextureAtlasSprite top, TextureAtlasSprite down, TextureAtlasSprite north, TextureAtlasSprite south, TextureAtlasSprite west, TextureAtlasSprite east) {
        List<BakedQuad> quads = new ArrayList<>();
        createPlaneTop(top, -1);
        createPlaneBottom(top, -1);
        createPlaneNorth(top, -1);
        createPlaneSouth(top, -1);
        createPlaneWest(top, -1);
        createPlaneEast(top, -1);
        return quads;
    }
    public static List<BakedQuad> createCuboidFromPlane(List<BakedQuad> top, List<BakedQuad> down, List<BakedQuad> north, List<BakedQuad> south, List<BakedQuad> west, List<BakedQuad> east) {
        List<BakedQuad> quads = new ArrayList<>();
        quads.addAll(top);
        quads.addAll(down);
        quads.addAll(north);
        quads.addAll(south);
        quads.addAll(west);
        quads.addAll(east);
        return quads;
    }
    /**
     * This just builds vectors and is useful for clean code
     * @param x x component
     * @param y y component
     * @param z z component
     * @return 3D-Vector with input values. Why am I writing this...
     */
    private static Vector3d v(double x, double y, double z) {
        return new Vector3d(x, y, z);
    }
}