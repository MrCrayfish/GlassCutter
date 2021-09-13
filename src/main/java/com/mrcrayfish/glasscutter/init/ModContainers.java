package com.mrcrayfish.glasscutter.init;

import com.mrcrayfish.glasscutter.Reference;
import com.mrcrayfish.glasscutter.inventory.container.GlasscutterContainer;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: MrCrayfish
 */
@Mod.EventBusSubscriber(modid = Reference.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModContainers
{
    private static final List<MenuType<?>> CONTAINER_TYPES = new ArrayList<>();

    public static final MenuType<GlasscutterContainer> GLASSCUTTER = register(Reference.MOD_ID + ":glasscutter", GlasscutterContainer::new);

    private static <T extends AbstractContainerMenu> MenuType<T> register(String key, MenuType.MenuSupplier<T> factory)
    {
        MenuType<T> type = new MenuType<>(factory);
        type.setRegistryName(key);
        CONTAINER_TYPES.add(type);
        return type;
    }

    @SubscribeEvent
    @SuppressWarnings("unused")
    public static void registerTypes(final RegistryEvent.Register<MenuType<?>> event)
    {
        IForgeRegistry<MenuType<?>> registry = event.getRegistry();
        CONTAINER_TYPES.forEach(registry::register);
        CONTAINER_TYPES.clear();
    }
}
