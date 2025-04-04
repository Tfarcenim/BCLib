package org.betterx.wover;

import de.ambertation.wunderlib.network.SendToClientImpl;
import de.ambertation.wunderlib.network.SendToServerImpl;
import org.betterx.wover.config.api.client.ClientConfigs;
import org.betterx.wover.events.api.client.ClientWorldLifecycle;
import org.betterx.wover.ui.impl.client.VersionCheckerClient;

public class WoverClientFabric {
    public static void onClientInitialize() {
        SendToServerImpl.registerAdapter();
        SendToClientImpl.registerAdapter();

        ClientWorldLifecycle.ALLOW_EXPERIMENTAL_WARNING_SCREEN.subscribe((show) -> {
            if (ClientConfigs.CLIENT.disableExperimentalWarning.get()) {
                return false;
            }
            return show;
        });

        ClientWorldLifecycle.ENUMERATE_STARTUP_SCREENS.subscribe(VersionCheckerClient::presentUpdateScreen);

        ClientConfigs.saveConfigs();
    }
}
