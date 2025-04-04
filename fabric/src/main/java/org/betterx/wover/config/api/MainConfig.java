package org.betterx.wover.config.api;

import de.ambertation.wunderlib.configs.ConfigFile;
import org.betterx.wover.WoverFabric;

public class MainConfig extends ConfigFile {
    public final static Group GENERAL_GROUP = new Group(WoverFabric.C_CORE.namespace, "general", 0);
    public final static Group WORLD_LOADING = new Group(WoverFabric.C_CORE.namespace, "loading", 800);
    public final static Group SERVER_GROUP = new Group(WoverFabric.C_CORE.namespace, "server", 1000);
    public final static Group STRUCTURE_GROUP = new Group(WoverFabric.C_CORE.namespace, "structure", 2000);
    public final static Group ENTITY_GROUP = new Group(WoverFabric.C_CORE.namespace, "entity", 2500);
    public final static Group PERFORMANCE_GROUP = new Group(WoverFabric.C_CORE.namespace, "performance", 3000);
    public final static Group COSMETIC_GROUP = new Group(WoverFabric.C_CORE.namespace, "cosmetic", 4000);
    public final static Group UI_GROUP = new Group(WoverFabric.C_CORE.namespace, "ui", 4300);
    public final static Group RENDERING_GROUP = new Group(WoverFabric.C_CORE.namespace, "rendering", 4600);


    public final static String LOG_CATEGORY = "log";
    public final static String SERVER_CATEGORY = SERVER_GROUP.title();

    public final BooleanValue verboseLogging = new BooleanValue(
            LOG_CATEGORY,
            "verbose",
            true
    ).setGroup(GENERAL_GROUP);

    public final BooleanValue forceDefaultWorldPresetOnServer = new BooleanValue(
            SERVER_CATEGORY,
            "force_default_world_preset",
            true
    ).setGroup(SERVER_GROUP);

    public MainConfig() {
        super(WoverFabric.C_CORE, "main");
    }
}
