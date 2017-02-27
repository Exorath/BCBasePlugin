package com.exorath.plugin.bcbase;

/**
 * Created by toonsev on 2/27/2017.
 */
public interface BCBaseAPI {
    String getServerId();

    static ExoBaseAPI getInstance(){
        return Main.getAPI();
    }
}
