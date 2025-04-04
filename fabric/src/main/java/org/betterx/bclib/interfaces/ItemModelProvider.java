package org.betterx.bclib.interfaces;

import org.betterx.bclib.client.models.ModelsHelper;

import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.resources.ResourceLocation;



public interface ItemModelProvider {
    
    default BlockModel getItemModel(ResourceLocation resourceLocation) {
        return ModelsHelper.createItemModel(resourceLocation);
    }
}