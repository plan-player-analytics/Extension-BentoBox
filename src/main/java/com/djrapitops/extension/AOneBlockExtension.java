package com.djrapitops.extension;

import com.djrapitops.plan.extension.annotation.PluginInfo;
import com.djrapitops.plan.extension.icon.Color;
import com.djrapitops.plan.extension.icon.Family;

/**
 * DataExtension for AOneBlock.
 *
 * @author AuroraLS3
 */
@PluginInfo(name = "AOneBlock", iconName = "cube", iconFamily = Family.SOLID, color = Color.GREEN)
public class AOneBlockExtension extends BentoBoxExtension {

    public AOneBlockExtension() {
        super("AOneBlock");
    }

    AOneBlockExtension(boolean forTesting) {
        super();
    }
}