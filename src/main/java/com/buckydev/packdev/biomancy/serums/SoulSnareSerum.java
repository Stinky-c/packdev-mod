package com.buckydev.packdev.biomancy.serums;


import com.github.elenterius.biomancy.serum.BasicSerum;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import org.jetbrains.annotations.Nullable;
import wayoftime.bloodmagic.potion.BloodMagicPotions;


public class SoulSnareSerum extends BasicSerum {
    private final int DURATION = 20 * 40; // 40 Seconds in ticks
    private final int AMPLIFIER = 1;
    private final StatusEffect EFFECT = BloodMagicPotions.SOUL_SNARE.get(); // Potion effect


    public SoulSnareSerum(int color) {
        super(color);
    }


    @Override
    public void affectEntity(ServerWorld serverWorld, NbtCompound tag, @Nullable LivingEntity source, LivingEntity target) {

        // TODO: find a way to respect nbt data of item to set custom duration/amplifier

        if (target.hasStatusEffect(EFFECT)) {
            target.getStatusEffect(EFFECT).mapDuration(len -> len + DURATION);
        } else {
            target.addStatusEffect(new StatusEffectInstance(EFFECT, DURATION, AMPLIFIER));
        }
    }

    @Override
    public boolean canAffectPlayerSelf(NbtCompound tag, PlayerEntity targetSelf) {
        return false;
    }

    @Override
    public void affectPlayerSelf(NbtCompound nbtCompound, ServerPlayerEntity serverPlayerEntity) {

    }
}
