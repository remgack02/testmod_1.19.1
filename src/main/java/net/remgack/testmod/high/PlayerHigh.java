package net.remgack.testmod.high;

import net.minecraft.nbt.CompoundTag;

public class PlayerHigh {
    private int high;
    private final int MIN_HIGH = 0;
    private final int MAX_HIGH = 10;

    public int getHigh() {
        return high;
    }

    public void addHigh(int add) {
        this.high = Math.min(high + add, MAX_HIGH);
    }
    public void subHigh(int sub) {
        this.high = Math.max(high - sub, MIN_HIGH);
    }

    public void copyFrom(PlayerHigh source) {
        this.high = source.high;
    }

    public void saveNBTData(CompoundTag nbt) {
        nbt.putInt("high", high);
    }

    public void loadNBTData(CompoundTag nbt) {
        high = nbt.getInt("high");
    }
}
