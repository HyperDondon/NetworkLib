package com.hyperdondon.networklib;

import com.hyperdondon.networklib.api.NetworkLibAPI;
import com.hyperdondon.networklib.platform.PlatformHandler;
import com.hyperdondon.networklib.platform.FabricPlatform;
import com.hyperdondon.networklib.test.TestClientboundPayload;
import com.hyperdondon.networklib.test.TestServerboundPayload;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;

public class NetworkLib implements ModInitializer {
    
    @Override
    public void onInitialize() {
        PlatformHandler.setPlatform(new FabricPlatform());

        NetworkLibAPI.registerServerboundPayload(TestServerboundPayload.TYPE, TestServerboundPayload.CODEC);

        NetworkLibAPI.registerClientboundPayload(TestClientboundPayload.TYPE, TestClientboundPayload.CODEC, TestClientboundPayload::onReceive);

        ClientPlayConnectionEvents.JOIN.register((minecraft, connection, commonListenerCookie) -> {
            TestServerboundPayload testPayload = new TestServerboundPayload("fds", 202);
            NetworkLibAPI.sendServerboundPayload(testPayload);
        });
    }
}
