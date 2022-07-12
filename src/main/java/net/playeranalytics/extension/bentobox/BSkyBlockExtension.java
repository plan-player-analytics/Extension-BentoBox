package net.playeranalytics.extension.bentobox;

import com.djrapitops.plan.extension.annotation.PluginInfo;
import com.djrapitops.plan.extension.icon.Color;
import com.djrapitops.plan.extension.icon.Family;

/**
 * DataExtension for BSkyBlock.
 *
 * @author AuroraLS3
 */
@PluginInfo(name = "BSkyBlock", iconName = "street-view", iconFamily = Family.SOLID, color = Color.GREEN)
public class BSkyBlockExtension extends BentoBoxExtension {

    public BSkyBlockExtension() {
        super("BSkyBlock");
    }

    BSkyBlockExtension(boolean forTesting) {
        super();
    }
}