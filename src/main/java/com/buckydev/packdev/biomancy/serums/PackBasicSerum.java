package com.buckydev.packdev.biomancy.serums;

import com.github.elenterius.biomancy.api.serum.Serum;
import com.github.elenterius.biomancy.init.ModSerums;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public abstract class PackBasicSerum implements Serum {
    private final int color;

    protected PackBasicSerum(int color) {
        this.color = color;
    }

    @Override
    public boolean canAffectEntity(NbtCompound tag, @Nullable LivingEntity source, LivingEntity target) {
        return false;
    }

    @Override
    public void affectEntity(ServerWorld serverWorld, NbtCompound tag, @Nullable LivingEntity source, LivingEntity target) {

    }

    @Override
    public boolean canAffectPlayerSelf(NbtCompound tag, PlayerEntity playerEntity) {
        return false;
    }

    @Override
    public void affectPlayerSelf(NbtCompound tag, ServerPlayerEntity serverPlayerEntity) {

    }

    @Override
    public int getColor() {
        return this.color;
    }


    @Override
    public String getNameTranslationKey() {
        return Serum.makeTranslationKey(Objects.requireNonNull(ModSerums.REGISTRY.get().getKey(this)));
    }

    @Override
    public String toString() {
        return "Serum{name=%s, color=%s}".formatted(ModSerums.REGISTRY.get().getKey(this), Integer.toHexString(color));
    }


}
