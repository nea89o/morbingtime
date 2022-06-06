package moe.nea.morbing.mixin;

import com.mojang.authlib.GameProfile;
import moe.nea.morbing.Morbing;
import moe.nea.morbing.MorbingTimeStatus;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractClientPlayerEntity.class)
public abstract class MixinAbstractClientPlayerEntity extends PlayerEntity {

    public MixinAbstractClientPlayerEntity(World world, BlockPos pos, float yaw, GameProfile profile) {
        super(world, pos, yaw, profile);
    }

    @Inject(method = "hasSkinTexture", at = @At("HEAD"), cancellable = true)
    public void onHasSkinTexture(CallbackInfoReturnable<Boolean> cir) {
        if (hasStatusEffect(MorbingTimeStatus.INSTANCE)) {
            cir.setReturnValue(true);
        }
    }

    @Inject(method = "getSkinTexture", at = @At("HEAD"), cancellable = true)
    public void onGetSkinTexture(CallbackInfoReturnable<Identifier> cir) {
        if (hasStatusEffect(MorbingTimeStatus.INSTANCE)) {
            cir.setReturnValue(Morbing.identifier("morbingskin.png"));
        }
    }


}
