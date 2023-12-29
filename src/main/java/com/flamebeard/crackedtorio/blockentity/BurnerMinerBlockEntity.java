package com.flamebeard.crackedtorio.blockentity;

import com.flamebeard.crackedtorio.Crackedtorio;
import com.flamebeard.crackedtorio.init.BlockEntityInit;
import com.mojang.datafixers.types.Type;
import net.minecraft.core.BlockPos;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.DispenserBlockEntity;
import net.minecraft.world.level.block.entity.DropperBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.Set;

public class BurnerMinerBlockEntity extends BlockEntity {
    private int counter;
    private int tick=0;

    public BurnerMinerBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(BlockEntityInit.BURNER_MINER_BLOCK_ENTITY.get(), pPos, pBlockState);
    }

    @Override
    public void load(@NotNull CompoundTag pTag) { // This data is not synced. Only on server side...
        super.load(pTag); //what saves persistent data
        CompoundTag data = pTag.getCompound(Crackedtorio.MODID);
        this.counter = data.getInt("Counter");
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag pTag) {
        super.saveAdditional(pTag);
        CompoundTag data = new CompoundTag();
        data.putInt("Counter", this.counter);
        pTag.put(Crackedtorio.MODID, data);
    }

    public int incrementCounter() {
        ++this.counter;
        setChanged(); // needed to save the data when it gets changed.
        return this.counter;
    }

    public void tick() {
        if (this.getLevel() == null) {
            return;
        }
        if (tick++ % 20 != 0) {
            return;
        }
        System.out.println("testing");
        ItemStack itemstack = Items.IRON_ORE.getDefaultInstance();
        ItemEntity itementity = new ItemEntity(this.getLevel(),
                this.getBlockPos().getX(),
                this.getBlockPos().getY() + 3,
                this.getBlockPos().getZ(),
                itemstack);
        this.getLevel().addFreshEntity(itementity);
    }
}
