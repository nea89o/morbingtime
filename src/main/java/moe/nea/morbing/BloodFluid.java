package moe.nea.morbing;

import net.minecraft.block.BlockState;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

public abstract class BloodFluid extends FlowableFluid {

    @Override
    public Item getBucketItem() {
        return Items.AIR;
    }

    @Override
    protected boolean canBeReplacedWith(FluidState state, BlockView world, BlockPos pos, Fluid fluid, Direction direction) {
        return true;
    }

    @Override
    public Vec3d getVelocity(BlockView world, BlockPos pos, FluidState state) {
        return new Vec3d(0, 0, 0);
    }

    @Override
    public Fluid getFlowing() {
        return Morbing.FLOWING_BLOOD;
    }

    @Override
    public Fluid getStill() {
        return Morbing.STILL_BLOOD;
    }

    @Override
    protected boolean isInfinite() {
        return false;
    }

    @Override
    protected void beforeBreakingBlock(WorldAccess world, BlockPos pos, BlockState state) {
    }

    @Override
    public boolean matchesType(Fluid fluid) {
        return fluid == getStill() || fluid == getFlowing();
    }

    @Override
    protected int getFlowSpeed(WorldView world) {
        return 0;
    }

    @Override
    protected int getLevelDecreasePerBlock(WorldView world) {
        return 8;
    }

    @Override
    protected FluidState getUpdatedState(WorldView world, BlockPos pos, BlockState state) {
        BlockPos abovePos = pos.up();
        BlockState aboveState = world.getBlockState(abovePos);
        FluidState aboveFluid = aboveState.getFluidState();
        if (state.getFluidState().isEmpty() && !aboveFluid.isEmpty() && matchesType(aboveFluid.getFluid())) {
            return getFlowing(aboveFluid.getLevel(), true);
        }
        if (state.getFluidState().getFluid() == getFlowing()) {
            BlockPos downPos = pos.down();
            BlockState downState = world.getBlockState(downPos);
            if (!matchesType(aboveFluid.getFluid())) {
                if (downState.getFluidState().getFluid().matchesType(this))
                    return Fluids.EMPTY.getDefaultState();
                return getFlowing(1, false);
            }
        }
        if (state.getFluidState().getFluid() == getStill()) {
            return getFlowing(1, false);
        }
        return state.getFluidState();
    }

    @Override
    public int getTickRate(WorldView world) {
        return 5;
    }

    @Override
    protected float getBlastResistance() {
        return 2;
    }

    @Override
    protected BlockState toBlockState(FluidState state) {
        return ModBlocks.INSTANCE.bloodFluidBlock.getDefaultState().with(Properties.LEVEL_15, getBlockStateLevel(state));
    }

    @Override
    public VoxelShape getShape(FluidState state, BlockView world, BlockPos pos) {
        return VoxelShapes.cuboid(0, 0, 0, 1, getHeight(state), 1);
    }

    public static class Flowing extends BloodFluid {
        @Override
        protected void appendProperties(StateManager.Builder<Fluid, FluidState> builder) {
            super.appendProperties(builder);
            builder.add(LEVEL);
        }

        @Override
        public int getLevel(FluidState state) {
            return state.get(LEVEL);
        }

        @Override
        public boolean isStill(FluidState state) {
            return false;
        }
    }

    public static class Still extends BloodFluid {

        @Override
        public boolean isStill(FluidState state) {
            return true;
        }

        @Override
        public int getLevel(FluidState state) {
            return 8;
        }
    }


}
