package ru.tesmio.utils.structure_saver;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.block.Blocks;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.ResourceLocationException;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.server.ServerWorld;
import ru.tesmio.reg.RegCommands;

public class CommandStrucSave {
    public CommandStrucSave(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(
                Commands.literal("save").then(Commands.argument("name", StringArgumentType.word()).executes((command) ->
                {
                    String itx = StringArgumentType.getString(command, "name");
                    save(command.getSource(), itx);
                    return 1;
                })));
    }
    public void save(CommandSource source, String name) throws CommandSyntaxException {

        ResourceLocation location = new ResourceLocation("soviet:" + name);
        ServerPlayerEntity pl = source.asPlayer();
        ServerWorld w = source.getWorld();
        TemplateManager tm = w.getStructureTemplateManager();
        Template t = tm.getTemplateDefaulted(location);

        if (RegCommands.pos1 == null || RegCommands.pos2 == null) {
            pl.sendMessage(new StringTextComponent("Points is null"), pl.getUniqueID());
        }
        int
                x1 = RegCommands.getMin(RegCommands.pos1.getX(), RegCommands.pos2.getX()),
                y1 = RegCommands.getMin(RegCommands.pos1.getY(), RegCommands.pos2.getY()),
                z1 = RegCommands.getMin(RegCommands.pos1.getZ(), RegCommands.pos2.getZ()),
                x2 = RegCommands.getMax(RegCommands.pos1.getX(), RegCommands.pos2.getX()),
                y2 = RegCommands.getMax(RegCommands.pos1.getY(), RegCommands.pos2.getY()),
                z2 = RegCommands.getMax(RegCommands.pos1.getZ(), RegCommands.pos2.getZ());

        assert t != null;
        t.takeBlocksFromWorld(w, new BlockPos(x1, y1, z1), new BlockPos(x2 - x1 + 1, y2 - y1 + 1, z2 - z1 + 1), false, Blocks.STRUCTURE_VOID);
            t.setAuthor("Tesmio");
            ITextComponent itx1 = new StringTextComponent("Size structure: " + tm.getTemplate(location).getSize());

            pl.sendMessage(itx1, pl.getUniqueID());

            try {
                ITextComponent itx2 = new StringTextComponent("Save in: run/saves/<world_save>/generated/soviet/structures/" + name + ".nbt");
                tm.writeToFile(location);
                pl.sendMessage(itx2, pl.getUniqueID());
                RegCommands.pos1 = RegCommands.pos2 = null;
            } catch (ResourceLocationException resourcelocationexception) {}

    }
}
