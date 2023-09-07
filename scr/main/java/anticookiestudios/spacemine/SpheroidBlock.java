package anticookiestudios.spacemine;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

public class SpheroidBlock extends Block {
    public SpheroidBlock(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return shape;
    }

    public static final VoxelShape shape = VoxelShapes.or(
            VoxelShapes.create(0.3125, 0.0, 0.3125, 0.6875, 1.0, 0.6875),
            VoxelShapes.create(0.0, 0.3125, 0.3125, 1.0, 0.6875, 0.6875),
            VoxelShapes.create(0.3125, 0.3125, 0.0, 0.6875, 0.6875, 1.0),
            VoxelShapes.create(0.3125, 0.0625, 0.25, 0.6875, 0.9375, 0.75),
            VoxelShapes.create(0.3125, 0.25, 0.0625, 0.6875, 0.75, 0.9375),
            VoxelShapes.create(0.3125, 0.125, 0.125, 0.6875, 0.875, 0.875),
            VoxelShapes.create(0.25, 0.0625, 0.3125, 0.75, 0.9375, 0.6875),
            VoxelShapes.create(0.0, 0.3125, 0.3125, 1.0, 0.6875, 0.6875),
            VoxelShapes.create(0.3125, 0.0, 0.3125, 0.6875, 1.0, 0.6875),
            VoxelShapes.create(0.0625, 0.25, 0.3125, 0.9375, 0.75, 0.6875),
            VoxelShapes.create(0.125, 0.125, 0.3125, 0.875, 0.875, 0.6875),
            VoxelShapes.create(0.125, 0.3125, 0.125, 0.875, 0.6875, 0.875),
            VoxelShapes.create(0.25, 0.3125, 0.0625, 0.75, 0.6875, 0.9375),
            VoxelShapes.create(0.25, 0.25, 0.125, 0.75, 0.75, 0.875),
            VoxelShapes.create(0.125, 0.25, 0.25, 0.875, 0.75, 0.75),
            VoxelShapes.create(0.0625, 0.3125, 0.25, 0.9375, 0.6875, 0.75),
            VoxelShapes.create(0.25, 0.125, 0.25, 0.75, 0.875, 0.75),
            VoxelShapes.create(0.1875, 0.1875, 0.1875, 0.8125, 0.8125, 0.8125)
    );
}
