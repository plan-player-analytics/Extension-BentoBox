package com.djrapitops.extension;

import com.djrapitops.plan.extension.annotation.PluginInfo;
import com.djrapitops.plan.extension.icon.Color;
import com.djrapitops.plan.extension.icon.Family;

/**
 * DataExtension for AcidIsland.
 *
 * @author Rsl1122
 */
@PluginInfo(name = "AcidIsland", iconName = "umbrella-beach", iconFamily = Family.SOLID, color = Color.GREEN)
public class AcidIslandExtension extends BentoBoxExtension {

    public AcidIslandExtension() {
        super("AcidIsland");
    }

    AcidIslandExtension(boolean forTesting) {
        super();
    }
}