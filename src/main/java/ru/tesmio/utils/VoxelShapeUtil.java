package ru.tesmio.utils;

import net.minecraft.block.Block;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;

//WiP - необходимо разработать логику для поворота составных шейпов. Доделать логику для для поворотов на 90 и -90 градусов.
public class VoxelShapeUtil {
    public static final VoxelShapeUtil INSTANCE = new VoxelShapeUtil();
    double minX, minY, minZ, maxX, maxY, maxZ;
    public VoxelShape rotateBox (VoxelShape shape, Rotation rot) {

        minY = shape.getBoundingBox().minY;
        minX = shape.getBoundingBox().minX;
        minZ = shape.getBoundingBox().minZ;
        maxY = shape.getBoundingBox().maxY;
        maxX = shape.getBoundingBox().maxX;
        maxZ = shape.getBoundingBox().maxZ;

        if(rot == Rotation.CLOCKWISE_90) {
            centralize();
            minY = -shape.getBoundingBox().minX;
            minZ = shape.getBoundingBox().minZ;
            minX = shape.getBoundingBox().minY;
            maxY = -shape.getBoundingBox().maxX;
            maxX = shape.getBoundingBox().maxY;
            maxZ = -shape.getBoundingBox().maxZ;
            decentralize();
            normalize();
            return Block.makeCuboidShape(minX, minY, minZ, maxX, maxY, maxZ);
        }
        if(rot == Rotation.CLOCKWISE_180) {
            centralize();
            minY *= -1;
            minX *= -1;
            minZ *= -1;
            maxY *= -1;
            maxX *= -1;
            maxZ *= -1;
            decentralize();
            normalize();

            return VoxelShapes.create(minX, minY, minZ, maxX, maxY, maxZ);
        }
        return VoxelShapes.fullCube();
    }
    public void centralize() {
        minX -= 0.5;
        minY -= 0.5;
        minZ -= 0.5;
        maxX -= 0.5;
        maxY -= 0.5;
        maxZ -= 0.5;
    }
    public void decentralize() {
        minX += 0.5;
        minY += 0.5;
        minZ += 0.5;
        maxX += 0.5;
        maxY += 0.5;
        maxZ += 0.5;
    }
    public void normalize() {
        if (maxX < minX) {
            maxX += minX;
            minX = maxX - minZ;
            maxX -= minX;
        }
        if (maxZ < minZ) {
            maxZ += minZ;
            minZ = maxZ - minZ;
            maxZ -= minZ;
        }
        if (maxY < minY) {
            maxY += minY;
            minY = maxY - minY;
            maxY -= minY;
        }
    }
}
