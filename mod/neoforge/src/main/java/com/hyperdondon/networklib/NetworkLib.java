package com.hyperdondon.networklib;

import com.hyperdondon.networklib.platform.PlatformHandler;
import com.hyperdondon.networklib.platform.NeoforgePlatform;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;

@Mod(value = ModConstants.MOD_ID, dist = Dist.CLIENT)
public class NetworkLib {

    public NetworkLib(IEventBus modEventBus) {
        modEventBus.addListener(this::registerPayloads);
    }

    private void registerPayloads(RegisterPayloadHandlersEvent event) {
        PlatformHandler.setPlatform(new NeoforgePlatform(event.registrar(ModConstants.MOD_ID).optional()));
    }
}