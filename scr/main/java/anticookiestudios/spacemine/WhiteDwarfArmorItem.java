package anticookiestudios.spacemine;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class WhiteDwarfArmorItem extends SpacemineArmorItem {

    @Override
    public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
        BlockPos pos = player.getPosition();
        for (Entity e : world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(pos.getX()-10,pos.getY()-10,pos.getZ()-10,pos.getX()+10,pos.getY()+10,pos.getZ()+10))) {
            Vector3d motion;
            if(e!=player) {
                motion = e.getMotion();
                e.setMotion(new Vector3d(motion.getX() / 1.68, motion.getY() / 1.68, motion.getZ() / 1.68));
                if (e instanceof LivingEntity)
                    ((LivingEntity) e).addPotionEffect(new EffectInstance(Effects.SLOWNESS, 20, 3));
            }
        }
    }

    public WhiteDwarfArmorItem(IArmorMaterial materialIn, EquipmentSlotType slot, Properties builderIn) {
        super(materialIn, slot, builderIn);
    }
}
