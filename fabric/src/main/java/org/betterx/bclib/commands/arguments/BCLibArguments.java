package org.betterx.bclib.commands.arguments;

import org.betterx.bclib.BCLibFabric;

import net.minecraft.commands.synchronization.SingletonArgumentInfo;

import net.fabricmc.fabric.api.command.v2.ArgumentTypeRegistry;

public class BCLibArguments {
    public static void register() {
        ArgumentTypeRegistry.registerArgumentType(
                BCLibFabric.makeID("template_placement"),
                TemplatePlacementArgument.class,
                SingletonArgumentInfo.contextFree(TemplatePlacementArgument::templatePlacement)
        );

        ArgumentTypeRegistry.registerArgumentType(
                BCLibFabric.makeID("float3"),
                Float3ArgumentType.class,
                new Float3ArgumentInfo()
        );

        ArgumentTypeRegistry.registerArgumentType(
                BCLibFabric.makeID("connector"),
                ConnectorArgument.class,
                SingletonArgumentInfo.contextFree(ConnectorArgument::id)
        );
    }
}
