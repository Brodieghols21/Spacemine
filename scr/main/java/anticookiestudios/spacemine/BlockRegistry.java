package anticookiestudios.spacemine;

import net.minecraft.block.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import anticookiestudios.spacemine.*;

public class BlockRegistry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,Spacemine.modid);
    public static final RegistryObject<Block> ASTEROID = BLOCKS.register("asteroid", ()->new SpheroidBlock(AbstractBlock.Properties.from(Blocks.OBSIDIAN)));
    public static final RegistryObject<Block> BLACK_DWARF = BLOCKS.register("black_dwarf", ()->new SpheroidBlock(AbstractBlock.Properties.from(Blocks.OBSIDIAN)));
    public static final RegistryObject<Block> BLACK_HOLE = BLOCKS.register("black_hole", ()->new BlackHoleBlock(AbstractBlock.Properties.from(Blocks.OBSIDIAN).doesNotBlockMovement().notSolid()));
    public static final RegistryObject<Block> BROWN_DWARF = BLOCKS.register("brown_dwarf", ()->new SpheroidBlock(AbstractBlock.Properties.from(Blocks.OBSIDIAN)));
    public static final RegistryObject<Block> DWARF_PLANET = BLOCKS.register("dwarf_planet", ()->new SpheroidBlock(AbstractBlock.Properties.from(Blocks.OBSIDIAN)));
    public static final RegistryObject<Block> LARGE_ASTEROID = BLOCKS.register("large_asteroid", ()->new SpheroidBlock(AbstractBlock.Properties.from(Blocks.OBSIDIAN)));
    public static final RegistryObject<Block> MOON = BLOCKS.register("moon", ()->new SpheroidBlock(AbstractBlock.Properties.from(Blocks.OBSIDIAN)));
    public static final RegistryObject<Block> NEUTRON_STAR = BLOCKS.register("neutron_star", ()->new SpheroidBlock(AbstractBlock.Properties.from(Blocks.OBSIDIAN).setLightLevel((state)->14)));
    public static final RegistryObject<Block> PLANET = BLOCKS.register("planet", ()->new PlanetBlock(AbstractBlock.Properties.from(Blocks.OBSIDIAN)));
    public static final RegistryObject<Block> RED_SUPERGIANT = BLOCKS.register("red_supergiant", ()->new SpheroidBlock(AbstractBlock.Properties.from(Blocks.OBSIDIAN).setLightLevel((state)->14)));
    public static final RegistryObject<Block> STAR = BLOCKS.register("star", ()->new SpheroidBlock(AbstractBlock.Properties.from(Blocks.OBSIDIAN).setLightLevel((state)->14)));
    public static final RegistryObject<Block> SUPER_MASSIVE_BLACK_HOLE = BLOCKS.register("super_massive_black_hole", ()->new BlackHoleBlock(AbstractBlock.Properties.from(Blocks.OBSIDIAN).doesNotBlockMovement().notSolid()));
    public static final RegistryObject<Block> UNIVERSE = BLOCKS.register("universe", ()->new UniverseBlock(AbstractBlock.Properties.from(Blocks.OBSIDIAN)));
    public static final RegistryObject<Block> WHITE_DWARF = BLOCKS.register("white_dwarf", ()->new SpheroidBlock(AbstractBlock.Properties.from(Blocks.OBSIDIAN).slipperiness(Blocks.BLUE_ICE.getSlipperiness())));
    public static final RegistryObject<Block> SOLAR_SYSTEM = BLOCKS.register("solar_system", ()->new SolarSystemBlock(AbstractBlock.Properties.from(Blocks.OBSIDIAN).setLightLevel((state)->14)));
    public static final RegistryObject<Block> GALAXY = BLOCKS.register("galaxy", ()->new SolarSystemBlock(AbstractBlock.Properties.from(Blocks.OBSIDIAN).setLightLevel((state)->14)));
    public static final RegistryObject<Block> PULSAR = BLOCKS.register("pulsar", ()->new SpheroidBlock(AbstractBlock.Properties.from(Blocks.OBSIDIAN)));
    public static final RegistryObject<Block> SOLAR_SYSTEM_CLUSTER = BLOCKS.register("solar_system_cluster", ()->new AsteroidBeltBlock(AbstractBlock.Properties.from(Blocks.OBSIDIAN)));
    public static final RegistryObject<Block> GALAXY_CLUSTER = BLOCKS.register("galaxy_cluster", ()->new AsteroidBeltBlock(AbstractBlock.Properties.from(Blocks.OBSIDIAN)));
    public static final RegistryObject<Block> MULTIVERSE = BLOCKS.register("multiverse", ()->new SpheroidBlock(AbstractBlock.Properties.from(Blocks.OBSIDIAN)));
    public static final RegistryObject<Block> SUPERNOVA = BLOCKS.register("supernova", ()->new SpheroidBlock(AbstractBlock.Properties.from(Blocks.OBSIDIAN)));
    public static final RegistryObject<Block> ASTEROID_BELT = BLOCKS.register("asteroid_belt", ()->new AsteroidBeltBlock(AbstractBlock.Properties.from(Blocks.OBSIDIAN)));
    public static final RegistryObject<Block> SHRINK_LASER = BLOCKS.register("shrink_laser", ()->new ShrinkLaserBlock(AbstractBlock.Properties.from(Blocks.OBSIDIAN)));
    public static final RegistryObject<Block> SHRINK_LASER_1 = BLOCKS.register("shrink_laser_1", ()->new ShrinkLaser1Block(AbstractBlock.Properties.from(Blocks.OBSIDIAN)));
    public static final RegistryObject<Block> SHRINK_LASER_2 = BLOCKS.register("shrink_laser_2", ()->new ShrinkLaser2Block(AbstractBlock.Properties.from(Blocks.OBSIDIAN)));
    public static final RegistryObject<Block> SHRINK_LASER_3 = BLOCKS.register("shrink_laser_3", ()->new ShrinkLaser3Block(AbstractBlock.Properties.from(Blocks.OBSIDIAN)));
    public static final RegistryObject<Block> SHRINK_LASER_4 = BLOCKS.register("shrink_laser_4", ()->new ShrinkLaser4Block(AbstractBlock.Properties.from(Blocks.OBSIDIAN)));
    //public static final RegistryObject<Block> SHRINK_LASER_5 = BLOCKS.register("shrink_laser_5", ()->new ShrinkLaser5Block(AbstractBlock.Properties.from(Blocks.OBSIDIAN)));
}
