package anticookiestudios.spacemine;

import static anticookiestudios.spacemine.Spacemine.addXItemsToList;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
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
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IWorld;
import net.minecraft.world.TickPriority;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.ArrayList;
import java.util.Random;

public class PlanetBlock extends SpheroidBlock {
    public static ArrayList<ItemStack> rngTable = new ArrayList<ItemStack>();

    static{
        rngTable.clear();
        addXItemsToList(rngTable, Items.STONE, 100);
        addXItemsToList(rngTable, Items.GRASS_BLOCK, 100);
        addXItemsToList(rngTable, Items.OAK_LOG, 100);
        addXItemsToList(rngTable, Items.DIRT, 100);
        addXItemsToList(rngTable, Items.COBBLESTONE, 100);
        addXItemsToList(rngTable, Items.NETHERRACK, 100);
        addXItemsToList(rngTable, Items.END_STONE, 100);

        addXItemsToList(rngTable, Items.GRANITE, 50);
        addXItemsToList(rngTable, Items.ANDESITE, 50);
        addXItemsToList(rngTable, Items.DIORITE, 50);
        addXItemsToList(rngTable, Items.COBBLESTONE, 50);
        addXItemsToList(rngTable, Items.SPRUCE_LOG, 50);
        addXItemsToList(rngTable, Items.BIRCH_LOG, 50);
        addXItemsToList(rngTable, Items.JUNGLE_LOG, 50);
        addXItemsToList(rngTable, Items.ACACIA_LOG, 50);
        addXItemsToList(rngTable, Items.DARK_OAK_LOG, 50);
        addXItemsToList(rngTable, Items.CRIMSON_STEM, 50);
        addXItemsToList(rngTable, Items.WARPED_STEM, 50);
        addXItemsToList(rngTable, Items.GRAVEL, 50);
        addXItemsToList(rngTable, Items.SAND, 50);
        addXItemsToList(rngTable, Items.CRIMSON_NYLIUM, 50);
        addXItemsToList(rngTable, Items.WARPED_NYLIUM, 50);
        addXItemsToList(rngTable, Items.RED_SAND, 50);
        addXItemsToList(rngTable, Items.CLAY, 50);
        addXItemsToList(rngTable, Items.SOUL_SAND, 50);
        addXItemsToList(rngTable, Items.SOUL_SOIL, 50);
        addXItemsToList(rngTable, Items.SNOW_BLOCK, 50);
        addXItemsToList(rngTable, Items.BASALT, 50);
        addXItemsToList(rngTable, Items.NETHER_BRICKS, 50);
        addXItemsToList(rngTable, Items.ICE, 50);

        addXItemsToList(rngTable, Items.GLOWSTONE, 25);
        addXItemsToList(rngTable, Items.COAL_ORE, 25);
        addXItemsToList(rngTable, Items.OAK_PLANKS, 25);
        addXItemsToList(rngTable, Items.NETHER_GOLD_ORE, 25);
        addXItemsToList(rngTable, Items.WHITE_WOOL, 25);
        addXItemsToList(rngTable, Items.MOSSY_COBBLESTONE, 25);
        addXItemsToList(rngTable, Items.STONE_BRICKS, 25);
        addXItemsToList(rngTable, Items.SANDSTONE, 25);
        addXItemsToList(rngTable, Items.SPRUCE_PLANKS, 25);
        addXItemsToList(rngTable, Items.PRISMARINE, 25);
        addXItemsToList(rngTable, Items.DARK_PRISMARINE, 25);
        addXItemsToList(rngTable, Items.MYCELIUM, 25);
        addXItemsToList(rngTable, Items.MAGMA_BLOCK, 25);
        addXItemsToList(rngTable, Items.BLACKSTONE, 25);

        addXItemsToList(rngTable, Items.GILDED_BLACKSTONE, 10);
        addXItemsToList(rngTable, Items.IRON_ORE, 10);
        addXItemsToList(rngTable, Items.WHITE_WOOL, 10);
        addXItemsToList(rngTable, Items.PUMPKIN, 10);
        addXItemsToList(rngTable, Items.MELON, 10);
        addXItemsToList(rngTable, Items.PURPUR_BLOCK, 10);
        addXItemsToList(rngTable, Items.BOOKSHELF, 10);
        addXItemsToList(rngTable, Items.HAY_BLOCK, 10);
        addXItemsToList(rngTable, Items.CRYING_OBSIDIAN, 10);
        addXItemsToList(rngTable, Items.OBSIDIAN, 10);

        addXItemsToList(rngTable, Items.GOLD_ORE, 5);
        addXItemsToList(rngTable, Items.REDSTONE_ORE, 5);
        addXItemsToList(rngTable, Items.LAPIS_ORE, 5);
        addXItemsToList(rngTable, Items.NETHER_QUARTZ_ORE, 5);

        addXItemsToList(rngTable, Items.DIAMOND_ORE, 2);
        addXItemsToList(rngTable, Items.EMERALD_ORE, 2);

        //addXItemsToList(rngTable, Items.BEDROCK, 1);
        addXItemsToList(rngTable, Items.ANCIENT_DEBRIS, 1);
    }

    @Override
    public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
        worldIn.getPendingBlockTicks().scheduleTick(pos, state.getBlock(), 1, TickPriority.HIGH);
        //addXItemsToList(rngTable, Items., );
        super.onBlockAdded(state, worldIn, pos, oldState, isMoving);
    }

    public PlanetBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
        super.tick(state, worldIn, pos, rand);
        worldIn.getPendingBlockTicks().scheduleTick(pos, this, 1200, TickPriority.HIGH);
        ItemStack item;
        if(rngTable.size()>0) {
            item = rngTable.get(rand.nextInt(rngTable.size()));
            for (Direction dir : Direction.values()) {
                IInventory inventory = getOffsetContainer(pos.offset(dir),worldIn);
                if (inventory!=null) {
                    insert(pos.offset(dir), inventory, dir.getOpposite(), item);
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
