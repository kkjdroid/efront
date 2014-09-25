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
package efront;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.FileSystems;
import java.nio.charset.StandardCharsets;
import java.io.IOException;

/**
 *
 * @author Kenny
 */
public class EFront extends Application 
{
    BorderPane border = new BorderPane();
    private int selectedGame = 0;
    private int selectedConsole = 0;
    @Override
    public void start(Stage primaryStage) 
    {
        border.setLeft(leftPane());
        border.setRight(rightPane());
        border.setCenter(new VBox());
        
        Scene scene = new Scene(border, 400, 300);
        
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
        for (Console sys : getSystems())
        {
            temp.add(new Button(sys.toString()));
        }
        final ArrayList<Button> buttons = temp;
        for(Button b : buttons)
        {
            b.setOnAction(new EventHandler<ActionEvent>() 
            {
                @Override public void handle(ActionEvent e) 
                {
                    selectedConsole = buttons.indexOf(e.getSource());
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
        buttons.get(0).setOnAction(new EventHandler<ActionEvent>() 
            {
                @Override public void handle(ActionEvent e) 
                {
                    getSystems().get(selectedConsole).runGame(selectedGame);
                }
            });
        
        right.getChildren().addAll(buttons);
        return right;
    }
    
    private ArrayList<Console> getSystems()
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
        final ArrayList<Game> games = getSystems().get(selectedConsole).getGames();
        for(Game game : games)
        {
            temp.add(new Button(game.getName()));
        }
        final ArrayList<Button> buttons = temp;
        for(Button button : buttons)
        {
            button.setOnAction(new EventHandler<ActionEvent>() 
            {
                @Override public void handle(ActionEvent e) 
                {
                    selectedGame = buttons.indexOf(e.getSource());
                }
            });
        }
        mid.getChildren().addAll(buttons);
        border.setCenter(mid);
    }
    private void loadConfig()
    {
        Path config = FileSystems.getDefault().getPath("efront.ini");
        ArrayList<String> file;
        try
        {
            file = (ArrayList)Files.readAllLines(config,StandardCharsets.UTF_8);
        }
        catch(IOException e)
        {
            try
            {
                config = Files.createFile(config);
            }
            catch(IOException f){}
        }
    }
}