/*
 * Copyright (C) 2014 Kenny
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

package com.kkj.efront;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Kenny
 */
public class EfrontController extends VBox implements Initializable {

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML private Text logo;
    @FXML private BorderPane background;
    @FXML private ListView consoles;
    @FXML private ListView games;
    @FXML private ToolBar bottom;
    @FXML private Button play;
    @FXML private Button settings;
    private ArrayList<Console> systems;
    
    public EfrontController(ArrayList a)
    {
        systems = a;
        FXMLLoader l = new FXMLLoader(getClass().getResource("Efront.fxml"));
        l.setRoot(this);
        l.setController(this);
        try
        {
            l.load();
        }
        catch(IOException e)
        {
            throw new RuntimeException(e);
        }
        initListeners();
    }
    
    public void refreshConsoles()
    {
        ObservableList<String> items = FXCollections.observableArrayList();
        for(Console c : systems)
        {
            items.add(c.toString());
        }
        consoles.setItems(items);
    }
    
    private void refreshGames()
    {
        ObservableList<String> items = FXCollections.observableArrayList();
        for(Game c : systems.get(getConsole()).getGames())
        {
            items.add(c.toString());
        }
        games.setItems(items);
    }
    
    public int getConsole()
    {
        return Math.max(consoles.getSelectionModel().getSelectedIndex(),0);
    }
    
    public int getGame()
    {
        return Math.max(games.getSelectionModel().getSelectedIndex(),0);
    }
    

    private void initListeners()
    {
        refreshConsoles();
        refreshGames();
        consoles.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
            {
                refreshGames();
            }
        });
        play.setOnAction(new EventHandler<ActionEvent>() 
        {
            @Override public void handle(ActionEvent e) 
            {
                systems.get(getConsole()).runGame(getGame());
            }
        });
        settings.setOnAction(new EventHandler<ActionEvent>() 
        {
            @Override public void handle(ActionEvent e) 
            {
                settingsDialog();
            }
        });
    }
    private void settingsDialog()
    {
        SettingsController c = new SettingsController();
        Stage s = new Stage();
        s.setScene(new Scene(c,500,300));
        s.setTitle("EFront Settings");
        s.initModality(Modality.APPLICATION_MODAL);
        s.show();
    }
}
