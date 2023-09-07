package anticookiestudios.spacemine;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;

public class SpacemineSwordItem extends SwordItem {

    public SpacemineSwordItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, Properties builderIn) {
        super(tier, attackDamageIn, attackSpeedIn, builderIn);
    }

    @Override
    public boolean isImmuneToFire() {
        return true;
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return false;
    }
}