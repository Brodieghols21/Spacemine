package anticookiestudios.spacemine;

import net.minecraft.block.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.*;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.HopperTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.TickPriority;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.items.IItemHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nullable;
import java.awt.*;
import java.util.Properties;
import java.util.Random;
import java.util.Vector;


public class ShrinkLaserBlock extends Block {
    /*@Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new ShrinkLaserTileEntity();
    }*/

    @Override
    public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
        worldIn.getPendingBlockTicks().scheduleTick(pos, state.getBlock(), 1, TickPriority.HIGH);
        super.onBlockAdded(state, worldIn, pos, oldState, isMoving);
    }

    public ShrinkLaserBlock(Properties properties) {
        super(properties);
    }


    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        ItemStack stack = player.getHeldItem(Hand.MAIN_HAND);
        ActionResultType result = ActionResultType.FAIL;
        boolean isNextTier = stack.isItemEqual(new ItemStack(ItemRegistry.RED_SUPERGIANT.get()));
        if (state.getBlock().getDefaultState().equals(BlockRegistry.SHRINK_LASER.get().getDefaultState()) && isNextTier) {
            worldIn.setBlockState(pos, BlockRegistry.SHRINK_LASER_1.get().getDefaultState());
            result = ActionResultType.CONSUME;
            stack.shrink(1);
        }
        return result;
    }

    @Override
    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
        super.tick(state, worldIn, pos, rand);
        worldIn.getPendingBlockTicks().scheduleTick(pos, this, 200, TickPriority.HIGH);
        Item item = ItemRegistry.ASTEROID.get();
        if(Math.random()<.0025)
            item = ItemRegistry.SPACE_PUFFERFISH.get();
/*
        IInventory belowInventory = HopperTileEntity.getInventoryAtPosition(worldIn, pos.getX(), pos.getY() - 1, pos.getZ());
        IInventory aboveInventory = HopperTileEntity.getInventoryAtPosition(worldIn, pos.getX(), pos.getY() + 1, pos.getZ());
        IInventory xPlusInventory = HopperTileEntity.getInventoryAtPosition(worldIn, pos.getX() + 1, pos.getY(), pos.getZ());
        IInventory xMinusInventory = HopperTileEntity.getInventoryAtPosition(worldIn, pos.getX() - 1, pos.getY(), pos.getZ());
        IInventory zPlusInventory = HopperTileEntity.getInventoryAtPosition(worldIn, pos.getX(), pos.getY(), pos.getZ() + 1);
        IInventory zMinusInventory = HopperTileEntity.getInventoryAtPosition(worldIn, pos.getX(), pos.getY(), pos.getZ() - 1);

        if (belowInventory != null)
            HopperTileEntity.putStackInInventoryAllSlots(null, belowInventory, new ItemStack(item), Direction.UP);
        else if (aboveInventory != null)
            HopperTileEntity.putStackInInventoryAllSlots(null, aboveInventory, new ItemStack(item), Direction.DOWN);
        else if (xPlusInventory != null)
            HopperTileEntity.putStackInInventoryAllSlots(null, xPlusInventory, new ItemStack(item), Direction.UP);
        else if (xMinusInventory != null)
            HopperTileEntity.putStackInInventoryAllSlots(null, xMinusInventory, new ItemStack(item), Direction.UP);
        else if (zPlusInventory != null)
            HopperTileEntity.putStackInInventoryAllSlots(null, zPlusInventory, new ItemStack(item), Direction.UP);
        else if (zMinusInventory != null)
            HopperTileEntity.putStackInInventoryAllSlots(null, zMinusInventory, new ItemStack(item), Direction.UP);
        */

        for (Direction dir : Direction.values()) {
            IInventory inventory = getOffsetContainer(pos.offset(dir),worldIn);
            if (inventory!=null) {
                insert(pos.offset(dir), inventory, dir.getOpposite(), new ItemStack(item));
                return;
            }
        }

    }

    public void insert(BlockPos pos, IInventory inventory, Direction direction, ItemStack itemStack){
        if (inventory != null)
            HopperTileEntity.putStackInInventoryAllSlots(null, inventory, itemStack, Direction.UP);
    }

    public IInventory getOffsetContainer(BlockPos pos, ServerWorld worldIn){
        return HopperTileEntity.getInventoryAtPosition(worldIn, pos.getX(), pos.getY(), pos.getZ());
    }
}
