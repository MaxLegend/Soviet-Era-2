package ru.tesmio.utils;

import net.minecraft.resources.IResourceManager;
import net.minecraftforge.resource.IResourceType;
import net.minecraftforge.resource.ISelectiveResourceReloadListener;
import ru.tesmio.reg.RegRenderTypes;

import java.util.function.Predicate;

public class AssetPreLoader implements ISelectiveResourceReloadListener {

    public static final AssetPreLoader INSTANCE = new AssetPreLoader();
    private boolean initialized = false;
    private AssetPreLoader() {}

    @Override
    public void onResourceManagerReload(IResourceManager resourceManager, Predicate<IResourceType> resourcePredicate) {
            RegRenderTypes.init();
            initialized = true;

    }


}