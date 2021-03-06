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
package net.babelsoft.negatron.view.control.form;

import java.util.Arrays;
import java.util.List;
import javafx.scene.layout.GridPane;
import net.babelsoft.negatron.io.configuration.Configuration;
import net.babelsoft.negatron.theme.Language;

/**
 *
 * @author capan
 */
public class ValueChoiceField<T extends Enum> extends ChoiceField<T> {
    
    public ValueChoiceField(GridPane grid, int row, String key, T[] values) {
        super(
            grid, row, 
            Language.Manager.getString("globalConf." + key),
            Language.Manager.tryGetString("globalConf." + key + ".tooltip")
        );
        
        List<T> list = Arrays.asList(values);
        choiceBox.getItems().addAll(list);
        
        String init = Configuration.Manager.getGlobalConfiguration(key);
        list.stream().filter(
            constant -> init.equals(constant.toString())
        ).findAny().ifPresent(
            constant -> choiceBox.getSelectionModel().select(constant)
        );
        
        choiceBox.getSelectionModel().selectedItemProperty().addListener((o, oV, newValue) -> {
            updateGlobalConfigurationSetting(key, newValue.toString());
        });
    }
}
