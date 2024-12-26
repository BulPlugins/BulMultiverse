package com.alihaine.bulmultiverse;

@FunctionalInterface
interface AddonAction {
    void execute(BulMultiverseAddon addon) throws Exception;
}
