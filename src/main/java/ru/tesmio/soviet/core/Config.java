package ru.tesmio.soviet.core;

import net.minecraftforge.common.ForgeConfigSpec;

public final class Config {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Boolean> canSpawnInAllBiomes;
    public static final ForgeConfigSpec.ConfigValue<Boolean> canDisassembleDeviceHand;
    public static final ForgeConfigSpec.ConfigValue<Integer> heightGenLabs;
    public static final ForgeConfigSpec.ConfigValue<Integer> freqGenLabs;

    public static final ForgeConfigSpec.ConfigValue<Integer> chanceGen_concertHall;
    public static final ForgeConfigSpec.ConfigValue<Integer> chanceGen_diesel_gen;
    public static final ForgeConfigSpec.ConfigValue<Integer> chanceGen_unk;
    public static final ForgeConfigSpec.ConfigValue<Integer> chanceGen_gateway;
    public static final ForgeConfigSpec.ConfigValue<Integer> chanceGen_clean_room;
    public static final ForgeConfigSpec.ConfigValue<Integer> chanceGen_research;
    public static final ForgeConfigSpec.ConfigValue<Integer> chanceGen_research2;

    static {
        BUILDER.push("Config Soviet Era 2");

        canDisassembleDeviceHand = BUILDER.comment("Can the player dismantle the devices with a right mouse click.").define("Can? Boolean Value", true);
        heightGenLabs = BUILDER.comment("Sets the height of the generation of procedural laboratories. The closer to 0, the higher.").define("Heights gen Labs. Integer Value", -170);
        freqGenLabs = BUILDER.comment("The frequency of laboratory generation in the world. The higher the value, the less frequent the generation. ").defineInRange("Frequency generation. Integer Value", 1, 1, 10);
        canSpawnInAllBiomes = BUILDER.comment("will underground labs be generated in all biome?.").define("Can spawn? Boolean Value", false);

        chanceGen_concertHall = BUILDER.comment("Frequency of fragment generation - concert hall. The higher the value, the less frequent the generation. 1 - always generate").defineInRange("Concert Hall. Integer Value", 1, 1, 50);
        chanceGen_diesel_gen = BUILDER.comment("Frequency of fragment generation - diesel generator room. The higher the value, the less frequent the generation. 1 - always generate").defineInRange("Diesel Generator Room. Integer Value", 1, 1, 50);
        chanceGen_unk = BUILDER.comment("Frequency of fragment generation - linear particle accelerator. The higher the value, the less frequent the generation. 1 - always generate").defineInRange("Linear Accelerator. Integer Value", 1, 1, 50);
        chanceGen_gateway = BUILDER.comment("Frequency of fragment generation - gateway linear particle accelerator. Recommended use with chanceGen_unk. The higher the value, the less frequent the generation. 0 - always generate").defineInRange("Gateway accelerator. Integer Value", 1, 1, 50);
        chanceGen_clean_room = BUILDER.comment("Frequency of fragment generation - clean room - laboratory. The higher the value, the less frequent the generation. 1 - always generate").defineInRange("Clean Room. Integer Value", 1, 1, 50);
        chanceGen_research = BUILDER.comment("Frequency of fragment generation - phito-laboratory. The higher the value, the less frequent the generation. 1 - always generate").defineInRange("Phito-laboratory. Integer Value", 1, 1, 50);
        chanceGen_research2 = BUILDER.comment("Frequency of fragment generation - control room. The higher the value, the less frequent the generation. 1 - always generate").defineInRange("Control Room. Integer Value", 1, 1, 50);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
