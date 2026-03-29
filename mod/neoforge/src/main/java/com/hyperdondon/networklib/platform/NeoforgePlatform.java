package com.hyperdondon.networklib.platform;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class NeoforgePlatform extends Platform {
    private final PayloadRegistrar registrar;

    public NeoforgePlatform(PayloadRegistrar registrar) {
        this.registrar = registrar;
    }

    @Override
    public <T extends CustomPacketPayload> void registerServerboundPayload(
            CustomPacketPayload.@NotNull Type<T> type,
            @NotNull StreamCodec<RegistryFriendlyByteBuf, T> codec
    ) {
        registrar.playToServer(type, codec, (payload, context) -> {});
    }


    @Override
    public <T extends CustomPacketPayload> void registerClientboundPayload(
            @NotNull final CustomPacketPayload.Type<T> type,
            @NotNull final StreamCodec<RegistryFriendlyByteBuf, T> codec,
            @Nullable Consumer<T> onReceive
    ) {
        registrar.playToClient(type, codec, (payload, context) -> {
            if (onReceive != null) {
                context.enqueueWork(() -> onReceive.accept(payload));
            }
        });
    }

    @Override
    public void sendServerboundPayload(CustomPacketPayload payload) {
        ClientPacketDistributor.sendToServer(payload);
    }
}
