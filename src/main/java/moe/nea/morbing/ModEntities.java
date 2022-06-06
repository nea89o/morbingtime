package moe.nea.morbing;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.registry.Registry;

public class ModEntities extends ModRegistry<EntityType<?>> {
    public ModEntities() {
        super(Registry.ENTITY_TYPE);
    }

    public static ModEntities INSTANCE = new ModEntities();

    public <U extends Entity> EntityType<U> register(String id, EntityType.Builder<U> builder) {
        return register(id, builder.build(id));
    }

    public EntityType<MorbArrowEntity> morbArrowEntityEntityType = register("morbarrow", EntityType.Builder.<MorbArrowEntity>create(MorbArrowEntity::new, SpawnGroup.MISC).setDimensions(0.5F, 0.5F).maxTrackingRange(4).trackingTickInterval(20));

}
