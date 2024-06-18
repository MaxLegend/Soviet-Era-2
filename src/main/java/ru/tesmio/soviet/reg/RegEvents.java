package ru.tesmio.soviet.reg;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.NetworkEvent;
import ru.tesmio.soviet.blocks.affinage_factory.AffinageFactory;
import ru.tesmio.soviet.blocks.baseblock.subtype.BrickBlock;
import ru.tesmio.soviet.blocks.crusher.BlockCrusher;
import ru.tesmio.soviet.blocks.decorative.devices.SmallButton;
import ru.tesmio.soviet.blocks.decorative.devices.base.BlockForFacingDevice;
import ru.tesmio.soviet.blocks.decorative.devices.base.BlockSideDevice;
import ru.tesmio.soviet.blocks.decorative.props.WindowGrid;
import ru.tesmio.soviet.blocks.decorative.props.base.BlockAxisProps;
import ru.tesmio.soviet.blocks.decorative.props.base.BlockRotatedAxisCMProps;
import ru.tesmio.soviet.core.Config;
import ru.tesmio.soviet.core.Core;
import ru.tesmio.soviet.data.providers.advancements.triggers.DiscoveryBlockTrigger;
import ru.tesmio.soviet.items.RedstoneGrinder;

@Mod.EventBusSubscriber(modid = Core.MODID)
public class RegEvents {
    @SubscribeEvent
    public static void leftClickGrinder(PlayerInteractEvent.LeftClickEmpty e) {
        if (e.getPlayer().ticksExisted == 2) {
            e.getWorld().playSound(e.getPlayer(), e.getPlayer().getPosition(), SovietSounds.GRINDER_WORK.get(), SoundCategory.PLAYERS, 0.1F, 1f);
        }

        if (e.getPlayer().ticksExisted % 35 == 0) {
            Item item = e.getPlayer().getHeldItem(e.getHand()).getItem();
            if (item instanceof RedstoneGrinder) {
                if (item.getDamage(e.getPlayer().getHeldItem(e.getHand())) > 0)
                    e.getWorld().playSound(e.getPlayer(), e.getPlayer().getPosition(), SovietSounds.GRINDER_WORK.get(), SoundCategory.PLAYERS, 0.04F, 1f);
            }
        }
    }

    public static void renderWorkGrinder(RenderHandEvent e) {

    }

    @SubscribeEvent
    public static void discoveryBlockEvent(PlayerInteractEvent.RightClickBlock e) {
        if (e.getWorld().isRemote) return;
        BlockState footPlaeyrBlockState = e.getPlayer().getBlockState();
        BlockState clickedBlockstate = e.getWorld().getBlockState(e.getPos());

        if (clickedBlockstate.getBlock() instanceof BrickBlock) {
            DiscoveryBlockTrigger.INSTANCE.trigger(footPlaeyrBlockState, (ServerPlayerEntity) e.getPlayer(), ItemStack.EMPTY);
        }
    }

    //Core.DISCOVERY_BLOCK_TRIGGER.trigger(state, sPlayer,pos, ItemStack.EMPTY);


    public static void openTabletGUI(NetworkEvent e) {
//        TileEntityTablet te = new TileEntityTablet();

//        e.setGui(new ScreenTablet());
    }

    @SubscribeEvent
    public static void rightClickDismatling(PlayerInteractEvent.RightClickBlock e) {
        if (!Config.canDisassembleDeviceHand.get()) return;
        if (!e.getPlayer().isCreative() && e.getPlayer().isCrouching()) {
            ItemStack mainHeld = e.getPlayer().getHeldItem(e.getHand());
            BlockState blockState = e.getWorld().getBlockState(e.getPos());
            boolean flag = blockState.getBlock() instanceof WindowGrid || blockState.getBlock() instanceof BlockSideDevice
                    || blockState.getBlock() instanceof BlockAxisProps
                    || blockState.getBlock() instanceof AffinageFactory
                    || blockState.getBlock() instanceof BlockCrusher
                    || blockState.getBlock() instanceof BlockForFacingDevice
                    || blockState.getBlock() instanceof BlockRotatedAxisCMProps;
            boolean isNotSmallButton = !(blockState.getBlock() instanceof SmallButton);
            if (mainHeld.isEmpty()) {
                if (flag) {
                    if (isNotSmallButton) {
                        e.getWorld().playSound(e.getPlayer(), e.getPlayer().getPosition(), SovietSounds.DEMONTAGE.get(), SoundCategory.PLAYERS, 0.2F, 1f);
                        Block.spawnAsEntity(e.getWorld(), e.getPos(), new ItemStack(blockState.getBlock(), 1));
                        e.getWorld().setBlockState(e.getPos(), Blocks.AIR.getDefaultState());
                    }
                }
            }
        }
    }
}