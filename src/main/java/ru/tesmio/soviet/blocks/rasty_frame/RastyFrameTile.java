package ru.tesmio.soviet.blocks.rasty_frame;

import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.model.ModelDataManager;
import net.minecraftforge.client.model.data.IModelData;
import net.minecraftforge.client.model.data.ModelDataMap;
import net.minecraftforge.client.model.data.ModelProperty;
import net.minecraftforge.common.util.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;

import static ru.tesmio.soviet.reg.RegTileEntitys.FRAMEBLOCK_TILE;

public class RastyFrameTile extends TileEntity {
    private static final Logger LOGGER = LogManager.getLogger();

    public RastyFrameTile() {
        super(FRAMEBLOCK_TILE.get());
    }

    public static final ModelProperty<BlockState> UP_CONTAINS = new ModelProperty<>();
    public static final ModelProperty<BlockState> DOWN_CONTAINS = new ModelProperty<>();
    public static final ModelProperty<BlockState> NORTH_CONTAINS = new ModelProperty<>();
    public static final ModelProperty<BlockState> SOUTH_CONTAINS = new ModelProperty<>();
    public static final ModelProperty<BlockState> WEST_CONTAINS = new ModelProperty<>();
    public static final ModelProperty<BlockState> EAST_CONTAINS = new ModelProperty<>();

    private BlockState up;
    private BlockState down;
    private BlockState north;
    private BlockState south;
    private BlockState west;
    private BlockState east;


    public BlockState getUpContains() {
        return up;
    }

    public BlockState getDownContains() {
        return down;
    }

    public BlockState getNorthContains() {
        return north;
    }

    public BlockState getSouthContains() {
        return south;
    }

    public BlockState getWestContains() {
        return west;
    }

    public BlockState getEastContains() {
        return east;
    }

    public void setUpContains(BlockState s) {
        this.up = s;
        markDirty();
        world.notifyBlockUpdate(pos, getBlockState(), getBlockState(), Constants.BlockFlags.BLOCK_UPDATE + Constants.BlockFlags.NOTIFY_NEIGHBORS);
    }

    public void setDownContains(BlockState s) {
        this.down = s;
        markDirty();
        world.notifyBlockUpdate(pos, getBlockState(), getBlockState(), Constants.BlockFlags.BLOCK_UPDATE + Constants.BlockFlags.NOTIFY_NEIGHBORS);
    }

    public void setNorthContains(BlockState s) {
        this.north = s;
        markDirty();
        world.notifyBlockUpdate(pos, getBlockState(), getBlockState(), Constants.BlockFlags.BLOCK_UPDATE + Constants.BlockFlags.NOTIFY_NEIGHBORS);
    }

    public void setSouthContains(BlockState s) {
        this.south = s;
        markDirty();
        world.notifyBlockUpdate(pos, getBlockState(), getBlockState(), Constants.BlockFlags.BLOCK_UPDATE + Constants.BlockFlags.NOTIFY_NEIGHBORS);
    }

    public void setWestContains(BlockState s) {
        this.west = s;
        markDirty();
        world.notifyBlockUpdate(pos, getBlockState(), getBlockState(), Constants.BlockFlags.BLOCK_UPDATE + Constants.BlockFlags.NOTIFY_NEIGHBORS);
    }

    public void setEastContains(BlockState s) {
        this.east = s;
        markDirty();
        world.notifyBlockUpdate(pos, getBlockState(), getBlockState(), Constants.BlockFlags.BLOCK_UPDATE + Constants.BlockFlags.NOTIFY_NEIGHBORS);
    }

    public void clear() {
        this.setDownContains(null);
        this.setUpContains(null);
        this.setNorthContains(null);
        this.setSouthContains(null);
        this.setWestContains(null);
        this.setEastContains(null);
    }

    @Override
    public CompoundNBT getUpdateTag() {
        CompoundNBT tag = super.getUpdateTag();

        if (up != null) {
            tag.put("up", NBTUtil.writeBlockState(up));
        }
        if (down != null) {
            tag.put("down", NBTUtil.writeBlockState(down));
        }
        if (north != null) {
            tag.put("north", NBTUtil.writeBlockState(north));
        }
        if (south != null) {
            tag.put("south", NBTUtil.writeBlockState(south));
        }
        if (west != null) {
            tag.put("west", NBTUtil.writeBlockState(west));
        }
        if (east != null) {
            tag.put("east", NBTUtil.writeBlockState(east));
        }
        return tag;
    }

    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(pos, 1, getUpdateTag());
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        Integer i;
        BlockState up, down, north, east, west, south;

        up = this.up;
        down = this.down;
        north = this.north;
        south = this.south;
        west = this.west;
        east = this.east;
        CompoundNBT tag = pkt.getNbtCompound();

        if (tag.contains("up")) {
            this.up = NBTUtil.readBlockState(tag.getCompound("up"));
            if (!Objects.equals(up, this.up)) {
                ModelDataManager.requestModelDataRefresh(this);
                world.notifyBlockUpdate(pos, getBlockState(), getBlockState(), Constants.BlockFlags.BLOCK_UPDATE + Constants.BlockFlags.NOTIFY_NEIGHBORS);
            }
        }
        if (tag.contains("down")) {
            this.down = NBTUtil.readBlockState(tag.getCompound("down"));
            if (!Objects.equals(down, this.down)) {
                ModelDataManager.requestModelDataRefresh(this);
                world.notifyBlockUpdate(pos, getBlockState(), getBlockState(), Constants.BlockFlags.BLOCK_UPDATE + Constants.BlockFlags.NOTIFY_NEIGHBORS);
            }
        }
        if (tag.contains("north")) {
            this.north = NBTUtil.readBlockState(tag.getCompound("north"));
            if (!Objects.equals(north, this.north)) {
                ModelDataManager.requestModelDataRefresh(this);
                world.notifyBlockUpdate(pos, getBlockState(), getBlockState(), Constants.BlockFlags.BLOCK_UPDATE + Constants.BlockFlags.NOTIFY_NEIGHBORS);
            }
        }
        if (tag.contains("south")) {
            this.south = NBTUtil.readBlockState(tag.getCompound("south"));
            if (!Objects.equals(south, this.south)) {
                ModelDataManager.requestModelDataRefresh(this);
                world.notifyBlockUpdate(pos, getBlockState(), getBlockState(), Constants.BlockFlags.BLOCK_UPDATE + Constants.BlockFlags.NOTIFY_NEIGHBORS);
            }
        }
        if (tag.contains("west")) {
            this.west = NBTUtil.readBlockState(tag.getCompound("west"));
            if (!Objects.equals(west, this.west)) {
                ModelDataManager.requestModelDataRefresh(this);
                world.notifyBlockUpdate(pos, getBlockState(), getBlockState(), Constants.BlockFlags.BLOCK_UPDATE + Constants.BlockFlags.NOTIFY_NEIGHBORS);
            }
        }
        if (tag.contains("east")) {
            this.east = NBTUtil.readBlockState(tag.getCompound("east"));
            if (!Objects.equals(east, this.east)) {
                ModelDataManager.requestModelDataRefresh(this);
                world.notifyBlockUpdate(pos, getBlockState(), getBlockState(), Constants.BlockFlags.BLOCK_UPDATE + Constants.BlockFlags.NOTIFY_NEIGHBORS);
            }
        }
    }

    @Nonnull
    @Override
    public IModelData getModelData() {
        return new ModelDataMap.Builder()
                .withInitial(UP_CONTAINS, up)
                .withInitial(DOWN_CONTAINS, down)
                .withInitial(NORTH_CONTAINS, north)
                .withInitial(SOUTH_CONTAINS, south)
                .withInitial(WEST_CONTAINS, west)
                .withInitial(EAST_CONTAINS, east)
                .build();
    }

    @Override
    public void read(BlockState state, CompoundNBT tag) {
        super.read(state, tag);

        if (tag.contains("up")) {
            up = NBTUtil.readBlockState(tag.getCompound("up"));
        }
        if (tag.contains("down")) {
            down = NBTUtil.readBlockState(tag.getCompound("down"));
        }
        if (tag.contains("north")) {
            north = NBTUtil.readBlockState(tag.getCompound("north"));
        }
        if (tag.contains("south")) {
            south = NBTUtil.readBlockState(tag.getCompound("south"));
        }
        if (tag.contains("west")) {
            west = NBTUtil.readBlockState(tag.getCompound("west"));
        }
        if (tag.contains("east")) {
            east = NBTUtil.readBlockState(tag.getCompound("east"));
        }
    }

    @Override
    public CompoundNBT write(CompoundNBT tag) {

        if (up != null) {
            tag.put("up", NBTUtil.writeBlockState(up));

        }
        if (down != null) {
            tag.put("down", NBTUtil.writeBlockState(down));

        }
        if (north != null) {
            tag.put("north", NBTUtil.writeBlockState(north));

        }
        if (south != null) {
            tag.put("south", NBTUtil.writeBlockState(south));

        }
        if (west != null) {
            tag.put("west", NBTUtil.writeBlockState(west));

        }
        if (east != null) {
            tag.put("east", NBTUtil.writeBlockState(east));

        }
        return super.write(tag);


    }
}
