package com.djrapitops.extension;

import com.djrapitops.plan.extension.annotation.PluginInfo;
import com.djrapitops.plan.extension.icon.Color;
import com.djrapitops.plan.extension.icon.Family;

/**
 * DataExtension for CaveBlock.
 *
 * @author AuroraLS3
 */
@PluginInfo(name = "CaveBlock", iconName = "ghost", iconFamily = Family.SOLID, color = Color.GREEN)
public class CaveBlockExtension extends BentoBoxExtension {

    public CaveBlockExtension() {
        super("CaveBlock");
    }

    CaveBlockExtension(boolean forTesting) {
        super();
    }
}