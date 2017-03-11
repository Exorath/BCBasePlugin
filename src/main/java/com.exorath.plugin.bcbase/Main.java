package com.exorath.plugin.bcbase;

import com.exorath.plugin.bcbase.serverId.ServerIdProvider;
import com.exorath.plugin.bcbase.serverId.ServerUUIDProvider;
import com.exorath.plugin.bcbase.serverInfoProvider.ConnectorAPIServerInfoProvider;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;
import net.md_5.bungee.event.EventHandler;

import java.io.File;
import java.io.IOException;

/**
 * Created by toonsev on 2/27/2017.
 */
public class Main extends Plugin implements Listener {
    private static Main instance;
    private static BCBaseAPI baseAPI;
    private ServerIdProvider serverIdProvider;

    private Configuration configuration;

    @Override
    public void onEnable() {
        Main.instance = this;
        this.serverIdProvider = new ServerUUIDProvider();
        System.out.println("BCBasePlugin: The uuid of this server is " + serverIdProvider.getServerId());
        getProxy().getPluginManager().registerListener(this, this);

        try {
            configuration = loadConfig();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
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

    private Configuration loadConfig() throws IOException {
        File config = new File(getDataFolder(), "config.yml");
        if (!config.exists())
            config.createNewFile();
        return ConfigurationProvider.getProvider(YamlConfiguration.class).load(config);

    }

    private String getLobbyGameId() {
        if(!configuration.contains("lobby.gameId")){
            System.out.println("No lobby.gameId key found in config, exiting.");
            System.exit(1);
        }
        return configuration.getString("lobby.gameId");
    }

    private String getLobbyFlavorId() {
        if(!configuration.contains("lobby.flavorId"))
            return null;
        return configuration.getString("lobby.flavorId");
    }

    private String getConnectorServiceAddress() {
        String address = System.getenv("CONNECTOR_SERVICE_ADDRESS");
        if(address == null){
            System.out.println("No CONNECTOR_SERVICE_ADDRESS environment value set");
            System.exit(1);
        }
        return address;
    }
}
