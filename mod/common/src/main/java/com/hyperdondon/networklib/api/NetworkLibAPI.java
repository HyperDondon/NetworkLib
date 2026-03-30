package com.hyperdondon.networklib.api;

import com.hyperdondon.networklib.platform.PlatformHandler;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public final class NetworkLibAPI {
    public static <T extends CustomPacketPayload> void registerServerboundPayload(
            @NotNull final CustomPacketPayload.Type<T> type,
            @NotNull final StreamCodec<RegistryFriendlyByteBuf, T> codec
    ) {
        PlatformHandler.getPlatform().registerServerboundPayload(type, codec);
    }

    public static <T extends CustomPacketPayload> void registerClientboundPayload(
            @NotNull final CustomPacketPayload.Type<T> type,
            @NotNull final StreamCodec<RegistryFriendlyByteBuf, T> codec,
            @Nullable Consumer<T> onReceive
    ) {
        PlatformHandler.getPlatform().registerClientboundPayload(type, codec, onReceive);
    }

    public static void sendServerboundPayload(CustomPacketPayload payload) {
        PlatformHandler.getPlatform().sendServerboundPayload(payload);
    }
}
