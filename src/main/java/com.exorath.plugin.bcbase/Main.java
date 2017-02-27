package com.exorath.plugin.bcbase;

import com.exorath.plugin.bcbase.serverId.ServerIdProvider;
import com.exorath.plugin.bcbase.serverId.ServerUUIDProvider;
import net.md_5.bungee.api.plugin.Plugin;

/**
 * Created by toonsev on 2/27/2017.
 */
public class Main extends Plugin {
    private static Main instance;
    private static BCBaseAPI baseAPI;
    private ServerIdProvider serverIdProvider;

    @Override
    public void onEnable() {
        Main.instance = this;
        this.serverIdProvider = new ServerUUIDProvider();
    }


    private static Main getInstance() {
        return instance;
    }

    public static synchronized BCBaseAPI getAPI() {
        if (Main.baseAPI == null) {
            BCBaseAPI baseAPI = new SimpleBCBaseAPI(
                    Main.getInstance().serverIdProvider
            );
            Main.baseAPI = baseAPI;
        }
        return Main.baseAPI;
    }
}
