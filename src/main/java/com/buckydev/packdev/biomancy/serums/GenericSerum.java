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


public class GenericSerum extends BasicSerum {


    public GenericSerum(int color) {
        super(color);
    }

    @Override
    public void affectEntity(ServerWorld serverWorld, NbtCompound tag, @Nullable LivingEntity source, LivingEntity target) {
        GenericSerumObject data = new GenericSerumObject(tag);
        applyEffect(target, data.effect, data.duration, data.amplifier);
    }


    @Override
    public void affectPlayerSelf(NbtCompound tag, ServerPlayerEntity serverPlayerEntity) {
        Packdev.LOGGER.info(tag.toString());
        GenericSerumObject data = new GenericSerumObject(tag);
        applyEffect(serverPlayerEntity, data.effect, data.duration, data.amplifier);
    }

    public void applyEffect(LivingEntity target, StatusEffect effect, int duration, int amplifier) {

        StatusEffectInstance effectInstance = target.getStatusEffect(effect);
        if (effectInstance != null) {
            // Extend duration if found
            int newDuration = effectInstance.getDuration() + duration;
            target.removeStatusEffect(effect);
            target.addStatusEffect(new StatusEffectInstance(effect, newDuration, Math.max(effectInstance.getAmplifier(), amplifier)));

        } else {
            // Else we just add the effect normally
            target.addStatusEffect(new StatusEffectInstance(effect, duration, amplifier));
        }
    }
}

