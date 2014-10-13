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
import java.io.IOException;

/**
 *
 * @author Kenny
 */
public class Console 
{
    private String bin;
    private String name;
    private ArrayList<Game> games;
<<<<<<< HEAD
    private String finalArgs;
=======
>>>>>>> 904f9c24ada0f15c28f774571cbec0e80b08b538
    
    public Console(String name, String bin)
    {
        this.bin = bin;
        this.name = name;
        this.games = new ArrayList<Game>();
<<<<<<< HEAD
        this.finalArgs = "";
    }
    
    public Console(String name, String bin, String arg)
    {
       this.bin = bin;
        this.name = name;
        this.games = new ArrayList<Game>();
        this.finalArgs = arg;
=======
>>>>>>> 904f9c24ada0f15c28f774571cbec0e80b08b538
    }
    
    public ArrayList<Game> getGames()
    {
        return games;
    }
    
    public boolean addGame(Game newGame)
    {
        if(!games.contains(newGame))
            {
                games.add(newGame);
                return true;
            }
        else return false;
    }
    
<<<<<<< HEAD
    public boolean removeGame(int game)
    {
        if(games.size() < game - 1)
=======
    public boolean removeGame(Game game)
    {
        if(!games.contains(game))
>>>>>>> 904f9c24ada0f15c28f774571cbec0e80b08b538
        {
            return false;
        }
        else
        {
<<<<<<< HEAD
            games.remove(game);
=======
            games.remove(games.indexOf(game));
>>>>>>> 904f9c24ada0f15c28f774571cbec0e80b08b538
            return true;
        }
    }
    
    public String toString()
    {
        return name;
    }
    
    public void runGame(int game)
    {
        try
        {
<<<<<<< HEAD
            Process process = Runtime.getRuntime().exec(bin + games.get(game).getPath() + finalArgs);
=======
            Process process = Runtime.getRuntime().exec(bin + games.get(game).getPath());
>>>>>>> 904f9c24ada0f15c28f774571cbec0e80b08b538
            System.out.print(bin + games.get(game).getPath());
        }
        catch(IOException e)
        {
            System.out.println("Either your emulator or your game doesn't exist.");
        }
    }
}