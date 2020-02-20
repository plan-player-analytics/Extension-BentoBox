/*
    Copyright(c) 2019 Risto Lahtela (Rsl1122)

    The MIT License(MIT)

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files(the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and / or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions :
    The above copyright notice and this permission notice shall be included in
    all copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
    THE SOFTWARE.
*/
package com.djrapitops.extension;

import com.djrapitops.plan.extension.*;
import com.djrapitops.plan.extension.annotation.*;
import com.djrapitops.plan.extension.icon.Color;
import com.djrapitops.plan.extension.icon.Family;
import org.bukkit.Bukkit;
import org.bukkit.World;
import world.bentobox.bentobox.BentoBox;
import world.bentobox.bentobox.api.addons.GameModeAddon;
import world.bentobox.bentobox.api.addons.request.AddonRequestBuilder;
import world.bentobox.bentobox.database.objects.Island;
import world.bentobox.bentobox.managers.AddonsManager;
import world.bentobox.bentobox.managers.IslandsManager;
import world.bentobox.bentobox.managers.PlayersManager;

import java.util.UUID;

import static org.bukkit.plugin.java.JavaPlugin.getPlugin;

/**
 * Abstract Extension for BentoBox game modes.
 *
 * @author Rsl1122
 */
@TabInfo(
        tab = "Islands",
        iconName = "street-view",
        elementOrder = {ElementOrder.VALUES}
)

@TabOrder({"Islands"})
public abstract class BentoBoxExtension implements DataExtension {

    private final String addOnName;

    private IslandsManager islands;
    private PlayersManager players;
    private AddonsManager addOns;

    protected BentoBoxExtension() {
        addOnName = null;
    }

    protected BentoBoxExtension(String addOnName) {
        this.addOnName = addOnName;
        prepare();
    }

    private void prepare() {
        if (!Bukkit.getPluginManager().isPluginEnabled("BentoBox")) throw new NotReadyException();
        BentoBox plugin = getPlugin(BentoBox.class);
        islands = plugin.getIslands();
        players = plugin.getPlayers();
        addOns = plugin.getAddonsManager();
        addOns.getAddonByName(addOnName).orElseThrow(NotReadyException::new);
    }

    private World getWorld() {
        return addOns.getAddonByName(addOnName)
                .filter(addOn -> addOn instanceof GameModeAddon)
                .map(addOn -> (GameModeAddon) addOn)
                .map(GameModeAddon::getOverWorld)
                .orElseThrow(NotReadyException::new);
    }

    @Override
    public CallEvents[] callExtensionMethodsOn() {
        return new CallEvents[]{
                CallEvents.PLAYER_JOIN,
                CallEvents.PLAYER_LEAVE,
                CallEvents.SERVER_PERIODICAL
        };
    }

    @BooleanProvider(
            text = "Has Island",
            description = "Does the player have an island",
            priority = 100,
            conditionName = "hasIsland",
            iconName = "street-view",
            iconColor = Color.GREEN
    )
    @Tab("Islands")
    public boolean hasIsland(UUID playerUUID) {
        return islands.hasIsland(getWorld(), playerUUID);
    }

    @Conditional("hasIsland")
    @StringProvider(
            text = "Island name",
            description = "Name of the player's island",
            iconName = "street-view",
            iconColor = Color.GREEN,
            priority = 99,
            showInPlayerTable = true
    )
    @Tab("Islands")
    public String islandName(UUID playerUUID) {
        String name = getIsland(playerUUID).getName();
        return name != null ? name : "-";
    }

    private Island getIsland(UUID playerUUID) {
        Island island = islands.getIsland(getWorld(), playerUUID);
        if (island == null) throw new NotReadyException();
        return island;
    }

    @Conditional("hasIsland")
    @NumberProvider(
            text = "Island level",
            description = "Level of the player's island",
            iconName = "street-view",
            iconColor = Color.AMBER,
            priority = 98
    )
    @Tab("Islands")
    public long islandLevel(UUID playerUUID) {
        World world = getWorld();
        Object level = new AddonRequestBuilder()
                .addon("Levels")
                .label("island-level")
                .addMetaData("world-name", world.getName())
                .addMetaData("player", playerUUID)
                .request();
        if (level instanceof Long) return (Long) level;
        throw new NotReadyException();
    }

    @Conditional("hasIsland")
    @NumberProvider(
            text = "Island Created",
            description = "When the Island was created",
            iconName = "calendar",
            iconFamily = Family.REGULAR,
            iconColor = Color.GREEN,
            priority = 97,
            format = FormatType.DATE_YEAR
    )
    @Tab("Islands")
    public long islandCreated(UUID playerUUID) {
        return getIsland(playerUUID).getCreatedDate();
    }

    @Conditional("hasIsland")
    @NumberProvider(
            text = "Last Changed",
            description = "When the Island was changed last time",
            iconName = "calendar",
            iconFamily = Family.REGULAR,
            iconColor = Color.GREEN,
            priority = 98,
            format = FormatType.DATE_SECOND
    )
    @Tab("Islands")
    public long islandUpdated(UUID playerUUID) {
        return getIsland(playerUUID).getUpdatedDate();
    }

    @Conditional("hasIsland")
    @NumberProvider(
            text = "Island Resets Left",
            description = "How many times can the player reset their island",
            iconName = "street-view",
            iconColor = Color.GREEN,
            priority = 99
    )
    @Tab("Islands")
    public long islandResets(UUID playerUUID) {
        return players.getResetsLeft(getWorld(), playerUUID);
    }

    @StringProvider(
            text = "Island World",
            description = "What world is used for ASkyBlock islands",
            priority = 102,
            iconName = "map",
            iconColor = Color.GREEN,
            iconFamily = Family.REGULAR
    )
    @Tab("Islands")
    public String islandWorld() {
        return getWorld().getName();
    }
}