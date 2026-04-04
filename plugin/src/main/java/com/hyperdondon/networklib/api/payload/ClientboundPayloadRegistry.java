package com.hyperdondon.networklib.api.payload;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.Plugin;

public final class ClientboundPayloadRegistry {
    public static void register(Plugin plugin, String id) {
        register(plugin, new NamespacedKey(plugin, id));
    }

    public static void register(Plugin plugin, NamespacedKey key) {
        Bukkit.getMessenger().registerOutgoingPluginChannel(
                plugin,
                key.toString()
        );
    }
}
