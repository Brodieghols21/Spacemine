package anticookiestudios.spacemine;

import anticookiestudios.spacemine.items.SpacemineFoods;
import com.mojang.datafixers.types.Type;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.tileentity.FurnaceTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Util;
import net.minecraft.util.datafix.TypeReferences;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class TileEntityRegistry {
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, "spacemine");

    //public static final RegistryObject<TileEntityType<?>> SHRINK_LASER = TILE_ENTITIES.register("shrink_laser", () -> TileEntityType.Builder.create(ShrinkLaserTileEntity::new, BlockRegistry.SHRINK_LASER.get()).build(null));
}