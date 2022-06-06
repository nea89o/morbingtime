package moe.nea.morbing;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;

public class MorbingClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        FluidRenderHandlerRegistry.INSTANCE.register(Morbing.STILL_BLOOD, Morbing.FLOWING_BLOOD, new SimpleFluidRenderHandler(
                new Identifier("minecraft", "block/water_still"),
                new Identifier("minecraft", "block/water_flow"),
                0x7f0622
        ));
        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(), Morbing.STILL_BLOOD, Morbing.FLOWING_BLOOD);
        EntityRendererRegistry.register(ModEntities.INSTANCE.morbArrowEntityEntityType, MorbArrowEntityRenderer::new);

    }
}
