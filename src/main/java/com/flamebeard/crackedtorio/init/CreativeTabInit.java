package com.flamebeard.crackedtorio.init;

import com.flamebeard.crackedtorio.Crackedtorio;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class CreativeTabInit {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Crackedtorio.MODID);

    public static final RegistryObject<CreativeModeTab> CRACKEDTORIO_TAB_1 = TABS.register("crackedtorio",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.crackedtorio"))
                    .icon(ItemInit.RED_SCIENCE.get()::getDefaultInstance) // This is a method reference supplier. fuck if i know...
                    .displayItems(((itemDisplayParameters, output) -> {
                        output.accept(ItemInit.COPPER_GEAR.get());
                        output.accept(ItemInit.IRON_PLATE.get());
                        output.accept(ItemInit.IRON_GRUB.get());
                        output.accept(ItemInit.RED_SCIENCE.get());
                        output.accept(BlockInit.BURNER_MINER.get());
                    }))
                    .build());
}
