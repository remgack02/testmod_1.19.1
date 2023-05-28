package net.remgack.testmod.high;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerHighProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<PlayerHigh> PLAYER_HIGH = CapabilityManager.get(new CapabilityToken<>() {
    });

    private PlayerHigh high = null;
    private final LazyOptional<PlayerHigh> optional = LazyOptional.of(this::createPlayerHigh);

    private PlayerHigh createPlayerHigh() {
        if (this.high == null) {
            this.high = new PlayerHigh();
        }
        return this.high;
    }
    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == PLAYER_HIGH) {
            return optional.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createPlayerHigh().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createPlayerHigh().loadNBTData(nbt);
    }
}
