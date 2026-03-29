package com.hyperdondon.networklib.payload;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public abstract class ClientboundPayload {
    public abstract void write(ByteBuf data);

    public void send(Player player, String id, Plugin plugin) {
        send(player, new NamespacedKey(plugin, id), plugin);
    }

    public void send(Player player, NamespacedKey channel, Plugin plugin) {
        ByteBuf buf = Unpooled.buffer();
        write(buf);
        byte[] data = new byte[buf.readableBytes()];
        buf.readBytes(data);
        player.sendPluginMessage(plugin, channel.toString(), data);
    }
}
