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
package net.babelsoft.negatron.model.component;

import javafx.beans.property.Property;

/**
 *
 * @author capan
 */
public interface MachineComponent<T, P extends Property<T>> {
    
    String getName();
    
    String getPreviousName();
    
    void setValue(T value);
    
    P valueProperty();
    
    default boolean isMandatory() {
        return false;
    }
    
    <S extends MachineComponent<T, P>> boolean canReplace(S otherComponent);
}
