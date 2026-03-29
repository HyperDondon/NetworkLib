package com.hyperdondon.networklib.payload;

import com.hyperdondon.networklib.listener.NetworkLibListener;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;

public final class ServerboundPayloadRegistry {
    public static final HashMap<NamespacedKey, Class<? extends ServerboundPayload>> REGISTRY = new HashMap<>();

    public static void register(Plugin plugin, String id, Class<? extends ServerboundPayload> payloadClass) {
        register(plugin, new NamespacedKey(plugin, id), payloadClass);
    }

    public static void register(Plugin plugin, NamespacedKey key, Class<? extends ServerboundPayload> payloadClass) {
        Bukkit.getMessenger().registerIncomingPluginChannel(
                plugin,
                key.toString(),
                NetworkLibListener.getInstance()
        );

        REGISTRY.put(key, payloadClass);
    }

    public static Class<? extends ServerboundPayload> getPayload(Plugin plugin, String id) {
        return getPayload(new NamespacedKey(plugin, id));
    }

    public static Class<? extends ServerboundPayload> getPayload(NamespacedKey key) {
        return REGISTRY.get(key);
    }
}
