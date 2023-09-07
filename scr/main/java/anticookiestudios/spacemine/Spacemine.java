package anticookiestudios.spacemine;

import com.ibm.icu.number.Scale;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.command.impl.ExecuteCommand;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.WaterFluid;
import net.minecraft.item.*;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.AbsorptionEffect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.*;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerContainerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import virtuoel.pehkui.api.ScaleData;
import virtuoel.pehkui.api.ScaleModifier;
import virtuoel.pehkui.api.ScaleRegistries;
import virtuoel.pehkui.api.ScaleType;
import virtuoel.pehkui.util.ScaleUtils;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("spacemine")
public class Spacemine {

    //GiantLuigi4
    public static final AtomicReference<ScaleModifier> SpacemineScaleModifier = new AtomicReference<>();
    public static final AtomicReference<ScaleType> SpacemineScaleType = new AtomicReference<>();
    public static String modid = "spacemine";

    public Spacemine() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ItemRegistry.ITEMS.register(bus);
        BlockRegistry.BLOCKS.register(bus);
        //SpacemineParticleTypes.PARTICLES.register(bus);
        TileEntityRegistry.TILE_ENTITIES.register(bus);
        if (ModList.get().isLoaded("threecore")) {
            SpacemineResizeType.spacemineSizeChangeTypes.register(bus);
        }
        //MinecraftForge.EVENT_BUS.addListener(this::onLivingHurtEvent);
        MinecraftForge.EVENT_BUS.addListener(this::onLivingEquipmentChangeEvent);
        MinecraftForge.EVENT_BUS.addListener(this::onPlayerTickEvent);

        //GiantLuigi4
        if (ModList.get().isLoaded("pehkui")) {
            ScaleModifier modifier = new ScaleModifier() {
                @Override
                public float modifyScale(ScaleData scaleData, float modifiedScale, float delta) {
                    return SpacemineScaleType.get().getScaleData(scaleData.getEntity()).getScale(delta) * modifiedScale;
                }
            };

            ScaleRegistries.SCALE_MODIFIERS.put(new ResourceLocation("spacemine:spacemine_resize"), modifier);
            SpacemineScaleModifier.set(modifier);
            ScaleType type = ScaleType.Builder.create()
                    .affectsDimensions()
                    .addDependentModifier(SpacemineScaleModifier.get())
                    .build();
            ScaleRegistries.SCALE_TYPES.put(new ResourceLocation("spacemine_spacemine_resize"), type);
            ScaleType.BASE.getDefaultBaseValueModifiers().add(modifier);
            SpacemineScaleType.set(type);
        }


    }

    public void onPlayerTickEvent(TickEvent.PlayerTickEvent event) {
        Random r = new Random();
        Double randX = r.nextDouble() * 2 - 1;
        Double randZ = r.nextDouble() * 2 - 1;
        Double randSpeed = r.nextDouble() / 2;
        //event.player.world.addParticle(SpacemineParticleTypes.ASTEROID, event.player.getPosX() + randX, event.player.getPosYHeight(1), event.player.getPosZ() + randZ, 0, -randSpeed, 0);
        float size = getSizeAverage(allArmorEquipped(event.player));
        if (size != ResizingUtils.getSize(event.player))
            ResizingUtils.resize(event.player, size);

        int resistance = resistanceTotal(allArmorEquipped(event.player));
        int absorption = absorptionTotal(allArmorEquipped(event.player));
        boolean hasGlowing = hasGlowing(allArmorEquipped(event.player));
        boolean hasNightVision = hasNightVision(allArmorEquipped(event.player));
        int speed = speedTotal(allArmorEquipped(event.player));
        int jump = jumpTotal(allArmorEquipped(event.player));

        if(allArmorEquipped(event.player).equals(new ArrayList<ArmorItem>())){
            ResizingUtils.resize(event.player, 1);
        }

        if (resistance >= 0)
            resistance(event.player, resistance);
        if (absorption >= 0)
            absorption(event.player, absorption);
        if (hasGlowing)
            glowing(event.player);
        if (hasNightVision)
            nightVision(event.player);
        if (speed >= 0)
            speed(event.player, speed);
        if (jump >= 0) {
            if (jump > 10)
                jump -= 1;
            jump(event.player, jump);
            //event.player.world.addParticle(SpacemineParticleTypes.ASTEROID.get(), event.player.getPosX() + randX, event.player.getPosYHeight(1), event.player.getPosZ() + randZ, 0, -randSpeed, 0);
        }
    }

    public ArrayList<ArmorItem> allArmorEquipped(LivingEntity livingEntity) {
        ArrayList<ArmorItem> armors = new ArrayList<ArmorItem>();

        for (ItemStack item : livingEntity.getEquipmentAndArmor()) {
            if (item != null && item.getItem() instanceof ArmorItem)
                armors.add((ArmorItem) item.getItem());
        }
        Item main = livingEntity.getHeldItemMainhand().getItem();
        Item offhand = livingEntity.getHeldItemOffhand().getItem();
        if(armors.contains(main))
            armors.remove(main);
        if(armors.contains(offhand))
            armors.remove(offhand);
        return armors;
    }

    public ArrayList<ItemStack> allItemsHeld(LivingEntity livingEntity) {
        Item main = livingEntity.getHeldItemMainhand().getItem();
        Item offhand = livingEntity.getHeldItemOffhand().getItem();
        ArrayList<ItemStack> items = new ArrayList<>();

        for (ItemStack item : livingEntity.getEquipmentAndArmor()) {
            if(item.getItem().equals(main)||item.getItem().equals(offhand))
                items.add(item);
        }
        return items;
    }

    public ArrayList<ItemStack> allArmorEquippedAsItemStacks(LivingEntity livingEntity) {
        ArrayList<ItemStack> armors = new ArrayList<ItemStack>();

        for (ItemStack item : livingEntity.getEquipmentAndArmor()) {
            if (item != null && item.getItem() instanceof ArmorItem)
                armors.add(item);
        }
        ItemStack main = livingEntity.getHeldItemMainhand();
        ItemStack offhand = livingEntity.getHeldItemOffhand();
        if(armors.contains(main))
            armors.remove(main);
        if(armors.contains(offhand))
            armors.remove(offhand);

        return armors;
    }

    /*public void onLivingHurtEvent(LivingHurtEvent event) {
        LivingEntity hit = event.getEntityLiving();
        int total = 1;
        ArmorItem armor;
        int protectionDivisor = 1;
        SpacemineArmorMaterial material;
        float amount;
        Float damage = event.getAmount();
        Iterable<ItemStack> equipment = event.getEntityLiving().getEquipmentAndArmor();


        event.setAmount(0);
        for (ItemStack itemStack : equipment) {
            if (itemStack.getItem().getGroup().equals(SpacemineItemGroup.SPACEMINE_ARMOR)) {
                armor = (ArmorItem) itemStack.getItem();
                material = (SpacemineArmorMaterial) armor.getArmorMaterial();
                protectionDivisor += material.getProc();
            }
        }
        amount = damage * protectionDivisor;

}*/

    public void onLivingEquipmentChangeEvent(LivingEquipmentChangeEvent event) {


        if (absorptionTotal(allArmorEquipped(event.getEntityLiving()))>=0) {
            event.getEntityLiving().addPotionEffect(new EffectInstance(Effects.ABSORPTION, 650, absorptionTotal(allArmorEquipped(event.getEntityLiving())), true, false));
        }
        if(hasNightVision(allArmorEquipped(event.getEntityLiving())))
            event.getEntityLiving().addPotionEffect(new EffectInstance(Effects.NIGHT_VISION, 650, 0, true, false));


        enchantArmor(allArmorEquippedAsItemStacks(event.getEntityLiving()));
        enchantItems(allItemsHeld(event.getEntityLiving()));
        enchantSword(allItemsHeld(event.getEntityLiving()));



    }

    public ArrayList<ArmorItem> ArmorsOfMaterial(ArrayList<ArmorItem> armors, SpacemineArmorMaterial material, ArrayList<ArmorItem> result) {
        if(result!=null) result.clear();
        int count = 0;

        for (ArmorItem armor : armors)
            if (armor.getArmorMaterial() == material)
                result.add(armor);
        return result;
    }

    public ArrayList<ArmorItem> dwarfArmors(ArrayList<ArmorItem> armors, ArrayList<ArmorItem> result) {
        if(result!=null) result.clear();
        for (ArmorItem armor : armors)
            if (armor.getArmorMaterial() instanceof SpacemineArmorMaterial&&((SpacemineArmorMaterial)armor.getArmorMaterial()).getSize()<1)
                result.add(armor);
        return result;
    }
    public ArrayList<ArmorItem> giantArmors(ArrayList<ArmorItem> armors, ArrayList<ArmorItem> result) {
        if(result!=null) result.clear();
        for (ArmorItem armor : armors)
            if (armor.getArmorMaterial() instanceof SpacemineArmorMaterial&&((SpacemineArmorMaterial)armor.getArmorMaterial()).getSize()>1)
                result.add(armor);
        return result;
    }

    public float getSizeAverage(ArrayList<ArmorItem> armors){
        ArrayList<ArmorItem> dwarf = new ArrayList<ArmorItem>();
        ArrayList<ArmorItem> giant = new ArrayList<ArmorItem>();
        dwarf = dwarfArmors(armors, dwarf);
        giant = giantArmors(armors, giant);

        float sizeTotal = 1;

        for(ArmorItem armor:dwarf){
            sizeTotal *= ((SpacemineArmorMaterial)armor.getArmorMaterial()).getSize();
        }
        for(ArmorItem armor:giant){
            sizeTotal *= ((SpacemineArmorMaterial)armor.getArmorMaterial()).getSize();
        }
            return sizeTotal;
    }

    public void resistance(LivingEntity entity, int level) {
        entity.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 20, level, true, false));
    }

    public void speed(LivingEntity entity, int level) {
        entity.addPotionEffect(new EffectInstance(Effects.SPEED, 20, level, true, false));
    }

    public void jump(LivingEntity entity, int level) {
        entity.addPotionEffect(new EffectInstance(Effects.JUMP_BOOST, 20, level, true, false));
    }

    public void absorption(LivingEntity entity, int level) {
        if (entity.ticksExisted % 600 == 0)
            entity.addPotionEffect(new EffectInstance(Effects.ABSORPTION, 650, level, true, false));
    }

    public void glowing(LivingEntity entity) {
        entity.addPotionEffect(new EffectInstance(Effects.GLOWING, 20, 0, true, false));
    }

    public void nightVision(LivingEntity entity) {
        if (entity.ticksExisted % 400 == 0)
            entity.addPotionEffect(new EffectInstance(Effects.NIGHT_VISION, 650, 0, true, false));
    }

    public int resistanceTotal(ArrayList<ArmorItem> armors) {
        int total = 0;
        for (ArmorItem armor : armors)
            if (armor.getArmorMaterial() instanceof SpacemineArmorMaterial)
                total+=((SpacemineArmorMaterial)armor.getArmorMaterial()).getResistance();
        return total-1;
    }

    public int absorptionTotal(ArrayList<ArmorItem> armors) {
        int total = 0;
        for (ArmorItem armor : armors)
            if (armor.getArmorMaterial() instanceof SpacemineArmorMaterial)
                total+=((SpacemineArmorMaterial)armor.getArmorMaterial()).getAbsorption();
        return total-1;
    }

    public int speedTotal(ArrayList<ArmorItem> armors) {
        int total = 1;
        for (ArmorItem armor : armors)
            if (armor.getArmorMaterial() instanceof SpacemineArmorMaterial)
                total*=((SpacemineArmorMaterial)armor.getArmorMaterial()).getSpeed();
        if(total==1)
            return -1;
        return total-1;
    }

    public int jumpTotal(ArrayList<ArmorItem> armors) {
        int total = 1;
        for (ArmorItem armor : armors)
            if (armor.getArmorMaterial() instanceof SpacemineArmorMaterial)
                total*=((SpacemineArmorMaterial)armor.getArmorMaterial()).getJump();
            if(total==1)
                return -1;
        return total-1;
    }

    public boolean hasGlowing(ArrayList<ArmorItem> armors) {
        for (ArmorItem armor : armors)
            if (armor.getArmorMaterial() instanceof SpacemineArmorMaterial&&((SpacemineArmorMaterial)armor.getArmorMaterial()).getGlowing())
                return true;
        return false;
    }

    public boolean hasNightVision(ArrayList<ArmorItem> armors) {
        for (ArmorItem armor : armors)
            if (armor.getArmorMaterial() instanceof SpacemineArmorMaterial&&((SpacemineArmorMaterial)armor.getArmorMaterial()).getNightVision())
                return true;
        return false;
    }

    public void enchantArmor(ArrayList<ItemStack> armors){
        for (ItemStack armor : armors) {
            if (((ArmorItem) armor.getItem()).getArmorMaterial() instanceof SpacemineArmorMaterial&&!armor.isEnchanted()) {
                SpacemineArmorMaterial material= ((SpacemineArmorMaterial) ((ArmorItem) armor.getItem()).getArmorMaterial());
                int value =material.getProc();
                float enchantNum;
                if(value*9.9<99&&value!=7)
                    enchantNum = (float) (value*9.9)+1;
                else
                    enchantNum = (float) (value*9.9);
                if(material.isAqua()) {
                    armor.addEnchantment(Enchantments.AQUA_AFFINITY, 1);
                    armor.addEnchantment(Enchantments.RESPIRATION, 3);
                    armor.addEnchantment(Enchantments.DEPTH_STRIDER, 3);
                }
                if(material.isSoul()) {
                    armor.addEnchantment(Enchantments.SOUL_SPEED, 1);
                }
                if(material.isBlastProc()) {
                    armor.addEnchantment(Enchantments.BLAST_PROTECTION, 1);
                }
                if(material.isFeatherFalling()) {
                    armor.addEnchantment(Enchantments.FEATHER_FALLING, 4);
                }
                if(material.isFrostWalk()) {
                    armor.addEnchantment(Enchantments.FROST_WALKER, 1);
                }
                if(material.isThorns()) {
                    armor.addEnchantment(Enchantments.THORNS, 1);
                }
                armor.addEnchantment(Enchantments.PROTECTION, (int) enchantNum);
                armor.addEnchantment(Enchantments.UNBREAKING, value);
                armor.addEnchantment(Enchantments.FIRE_PROTECTION, 4);
            }
        }


    }

    public void enchantItems(ArrayList<ItemStack> items){
        int efficiency = 0;
        int value = 0;
        for (ItemStack item : items) {
            if(item.getItem() instanceof MattockToolItem&&!item.isEnchanted()){
                float damage = ((MattockToolItem)item.getItem()).getAttackDamage();
                efficiency = ((int)damage)/6;
                value = ((int)(efficiency/9.9));
                item.addEnchantment(Enchantments.EFFICIENCY, efficiency);
                if(value!=0)
                    item.addEnchantment(Enchantments.UNBREAKING, value);
            }
        }
    }

    public void enchantSword(ArrayList<ItemStack> items){
        int fireAspect, knockback, looting, smite, mending, sweepingEdge, unbreaking;
        for (ItemStack item : items) {
            if(item.getItem() instanceof SpacemineSwordItem&&!item.isEnchanted()){
                fireAspect = ((SpacemineItemTier)(((SpacemineSwordItem)(item.getItem())).getTier())).getFireAspect();
                knockback = ((SpacemineItemTier)(((SpacemineSwordItem)(item.getItem())).getTier())).getKnockback();
                looting = ((SpacemineItemTier)(((SpacemineSwordItem)(item.getItem())).getTier())).getLooting();
                smite = ((SpacemineItemTier)(((SpacemineSwordItem)(item.getItem())).getTier())).getSmite();
                mending = ((SpacemineItemTier)(((SpacemineSwordItem)(item.getItem())).getTier())).getMending();
                sweepingEdge = ((SpacemineItemTier)(((SpacemineSwordItem)(item.getItem())).getTier())).getSweepingEdge();
                unbreaking = ((SpacemineItemTier)(((SpacemineSwordItem)(item.getItem())).getTier())).getUnbreaking();

                if(fireAspect!=0)
                    item.addEnchantment(Enchantments.FIRE_ASPECT,fireAspect);
                if(knockback!=0)
                    item.addEnchantment(Enchantments.KNOCKBACK,knockback);
                if(looting!=0)
                    item.addEnchantment(Enchantments.LOOTING,looting);
                if(smite!=0)
                    item.addEnchantment(Enchantments.SMITE,smite);
                if(mending!=0)
                    item.addEnchantment(Enchantments.MENDING,mending);
                if(sweepingEdge!=0)
                    item.addEnchantment(Enchantments.SWEEPING,sweepingEdge);
                if(unbreaking!=0)
                    item.addEnchantment(Enchantments.UNBREAKING, unbreaking);
            }
        }
    }

    public static void addXItemsToList(ArrayList<ItemStack> items, Item item, int count){
        for(int i=0;i<count;i++)
            items.add(new ItemStack(item));
    }
}


