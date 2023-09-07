package anticookiestudios.spacemine.items;

import net.minecraft.client.gui.fonts.TexturedGlyph;
import net.minecraft.item.Food;
import net.minecraft.item.Foods;
import net.minecraft.item.Item;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.fml.RegistryObject;

public class SpacemineFoods {

    public static final Food ASTEROID = (new Food.Builder()).hunger(20).saturation(1).fastToEat().build();
    public static final Food LARGE_ASTEROID = (new Food.Builder()).hunger(20).saturation(1).build();
    public static final Food ASTEROID_BELT = (new Food.Builder()).hunger(20).saturation(1).build();
    public static final Food MOON = (new Food.Builder()).hunger(20).saturation(1).fastToEat().build();
    public static final Food PLANET = (new Food.Builder()).hunger(20).saturation(1).effect(() -> new EffectInstance(Effects.BAD_OMEN, 1200, 0), 1).setAlwaysEdible().build();
    public static final Food STAR = (new Food.Builder()).hunger(20).saturation(1).effect(() -> new EffectInstance(Effects.GLOWING, 1200, 0), 1).effect(() -> new EffectInstance(Effects.NIGHT_VISION, 1200, 0), 1).setAlwaysEdible().build();
    public static final Food SOLAR_SYSTEM = (new Food.Builder()).hunger(20).saturation(1).build();
    public static final Food DWARF_PLANET = (new Food.Builder()).hunger(20).saturation(1).fastToEat().build();
    public static final Food BROWN_DWARF = (new Food.Builder()).hunger(20).saturation(1).fastToEat().build();
    public static final Food WHITE_DWARF = (new Food.Builder()).hunger(20).saturation(1).fastToEat().effect(() -> new EffectInstance(Effects.GLOWING, 1200, 0), 1).effect(() -> new EffectInstance(Effects.NIGHT_VISION, 1200, 0), 1).setAlwaysEdible().build();
    public static final Food BLACK_DWARF = (new Food.Builder()).hunger(20).saturation(1).fastToEat().build();
    public static final Food BLACK_HOLE = (new Food.Builder()).hunger(-10).saturation(1).effect(() -> new EffectInstance(Effects.WITHER, 100, 1), 1).setAlwaysEdible().build();
    public static final Food RED_SUPERGIANT = (new Food.Builder()).hunger(20).saturation(1).effect(() -> new EffectInstance(Effects.GLOWING, 1200, 0), 1).effect(() -> new EffectInstance(Effects.NIGHT_VISION, 1200, 0), 1).setAlwaysEdible().build();
    public static final Food SUPERNOVA = (new Food.Builder()).hunger(20).saturation(1).build();
    public static final Food NEUTRON_STAR = (new Food.Builder()).hunger(20).saturation(1).effect(() -> new EffectInstance(Effects.RESISTANCE, 20, 4), 1).effect(() -> new EffectInstance(Effects.GLOWING, 1200, 0), 1).effect(() -> new EffectInstance(Effects.NIGHT_VISION, 1200, 0), 1).setAlwaysEdible().build();
    public static final Food PULSAR = (new Food.Builder()).hunger(20).saturation(1).effect(() -> new EffectInstance(Effects.RESISTANCE, 20, 4), 1).effect(() -> new EffectInstance(Effects.GLOWING, 1200, 0), 1).effect(() -> new EffectInstance(Effects.NIGHT_VISION, 1200, 0), 1).setAlwaysEdible().build();
    public static final Food SOLAR_SYSTEM_CLUSTER = (new Food.Builder()).hunger(20).saturation(1).build();
    public static final Food GALAXY = (new Food.Builder()).hunger(20).saturation(1).build();
    public static final Food GALAXY_CLUSTER = (new Food.Builder()).hunger(20).saturation(1).build();
    public static final Food UNIVERSE = (new Food.Builder()).hunger(20).saturation(1).build();
    public static final Food MULTIVERSE = (new Food.Builder()).hunger(20).saturation(1).build();
    public static final Food SUPER_MASSIVE_BLACK_HOLE = (new Food.Builder()).hunger(-20).saturation(1).effect(() -> new EffectInstance(Effects.WITHER, 100, 2), 1).setAlwaysEdible().build();
    public static final Food SPACE_PUFFERFISH = (new Food.Builder()).hunger(3).saturation(1).effect(() -> new EffectInstance(Effects.POISON, 100, 2), 1).effect(() -> new EffectInstance(Effects.NAUSEA, 200, 1), 1).effect(() -> new EffectInstance(Effects.LEVITATION, 200, 1), 1).setAlwaysEdible().build();
    public static final Food ANTIMATTER = (new Food.Builder()).hunger(-100).saturation(.1F).effect(() -> new EffectInstance(Effects.INSTANT_DAMAGE, 2000, 7), 1).setAlwaysEdible().build();

}
