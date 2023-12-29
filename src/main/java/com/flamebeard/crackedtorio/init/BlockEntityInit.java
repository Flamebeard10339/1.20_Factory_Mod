package com.flamebeard.crackedtorio.init;

import com.flamebeard.crackedtorio.Crackedtorio;
import com.flamebeard.crackedtorio.blockentity.BurnerMinerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockEntityInit {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Crackedtorio.MODID);

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }

    public static final RegistryObject<BlockEntityType<BurnerMinerBlockEntity>> BURNER_MINER_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("burner_miner_block_entity",
                    () -> BlockEntityType.Builder.of(BurnerMinerBlockEntity::new, BlockInit.BURNER_MINER_BLOCK.get())
                            .build(null)
            );

}
