package moe.nea.morbing;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.block.MapColor;
import net.minecraft.util.registry.Registry;

public class ModBlocks extends ModRegistry<Block> {
    private ModBlocks() {
        super(Registry.BLOCK);
    }

    public static ModBlocks INSTANCE = new ModBlocks();
    public FluidBlock bloodFluidBlock = register("bloodfluid", new FluidBlock(Morbing.STILL_BLOOD, FabricBlockSettings.copy(Blocks.WATER).mapColor(MapColor.BRIGHT_RED)));


}
