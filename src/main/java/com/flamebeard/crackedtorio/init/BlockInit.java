package com.flamebeard.crackedtorio.init;

import com.flamebeard.crackedtorio.Crackedtorio;
import com.flamebeard.crackedtorio.block.BurnerMinerBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class BlockInit {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Crackedtorio.MODID);

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

    // Can use a regular register instead of register block if you don't want the block to have an associated item with it.
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block>RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ItemInit.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static final RegistryObject<BurnerMinerBlock> BURNER_MINER_BLOCK = registerBlock("burner_miner",
            () -> new BurnerMinerBlock(BlockBehaviour.Properties.copy(Blocks.DROPPER)
                    .lightLevel(state -> 10)
                    .requiresCorrectToolForDrops()
                    .pushReaction(PushReaction.BLOCK)
            ));
}
