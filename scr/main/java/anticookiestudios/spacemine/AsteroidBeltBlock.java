package anticookiestudios.spacemine;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

public class AsteroidBeltBlock extends Block {
    public AsteroidBeltBlock(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return shape;
    }



    public static final VoxelShape shape = VoxelShapes.or(
            VoxelShapes.create(0.4375, 0.0625, 0.4375, 0.5, 0.125, 0.5),
            VoxelShapes.create(0.875, 0.625, 0.0625, 0.9375, 0.6875, 0.125),
            VoxelShapes.create(0.0, 0.1875, 0.1875, 0.0625, 0.25, 0.25),
            VoxelShapes.create(0.9375, 0.375, 0.625, 1.0, 0.4375, 0.6875),
            VoxelShapes.create(0.1875, 0.1875, 0.75, 0.25, 0.25, 0.8125),
            VoxelShapes.create(0.5, 0.5, 0.75, 0.5625, 0.5625, 0.8125),
            VoxelShapes.create(0.6875, 0.3125, 0.875, 0.75, 0.375, 0.9375),
            VoxelShapes.create(0.4375, 0.4375, 0.3125, 0.5, 0.5, 0.375),
            VoxelShapes.create(0.75, 0.1875, 0.75, 0.8125, 0.25, 0.8125),
            VoxelShapes.create(0.75, 0.5, 0.375, 0.8125, 0.5625, 0.4375)
    );
}

