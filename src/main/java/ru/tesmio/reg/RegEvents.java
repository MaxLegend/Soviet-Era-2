package ru.tesmio.reg;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.NetworkEvent;
import ru.tesmio.blocks.affinage_factory.AffinageFactory;
import ru.tesmio.blocks.baseblock.subtype.BrickBlock;
import ru.tesmio.blocks.crusher.BlockCrusher;
import ru.tesmio.blocks.decorative.devices.SmallButton;
import ru.tesmio.blocks.decorative.devices.base.BlockForFacingDevice;
import ru.tesmio.blocks.decorative.devices.base.BlockSideDevice;
import ru.tesmio.blocks.decorative.props.WindowGrid;
import ru.tesmio.blocks.decorative.props.base.BlockAxisProps;
import ru.tesmio.blocks.decorative.props.base.BlockRotatedAxisCMProps;
import ru.tesmio.blocks.placeable.BucketDye;
import ru.tesmio.core.Config;
import ru.tesmio.core.Core;
import ru.tesmio.data.providers.advancements.triggers.DiscoveryBlockTrigger;
import ru.tesmio.enums.EnumBucketState;

@Mod.EventBusSubscriber(modid = Core.MODID)
public class RegEvents {
    @SubscribeEvent
    public static void rightClickWaterBucket(PlayerInteractEvent.RightClickBlock e) {
        World w = e.getWorld();
        BlockPos p = e.getPos();
        BlockState s = w.getBlockState(p);
        PlayerEntity pl = e.getPlayer();
        Hand h = e.getHand();
        if(s.getBlock() instanceof BucketDye) {
            if (s.get(BucketDye.COLOR) == EnumBucketState.EMPTY && pl.getHeldItemMainhand().getStack().getItem() == Items.WATER_BUCKET) {
                w.setBlockState(p, s.with(BucketDye.COLOR, EnumBucketState.WATER));
                if (!pl.isCreative()) pl.getHeldItemMainhand().shrink(1);
                if (!pl.isCreative()) pl.setHeldItem(Hand.MAIN_HAND, new ItemStack(Items.BUCKET));

            }
        }
    }
//    @SubscribeEvent
//    public static void leftClickGrinder(PlayerInteractEvent.LeftClickEmpty e) {
//        if(e.getPlayer().ticksExisted == 2) {
//            e.getWorld().playSound(e.getPlayer(), e.getPlayer().getPosition(), RegSounds.SOUND_GRINDER_WORK.get(), SoundCategory.PLAYERS, 0.1F, 1f);
//        }
//
//        if(e.getPlayer().ticksExisted % 35 == 0) {
//            Item item = e.getPlayer().getHeldItem(e.getHand()).getItem();
//            if(item instanceof RedstoneGrinder) {
//                if(item.getDamage(e.getPlayer().getHeldItem(e.getHand())) > 0)
//                e.getWorld().playSound(e.getPlayer(), e.getPlayer().getPosition(), RegSounds.SOUND_GRINDER_WORK.get(), SoundCategory.PLAYERS, 0.04F, 1f);
//            }
//        }
//    }
    public static void renderWorkGrinder(RenderHandEvent e) {

    }
    @SubscribeEvent
    public static void discoveryBlockEvent(PlayerInteractEvent.RightClickBlock e) {
        BlockState s = e.getPlayer().getBlockState();
        BlockState s2 = e.getWorld().getBlockState(e.getPos());

        if( s2.getBlock() instanceof BrickBlock) {

            if(!e.getWorld().isRemote) {
                DiscoveryBlockTrigger.INSTANCE.trigger(s, (ServerPlayerEntity) e.getPlayer(), ItemStack.EMPTY);
            }
        }
    }

    //Core.DISCOVERY_BLOCK_TRIGGER.trigger(state, sPlayer,pos, ItemStack.EMPTY);



    public static void openTabletGUI(NetworkEvent e) {
//        TileEntityTablet te = new TileEntityTablet();

//        e.setGui(new ScreenTablet());
    }

    @SubscribeEvent
    public static void rightClickDismatling(PlayerInteractEvent.RightClickBlock e) {

             if (Config.canDisassembleDeviceHand.get()) {
            if (!e.getPlayer().isCreative() && e.getPlayer().isCrouching()) {
                ItemStack is = e.getPlayer().getHeldItem(e.getHand());
                BlockState s = e.getWorld().getBlockState(e.getPos());
                boolean flag = s.getBlock() instanceof WindowGrid || s.getBlock() instanceof BlockSideDevice
                        || s.getBlock() instanceof BlockAxisProps
                        || s.getBlock() instanceof AffinageFactory
                        || s.getBlock() instanceof BlockCrusher
                        || s.getBlock() instanceof BlockForFacingDevice
                        || s.getBlock() instanceof BlockRotatedAxisCMProps;
                boolean flagEx = !(s.getBlock() instanceof SmallButton);
                assert s != null;
                assert is != null;
                if (is.isEmpty()) {
                    if (flag) {
                        if (flagEx) {
                            e.getWorld().playSound(e.getPlayer(), e.getPlayer().getPosition(), RegSounds.SOUND_DEMONTAGE.get(), SoundCategory.PLAYERS, 0.2F, 1f);
                            Block.spawnAsEntity(e.getWorld(), e.getPos(), new ItemStack(s.getBlock(), 1));
                            e.getWorld().setBlockState(e.getPos(), Blocks.AIR.getDefaultState());
                        }
                   }
                }
            }
        }

    }


}

