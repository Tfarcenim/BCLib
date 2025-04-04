package org.betterx.wover.biome.impl.modification;

import org.betterx.wover.WoverFabric;
import org.betterx.wover.mixin.ChunkGeneratorAccessor;

import net.minecraft.core.Holder;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.biome.FeatureSorter;
import net.minecraft.world.level.chunk.ChunkGenerator;

import com.google.common.base.Suppliers;

import java.util.List;
import java.util.function.Function;

public class ChunkGeneratorHelper {
    public static void rebuildFeaturesPerStep(ChunkGenerator generator, BiomeSource biomeSource) {
        if (generator instanceof ChunkGeneratorAccessor acc) {
            Function<Holder<Biome>, BiomeGenerationSettings> function
                    = (Holder<Biome> biomeHolder) -> biomeHolder.value().getGenerationSettings();
            acc.wover_setFeaturesPerStep(Suppliers.memoize(() -> {
                try {
                    return FeatureSorter.buildFeaturesPerStep(
                            List.copyOf(biomeSource.possibleBiomes()),
                            (hh) -> function.apply(hh).features(),
                            true
                    );
                } catch (IllegalStateException e) {
                    var message = e.getMessage();
                    WoverFabric.C_BIOME.LOG.error("Failed to rebuild features per step", e);
                    for (Holder<Biome> biome : biomeSource.possibleBiomes()) {
                        var loc = biome.unwrapKey().orElseThrow().location().toString();
                        if (!message.contains(loc)) continue;
                        var res = biome.value().getGenerationSettings();
                        WoverFabric.C_BIOME.LOG.verbose(loc);
                        int ct = 0;
                        for (var feature : res.features()) {
                            WoverFabric.C_BIOME.LOG.verbose("  -------" + ct + "-------");
                            ct++;
                            for (int i = 0; i < feature.size(); i++) {
                                WoverFabric.C_BIOME.LOG.verbose("    + " + feature
                                        .get(i)
                                        .unwrapKey()
                                        .orElseThrow()
                                        .location()
                                        .toString());
                            }
                        }
                    }
                    throw e;
                }
            }));
        }
    }
}
