package org.betterx.bclib.api.v3.tag;


import org.betterx.bclib.BCLibFabric;
import org.betterx.wover.tag.api.TagManager;

import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

import org.jetbrains.annotations.ApiStatus;

public class BCLBlockTags {
    public static final TagKey<Block> BONEMEAL_SOURCE_NETHERRACK = TagManager.BLOCKS.makeTag(BCLibFabric.C, "bonemeal/source/netherrack");
    public static final TagKey<Block> BONEMEAL_TARGET_NETHERRACK = TagManager.BLOCKS.makeTag(
            BCLibFabric.C,
            "bonemeal/target/netherrack"
    );
    public static final TagKey<Block> BONEMEAL_SOURCE_END_STONE = TagManager.BLOCKS.makeTag(
            BCLibFabric.C,
            "bonemeal/source/end_stone"
    );
    public static final TagKey<Block> BONEMEAL_TARGET_END_STONE = TagManager.BLOCKS.makeTag(
            BCLibFabric.C,
            "bonemeal/target/end_stone"
    );
    public static final TagKey<Block> BONEMEAL_SOURCE_OBSIDIAN = TagManager.BLOCKS.makeTag(
            BCLibFabric.C,
            "bonemeal/source/obsidian"
    );
    public static final TagKey<Block> BONEMEAL_TARGET_OBSIDIAN = TagManager.BLOCKS.makeTag(
            BCLibFabric.C,
            "bonemeal/target/obsidian"
    );

    @ApiStatus.Internal
    public static void ensureStaticallyLoaded() {

    }
}
