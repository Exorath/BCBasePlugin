package com.exorath.plugin.bcbase;

import net.md_5.bungee.api.connection.ProxiedPlayer;

/**
 * Created by toonsev on 2/27/2017.
 */
public interface BCBaseAPI {
    String getServerId();

    static BCBaseAPI getInstance(){
        return Main.getAPI();
    }

    void onPostLogin(ProxiedPlayer player);
}
