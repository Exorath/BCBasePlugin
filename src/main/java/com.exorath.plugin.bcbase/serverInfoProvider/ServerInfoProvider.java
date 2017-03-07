package com.exorath.plugin.bcbase.serverInfoProvider;

import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;

/**
 * Created by toonsev on 3/7/2017.
 */
public interface ServerInfoProvider {
    ServerInfo getServerInfo(ProxiedPlayer player);
}
