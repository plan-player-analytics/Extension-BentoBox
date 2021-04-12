package com.djrapitops.extension;

import com.djrapitops.plan.extension.ElementOrder;
import com.djrapitops.plan.extension.NotReadyException;
import com.djrapitops.plan.extension.annotation.DataBuilderProvider;
import com.djrapitops.plan.extension.annotation.PluginInfo;
import com.djrapitops.plan.extension.annotation.TabInfo;
import com.djrapitops.plan.extension.builder.ExtensionDataBuilder;
import com.djrapitops.plan.extension.icon.Color;
import com.djrapitops.plan.extension.icon.Family;
import com.djrapitops.plan.extension.icon.Icon;
import world.bentobox.bentobox.api.addons.request.AddonRequestBuilder;

import java.util.Map;
import java.util.UUID;

/**
 * DataExtension for AOneBlock.
 *
 * @author AuroraLS3
 */
@PluginInfo(name = "AOneBlock", iconName = "cube", iconFamily = Family.SOLID, color = Color.GREEN)
@TabInfo(tab = "AOneBlock", iconName = "cube", elementOrder = {ElementOrder.VALUES})
public class AOneBlockExtension extends BentoBoxExtension {

    public AOneBlockExtension() {
        super("AOneBlock");
    }

    AOneBlockExtension(boolean forTesting) {
        super();
    }

    @DataBuilderProvider
    public ExtensionDataBuilder gameModeStatistics(UUID playerUUID) {
        Object response = new AddonRequestBuilder()
                .addon("AOneBlock")
                .label("island-stats")
                .addMetaData("player", playerUUID)
                .request();
        if (response instanceof Map) {
            Map<String, String> stats = (Map<String, String>) response;
            return newExtensionDataBuilder()
                    .addValue(String.class, valueBuilder("Block count")
                            .priority(100)
                            .showOnTab("AOneBlock")
                            .icon(Icon.called("cube").of(Color.GREEN).build())
                            .buildString(stats.get("count")))
                    .addValue(String.class, valueBuilder("Done")
                            .priority(90)
                            .showOnTab("AOneBlock")
                            .icon(Icon.called("percentage").of(Color.GREEN).build())
                            .buildString(stats.get("percentDone")))
                    .addValue(String.class, valueBuilder("Current phase")
                            .priority(80)
                            .showOnTab("AOneBlock")
                            .icon(Icon.called("box-open").of(Color.GREEN).build())
                            .buildString(stats.get("phase")))
                    .addValue(String.class, valueBuilder("Next phase")
                            .priority(70)
                            .showOnTab("AOneBlock")
                            .icon(Icon.called("box").of(Color.GREEN).build())
                            .buildString(stats.get("nextPhase")));
        }
        throw new NotReadyException();
    }

}