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
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
/**
 *
 * @author Kenny
 */
public class Config 
{
    public Config(String path)
    {
        //get config from path
    }
    public ArrayList getSystems()
    {
        ArrayList<Console> systems = new ArrayList();
        //get systems from config file
        return systems;
    }
    public ArrayList getGames()
    {
        ArrayList<ArrayList<Game>> games = new ArrayList();
        //getSystems.get(n) should be the console that corresponds to the games in getGames().get(n)
        return games;
    }
}
