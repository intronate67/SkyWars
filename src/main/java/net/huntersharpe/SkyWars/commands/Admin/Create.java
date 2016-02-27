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
package net.huntersharpe.SkyWars.commands.Admin;

import net.huntersharpe.SkyWars.MessageHandler;
import net.huntersharpe.SkyWars.arena.ArenaManager;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

public class Create implements CommandExecutor{

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        ArenaManager am = new ArenaManager();
        if(!(src instanceof Player)){
            MessageHandler.getManager().wrongSourceError(src);
            return CommandResult.success();
        }
        Player p = (Player)src;
        if(args.hasAny("size")){
            int size = Integer.parseInt(args.getOne("size").get().toString());
            if(args.getOne("choice").get().equals("default")){
                //TODO: Create default map with custom size.
                am.createArena(p.getLocation(), size, "default", p);
                return CommandResult.success();
            }
            am.createArena(p.getLocation(), size, "custom", p);
            return CommandResult.success();
        }
        if(args.getOne("choice").get().equals("default")){
            //TODO: Create default map with default size.
            am.createArena(p.getLocation(), 8, "default", p);
            return CommandResult.success();
        }
        am.createArena(p.getLocation(), 8, "custom", p);
        return CommandResult.success();
    }
}
