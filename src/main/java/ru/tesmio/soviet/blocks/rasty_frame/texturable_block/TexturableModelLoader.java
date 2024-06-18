package ru.tesmio.soviet.blocks.rasty_frame.texturable_block;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import net.minecraft.resources.IResourceManager;
import net.minecraftforge.client.model.IModelLoader;

public class TexturableModelLoader implements IModelLoader<TexturableModelGeometry> {
    @Override
    public void onResourceManagerReload(IResourceManager resourceManager) {
    }

    @Override
    public TexturableModelGeometry read(JsonDeserializationContext deserializationContext, JsonObject modelContents) {
        return new TexturableModelGeometry();
    }
}
