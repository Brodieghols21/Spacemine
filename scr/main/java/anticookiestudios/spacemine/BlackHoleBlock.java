package anticookiestudios.spacemine;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.MagmaBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.TickPriority;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.Properties;
import java.util.Random;
import java.util.Vector;

public class BlackHoleBlock extends SpheroidBlock {

    @Override
    public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
        worldIn.getPendingBlockTicks().scheduleTick(pos, state.getBlock(), 1, TickPriority.HIGH);
        super.onBlockAdded(state, worldIn, pos, oldState, isMoving);
    }

    public BlackHoleBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        ClientPlayerEntity player = Minecraft.getInstance().player;
        Vector3d motion = player.getMotion();
        player.setMotion(motion.add(new Vector3d((pos.getX() + .5 - player.getPosX()) / 10.0, (pos.getY() - player.getPosY()) / 10.0, (pos.getZ() + .5 - player.getPosZ()) / 10.0)));
    }

    @Override
    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {


        worldIn.getPendingBlockTicks().scheduleTick(pos, state.getBlock(), 1, TickPriority.HIGH);
        for (Entity e : worldIn.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(pos.getX() - 10, pos.getY() - 10, pos.getZ() - 10, pos.getX() + 10, pos.getY() + 10, pos.getZ() + 10))) {
            float scale = ResizingUtils.getSize(e);
            float scaleMultiplier = 1F/(scale*5);
            if(scale>3){
                scaleMultiplier = 0.001F;
            }
            Vector3d motion = e.getMotion();
            e.setMotion(motion.add(new Vector3d((pos.getX() + .5 - e.getPosX()) / 40.0*scaleMultiplier, (pos.getY() - e.getPosY()) / 40.0*scaleMultiplier, (pos.getZ() + .5 - e.getPosZ()) / 40.0*scaleMultiplier)));
        }
    }
}
