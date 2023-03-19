package ru.tesmio.reg;

import com.google.common.collect.ImmutableMap;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import ru.tesmio.core.Core;
import ru.tesmio.world.structure.labs.UndergroundLabsStructure;

import java.util.HashMap;
import java.util.Map;

public class RegStructures {
    public static final DeferredRegister<Structure<?>> STRUCTURES =
            DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, Core.MODID);

    public static final RegistryObject<Structure<NoFeatureConfig>> PROCEDURAL_LABORATORY = STRUCTURES.register("proc_lab", UndergroundLabsStructure::new);

    /* среднее расстояние между блоками между попытками возрождения */
    /* минимальное расстояние между блоками между попытками возрождения. ДОЛЖНО БЫТЬ МЕНЬШЕ УКАЗАННОГО ВЫШЕ ЗНАЧЕНИЯ*/
/* это изменяет начальное значение структуры, так что никакие две структуры не всегда появляются друг над другом.
Сделайте это большим и уникальным. */
    public static void setupStructures() {
        setupMapSpacingAndLand(PROCEDURAL_LABORATORY.get(), new StructureSeparationSettings(15,5, 1234567890),
                true);
    }
    /*
    Добавляет предоставленную структуру в реестр и добавляет настройки разделения.
     * Редкость структуры определяется на основе значений, переданных в этот метод в аргументе structureSeparationSettings.
     * Этот метод вызывается setupStructures выше.
     */
    public static <F extends Structure<?>> void setupMapSpacingAndLand(F structure, StructureSeparationSettings structureSeparationSettings, boolean transformSurroundingLand) {
        //добавьте наши структуры на карту в классе Structure
        Structure.NAME_STRUCTURE_BIMAP.put(structure.getRegistryName().toString(), structure);

        /*
        Будет ли окружающая земля автоматически изменена в соответствии с нижней частью конструкции.
        По сути, это добавляет землю к основанию сооружения, как это делается для деревень и аванпостов.
        Плохо работает на конструкциях, элементы которых расположены вертикально или изменяются по высоте.
         */
//        if (transformSurroundingLand) {
//            Structure.field_236384_t_ = ImmutableList.<Structure<?>>builder()
//                    .addAll(Structure.field_236384_t_)
//                    .add(structure)
//                    .build();
//        }
        /*
            Это карта, на которой указаны расстояния между всеми структурами по умолчанию.
            Всегда добавляйте сюда свою структуру, чтобы другие моды могли использовать ее при необходимости.
            Однако, хотя он действительно распространяет интервал на некоторые правильные размеры из этой карты,
            кажется, что это не всегда работает для размеров, созданных в коде, поскольку они предварительно считываются из этого списка.

            Вместо этого мы будем использовать WorldEvent.Загрузите событие в Mod World Events, чтобы добавить структурный интервал из этого
             списка в это измерение или правильно внести измерение в черный список.
            Мы также используем нашу запись в настройках размерных структур.ЗНАЧЕНИЯ по умолчанию в WorldEvent.Загружайте также.
            ПО УМОЛЧАНИЮ требуется AccessTransformer (см. ресурсы/META-INF/accesstransformer.cfg)
         */
        DimensionStructuresSettings.field_236191_b_ =
                ImmutableMap.<Structure<?>, StructureSeparationSettings>builder()
                        .putAll(DimensionStructuresSettings.field_236191_b_)
                        .put(structure, structureSeparationSettings)
                        .build();

        /*
            Существует очень мало модов, которые полагаются на просмотр вашей структуры в реестре настроек шума до создания мира.
            Вы можете увидеть, что некоторые моды добавляют свои интервалы в настройки размеров.ВСТРОЕННЫЙ IN_OVERWORLD вместо приведенного
             ниже цикла NOISE_GENERATOR_SETTINGS, но это поле применяется только к overworld по умолчанию и не будет добавляться к типам или
             измерениям otherworld (например, amplified или Nether).
             Так что да, не делайте настройки размеров.ПОСТРОЕН В_OVERWORLD. Вместо этого, если необходимо, используйте цикл NOISE_GENERATOR_SETTINGS, приведенный ниже.
         */
        WorldGenRegistries.NOISE_SETTINGS.getEntries().forEach(settings -> {
            Map<Structure<?>, StructureSeparationSettings> structureMap =
                    settings.getValue().getStructures().func_236195_a_();
            /*
               Мера предосторожности на случай, если мод сделает карту структуры неизменяемой, как это делают пакеты данных.
               Я сам не рискую. Вы никогда не знаете, что делает другой модник...
               для конфигурации структуры требуется AccessTransformer (см. ресурсы/META-INF/accesstransformer.cfg)
             */
            if (structureMap instanceof ImmutableMap) {
                Map<Structure<?>, StructureSeparationSettings> tempMap = new HashMap<>(structureMap);
                tempMap.put(structure, structureSeparationSettings);
                settings.getValue().getStructures().func_236195_a_();

            } else {
                structureMap.put(structure, structureSeparationSettings);
            }
        });
    }

    public static void register(IEventBus eventBus) {
        STRUCTURES.register(eventBus);
    }
    }
