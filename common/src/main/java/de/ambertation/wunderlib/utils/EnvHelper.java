package de.ambertation.wunderlib.utils;

import org.betterx.bclib.platform.Services;

public class EnvHelper {
    public static boolean isClient() {
        return Services.PLATFORM.isClient();
    }
}
