package com.exorath.plugin.bcbase;

import com.exorath.plugin.bcbase.serverId.ServerIdProvider;
import com.exorath.plugin.bcbase.serverInfoProvider.ServerInfoProvider;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;

/**
 * Created by toonsev on 2/27/2017.
 */
public class SimpleBCBaseAPI implements BCBaseAPI {
    private ServerInfoProvider serverInfoProvider;
    private ServerIdProvider serverIdProvider;
    public SimpleBCBaseAPI(ServerInfoProvider serverInfoProvider, ServerIdProvider serverIdProvider) {
        this.serverInfoProvider = serverInfoProvider;
        this.serverIdProvider = serverIdProvider;
    }

    @Override
    public String getServerId() {
        return serverIdProvider.getServerId();
    }

    @Override
    public ServerInfo getJoinable(ProxiedPlayer player){//this takes too long
        ServerInfo serverInfo = serverInfoProvider.getServerInfo(player);
        if(serverInfo == null){
            player.disconnect(new TextComponent("No lobby found, we are very sorry!"));
            return null;
        }
        return serverInfo;
    }
}
