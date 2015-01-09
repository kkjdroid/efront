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

import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 *
 * @author Kenny
 */
public class Main extends Application
{
    @Override
    public void start(Stage s) 
    {
        EfrontController e = new EfrontController();
        ArrayList<Console> systems = new ArrayList();
        systems.add(new Console("Steam","cmd -c \"start steam://run/","\""));
        systems.add(new Console("Gamecube","C:\\Program Files\\Dolphin\\Dolphin.exe -b -e "));
        systems.get(0).addGame(new Game("Team Fortress 2","440"));
        systems.get(0).addGame(new Game("Sid Meier's Civilization V","36075"));
        systems.get(0).addGame(new Game("Dust: An Elysian Tail","236090"));
        systems.get(1).addGame(new Game("Project M","C:\\Users\\Kenny\\Downloads\\PM\\ProjectM.iso"));
        systems.get(1).addGame(new Game("Brawl","C:\\Users\\Kenny\\Downloads\\PM\\RSBE01.iso"));
        e.setConsoles(systems);
        s.setScene(new Scene(e, 800, 500));
        s.setTitle("EFront");
        s.show();
    }
    public static void main(String[] args)
    {
        launch(args);
    }
}