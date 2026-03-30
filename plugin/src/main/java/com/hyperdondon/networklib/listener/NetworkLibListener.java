package com.hyperdondon.networklib.listener;

import com.hyperdondon.networklib.api.payload.ServerboundPayload;
import com.hyperdondon.networklib.api.payload.ServerboundPayloadRegistry;
import io.netty.buffer.Unpooled;
import lombok.Getter;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.jetbrains.annotations.NotNull;

public class NetworkLibListener implements PluginMessageListener {

    @Getter
    private static NetworkLibListener instance = new NetworkLibListener();

    @Override
    public void onPluginMessageReceived(@NotNull String channel, @NotNull Player player, byte @NotNull [] message) {
        NamespacedKey key;
        try {
            key = NamespacedKey.fromString(channel);
        } catch (Exception e) {
            //throw new RuntimeException("Unable to parse packet id. Invalid request.", e);

            // In case some cheaters do some weird stuff
            return;
        }

        ServerboundPayload payload;
        try {
            payload = ServerboundPayloadRegistry.getPayload(key).getConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        payload.read(Unpooled.wrappedBuffer(message));
    }
}
