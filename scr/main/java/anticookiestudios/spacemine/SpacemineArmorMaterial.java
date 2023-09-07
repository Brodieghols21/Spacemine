package anticookiestudios.spacemine;

import java.util.function.Supplier;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public enum SpacemineArmorMaterial implements IArmorMaterial {
    ASTEROID("asteroid",8, new int[]{3,3,3,3}, 15, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 1.0F, 0, () -> { return Ingredient.fromItems(ItemRegistry.ASTEROID.get()); },1,1,0,0,false,false,0,0,false,false,false,false,false,false),
    LARGE_ASTEROID("large_asteroid",13, new int[]{6,6,6,6}, 15, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 2.0F, 0, () -> { return Ingredient.fromItems(ItemRegistry.LARGE_ASTEROID.get()); },2,1.33F,0,0,false,false,0,0,false,false,true,false,false,false),
    ASTEROID_BELT("asteroid_belt",13, new int[]{6,6,6,6}, 15, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 2.0F, 0, () -> { return Ingredient.fromItems(ItemRegistry.ASTEROID_BELT.get()); },2,1,0,0,false,false,0,0,false,false,false,true,false,true),
    MOON("moon",20, new int[]{9,9,9,9}, 15, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 3.0F, 0, () -> { return Ingredient.fromItems(ItemRegistry.MOON.get()); },3,0.8F,0,0,true,true,0,0,false,false,true,false,false,false),
    PLANET("planet",20, new int[]{9,9,9,9}, 15, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 3.0F, 0, () -> { return Ingredient.fromItems(ItemRegistry.PLANET.get()); },3,1,0,0,false,false,0,0,true,true,true,false,false,false),
    STAR("star",34, new int[]{12,12,12,12}, 15, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4.0F, 0, () -> { return Ingredient.fromItems(ItemRegistry.STAR.get()); },4,1,0,0,true,true,0,0,false,false,false,false,false,false),
    SOLAR_SYSTEM("solar_system",55, new int[]{15,15,15,15}, 15, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 5.0F, 0, () -> { return Ingredient.fromItems(ItemRegistry.SOLAR_SYSTEM.get()); },5,1,0,0,true,true,0,0,false,false,false,false,false,false),
    DWARF_PLANET("dwarf_planet",34, new int[]{12,12,12,12}, 15, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4.0F, 0, () -> { return Ingredient.fromItems(ItemRegistry.DWARF_PLANET.get()); },4,0.5F,0,0,false,false,0,0,false,false,true,false,false,false),
    BROWN_DWARF("brown_dwarf",55, new int[]{15,15,15,15}, 15, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 5.0F, 0, () -> { return Ingredient.fromItems(ItemRegistry.BROWN_DWARF.get()); },5,0.5F,0,0,false,false,0,0,false,false,false,false,false,false),
    WHITE_DWARF("white_dwarf",55, new int[]{15,15,15,15}, 15, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 5.0F, 0, () -> { return Ingredient.fromItems(ItemRegistry.WHITE_DWARF.get()); },5,0.5F,0,0,false,false,0,0,false,false,false,false,true,false),
    BLACK_DWARF("black_dwarf",88, new int[]{18,18,18,18}, 15, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 6.0F, 0, () -> { return Ingredient.fromItems(ItemRegistry.BLACK_DWARF.get()); },6,0.5F,0,0,false,false,0,0,false,false,false,false,false,false),
    BLACK_HOLE("black_hole",142, new int[]{21,21,21,21}, 15, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 7.0F, 1F, () -> { return Ingredient.fromItems(ItemRegistry.BLACK_HOLE.get()); },7,.8F,0,0,false,false,0,0,false,false,false,false,false,false),
    RED_SUPERGIANT("red_supergiant",55, new int[]{3+5, 6+5, 8+5, 3+5}, 15, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 5.0F, 0, () -> { return Ingredient.fromItems(ItemRegistry.RED_SUPERGIANT.get()); },5,1.74F,1,0,true,true,0,0,false,false,false,false,false,false),
    SUPERNOVA("supernova",88, new int[]{18,18,18,18}, 15, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 6.0F, 0, () -> { return Ingredient.fromItems(ItemRegistry.SUPERNOVA.get()); },6,1,0,0,false,false,0,0,false,false,false,true,false,false),
    NEUTRON_STAR("neutron_star",142, new int[]{21,21,21,21}, 15, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 7.0F, 0, () -> { return Ingredient.fromItems(ItemRegistry.NEUTRON_STAR.get()); },7,0.5F,0,0,true,true,2,2,false,false,true,false,false,false),
    PULSAR("pulsar",230, new int[]{24, 24, 24, 24}, 15, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 8.0F, 0, () -> { return Ingredient.fromItems(ItemRegistry.PULSAR.get()); },8,0.5F,0,0,true,true,2,2,false,false,false,true,false,false),
    SOLAR_SYSTEM_CLUSTER("solar_system_cluster",88, new int[]{18,18,18,18}, 15, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 6.0F, 0, () -> { return Ingredient.fromItems(ItemRegistry.SOLAR_SYSTEM_CLUSTER.get()); },6,1,0,0,true,true,0,0,false,false,false,true,false,true),
    GALAXY("galaxy",142, new int[]{21,21,21,21}, 15, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 7.0F, 0, () -> { return Ingredient.fromItems(ItemRegistry.GALAXY.get()); },7,1,0,5,false,false,0,0,false,false,false,true,false,false),
    GALAXY_CLUSTER("galaxy_cluster",230, new int[]{24, 24, 24, 24}, 15, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 8.0F, 0, () -> { return Ingredient.fromItems(ItemRegistry.GALAXY_CLUSTER.get()); },8,1,0,10,false,false,0,0,false,false,false,true,false,true),
    UNIVERSE("universe",372, new int[]{27, 27, 27, 27}, 15, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 9.0F, 0, () -> { return Ingredient.fromItems(ItemRegistry.UNIVERSE.get()); },9,1,0,15,false,false,0,0,false,false,false,false,false,false),
    MULTIVERSE("multiverse",600, new int[]{30, 30, 30, 30}, 15, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 1000.0F, 0, () -> { return Ingredient.fromItems(ItemRegistry.MULTIVERSE.get()); },10, 1,1,20,false,false,0,0,false,false,false,false,false,true),
    SUPER_MASSIVE_BLACK_HOLE("super_massive_black_hole",230, new int[]{24, 24, 24, 24}, 15, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 8.0F, 1F, () -> { return Ingredient.fromItems(ItemRegistry.SUPER_MASSIVE_BLACK_HOLE.get()); },8,1.74F,1,0,false,false,0,0,false, false, false, false, false, false);

    private static final int[] MAX_DAMAGE_ARRAY = new int[]{13, 15, 16, 11};
    private final String name;
    private final int maxDamageFactor;
    private final int[] damageReductionAmountArray;
    private final int enchantability;
    private final SoundEvent soundEvent;
    private final float toughness;
    private final float knockbackResistance;
    private final LazyValue<Ingredient> repairMaterial;
    private final int resistance, speed,jump, proc, absorption;
    private final boolean glowing, night_vision, aqua, soul, blastProc, featherFalling, frostWalk, thorns;
    private final float size;

    private SpacemineArmorMaterial(String name, int maxDamageFactor, int[] damageReductionAmountArray, int enchantability, SoundEvent soundEvent, float toughness, float knockbackResistance, Supplier<Ingredient> repairMaterial, int proc, float size, int resistance, int absorption, boolean glowing, boolean night_vision, int speed, int jump, boolean aqua, boolean soul, boolean blastProc, boolean featherFalling, boolean frostWalk, boolean thorns ) {
        this.name = name;
        this.maxDamageFactor = maxDamageFactor;
        this.damageReductionAmountArray = damageReductionAmountArray;
        this.enchantability = enchantability;
        this.soundEvent = soundEvent;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairMaterial = new LazyValue<>(repairMaterial);
        this.proc = proc;
        this.size = size;
        this.resistance = resistance;
        this.absorption = absorption;
        this.glowing = glowing;
        this.night_vision = night_vision;
        this.speed = speed;
        this.jump = jump;
        this.aqua = aqua;
        this.soul = soul;
        this.blastProc = blastProc;
        this.featherFalling = featherFalling;
        this.frostWalk = frostWalk;
        this.thorns = thorns;
    }

    public int getDurability(EquipmentSlotType slotIn) {
        return MAX_DAMAGE_ARRAY[slotIn.getIndex()] * this.maxDamageFactor;
    }

    public int getProc() {return this.proc; }

    public int getMaxDamageFactor() {
        return maxDamageFactor;
    }

    public int[] getDamageReductionAmountArray() {
        return damageReductionAmountArray;
    }

    public boolean isGlowing() {
        return glowing;
    }

    public boolean isNight_vision() {
        return night_vision;
    }

    public boolean isAqua() {
        return aqua;
    }

    public boolean isSoul() {
        return soul;
    }

    public boolean isBlastProc() {
        return blastProc;
    }

    public boolean isFeatherFalling() {
        return featherFalling;
    }

    public boolean isFrostWalk() {
        return frostWalk;
    }

    public boolean isThorns() {
        return thorns;
    }

    public static int[] getMaxDamageArray() {
        return MAX_DAMAGE_ARRAY;
    }

    public int getDamageReductionAmount(EquipmentSlotType slotIn) {
        return this.damageReductionAmountArray[slotIn.getIndex()];
    }

    public int getEnchantability() {
        return this.enchantability;
    }

    public SoundEvent getSoundEvent() {
        return this.soundEvent;
    }

    public Ingredient getRepairMaterial() {
        return this.repairMaterial.getValue();
    }

    @OnlyIn(Dist.CLIENT)
    public String getName() {
        return this.name;
    }

    public float getToughness() {
        return this.toughness;
    }

    /**
     * Gets the percentage of knockback resistance provided by armor of the material.
     */
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }

    public float getSize() {
        return this.size;
    }

    public float getResistance() {
        return this.resistance;
    }

    public float getAbsorption() {
        return this.absorption;
    }

    public boolean getGlowing() {
        return this.glowing;
    }

    public boolean getNightVision() {
        return this.night_vision;
    }

    public int getSpeed() {
        return this.speed;
    }

    public int getJump() { return this.jump; }

}
