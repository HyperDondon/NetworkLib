package com.hyperdondon.networklib.api.payload;

import com.hyperdondon.networklib.listener.NetworkLibListener;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public final class ServerboundPayloadRegistry {
    public static final HashMap<NamespacedKey, Class<? extends ServerboundPayload>> REGISTRY = new HashMap<>();

    public static void register(@NotNull Plugin plugin, @NotNull String id, @NotNull Class<? extends ServerboundPayload> payloadClass) {
        register(plugin, new NamespacedKey(plugin, id), payloadClass);
    }

    public static void register(@NotNull Plugin plugin, @NotNull NamespacedKey key, @NotNull Class<? extends ServerboundPayload> payloadClass) {
        Bukkit.getMessenger().registerIncomingPluginChannel(
                plugin,
                key.toString(),
                NetworkLibListener.getInstance()
        );

        REGISTRY.put(key, payloadClass);
    }

    public static Class<? extends ServerboundPayload> getPayload(@NotNull Plugin plugin, @NotNull String id) {
        return getPayload(new NamespacedKey(plugin, id));
    }

    public static Class<? extends ServerboundPayload> getPayload(@NotNull NamespacedKey key) {
        return REGISTRY.get(key);
    }
}
