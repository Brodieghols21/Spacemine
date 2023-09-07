package anticookiestudios.spacemine;

import net.minecraft.item.IItemTier;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.LazyValue;

import java.util.function.Supplier;

public enum SpacemineItemTier implements IItemTier {

    ASTEROID (3, 1561, 8.0F, 3.0F, 10, () -> {return Ingredient.fromItems(ItemRegistry.ASTEROID.get());},0,1,0,0,0,2,0),
            LARGE_ASTEROID(3, 1561, 8.0F, 3.0F, 10, () -> {return Ingredient.fromItems(ItemRegistry.LARGE_ASTEROID.get());},0,2,0,0,0,1,5),
    ASTEROID_BELT(3, 1561, 8.0F, 3.0F, 10, () -> {return Ingredient.fromItems(ItemRegistry.ASTEROID_BELT.get());},0,1,0,0,0,1,5),
            MOON(3, 1561, 8.0F, 3.0F, 10, () -> {return Ingredient.fromItems(ItemRegistry.MOON.get());},0,1,0,4,0,1,10),
    PLANET(3, 1561, 8.0F, 3.0F, 10, () -> {return Ingredient.fromItems(ItemRegistry.PLANET.get());},0,1,3,0,1,1,10),
            STAR(3, 1561, 8.0F, 3.0F, 10, () -> {return Ingredient.fromItems(ItemRegistry.STAR.get());},2,0,0,5,0,0,25),
    SOLAR_SYSTEM(3, 1561, 8.0F, 3.0F, 10, () -> {return Ingredient.fromItems(ItemRegistry.SOLAR_SYSTEM.get());},3,0,0,5,0,0,25),
            DWARF_PLANET(3, 1561, 8.0F, 3.0F, 10, () -> {return Ingredient.fromItems(ItemRegistry.DWARF_PLANET.get());},0,1,0,0,0,1,10),
    BROWN_DWARF(3, 1561, 8.0F, 3.0F, 10, () -> {return Ingredient.fromItems(ItemRegistry.BROWN_DWARF.get());},1,1,0,3,0,0,25),
            WHITE_DWARF(3, 1561, 8.0F, 3.0F, 10, () -> {return Ingredient.fromItems(ItemRegistry.WHITE_DWARF.get());},1,1,0,3,0,0,25),
    BLACK_DWARF(3, 1561, 8.0F, 3.0F, 10, () -> {return Ingredient.fromItems(ItemRegistry.BLACK_DWARF.get());},1,1,0,3,0,0,25),
            BLACK_HOLE(3, 1561, 8.0F, 3.0F, 10, () -> {return Ingredient.fromItems(ItemRegistry.BLACK_HOLE.get());},0,0,0,0,1,3,25),
    RED_SUPERGIANT(3, 1561, 8.0F, 3.0F, 10, () -> {return Ingredient.fromItems(ItemRegistry.RED_SUPERGIANT.get());},3,0,0,10,0,0,25),
            SUPERNOVA(3, 1561, 8.0F, 3.0F, 10, () -> {return Ingredient.fromItems(ItemRegistry.SUPERNOVA.get());},4,0,0,10,0,0,25),
    NEUTRON_STAR(3, 1561, 8.0F, 3.0F, 10, () -> {return Ingredient.fromItems(ItemRegistry.NEUTRON_STAR.get());},1,0,0,10,0,0,25),
            PULSAR(3, 1561, 8.0F, 3.0F, 10, () -> {return Ingredient.fromItems(ItemRegistry.PULSAR.get());},1,10,0,10,0,10,5),
    SOLAR_SYSTEM_CLUSTER(3, 1561, 8.0F, 3.0F, 10, () -> {return Ingredient.fromItems(ItemRegistry.SOLAR_SYSTEM_CLUSTER.get());},4,0,0,0,0,0,30),
            GALAXY(3, 1561, 8.0F, 3.0F, 10, () -> {return Ingredient.fromItems(ItemRegistry.GALAXY.get());},0,0,0,0,0,0,40),
    GALAXY_CLUSTER(3, 1561, 8.0F, 3.0F, 10, () -> {return Ingredient.fromItems(ItemRegistry.GALAXY_CLUSTER.get());},0,0,0,0,0,0,50),
            UNIVERSE(3, 1561, 8.0F, 3.0F, 10, () -> {return Ingredient.fromItems(ItemRegistry.UNIVERSE.get());},0,0,0,0,1,0,60),
    MULTIVERSE(3, 1561, 8.0F, 3.0F, 10, () -> {return Ingredient.fromItems(ItemRegistry.MULTIVERSE.get());},0,10,3,0,1,5,99),
            SUPER_MASSIVE_BLACK_HOLE(3, 1561, 8.0F, 3.0F, 10, () -> {return Ingredient.fromItems(ItemRegistry.SUPER_MASSIVE_BLACK_HOLE.get());},0,0,0,0,1,10,75);


        private final int harvestLevel;
        private final int maxUses;
        private final float efficiency;
        private final float attackDamage;
        private final int enchantability;
        private final LazyValue<Ingredient> repairMaterial;
        private final int fireAspect, knockback, looting, smite, mending, sweepingEdge, unbreaking;

    public int getFireAspect() {
        return fireAspect;
    }

    public int getKnockback() {
        return knockback;
    }

    public int getLooting() {
        return looting;
    }

    public int getSmite() {
        return smite;
    }

    public int getMending() {
        return mending;
    }

    public int getSweepingEdge() {
        return sweepingEdge;
    }

    public int getUnbreaking() {
        return unbreaking;
    }

    private SpacemineItemTier(int harvestLevelIn, int maxUsesIn, float efficiencyIn, float attackDamageIn, int enchantabilityIn, Supplier<Ingredient> repairMaterialIn) {
            this.harvestLevel = harvestLevelIn;
            this.maxUses = maxUsesIn;
            this.efficiency = efficiencyIn;
            this.attackDamage = attackDamageIn;
            this.enchantability = enchantabilityIn;
            this.repairMaterial = new LazyValue<>(repairMaterialIn);
            this.fireAspect= 0;
            this.knockback= 0;
            this.looting= 0;
            this.smite= 0;
            this.mending= 0;
            this.sweepingEdge= 0;
            this.unbreaking = 0;
        }

    private SpacemineItemTier(int harvestLevelIn, int maxUsesIn, float efficiencyIn, float attackDamageIn, int enchantabilityIn, Supplier<Ingredient> repairMaterialIn, int fireAspect,int knockback,int looting,int smite,int mending,int sweepingEdge,int unbreaking) {
        this.harvestLevel = harvestLevelIn;
        this.maxUses = maxUsesIn;
        this.efficiency = efficiencyIn;
        this.attackDamage = attackDamageIn;
        this.enchantability = enchantabilityIn;
        this.repairMaterial = new LazyValue<>(repairMaterialIn);
        this.fireAspect= fireAspect;
        this.knockback= knockback;
        this.looting= looting;
        this.smite= smite;
        this.mending= mending;
        this.sweepingEdge= sweepingEdge;
        this.unbreaking =unbreaking;
    }

        public int getMaxUses() {
            return this.maxUses;
        }

        public float getEfficiency() {
            return this.efficiency;
        }

        public float getAttackDamage() {
            return this.attackDamage;
        }

        public int getHarvestLevel() {
            return this.harvestLevel;
        }

        public int getEnchantability() {
            return this.enchantability;
        }

        public Ingredient getRepairMaterial() {
            return this.repairMaterial.getValue();
        }
    }