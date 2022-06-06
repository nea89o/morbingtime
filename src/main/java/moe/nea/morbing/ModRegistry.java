package moe.nea.morbing;

import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.List;

public class ModRegistry<T> {
    private Registry<T> registry;
    private List<Pair<Identifier, T>> toRegister = new ArrayList<>();

    public ModRegistry(Registry<T> registry) {
        this.registry = registry;
    }


    protected <U extends T> U register(String id, U item) {
        toRegister.add(new Pair<>(new Identifier(Morbing.MODID, id), item));
        return item;
    }

    public void registerAll() {
        toRegister.forEach(it ->
                Registry.register(registry, it.getLeft(), it.getRight())
        );
    }
}
