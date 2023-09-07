package anticookiestudios.spacemine;
/*
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nonnull;

public class SpacemineItemHandler implements IItemHandler {
    private final ShrinkLaserTileEntity te;

    @Override
    public int getSlots() {
        return 1;
    }

    public SpacemineItemHandler(ShrinkLaserTileEntity te) {
        this.te = te;
    }


    @Nonnull
    @Override
    public ItemStack getStackInSlot(int slot) {
        return te.getItemSpawn();
    }
    @Nonnull
    @Override
    public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
        return ItemStack.EMPTY;
    }

    @Nonnull
    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        return getStackInSlot(slot);
    }

    @Override
    public int getSlotLimit(int slot) {
        return 0;
    }

    @Override
    public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
        return false;
    }
}
*/