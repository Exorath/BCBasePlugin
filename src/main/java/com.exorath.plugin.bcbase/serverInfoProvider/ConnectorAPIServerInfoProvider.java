package com.exorath.plugin.bcbase.serverInfoProvider;

import com.exorath.service.connector.api.ConnectorServiceAPI;
import com.exorath.service.connector.res.Filter;
import com.exorath.service.connector.res.Server;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.net.InetSocketAddress;
/**
 * Created by toonsev on 3/7/2017.
 */
public class ConnectorAPIServerInfoProvider implements ServerInfoProvider{
    private Filter filter;
    private ConnectorServiceAPI connectorServiceAPI;

    public ConnectorAPIServerInfoProvider(String gameIdProvider, String flavorIdProvider, String address){
        this.connectorServiceAPI = new ConnectorServiceAPI(address);
        try {
            this.filter = new Filter(gameIdProvider).withFlavorId(flavorIdProvider);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
    @Override
    public ServerInfo getServerInfo(ProxiedPlayer player) {
        Server server = connectorServiceAPI.getJoinableServer(filter, player.getUniqueId().toString());
        if(server == null)
            return null;
        String address = server.getSocket().split(":")[0];
        int port = server.getSocket().contains(":") ? Integer.valueOf(server.getSocket().split(":")[1]) : 25565;
        return ProxyServer.getInstance().constructServerInfo(server.getGameId(), new InetSocketAddress(address, port), "some motd...", false);
    }
}
