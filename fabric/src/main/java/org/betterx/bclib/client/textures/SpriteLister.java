package org.betterx.bclib.client.textures;

import net.minecraft.client.renderer.texture.atlas.sources.DirectoryLister;



public class SpriteLister extends DirectoryLister {
    public SpriteLister(String string) {
        super(string, string + "/");
    }
}
