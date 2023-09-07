package anticookiestudios.spacemine;

import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public abstract class SpacemineItemGroup extends ItemGroup {
    public SpacemineItemGroup(String label) {
        super(label);
    }

    public static final ItemGroup SPACEMINE_ARMOR = new SpacemineItemGroup("spacemine_armor") {
        public ItemStack createIcon() {
            return new ItemStack(ItemRegistry.ASTEROID_HELMET.get());
        }
    };
    public static final ItemGroup SPACEMINE_WEAPONS = new SpacemineItemGroup("spacemine_weapons") {
        public ItemStack createIcon() {
            return new ItemStack(ItemRegistry.ASTEROID_SWORD.get());
        }
    };
    public static final ItemGroup SPACEMINE_TOOLS = new SpacemineItemGroup("spacemine_tools") {
        public ItemStack createIcon() {
            return new ItemStack(ItemRegistry.ASTEROID_MATTOCK.get());
        }
    };
    public static final ItemGroup SPACEMINE_EDIBLES = new SpacemineItemGroup("spacemine_edibles") {
        public ItemStack createIcon() {
            return new ItemStack(ItemRegistry.ASTEROID.get());
        }
    };
    public static final ItemGroup SPACEMINE_MISC = new SpacemineItemGroup("spacemine_misc") {
        public ItemStack createIcon() {
            return new ItemStack(BlockRegistry.SHRINK_LASER.get());
        }
    };

}
