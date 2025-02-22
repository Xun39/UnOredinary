package xun.unoredinary.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import xun.unoredinary.UnOredinary;
import xun.unoredinary.data.generator.*;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = UnOredinary.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {

        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> registries = event.getLookupProvider();
        ExistingFileHelper helper = event.getExistingFileHelper();
        boolean client = event.includeClient();
        boolean server = event.includeServer();

        BlockTagsProvider blockTagsProvider = new ModBlockTags(output, registries, helper);

        generator.addProvider(client, new ModBlockStates(output, helper));
        generator.addProvider(client, new ModItemModels(output, helper));

        generator.addProvider(server, new ModAdvancements(output, registries, helper));

        generator.addProvider(server, new ModLootTables(output, registries));
        generator.addProvider(server, new ModRecipes(output, registries));
        generator.addProvider(server, blockTagsProvider);
        generator.addProvider(server, new ModItemTags(output, registries, blockTagsProvider.contentsGetter(), helper));
        generator.addProvider(server, new ModEntityTypeTags(output, registries, helper));
        generator.addProvider(server, new ModBiomeTags(output, registries, helper));
        generator.addProvider(server, new ModDatapackEntries(output, registries));

    }
}
