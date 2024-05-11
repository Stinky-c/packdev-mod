package com.buckydev.packdev;

import com.buckydev.packdev.biomancy.ModSerums;
import com.mojang.logging.LogUtils;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(Packdev.MOD_ID)
public final class Packdev {
    public static final String MOD_ID = "packdev";
    public static final Logger LOGGER = LogUtils.getLogger();

    public Packdev() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModSerums.ITEMS.register(modEventBus);
        ModSerums.SERUMS_.register(modEventBus);
        ModSerums.ITEM_GROUPS.register(modEventBus);

    }


}
