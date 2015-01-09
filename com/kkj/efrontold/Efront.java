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
package com.kkj.efrontold;

import com.kkj.efront.Console;
import com.kkj.efront.Game;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

/**
 *
 * @author Kenny
 */
public class Efront extends Application 
{
    BorderPane border = new BorderPane();
    private int selectedGame = 0;
    private int selectedConsole = 0;
    private ArrayList<Console> systems = new ArrayList();
    @Override
    public void start(Stage primaryStage) 
    {
        border.setLeft(leftPane());
        border.setRight(rightPane());
        midPane();
        Scene scene = new Scene(border, 400, 300);
        scene.getStylesheets().add(Efront.class.getResource("Efront.css").toExternalForm());
        primaryStage.setTitle("EFront");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        launch(args);
    }
    
    private VBox leftPane()
    {
        VBox left = new VBox();
        left.setPadding(new Insets(5,5,5,5));
        left.setSpacing(5);
        ArrayList<Button> temp = new ArrayList<Button>();
        if(systems.size() == 0) systems = loadTempSystems();
        //loadConfig();
        for (Console sys : systems)
        {
            temp.add(new Button(sys.toString()));
        }
        final ArrayList<Button> buttons = temp;
        if(selectedConsole == 0) buttons.get(0).setId("focused");
        for(Button b : buttons)
        {
            b.setMaxWidth(Double.MAX_VALUE);
            b.setOnAction(new EventHandler<ActionEvent>() 
            {
                @Override public void handle(ActionEvent e) 
                {
                    buttons.get(selectedConsole).setId(null);
                    ((Button)e.getSource()).setId("focused");
                    selectedConsole = buttons.indexOf(e.getSource());
                    selectedGame = 0;
                    midPane();
                }
            });
        }
        left.getChildren().addAll(buttons);
        return left;
    }
    
    private VBox rightPane()
    {
        VBox right = new VBox();
        right.setPadding(new Insets(5,5,5,5));
        right.setSpacing(5);
        ArrayList<Button> buttons = new ArrayList<Button>();
        buttons.add(new Button("Play"));
        buttons.add(new Button("Add game"));
        buttons.add(new Button("Remove game"));
        buttons.add(new Button("Add system"));
        buttons.add(new Button("Remove system"));
        buttons.add(new Button("Save"));
        buttons.add(new Button("Load"));
        for(Button b : buttons) b.setMaxWidth(Double.MAX_VALUE);
        buttons.get(0).setOnAction(new EventHandler<ActionEvent>() 
            {
                @Override public void handle(ActionEvent e) 
                {
                    systems.get(selectedConsole).runGame(selectedGame);
                }
            });
        buttons.get(1).setOnAction(new EventHandler<ActionEvent>() 
            {
                @Override public void handle(ActionEvent e) 
                {
                    final Stage dialog = new Stage();
                    dialog.setTitle("Add a new game");
                    dialog.initModality(Modality.APPLICATION_MODAL);
                    VBox dialogVbox = new VBox(20);
                    final TextField name = new TextField("Name");
                    final TextField path = new TextField("Full file path");
                    Button b = new Button("OK");
                    b.setOnAction(new EventHandler<ActionEvent>() 
                    {
                        @Override public void handle(ActionEvent e) 
                        {
                            systems.get(selectedConsole).addGame(new Game(name.getCharacters().toString(),path.getCharacters().toString()));
                            System.out.print(name.getCharacters().toString() + path.getCharacters().toString());
                            midPane();
                            dialog.close();
                        }
                    });
                dialogVbox.getChildren().add(name);
                dialogVbox.getChildren().add(path);
                dialogVbox.getChildren().add(b);
                Scene dialogScene = new Scene(dialogVbox, 300, 200);
                dialog.setScene(dialogScene);
                dialog.show();
                }
            });
        buttons.get(2).setOnAction(new EventHandler<ActionEvent>() 
            {
                @Override public void handle(ActionEvent e) 
                {
                    systems.get(selectedConsole).removeGame(selectedGame);
                    midPane();
                }
            });
        buttons.get(3).setOnAction(new EventHandler<ActionEvent>()
            {
                @Override public void handle(ActionEvent e)
                {
                    final Stage dialog = new Stage();
                    dialog.setTitle("Add a new game");
                    dialog.initModality(Modality.APPLICATION_MODAL);
                    VBox dialogVbox = new VBox(20);
                    final TextField name = new TextField("Name");
                    final TextField path = new TextField("Full file path");
                    Button b = new Button("OK");
                    b.setOnAction(new EventHandler<ActionEvent>() 
                    {
                        @Override public void handle(ActionEvent e) 
                        {
                            systems.add(new Console(name.getCharacters().toString(),path.getCharacters().toString()));
                            System.out.print(name.getCharacters().toString() + path.getCharacters().toString());
                            border.setLeft(leftPane());
                            dialog.close();
                        }
                    });
                dialogVbox.getChildren().add(name);
                dialogVbox.getChildren().add(path);
                dialogVbox.getChildren().add(b);
                Scene dialogScene = new Scene(dialogVbox, 300, 200);
                dialog.setScene(dialogScene);
                dialog.show();
                }
            });
        buttons.get(4).setOnAction(new EventHandler<ActionEvent>()
            {
                @Override public void handle(ActionEvent e)
                {
                    System.out.print(selectedConsole);
                    System.out.print(systems.get(selectedConsole));
                    systems.remove(selectedConsole);
                    border.setLeft(leftPane());
                    midPane();
                }
            
            });
        right.getChildren().addAll(buttons);
        return right;
    }
    private ArrayList<Console> loadTempSystems()
    {
        ArrayList<Console> systems = new ArrayList();
        systems.add(new Console("Steam","cmd -c \"start steam://run/"));
        systems.add(new Console("Gamecube","C:\\Program Files\\Dolphin\\Dolphin.exe -b -e "));
        systems.get(0).addGame(new Game("Team Fortress 2","440\""));
        systems.get(0).addGame(new Game("Sid Meier's Civilization V","36075"));
        systems.get(0).addGame(new Game("Dust: An Elysian Tail","236090"));
        systems.get(1).addGame(new Game("Project M","C:\\Users\\Kenny\\Downloads\\PM\\ProjectM.iso"));
        systems.get(1).addGame(new Game("Brawl","C:\\Users\\Kenny\\Downloads\\PM\\RSBE01.iso"));
        return systems;
    }
    private void midPane()
    {
        VBox mid = new VBox();
        mid.setPadding(new Insets(5,5,5,5));
        mid.setSpacing(5);
        ArrayList<Button> temp = new ArrayList();
        final ArrayList<Game> games = systems.get(selectedConsole).getGames();
        for(Game game : games)
        {
            temp.add(new Button(game.getName()));
        }
        final ArrayList<Button> buttons = temp;
        buttons.get(selectedGame).setId("focused");
        for(Button button : buttons)
        {
            button.setMaxWidth(Double.MAX_VALUE);
            button.setOnAction(new EventHandler<ActionEvent>() 
            {
                @Override public void handle(ActionEvent e) 
                {
                    buttons.get(selectedGame).setId(null);
                    ((Button)e.getSource()).setId("focused");
                    selectedGame = buttons.indexOf(e.getSource());
                }
            });
        }
        mid.getChildren().addAll(buttons);
        border.setCenter(mid);
    }
    private void loadConfig()
    {
        Config config = new Config("efront.cfg");
    }
}