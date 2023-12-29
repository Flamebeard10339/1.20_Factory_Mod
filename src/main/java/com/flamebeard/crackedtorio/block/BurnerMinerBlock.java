package com.flamebeard.crackedtorio.block;

import com.flamebeard.crackedtorio.blockentity.BurnerMinerBlockEntity;
import com.flamebeard.crackedtorio.init.BlockEntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BurnerMinerBlock extends HorizontalDirectionalBlock implements EntityBlock {

    public BurnerMinerBlock(Properties pProperties) {
        super(pProperties);
        registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH)); // for safety, we give it a default property
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return BlockEntityInit.BURNER_MINER_BLOCK_ENTITY.get().create(pPos, pState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return pLevel.isClientSide() ? null : (pLevel1, pPos, pState1, pBlockEntity) -> ((BurnerMinerBlockEntity)pBlockEntity).tick();
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder); // for facing init
        pBuilder.add(FACING);  // You have to add variables to the builder...
    }

    @Override
    public @NotNull InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (!pLevel.isClientSide()) {
            BlockEntity be = pLevel.getBlockEntity(pPos);
            if (be instanceof BurnerMinerBlockEntity blockEntity) {
                int counter = blockEntity.incrementCounter();
                pPlayer.sendSystemMessage(Component.literal("has been used %d times".formatted(counter)));
                return InteractionResult.sidedSuccess(pLevel.isClientSide());
            }
        }

        return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());

    }

    //    @Override
//    protected void dispenseFrom(ServerLevel pLevel, BlockState pState, BlockPos pPos) {
//        DispenserBlockEntity dispenserblockentity = pLevel.getBlockEntity(pPos, BlockEntityType.DROPPER).orElse((DropperBlockEntity)null);
//        BlockSource blocksource = new BlockSource(pLevel, pPos, pState, dispenserblockentity);
//        ItemStack itemstack = Items.IRON_ORE.getDefaultInstance();
//        ItemStack itemstack1 = new DefaultDispenseItemBehavior().dispense(blocksource, itemstack);
//
//    }
}
