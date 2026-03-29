package com.hyperdondon.networklib;

import com.hyperdondon.networklib.api.NetworkLibAPI;
import com.hyperdondon.networklib.platform.PlatformHandler;
import com.hyperdondon.networklib.platform.NeoforgePlatform;
import com.hyperdondon.networklib.test.TestClientboundPayload;
import com.hyperdondon.networklib.test.TestServerboundPayload;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.ClientPlayerNetworkEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.fml.loading.FMLEnvironment;

@Mod(value = ModConstants.MOD_ID, dist = Dist.CLIENT)
public class NetworkLib {

    public NetworkLib(IEventBus modEventBus) {
        ModConstants.LOG.info("Hello NeoForge world!");

        modEventBus.addListener(this::registerPayloads);

        NeoForge.EVENT_BUS.addListener(this::onClientJoin);
    }

    private void registerPayloads(RegisterPayloadHandlersEvent event) {
        PlatformHandler.setPlatform(new NeoforgePlatform(event.registrar(ModConstants.MOD_ID).optional()));

        NetworkLibAPI.registerServerboundPayload(TestServerboundPayload.TYPE, TestServerboundPayload.CODEC);
        NetworkLibAPI.registerClientboundPayload(TestClientboundPayload.TYPE, TestClientboundPayload.CODEC, TestClientboundPayload::onReceive);
    }

    private void onClientJoin(ClientPlayerNetworkEvent.LoggingIn event) {
        TestServerboundPayload testPayload = new TestServerboundPayload("fds", 202);
        NetworkLibAPI.sendServerboundPayload(testPayload);
    }
}