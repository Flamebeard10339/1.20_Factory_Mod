package com.flamebeard.crackedtorio.init;

import com.flamebeard.crackedtorio.Crackedtorio;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Crackedtorio.MODID);

    public static final RegistryObject<Item> COPPER_GEAR = ITEMS.register("copper_gear",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> IRON_PLATE = ITEMS.register("iron_plate",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RED_SCIENCE = ITEMS.register("red_science",
            () -> new Item(new Item.Properties().stacksTo(16)));
    public static final RegistryObject<Item> IRON_GRUB = ITEMS.register("iron_grub",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(1)
                    .saturationMod(0.2f)
                    .fast()
                    .effect(() -> new MobEffectInstance(MobEffects.HUNGER, 100, 1), 1.0f)
                    .build())));

    public static final RegistryObject<BlockItem> BURNER_MINER = ITEMS.register("burner_miner",
            () -> new BlockItem(BlockInit.BURNER_MINER.get(), new Item.Properties()));
}
