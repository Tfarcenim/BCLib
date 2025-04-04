package org.betterx.wover;

import de.ambertation.wunderlib.network.SendToClientImpl;
import de.ambertation.wunderlib.network.SendToServerImpl;

public class WoverClientFabric {
    public static void onClientInitialize() {
        SendToServerImpl.registerAdapter();
        SendToClientImpl.registerAdapter();
    }
}
