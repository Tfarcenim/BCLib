package org.betterx.wover.mixin;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import org.betterx.wover.biome.impl.modification.ChunkGeneratorHelper;
import org.betterx.wover.common.generator.api.chunkgenerator.RebuildableFeaturesPerStep;

import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;

import org.betterx.wover.common.surface.api.InjectableSurfaceRules;
import org.betterx.wover.surface.impl.SurfaceRuleUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(NoiseBasedChunkGenerator.class)
public abstract class NoiseBasedChunkGeneratorMixin implements RebuildableFeaturesPerStep<NoiseBasedChunkGenerator>, InjectableSurfaceRules<NoiseBasedChunkGenerator> {
    // This is needed by BiomeModificationImpl to synchronize the state of available features between the biomes and the
    // chunk generator. If this is not executed, added features that were previously not available in any biome known
    // to the chunk generator will result in an Index -1 out of bounds exception in applyBiomeDecoration
    @Override
    public void wover_rebuildFeaturesPerStep() {
        final ChunkGenerator g = (ChunkGenerator) (Object) this;
        ChunkGeneratorHelper.rebuildFeaturesPerStep(g, g.getBiomeSource());
    }

    @Shadow
    public abstract Holder<NoiseGeneratorSettings> generatorSettings();

    public void wover_injectSurfaceRules(Registry<LevelStem> dimensionRegistry, ResourceKey<LevelStem> dimensionKey) {
        ChunkGenerator self = (ChunkGenerator) (Object) this;

        SurfaceRuleUtil.injectNoiseBasedSurfaceRules(
                dimensionKey,
                generatorSettings(),
                self.getBiomeSource()
        );
    }

}
