package anticookiestudios.spacemine;
/*
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nullable;

public class ShrinkLaserTileEntity extends TileEntity {

    @CapabilityInject(IItemHandler.class)
    public static Capability<IItemHandler> ITEM_HANDLER_CAPABILITY = null;
    SpacemineItemHandler handler = new SpacemineItemHandler(this);
    private final LazyOptional<SpacemineItemHandler> inventoryOptional = LazyOptional.of(() -> handler);


    public static final Logger LOGGER = LogManager.getLogger();
    public ShrinkLaserTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }
    public ShrinkLaserTileEntity(){
        super(TileEntityRegistry.SHRINK_LASER.get());
    }

    // suppose the presence of a field 'inventory' of type 'IItemHandler'



    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> capability, @Nullable Direction direction) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY
                && (direction == null || direction == Direction.DOWN)) {
            return this.inventoryOptional.cast();
        }
        return super.getCapability(capability, direction); // See note after snippet
    }

    @Override
    protected void invalidateCaps() {
        super.invalidateCaps();
        this.inventoryOptional.invalidate();
    }

    public ItemStack getItemSpawn(){
        return new ItemStack(ItemRegistry.ASTEROID.get());
    }


}
*/