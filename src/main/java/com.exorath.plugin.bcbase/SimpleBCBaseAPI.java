package com.exorath.plugin.bcbase;

import com.exorath.plugin.bcbase.serverId.ServerIdProvider;

/**
 * Created by toonsev on 2/27/2017.
 */
public class SimpleBCBaseAPI implements BCBaseAPI {
    private ServerIdProvider serverIdProvider;
    public SimpleBCBaseAPI(ServerIdProvider serverIdProvider) {
        this.serverIdProvider = serverIdProvider;
    }

    @Override
    public String getServerId() {
        return serverIdProvider.getServerId();
    }
}
