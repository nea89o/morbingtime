package moe.nea.morbing;


import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ModItems extends ModRegistry<Item> {

    private ModItems() {
        super(Registry.ITEM);
    }

    public static ModItems INSTANCE = new ModItems();
    public Item morbItem = register("morbitem", new Item(new FabricItemSettings()));
    public Item morbArrow = register("morbarrow", new MorbArrowItem(new FabricItemSettings()));
    public Item morbSyringe = register("morbsyringe", new Item(new FabricItemSettings()) {
        @Override
        public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
            super.appendTooltip(stack, world, tooltip, context);
            tooltip.add(new TranslatableText("tooltip.morbing.morbyringe"));
        }
    });
}
