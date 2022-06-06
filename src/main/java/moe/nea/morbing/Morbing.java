package moe.nea.morbing;

import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.potion.Potion;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Morbing implements ModInitializer {

    public static String MODID = "morbing";

    public static Identifier identifier(String path) {
        return new Identifier(MODID, path);
    }

    public static FlowableFluid STILL_BLOOD = new BloodFluid.Still();
    public static FlowableFluid FLOWING_BLOOD = new BloodFluid.Flowing();

    @Override
    public void onInitialize() {
        ModItems.INSTANCE.registerAll();
        ModBlocks.INSTANCE.registerAll();
        ModEntities.INSTANCE.registerAll();
        Registry.register(Registry.FLUID, Morbing.identifier("blood"), STILL_BLOOD);
        Registry.register(Registry.FLUID, Morbing.identifier("blood_flowing"), FLOWING_BLOOD);
        Registry.register(Registry.STATUS_EFFECT, Morbing.identifier("morbingtime"), new MorbingTimeStatus());
        Registry.register(Registry.POTION, Morbing.identifier("morbingtime"), new Potion(new StatusEffectInstance(new MorbingTimeStatus(), 60)));
        System.out.println("It's Morbing time!");
    }
}
