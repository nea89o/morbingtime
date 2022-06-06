package moe.nea.morbing;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.util.Identifier;

public class MorbArrowEntityRenderer extends ProjectileEntityRenderer<MorbArrowEntity> {
    public MorbArrowEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(MorbArrowEntity entity) {
        return Morbing.identifier("textures/entity/projectiles/morb_arrow.png");
    }
}
