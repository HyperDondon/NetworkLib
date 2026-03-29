package com.hyperdondon.networklib.platform;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public abstract class Platform {
    public abstract <T extends CustomPacketPayload> void registerServerboundPayload(
            @NotNull final CustomPacketPayload.Type<T> type,
            @NotNull final StreamCodec<RegistryFriendlyByteBuf, T> codec
    );

    public abstract <T extends CustomPacketPayload> void registerClientboundPayload(
            @NotNull final CustomPacketPayload.Type<T> type,
            @NotNull final StreamCodec<RegistryFriendlyByteBuf, T> codec,
            @Nullable Consumer<T> onReceive
    );

    public abstract void sendServerboundPayload(CustomPacketPayload payload);
}
