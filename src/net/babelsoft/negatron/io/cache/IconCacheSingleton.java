/*
 * This file is part of Negatron.
 * Copyright (C) 2015-2018 BabelSoft S.A.S.U.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.babelsoft.negatron.io.cache;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.babelsoft.negatron.io.loader.IconLoader;
import net.babelsoft.negatron.io.loader.InitialisedCallable;
import net.babelsoft.negatron.io.loader.ThreadedCacheLoader;

/**
 *
 * @author capan
 */
public enum IconCacheSingleton implements ThreadedCacheLoader<InitialisedCallable<Void>> {
    Instance;
    
    private IconCache cache;
    
    IconCacheSingleton() {
        try {
            cache = new IconCache();
        } catch (ClassNotFoundException | IOException ex) {
            Logger.getLogger(IconCacheSingleton.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<InitialisedCallable<Void>> threadedLoad() throws ClassNotFoundException, IOException {
        IconLoader loader = new IconLoader(cache);
        List<InitialisedCallable<Void>> list = new ArrayList<>();
        list.add(loader);
        
        return list;
    }
}
