package moe.nea.morbing;

import moe.nea.morbing.mixin.AccessorBrewingRecipeRegistry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.sound.SoundEvent;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;

public class Morbing implements ModInitializer {

    public static String MODID = "morbing";

    public static Identifier identifier(String path) {
        return new Identifier(MODID, path);
    }

    public static FlowableFluid STILL_BLOOD = new BloodFluid.Still();
    public static FlowableFluid FLOWING_BLOOD = new BloodFluid.Flowing();
    public static SoundEvent ITSMORBINTIME_SOUND = new SoundEvent(Morbing.identifier("itsmorbintime"));
    public static Potion MORB_POTION = new Potion(new StatusEffectInstance(MorbingTimeStatus.INSTANCE, 600));

    @Override
    public void onInitialize() {
        ModItems.INSTANCE.registerAll();
        ModBlocks.INSTANCE.registerAll();
        ModEntities.INSTANCE.registerAll();
        Registry.register(Registry.FLUID, Morbing.identifier("blood"), STILL_BLOOD);
        Registry.register(Registry.FLUID, Morbing.identifier("blood_flowing"), FLOWING_BLOOD);
        Registry.register(Registry.STATUS_EFFECT, Morbing.identifier("morbingtime"), MorbingTimeStatus.INSTANCE);
        Registry.register(Registry.POTION, Morbing.identifier("morbingtime"), MORB_POTION);

        AttackEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            StatusEffectInstance morbingStatus = player.getStatusEffect(MorbingTimeStatus.INSTANCE);
            if (morbingStatus != null && morbingStatus.getDuration() > 0) {
                BlockPos blockPos = entity.getBlockPos();
                world.setBlockState(blockPos, ModBlocks.INSTANCE.bloodFluidBlock.getDefaultState().with(Properties.LEVEL_15, 1));
            }
            return ActionResult.PASS;
        });
        Registry.register(Registry.SOUND_EVENT, Morbing.identifier("itsmorbintime"), ITSMORBINTIME_SOUND);
        AccessorBrewingRecipeRegistry.registerPotionRecipe(Potions.AWKWARD, ModItems.INSTANCE.morbSyringe, MORB_POTION);
        System.out.println("It's Morbing time!");
    }
}
