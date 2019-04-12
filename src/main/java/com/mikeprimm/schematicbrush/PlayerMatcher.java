package com.mikeprimm.schematicbrush;

import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.WorldVector;
import com.sk89q.worldedit.entity.BaseEntity;
import com.sk89q.worldedit.entity.Player;
import com.sk89q.worldedit.extension.platform.AbstractPlayerActor;
import com.sk89q.worldedit.extent.inventory.BlockBag;
import com.sk89q.worldedit.session.SessionKey;
import com.sk89q.worldedit.util.Location;
import com.sk89q.worldedit.world.World;
import java.util.Optional;
import java.util.UUID;
import javax.annotation.Nullable;

/**
 * Used to retrieve the (WE)Platform's implementation of the (WE)Player type
 * Assumes that Platform.matchPlayer(..) relies on either the name (String) or id (UUID) of the player
 * Any other method call will result in an UnsupportedOperationException
 */
public class PlayerMatcher extends AbstractPlayerActor {

    private final String name;
    private final UUID id;

    private PlayerMatcher(String name, UUID id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public UUID getUniqueId() {
        return id;
    }

    @Override
    public World getWorld() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getItemInHand() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void giveItem(int i, int i1) {
        throw new UnsupportedOperationException();
    }

    @Override
    public BlockBag getInventoryBlockBag() {
        throw new UnsupportedOperationException();
    }

    @Override
    public WorldVector getPosition() {
        throw new UnsupportedOperationException();
    }

    @Override
    public double getPitch() {
        throw new UnsupportedOperationException();
    }

    @Override
    public double getYaw() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setPosition(Vector vector, float v, float v1) {
        throw new UnsupportedOperationException();
    }

    @Nullable
    @Override
    public BaseEntity getState() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Location getLocation() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void printRaw(String s) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void printDebug(String s) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void print(String s) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void printError(String s) {
        throw new UnsupportedOperationException();
    }

    @Override
    public SessionKey getSessionKey() {
        throw new UnsupportedOperationException();
    }

    @Nullable
    @Override
    public <T> T getFacet(Class<? extends T> aClass) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String[] getGroups() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean hasPermission(String s) {
        throw new UnsupportedOperationException();
    }

    /**
     * Retrieve a WorldEdit Player instance for the given name and/or uuid
     * @param name The name of the Player
     * @param uuid The uuid of the Player
     * @return The WorldEdit Player or empty if not found
     */
    public static Optional<Player> find(String name, UUID uuid) {
        PlayerMatcher matcher = new PlayerMatcher(name, uuid);
        Player result = WorldEdit.getInstance().getServer().matchPlayer(matcher);
        // If null or our PlayerMatcher instance is returned then assume that the Player was not found
        if (result == null || result == matcher) {
            return Optional.empty();
        }
        return Optional.of(result);
    }

    /**
     * Retrieve a WorldEdit Player instance for the given Sponge Player
     * @param player The Sponge Player to look up
     * @return The WorldEdit Player or empty if not found
     */
    public static Optional<Player> find(org.spongepowered.api.entity.living.player.Player player) {
        return PlayerMatcher.find(player.getName(), player.getUniqueId());
    }
}
