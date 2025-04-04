package org.betterx.wover;

import de.ambertation.wunderlib.network.SendToClientImpl;
import de.ambertation.wunderlib.network.SendToServerImpl;
import org.betterx.wover.biome.impl.BiomeManagerImpl;
import org.betterx.wover.biome.impl.data.BiomeCodecRegistryImpl;
import org.betterx.wover.biome.impl.modification.BiomeModificationRegistryImpl;
import org.betterx.wover.biome.impl.modification.predicates.BiomePredicateRegistryImpl;
import org.betterx.wover.block.impl.predicate.BlockPredicatesImpl;
import org.betterx.wover.config.api.Configs;
import org.betterx.wover.core.api.ModCore;
import org.betterx.wover.datagen.api.WoverDataGenEntryPoint;
import org.betterx.wover.datagen.api.provider.AutoBlockLootProvider;
import org.betterx.wover.datagen.api.provider.AutoBlockRegistryTagProvider;
import org.betterx.wover.datagen.impl.AutoBiomeTagProvider;
import org.betterx.wover.datagen.impl.AutoBlockTagProvider;
import org.betterx.wover.datagen.impl.AutoItemTagProvider;
import org.betterx.wover.events.api.WorldLifecycle;
import org.betterx.wover.feature.impl.FeatureManagerImpl;
import org.betterx.wover.feature.impl.placed.modifiers.PlacementModifiersImpl;
import org.betterx.wover.item.impl.AutoItemRegistryTagProvider;
import org.betterx.wover.poi.impl.PoiManagerImpl;
import org.betterx.wover.state.impl.WorldConfigImpl;
import org.betterx.wover.state.impl.WorldDatapackConfigImpl;
import org.betterx.wover.state.impl.WorldStateImpl;
import org.betterx.wover.structure.impl.StructureManagerImpl;
import org.betterx.wover.structure.impl.pools.StructurePoolElementTypeManagerImpl;
import org.betterx.wover.surface.impl.SurfaceRuleRegistryImpl;
import org.betterx.wover.surface.impl.conditions.MaterialConditionRegistryImpl;
import org.betterx.wover.surface.impl.numeric.NumericProviderRegistryImpl;
import org.betterx.wover.surface.impl.rules.MaterialRuleRegistryImpl;
import org.betterx.wover.tag.api.predefined.*;
import org.betterx.wover.tag.impl.TagBootstrapContextImpl;

import static org.betterx.wover.events.impl.AbstractEvent.SYSTEM_PRIORITY;

public class WoverFabric {

    public static final ModCore C_BIOME = ModCore.create("wover-biome", "wover");
    public static final ModCore C_BLOCK = ModCore.create("wover-block", "wover");
    public static final ModCore C_CORE = ModCore.create("wover-core", "wover");
    public static final ModCore C_DATAGEN = ModCore.create("wover-datagen", "wover");
    public static final ModCore C_EVENTS = ModCore.create("wover-events", "wover");
    public static final ModCore C_FEATURE = ModCore.create("wover-feature", "wover");
    public static final ModCore C_ITEM = ModCore.create("wover-item", "wover");
    public static final ModCore C_MATH = ModCore.create("wover-math", "wover");
    public static final ModCore C_RECIPE = ModCore.create("wover-recipe", "wover");
    public static final ModCore C_STRUCTURE = ModCore.create("wover-structure", "wover");
    public static final ModCore C_SURFACE = ModCore.create("wover-surface", "wover");
    public static final ModCore C_TAG = ModCore.create("wover-tag", "wover");
    public static final ModCore C_UI = ModCore.create("wover-ui", "wover");

    public static void onInitialize() {
        BiomeManagerImpl.initialize();
        BiomeCodecRegistryImpl.initialize();
        //BiomeDataRegistryImpl.initialize(); //done in the wover.datapack.registry entrypoint
        BiomePredicateRegistryImpl.initialize();
        BiomeModificationRegistryImpl.initialize();

        //make sure the Datagen will automatically include all Tags assigned to Blocks in the BlockRegistry
        WoverDataGenEntryPoint.registerAutoProvider(AutoBlockRegistryTagProvider::new);
        WoverDataGenEntryPoint.registerAutoProvider(AutoBlockLootProvider::new);

        BlockPredicatesImpl.ensureStaticInitialization();
        PoiManagerImpl.registerAll();

        Configs.saveConfigs();

        WorldConfigImpl.initialize();
        WorldDatapackConfigImpl.initialize();
        WorldStateImpl.ensureStaticallyLoaded();

        PlacementModifiersImpl.ensureStaticInitialization();
        FeatureManagerImpl.ensureStaticInitialization();
        //FeatureConfiguratorImpl.initialize(); //done in the wover.datapack.registry entrypoint
        //PlacedFeatureManagerImpl.initialize(); //done in the wover.datapack.registry entrypoint

        //EnchantmentManagerImpl.initialize(); //done in the wover.datapack.registry entrypoint
        WoverDataGenEntryPoint.registerAutoProvider(AutoItemRegistryTagProvider::new);

        StructurePoolElementTypeManagerImpl.ensureStaticallyLoaded();
        //StructurePoolManagerImpl.initialize(); //done in the wover.datapack.registry entrypoint
        StructureManagerImpl.initialize();
        //StructureSetManagerImpl.initialize(); //done in the wover.datapack.registry entrypoint

        NumericProviderRegistryImpl.bootstrap();
        MaterialConditionRegistryImpl.bootstrap();
        MaterialRuleRegistryImpl.bootstrap();
        SurfaceRuleRegistryImpl.initialize();

        WoverDataGenEntryPoint.registerAutoProvider(AutoBlockTagProvider::new);
        WoverDataGenEntryPoint.registerAutoProvider(AutoItemTagProvider::new);
        WoverDataGenEntryPoint.registerAutoProvider(AutoBiomeTagProvider::new);

        CommonBiomeTags.ensureStaticallyLoaded();
        CommonBlockTags.ensureStaticallyLoaded();
        CommonItemTags.ensureStaticallyLoaded();
        CommonPoiTags.ensureStaticallyLoaded();

        MineableTags.ensureStaticallyLoaded();
        ToolTags.ensureStaticallyLoaded();

        WorldLifecycle
                .BEFORE_LOADING_RESOURCES
                .subscribe((resourceManager, featureFlagSet) -> TagBootstrapContextImpl.invalidateCaches(), SYSTEM_PRIORITY);

    }

}
