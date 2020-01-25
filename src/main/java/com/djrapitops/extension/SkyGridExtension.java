package com.djrapitops.extension;

import com.djrapitops.plan.extension.annotation.PluginInfo;
import com.djrapitops.plan.extension.icon.Color;
import com.djrapitops.plan.extension.icon.Family;

/**
 * DataExtension for SkyGrid.
 *
 * @author Rsl1122
 */
@PluginInfo(name = "SkyGrid", iconName = "cube", iconFamily = Family.SOLID, color = Color.GREEN)
public class SkyGridExtension extends BentoBoxExtension {

    public SkyGridExtension() {
        super("SkyGrid");
    }

    SkyGridExtension(boolean forTesting) {
        super();
    }
}