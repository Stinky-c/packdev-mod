package com.buckydev.packdev.biomancy;

import com.buckydev.packdev.Packdev;
import com.buckydev.packdev.biomancy.serums.*;
import com.github.elenterius.biomancy.api.serum.Serum;
import com.github.elenterius.biomancy.init.ModRarities;
import com.github.elenterius.biomancy.item.SerumItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.text.Text;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.github.elenterius.biomancy.init.ModSerums.SERUMS;


/*
TODO: override the lang key to add my own
 */
public class ModSerums {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Packdev.MOD_ID);
    //    public static final DeferredRegister<Serum> SERUMS_ = DeferredRegister.create(SERUMS.getRegistryKey(), BiomancyMod.MOD_ID);
    public static final DeferredRegister<Serum> SERUMS_ = DeferredRegister.create(SERUMS.getRegistryKey(), Packdev.MOD_ID);
    public static final DeferredRegister<ItemGroup> ITEM_GROUPS = DeferredRegister.create(Registries.ITEM_GROUP.getKey(), Packdev.MOD_ID);


    public static final RegistryObject<FlightSerum> FLIGHT_SERUM = SERUMS_.register("flight_serum", () -> new FlightSerum(0x23DDE1));
    public static final RegistryObject<SoulSnareSerum> SOULSNARE_SERUM = SERUMS_.register("soulsnare_serum", () -> new SoulSnareSerum(0xFFFFFF));
    public static final RegistryObject<GenericSerum> GENERIC_SERUM = SERUMS_.register("generic_serum", () -> new GenericSerum(0xFFFFFF));

    public static final RegistryObject<SerumItem> FLIGHT_SERUM_ITEM = registerSerumItem(FLIGHT_SERUM);
    public static final RegistryObject<SerumItem> SOULSNARE_SERUM_ITEM = registerSerumItem(SOULSNARE_SERUM);
    public static final RegistryObject<SerumItem> GENERIC_SERUM_ITEM = registerSerumItem(GENERIC_SERUM);


    public static final RegistryObject<ItemGroup> MOD_ITEM_GROUP = ITEM_GROUPS.register("name", () -> ItemGroup.builder()
            .displayName(Text.translatable("item_group." + Packdev.MOD_ID + ".name"))
            .icon(() -> GENERIC_SERUM_ITEM.get().getDefaultStack())
            .entries((displayContext, entries) -> {
                entries.add(FLIGHT_SERUM_ITEM.get().getDefaultStack());
                entries.add(SOULSNARE_SERUM_ITEM.get().getDefaultStack());
                entries.add(GENERIC_SERUM_ITEM.get().getDefaultStack());
            })
            .build());

    private static <T extends Serum> RegistryObject<SerumItem> registerSerumItem(RegistryObject<T> serumRegistryObject) { // YOLO?
        return ITEMS.register(serumRegistryObject.getId().getPath(), () -> new SerumItem(new Item.Settings().rarity(ModRarities.RARE).maxCount(8), serumRegistryObject));
    }


}