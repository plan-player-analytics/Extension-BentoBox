package com.djrapitops.extension;

import com.djrapitops.plan.extension.annotation.PluginInfo;
import com.djrapitops.plan.extension.icon.Color;
import com.djrapitops.plan.extension.icon.Family;

/**
 * DataExtension for BSkyBlock.
 *
 * @author Rsl1122
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