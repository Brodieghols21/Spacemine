package anticookiestudios.spacemine;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.tileentity.HopperTileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.TickPriority;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.ArrayList;
import java.util.Random;

import static anticookiestudios.spacemine.Spacemine.addXItemsToList;

public class UniverseBlock extends SpheroidBlock {

    @Override
    public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
        worldIn.getPendingBlockTicks().scheduleTick(pos, state.getBlock(), 1, TickPriority.HIGH);
        //addXItemsToList(rngTable, Items., );
        super.onBlockAdded(state, worldIn, pos, oldState, isMoving);
    }

    public UniverseBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
        super.tick(state, worldIn, pos, rand);
        worldIn.getPendingBlockTicks().scheduleTick(pos, this, 1200, TickPriority.HIGH);

        Item item = ItemRegistry.ANTIMATTER.get();

        if(Math.random()<.01) {
            for (Direction dir : Direction.values()) {
                IInventory inventory = getOffsetContainer(pos.offset(dir), worldIn);
                if (inventory != null) {
                    insert(pos.offset(dir), inventory, dir.getOpposite(), new ItemStack(item));
                    return;
                }
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


    @Override
    public boolean removedByPlayer(BlockState state, World world, BlockPos pos, PlayerEntity player, boolean willHarvest, FluidState fluid) {
        player.addPotionEffect(new EffectInstance(Effects.BAD_OMEN,1200,68,true,false));
        return super.removedByPlayer(state, world, pos, player, willHarvest, fluid);
    }

}
