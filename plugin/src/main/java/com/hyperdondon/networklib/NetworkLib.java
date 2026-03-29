package com.hyperdondon.networklib;

import com.hyperdondon.networklib.payload.ClientboundPayload;
import com.hyperdondon.networklib.payload.ClientboundPayloadRegistry;
import com.hyperdondon.networklib.payload.ServerboundPayloadRegistry;
import com.hyperdondon.networklib.test.TestCommand;
import com.hyperdondon.networklib.test.TestServerboundPayload;
import lombok.Getter;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class NetworkLib extends JavaPlugin {
    @Getter
    private static NetworkLib instance;

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {


        //ServerboundPayloadRegistry.register(this, "test_payload", TestServerboundPayload.class);
        //ClientboundPayloadRegistry.register(this, "test_clientbound_payload", ClientboundPayload.class);

        //Bukkit.getServer().getCommandMap().register("networklib", new TestCommand());
    }

    @Override
    public void onDisable() {
        Bukkit.getMessenger().unregisterIncomingPluginChannel(instance);
        Bukkit.getMessenger().unregisterOutgoingPluginChannel(instance);
        instance = null;
    }
}