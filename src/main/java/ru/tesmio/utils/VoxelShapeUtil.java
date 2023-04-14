package ru.tesmio.utils;

import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;

public class VoxelShapeUtil {
    public static final VoxelShapeUtil INSTANCE = new VoxelShapeUtil();
    public static VoxelShape shapeRotCCW90(VoxelShape vs) {
        double new_minX, new_minZ, new_maxX, new_maxZ;
        new_minX = 1 - vs.getBoundingBox().maxZ;
        new_minZ =  vs.getBoundingBox().minX;
        new_maxX = 1 - vs.getBoundingBox().minZ;
        new_maxZ =  vs.getBoundingBox().maxX;
        return VoxelShapes.create(new_minX, vs.getBoundingBox().minY, new_minZ, new_maxX, vs.getBoundingBox().maxY, new_maxZ);
        }
        public static VoxelShape shapeRotCW90(VoxelShape vs) {
            double new_minX, new_minZ, new_maxX, new_maxZ;
            new_minX = vs.getBoundingBox().minZ;
            new_minZ = vs.getBoundingBox().minX;
            new_maxX = vs.getBoundingBox().maxZ;
            new_maxZ = vs.getBoundingBox().maxX;
            return VoxelShapes.create(new_minX, vs.getBoundingBox().minY, new_minZ, new_maxX, vs.getBoundingBox().maxY, new_maxZ);
        }
        public static VoxelShape shapeRot180(VoxelShape vs) {
        double new_minX, new_minZ, new_maxX, new_maxZ;
        new_minX = 1 - vs.getBoundingBox().minX;
        new_minZ = 1 - vs.getBoundingBox().minZ;
        new_maxX = 1 - vs.getBoundingBox().maxX;
        new_maxZ = 1 - vs.getBoundingBox().maxZ;
        return VoxelShapes.create(new_minX, vs.getBoundingBox().minY, new_minZ, new_maxX, vs.getBoundingBox().maxY, new_maxZ);
        }
    public static VoxelShape shapeRotComplex180(VoxelShape vs) {
        //прототип поворачивалки комплексных шейпов. не работает
        double new_minX, new_minZ, new_maxX, new_maxZ;

        for (AxisAlignedBB aabb : vs.toBoundingBoxList()) {
            new_minX = 1 - aabb.minX;
            new_minZ = 1 - aabb.minZ;
            new_maxX = 1 - aabb.maxX;
            new_maxZ = 1 - aabb.maxZ;
            return VoxelShapes.create(new_minX, vs.getBoundingBox().minY, new_minZ, new_maxX, vs.getBoundingBox().maxY, new_maxZ);
        }
        return vs;
    }

    public static VoxelShape shapeMirror(VoxelShape vs) {
        return shapeRot180(vs);
    }
}
