package ru.tesmio.mixins;

import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ModelBuilder;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.nio.file.Path;

//@Mixin(ModelProvider.class)
public abstract class MixinModelProvider<T extends ModelBuilder<T>> {
    protected DataGenerator generator;


 //   @Inject(method = "getPath", at = @At("HEAD"), cancellable = true, remap = false)
    private void getPath(T model, CallbackInfoReturnable<Path> cb) {
        ResourceLocation loc = model.getLocation();
        System.out.println("MIXINS!");
        cb.setReturnValue(generator.getOutputFolder().resolve("assets/" + loc.getNamespace() + "/models/" + "/block/" + loc.getPath() + ".json"));

    }
}
