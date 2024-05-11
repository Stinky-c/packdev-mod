package com.buckydev.packdev.biomancy.serums;


import com.buckydev.packdev.Packdev;
import com.github.elenterius.biomancy.serum.BasicSerum;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import org.jetbrains.annotations.Nullable;
import wayoftime.bloodmagic.potion.BloodMagicPotions;


public class FlightSerum extends BasicSerum {
    private final int DURATION = 20 * (30 * 60); // Duration in seconds
    private final int AMPLIFIER = 1;
    private final StatusEffect EFFECT_TYPE = BloodMagicPotions.FLIGHT.get(); // Potion effect


    public FlightSerum(int color) {
        super(color);
    }

    @Override
    public void affectEntity(ServerWorld serverWorld, NbtCompound nbtCompound, @Nullable LivingEntity livingEntity, LivingEntity livingEntity1) {

    }

    @Override
    public boolean canAffectEntity(NbtCompound tag, @Nullable LivingEntity source, LivingEntity target) {
        return false;
    }

    @Override
    public void affectPlayerSelf(NbtCompound nbtCompound, ServerPlayerEntity serverPlayerEntity) {
        // TODO: find a way to respect nbt data of item to set custom duration/amplifier

        StatusEffectInstance effect = serverPlayerEntity.getStatusEffect(EFFECT_TYPE);
        if (effect != null) {
            // Extend duration if found
            int newDuration = effect.getDuration() + DURATION;
            serverPlayerEntity.removeStatusEffect(EFFECT_TYPE);
            serverPlayerEntity.addStatusEffect(new StatusEffectInstance(EFFECT_TYPE, newDuration, effect.getAmplifier()));

        } else {
            // Else we just add the effect normally
            serverPlayerEntity.addStatusEffect(new StatusEffectInstance(EFFECT_TYPE, DURATION, AMPLIFIER));
        }
    }
}
