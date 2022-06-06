package moe.nea.morbing;


import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;

public class ModItems extends ModRegistry<Item> {

    private ModItems() {
        super(Registry.ITEM);
    }

    public static ModItems INSTANCE = new ModItems();
    public Item morbItem = register("morbitem", new Item(new FabricItemSettings()));
    public Item morbArrow = register("morbarrow", new MorbArrowItem(new FabricItemSettings()));
    public Item morbSyringe = register("morbsyringe", new Item(new FabricItemSettings()));
}
