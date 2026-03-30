package com.hyperdondon.networklib;

import lombok.Getter;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class NetworkLib extends JavaPlugin {
    @Getter
    private static NetworkLib instance;

    @Override
    public void onLoad() {
        instance = this;
    }
}