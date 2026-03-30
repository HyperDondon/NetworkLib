package com.hyperdondon.networklib;

import com.hyperdondon.networklib.platform.PlatformHandler;
import com.hyperdondon.networklib.platform.FabricPlatform;
import net.fabricmc.api.ModInitializer;

public class NetworkLib implements ModInitializer {
    
    @Override
    public void onInitialize() {
        PlatformHandler.setPlatform(new FabricPlatform());
    }
}
