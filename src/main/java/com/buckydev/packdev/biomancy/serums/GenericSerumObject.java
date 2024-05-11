package com.buckydev.packdev.biomancy.serums;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class GenericSerumObject {
    public int duration;
    public int amplifier;
    public StatusEffect effect;

    public static int DEFAULT_DURATION = 20;
    public static int DEFAULT_AMPLIFIER = 1;
    public static StatusEffect DEFAULT_EFFECT = StatusEffects.POISON;

    public final String DURRATION_KEY = "serumDur";
    public final String AMPLIFIER_KEY = "serumAmp";
    public final String EFFECT_KEY = "serumEffect";


    public GenericSerumObject(StatusEffect effect, int duration, int amplifier) {
        this.effect = effect;
        this.duration = duration;
        this.amplifier = amplifier;
    }

    public GenericSerumObject() {
        this.effect = DEFAULT_EFFECT;
        this.duration = DEFAULT_DURATION;
        this.amplifier = DEFAULT_AMPLIFIER;
    }

    public GenericSerumObject(NbtCompound nbt) {

        // may not be completely safe
        if (nbt.contains(DURRATION_KEY, NbtCompound.INT_TYPE)) {
            this.duration = nbt.getInt(DURRATION_KEY);
        } else {
            this.duration = DEFAULT_DURATION;
        }


        if (nbt.contains(AMPLIFIER_KEY, NbtCompound.INT_TYPE)) {
            this.amplifier = nbt.getInt(AMPLIFIER_KEY);
        } else {
            this.amplifier = DEFAULT_AMPLIFIER;
        }
        if (nbt.contains(EFFECT_KEY, NbtCompound.STRING_TYPE)) {
            String key = nbt.getString(EFFECT_KEY);
            @NotNull Optional<RegistryEntry.Reference<StatusEffect>> val = ForgeRegistries.MOB_EFFECTS.getDelegate(new Identifier(key));
            if (val.isPresent()) {
                this.effect = val.get().get();
            } else {
                this.effect = DEFAULT_EFFECT;
            }

        } else {
            this.effect = DEFAULT_EFFECT;
        }


    }

}
