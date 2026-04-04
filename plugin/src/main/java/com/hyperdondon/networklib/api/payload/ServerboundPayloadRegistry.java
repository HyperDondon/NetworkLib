package com.hyperdondon.networklib.api.payload;

import com.hyperdondon.networklib.listener.NetworkLibListener;
import io.netty.buffer.ByteBuf;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.function.Consumer;
import java.util.function.Supplier;

public final class ServerboundPayloadRegistry {
    public static final HashMap<NamespacedKey, Supplier<? extends ServerboundPayload>> REGISTRY = new HashMap<>();

    public static void register(@NotNull Plugin plugin, @NotNull String id, @NotNull Consumer<ByteBuf> onReceive) {
        register(plugin, new NamespacedKey(plugin, id), onReceive);
    }

    public static void register(@NotNull Plugin plugin, @NotNull NamespacedKey key, @NotNull Consumer<ByteBuf> onReceive) {
        ServerboundPayload payload = new ServerboundPayload() {
            @Override
            public void read(ByteBuf data) {
                onReceive.accept(data);
            }
        };
        register(plugin, key, () -> payload);
    }

    public static void register(@NotNull Plugin plugin, @NotNull String id, @NotNull Supplier<? extends ServerboundPayload> factory) {
        register(plugin, new NamespacedKey(plugin, id), factory);
    }

    public static void register(@NotNull Plugin plugin, @NotNull NamespacedKey key, @NotNull Supplier<? extends ServerboundPayload> factory) {
        Bukkit.getMessenger().registerIncomingPluginChannel(
                plugin,
                key.toString(),
                NetworkLibListener.getInstance()
        );

        REGISTRY.put(key, factory);
    }

    public static Supplier<? extends ServerboundPayload> getPayload(@NotNull Plugin plugin, @NotNull String id) {
        return getPayload(new NamespacedKey(plugin, id));
    }

    public static Supplier<? extends ServerboundPayload> getPayload(@NotNull NamespacedKey key) {
        return REGISTRY.get(key);
    }
}
