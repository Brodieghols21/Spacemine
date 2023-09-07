package anticookiestudios.spacemine;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

public class SpacemineBlockItem extends BlockItem {


    public SpacemineBlockItem(Block blockIn, Properties builder) {
        super(blockIn, builder);
    }

    @Override
    public boolean isImmuneToFire() {
        return true;
    }
}