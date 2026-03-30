package com.hyperdondon.networklib.api.payload;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public abstract class ClientboundPayload {
    /**
     * Writes the data used to send to a player.
     * @param data A {@link ByteBuf} that contains the data that the client sent.
     */
    public abstract void write(@NotNull ByteBuf data);

    /**
     * Sends a payload to a player.
     * The namespace is made with the provided plugin and channel key.
     * @param player The player to send to.
     * @param channelKey The key for the namespace.
     * @param plugin The plugin sending this message.
     */
    public void send(@NotNull Player player, @NotNull String channelKey, @NotNull Plugin plugin) {
        send(player, new NamespacedKey(plugin, channelKey), plugin);
    }

    /**
     * Sends a payload to a player.
     * The namespace is made with the provided plugin and id.
     * @param player The player to send to.
     * @param channel The channel used to send the payload through.
     * @param plugin The plugin sending this message.
     */
    public void send(@NotNull Player player, @NotNull NamespacedKey channel, @NotNull Plugin plugin) {
        ByteBuf buf = Unpooled.buffer();
        write(buf);
        byte[] data = new byte[buf.readableBytes()];
        buf.readBytes(data);
        player.sendPluginMessage(plugin, channel.toString(), data);
    }
}
