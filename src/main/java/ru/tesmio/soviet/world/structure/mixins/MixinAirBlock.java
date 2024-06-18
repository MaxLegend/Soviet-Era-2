package ru.tesmio.soviet.world.structure.mixins;

//@Mixin(AirBlock.class)
public class MixinAirBlock {
    private float levelLight;

    public float getLightLevel() {
        return this.levelLight;
    }

}
