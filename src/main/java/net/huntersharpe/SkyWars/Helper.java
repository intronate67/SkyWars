/*
 * Copyright (c) 2016 Hunter Sharpe
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of 
 * this software and associated documentation files (the "Software"), to deal in 
 * the Software without restriction, including without limitation the rights to use, 
 * copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the 
 * Software, and to permit persons to whom the Software is furnished to do so, 
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all 
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION 
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE 
 * SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package net.huntersharpe.SkyWars;

import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Helper {

    public Map<String, Text> helpInfo = new HashMap<>();

    public static Helper instance = new Helper();

    public static Helper getInstance(){
        return instance;
    }

    public Text[] commandList = new Text[]{
            Text.of("help"),
            Text.of("join"),
            Text.of("sw"),
            Text.of("skywars"),
            Text.of("leave"),
            Text.of("list"),
            Text.of("create"),
            Text.of("delete"),
            Text.of("setspawn"),
            Text.of("start"),
            Text.of("quit"),
            Text.of("stats"),
            Text.of("info")
    };

    public Text getHelp(Text command){
        if(Arrays.asList(commandList).contains(command)){
            return helpInfo.get(command.toString());
        }
        return Text.of("Command: " + command + " not found!");
    }

    public void putHelpInfo(){
        helpInfo.put("help", Text.of(
                TextColors.DARK_GRAY,
                "--------",
                TextColors.BLUE, "Help Info",
                TextColors.DARK_GRAY,
                "--------\n",
                TextColors.GRAY,
                "Command:",
                TextColors.AQUA,
                " /sw help\n",
                TextColors.GRAY,
                "Usage:",
                TextColors.AQUA,
                " /sw help <command>\n",
                TextColors.GRAY,
                "Description: ",
                TextColors.AQUA,
                "Displays help info about a certain command. Arguments in <> are required, arguments in [] are optional."
        ));
        helpInfo.put("join", Text.of(
                TextColors.DARK_GRAY,
                "--------",
                TextColors.BLUE, "Help Info",
                TextColors.DARK_GRAY,
                "--------\n",
                TextColors.GRAY,
                "Command:",
                TextColors.AQUA,
                " /sw join\n",
                TextColors.GRAY,
                "Usage:",
                TextColors.AQUA,
                " /sw join [id]\n",
                TextColors.GRAY,
                "Description: ",
                TextColors.AQUA,
                "Used to join the fullest or specific arena using /sw list."
        ));
        helpInfo.put("leave", Text.of(
                TextColors.DARK_GRAY,
                "--------",
                TextColors.BLUE, "Help Info",
                TextColors.DARK_GRAY,
                "--------\n",
                TextColors.GRAY,
                "Command:",
                TextColors.AQUA,
                " /sw leave\n",
                TextColors.GRAY,
                "Usage:",
                TextColors.AQUA,
                " /sw leave\n",
                TextColors.GRAY,
                "Description: ",
                TextColors.AQUA,
                "Used to leave your current arena."
        ));
        helpInfo.put("list", Text.of(
                TextColors.DARK_GRAY,
                "--------",
                TextColors.BLUE, "Help Info",
                TextColors.DARK_GRAY,
                "--------\n",
                TextColors.GRAY,
                "Command:",
                TextColors.AQUA,
                " /sw list\n",
                TextColors.GRAY,
                "Usage:",
                TextColors.AQUA,
                " /sw list\n",
                TextColors.GRAY,
                "Description: ",
                TextColors.AQUA,
                "Lists all open arenas."
        ));
        helpInfo.put("create", Text.of(
                TextColors.DARK_GRAY,
                "--------",
                TextColors.BLUE, "Help Info",
                TextColors.DARK_GRAY,
                "--------\n",
                TextColors.GRAY,
                "Command:",
                TextColors.AQUA,
                " /sw create\n",
                TextColors.GRAY,
                "Usage:",
                TextColors.AQUA,
                " /sw create custom|default [size]\n",
                TextColors.GRAY,
                "Description: ",
                TextColors.AQUA,
                "With /sw create default it creates the default skywars arena with default 8 players and default map.\n " +
                        "/sw create custom [size] creates new arena with main island at your position with either default (10) amount of players or custom amount."
        ));
        helpInfo.put("setspawn", Text.of(
                TextColors.DARK_GRAY,
                "--------",
                TextColors.BLUE, "Help Info",
                TextColors.DARK_GRAY,
                "--------\n",
                TextColors.GRAY,
                "Command:",
                TextColors.AQUA,
                " /sw setspawn\n",
                TextColors.GRAY,
                "Usage:",
                TextColors.AQUA,
                " /sw setspawn <id>\n",
                TextColors.GRAY,
                "Description: ",
                TextColors.AQUA,
                "Sets a spawn location for an island for a specific arena."
        ));
        helpInfo.put("delete", Text.of(
                TextColors.DARK_GRAY,
                "--------",
                TextColors.BLUE, "Help Info",
                TextColors.DARK_GRAY,
                "--------\n",
                TextColors.GRAY,
                "Command:",
                TextColors.AQUA,
                " /sw delete\n",
                TextColors.GRAY,
                "Usage:",
                TextColors.AQUA,
                " /sw delete <id>\n",
                TextColors.GRAY,
                "Description: ",
                TextColors.AQUA,
                "Deletes a specified arena."
        ));
        helpInfo.put("start", Text.of(
                TextColors.DARK_GRAY,
                "--------",
                TextColors.BLUE, "Help Info",
                TextColors.DARK_GRAY,
                "--------\n",
                TextColors.GRAY,
                "Command:",
                TextColors.AQUA,
                " /sw start\n",
                TextColors.GRAY,
                "Usage:",
                TextColors.AQUA,
                " /sw start <id>\n",
                TextColors.GRAY,
                "Description: ",
                TextColors.AQUA,
                "Force starts a specified arena."
        ));
        helpInfo.put("quit", Text.of(
                TextColors.DARK_GRAY,
                "--------",
                TextColors.BLUE, "Help Info",
                TextColors.DARK_GRAY,
                "--------\n",
                TextColors.GRAY,
                "Command:",
                TextColors.AQUA,
                " /sw quit\n",
                TextColors.GRAY,
                "Usage:",
                TextColors.AQUA,
                " /sw quit <id>\n",
                TextColors.GRAY,
                "Description: ",
                TextColors.AQUA,
                "Force ends a specified arena without a winner."
        ));
        helpInfo.put("stats", Text.of(
                TextColors.DARK_GRAY,
                "--------",
                TextColors.BLUE, "Help Info",
                TextColors.DARK_GRAY,
                "--------\n",
                TextColors.GRAY,
                "Command:",
                TextColors.AQUA,
                " /sw stats\n",
                TextColors.GRAY,
                "Usage:",
                TextColors.AQUA,
                " /sw stats\n",
                TextColors.GRAY,
                "Description: ",
                TextColors.AQUA,
                "View inf about your current arena."
        ));
        helpInfo.put("info", Text.of(
                TextColors.DARK_GRAY,
                "--------",
                TextColors.BLUE, "Help Info",
                TextColors.DARK_GRAY,
                "--------\n",
                TextColors.GRAY,
                "Command:",
                TextColors.AQUA,
                " /sw info\n",
                TextColors.GRAY,
                "Usage:",
                TextColors.AQUA,
                " /sw <id> size|name|delspawn <size|name|id>\n",
                TextColors.GRAY,
                "Description: ",
                TextColors.AQUA,
                "View and edit information about a specified arena. View wiki for more information."
        ));
    }

}
