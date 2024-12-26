package com.alihaine.bulmultiverse.addon;

@FunctionalInterface
public interface AddonAction {
    void execute(BulMultiverseAddon addon) throws Exception;
}
