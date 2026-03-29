package com.hyperdondon.networklib.payload;

import com.hyperdondon.networklib.listener.NetworkLibListener;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;

public final class ClientboundPayloadRegistry {
    public static final HashMap<NamespacedKey, Class<? extends ClientboundPayload>> REGISTRY = new HashMap<>();

    public static void register(Plugin plugin, String id, Class<? extends ClientboundPayload> payloadClass) {
        register(plugin, new NamespacedKey(plugin, id), payloadClass);
    }

    public static void register(Plugin plugin, NamespacedKey key, Class<? extends ClientboundPayload> payloadClass) {
        Bukkit.getMessenger().registerOutgoingPluginChannel(
                plugin,
                key.toString()
        );

        REGISTRY.put(key, payloadClass);
    }

    public static Class<? extends ClientboundPayload> getPayload(Plugin plugin, String id) {
        return getPayload(new NamespacedKey(plugin, id));
    }

    public static Class<? extends ClientboundPayload> getPayload(NamespacedKey key) {
        return REGISTRY.get(key);
    }
}
