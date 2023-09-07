package anticookiestudios.spacemine;

import com.google.common.collect.ImmutableSet;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ToolItem;

import java.util.Set;
//luigi
public class MattockToolItem extends ToolItem {
    public MattockToolItem(float attackDamageIn, float attackSpeedIn, IItemTier tier, Properties builderIn) {
        super(attackDamageIn, attackSpeedIn, tier, ImmutableSet.of(), builderIn);
    }

    @Override
    public boolean canHarvestBlock(ItemStack stack, BlockState state) {
        boolean canPickaxe = Items.NETHERITE_PICKAXE.canHarvestBlock(stack, state);
        boolean canAxe = Items.NETHERITE_AXE.canHarvestBlock(stack, state);
        boolean canSword = Items.NETHERITE_SHOVEL.canHarvestBlock(stack, state);
        boolean canShovel = Items.NETHERITE_SHOVEL.canHarvestBlock(stack, state);
        boolean canHoe = Items.NETHERITE_HOE.canHarvestBlock(stack, state);
        return canHoe||canAxe||canShovel||canPickaxe||canSword;
    }

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        return canHarvestBlock(state) ? super.getDestroySpeed(stack, state) : this.efficiency; }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return false;
    }

    @Override
    public boolean isImmuneToFire() {
        return true;
    }
}
