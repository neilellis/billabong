/*
 * Copyright 2012 Cazcade Limited
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.cazcade.billabong.store.impl;

import com.cazcade.billabong.common.DateHelper;
import com.cazcade.billabong.common.impl.DefaultDateHelper;
import com.cazcade.billabong.store.BinaryStore;
import com.cazcade.billabong.store.BinaryStoreEntry;
import com.cazcade.billabong.store.BinaryStoreRetrievalResult;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Map based implementation of the Binary Store.
 */
public class MapBasedBinaryStore implements BinaryStore {

    protected Map<String, BinaryStoreEntry> map = new HashMap<String, BinaryStoreEntry>();
    protected DateHelper dateHelper = new DefaultDateHelper();

    @Override
    public BinaryStoreRetrievalResult retrieveFromStore(String storeKey) {
        return new BinaryStoreRetrievalResult(storeKey, map.get(storeKey));
    }

    @Override
    public final boolean placeInStore(String storeKey, InputStream storeEntry, boolean override) {
        if (map.containsKey(storeKey)) {
            if (override) {
                addToMap(storeKey, storeEntry);
                return true;
            }
            return false;
        }
        else {
            addToMap(storeKey, storeEntry);
            return true;
        }
    }

    /**
     * Basic implementation of addToMap that uses a simple InMemoryBinaryStoreEntry. This method can be overridden to
     * allow other entry types to be used.
     *
     * @param storeKey The key to use for adding the entry.
     * @param data     The data to be held in the entry.
     */
    protected void addToMap(String storeKey, InputStream data) {
        try {
            if (data != null) {
                map.put(storeKey, new InMemoryBinaryStoreEntry(dateHelper.current(), IOUtils.toByteArray(data)));
            }
            else {
                map.remove(storeKey);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setMap(Map<String, BinaryStoreEntry> map) {
        this.map = map;
    }

    public void setDateHelper(DateHelper dateHelper) {
        this.dateHelper = dateHelper;
    }
}
