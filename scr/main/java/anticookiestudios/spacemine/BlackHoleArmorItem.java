package anticookiestudios.spacemine;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class BlackHoleArmorItem extends SpacemineArmorItem {

    @Override
    public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
        BlockPos pos = player.getPosition();
        for (Entity e : world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(pos.getX()-10,pos.getY()-10,pos.getZ()-10,pos.getX()+10,pos.getY()+10,pos.getZ()+10))) {
            Vector3d motion;
            if(e!=player) {
                motion = e.getMotion();
                e.setMotion(motion.add(new Vector3d((pos.getX() + .5 - e.getPosX())/40.0, (pos.getY() - e.getPosY())/40.0, (pos.getZ() + .5 - e.getPosZ())/40.0)));            }
        }
    }

    public BlackHoleArmorItem(IArmorMaterial materialIn, EquipmentSlotType slot, Properties builderIn) {
        super(materialIn, slot, builderIn);
    }
}
