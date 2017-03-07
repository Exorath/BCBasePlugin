package com.exorath.plugin.bcbase;

import com.exorath.plugin.bcbase.serverId.ServerIdProvider;
import com.exorath.plugin.bcbase.serverId.ServerUUIDProvider;
import com.exorath.plugin.bcbase.serverInfoProvider.ConnectorAPIServerInfoProvider;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;

/**
 * Created by toonsev on 2/27/2017.
 */
public class Main extends Plugin implements Listener {
    private static Main instance;
    private static BCBaseAPI baseAPI;
    private ServerIdProvider serverIdProvider;

    @Override
    public void onEnable() {
        Main.instance = this;
        this.serverIdProvider = new ServerUUIDProvider();
        System.out.println("BCBasePlugin: The uuid of this server is " + serverIdProvider.getServerId());
        getProxy().getPluginManager().registerListener(this, this);
    }

    @EventHandler
    public void onPostLogin(PostLoginEvent event) {
        if (baseAPI != null)
            baseAPI.onPostLogin(event.getPlayer());
    }

    private static Main getInstance() {
        return instance;
    }

    public static synchronized BCBaseAPI getAPI() {
        if (Main.baseAPI == null) {
            BCBaseAPI baseAPI = new SimpleBCBaseAPI(
                    new ConnectorAPIServerInfoProvider(getInstance().getLobbyGameId(), getInstance().getLobbyFlavorId(), getInstance().getConnectorServiceAddress()),
                    Main.getInstance().serverIdProvider
            );
            Main.baseAPI = baseAPI;
        }
        return Main.baseAPI;
    }

    private  String getLobbyGameId(){

    }

    private  String getLobbyFlavorId(){

    }

    private String getConnectorServiceAddress(){

    }
}
