package anticookiestudios.spacemine;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IntegerProperty;
import net.minecraft.tileentity.HopperTileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.TickPriority;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;


public class ShrinkLaser4Block extends Block {
    public final IntegerProperty TIER = IntegerProperty.create("tier", 0, 5);
    public static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
        worldIn.getPendingBlockTicks().scheduleTick(pos, state.getBlock(), 1, TickPriority.HIGH);
        super.onBlockAdded(state, worldIn, pos, oldState, isMoving);
    }

    public ShrinkLaser4Block(Properties properties) {
        super(properties);
    }

    /*@Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        ItemStack stack = player.getHeldItem(Hand.MAIN_HAND);
        ActionResultType result = ActionResultType.FAIL;
        boolean isItemMultiverse = stack.isItemEqual(new ItemStack(ItemRegistry.MULTIVERSE.get()));
        if (state.getBlock().getDefaultState().equals(BlockRegistry.SHRINK_LASER_4.get().getDefaultState())&& isItemMultiverse) {
            worldIn.setBlockState(pos, BlockRegistry.SHRINK_LASER_5.get().getDefaultState());
            result = ActionResultType.CONSUME;
            stack.shrink(1);
        }
        return result;
    }*/

    @Override
    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
        super.tick(state, worldIn, pos, rand);
        worldIn.getPendingBlockTicks().scheduleTick(pos, this, 200, TickPriority.HIGH);

        Item item = ItemRegistry.GALAXY.get();
        if(Math.random()<.0025)
            item = ItemRegistry.SPACE_PUFFERFISH.get();

        IInventory belowInventory = HopperTileEntity.getInventoryAtPosition(worldIn, pos.getX(), pos.getY() - 1, pos.getZ());
        IInventory aboveInventory = HopperTileEntity.getInventoryAtPosition(worldIn, pos.getX(), pos.getY() + 1, pos.getZ());
        IInventory xPlusInventory = HopperTileEntity.getInventoryAtPosition(worldIn, pos.getX() + 1, pos.getY(), pos.getZ());
        IInventory xMinusInventory = HopperTileEntity.getInventoryAtPosition(worldIn, pos.getX() - 1, pos.getY(), pos.getZ());
        IInventory zPlusInventory = HopperTileEntity.getInventoryAtPosition(worldIn, pos.getX(), pos.getY(), pos.getZ() + 1);
        IInventory zMinusInventory = HopperTileEntity.getInventoryAtPosition(worldIn, pos.getX(), pos.getY(), pos.getZ() - 1);

        if (belowInventory != null)
            HopperTileEntity.putStackInInventoryAllSlots(null, belowInventory, new ItemStack(item), Direction.UP);
        else if (aboveInventory != null)
            HopperTileEntity.putStackInInventoryAllSlots(null, aboveInventory, new ItemStack(item), Direction.UP);
        else if (xPlusInventory != null)
            HopperTileEntity.putStackInInventoryAllSlots(null, xPlusInventory, new ItemStack(item), Direction.UP);
        else if (xMinusInventory != null)
            HopperTileEntity.putStackInInventoryAllSlots(null, xMinusInventory, new ItemStack(item), Direction.UP);
        else if (zPlusInventory != null)
            HopperTileEntity.putStackInInventoryAllSlots(null, zPlusInventory, new ItemStack(item), Direction.UP);
        else if (zMinusInventory != null)
            HopperTileEntity.putStackInInventoryAllSlots(null, zMinusInventory, new ItemStack(item), Direction.UP);

    }
}
