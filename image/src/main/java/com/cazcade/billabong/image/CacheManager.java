package com.cazcade.billabong.image;

import java.net.URI;

/**
 * Defines the basic responsibility of a component managing the cache.
 */
public interface CacheManager {

    public void generateCacheRequest(String storeKey, URI uri, int delay, String waitForStatus);

    public void generateImageCacheRequest(String storeKey, URI uri);
}
