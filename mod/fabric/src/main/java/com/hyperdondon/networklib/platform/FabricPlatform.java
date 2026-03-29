package com.hyperdondon.networklib.platform;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class FabricPlatform extends Platform {
    @Override
    public <T extends CustomPacketPayload> void registerServerboundPayload(
            CustomPacketPayload.@NotNull Type<T> type,
            @NotNull StreamCodec<RegistryFriendlyByteBuf, T> codec
    ) {
        PayloadTypeRegistry.playC2S().register(type, codec);
    }


    @Override
    public <T extends CustomPacketPayload> void registerClientboundPayload(
            @NotNull final CustomPacketPayload.Type<T> type,
            @NotNull final StreamCodec<RegistryFriendlyByteBuf, T> codec,
            @Nullable Consumer<T> onReceive
    ) {
        PayloadTypeRegistry.playS2C().register(type, codec);

        ClientPlayNetworking.registerGlobalReceiver(type, (payload, context) -> {
            if (onReceive != null) onReceive.accept(payload);
        });
    }

    @Override
    public void sendServerboundPayload(CustomPacketPayload payload) {
        ClientPlayNetworking.send(payload);
    }
}
