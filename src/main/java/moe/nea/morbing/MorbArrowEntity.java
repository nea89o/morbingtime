package moe.nea.morbing;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.BatEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class MorbArrowEntity extends PersistentProjectileEntity {
    public MorbArrowEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    public MorbArrowEntity(double x, double y, double z, World world) {
        super(ModEntities.INSTANCE.morbArrowEntityEntityType, x, y, z, world);
    }

    public MorbArrowEntity(LivingEntity owner, World world) {
        super(ModEntities.INSTANCE.morbArrowEntityEntityType, owner, world);
    }



    @Override
    protected ItemStack asItemStack() {
        return new ItemStack(ModItems.INSTANCE.morbArrow);
    }

    @Override
    protected void onHit(LivingEntity target) {
        super.onHit(target);
        if (target instanceof BatEntity) {
            target.dropItem(ModItems.INSTANCE.morbSyringe);
        }
    }
}
