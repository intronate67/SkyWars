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

import com.google.inject.Inject;
import net.huntersharpe.SkyWars.commands.Admin.*;
import net.huntersharpe.SkyWars.commands.Help;
import net.huntersharpe.SkyWars.commands.Join;
import net.huntersharpe.SkyWars.commands.List;
import net.huntersharpe.SkyWars.commands.SWCommand;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import org.spongepowered.api.Game;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.args.CommandElement;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.config.DefaultConfig;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.text.Text;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Plugin(id="skywars", name="SkyWars", version="1.0")
public class SkyWars {

    @Inject
    @DefaultConfig(sharedRoot = true)
    public File configuration = null;

    @Inject
    @DefaultConfig(sharedRoot = true)
    public ConfigurationLoader<CommentedConfigurationNode> configurationLoader = null;

    public CommentedConfigurationNode configurationNode = null;

    @Inject
    Game game = Sponge.getGame();

    @Listener
    public void onPreInit(GamePreInitializationEvent e){
        //Puts all help info into HashMap
        Helper.getInstance().putHelpInfo();
        registerCommands();
        try{
            if(!configuration.exists()){
                configuration.createNewFile();
                configurationNode = configurationLoader.load();
                //TODO: Set default values below, before catch and before config save
                configurationLoader.save(configurationNode);
            }
            configurationNode = configurationLoader.load();
        }catch (IOException exception){
            exception.printStackTrace();
        }
    }

    private void registerCommands(){
        Map<String, CommandElement> args = new HashMap<>();
        args.put("custom", GenericArguments.optional(
                GenericArguments.none(),
                GenericArguments.onlyOne(GenericArguments.integer(Text.of("size"))
                )));
        args.put("default", GenericArguments.optional(
                GenericArguments.none(),
                GenericArguments.onlyOne(GenericArguments.integer(Text.of("size"))
                )));
        CommandSpec createSpec = CommandSpec.builder()
                .arguments(GenericArguments.choices(Text.of("choice"), args))
                .executor(new Create())
                .build();
        CommandSpec delSpec = CommandSpec.builder()
                .arguments(GenericArguments.onlyOne(GenericArguments.integer(Text.of("id"))))
                .executor(new Delete())
                .build();
        CommandSpec infoSpec = CommandSpec.builder()
                .executor(new Info())
                .build();
        CommandSpec quitSpec = CommandSpec.builder()
                .arguments(GenericArguments.onlyOne(GenericArguments.integer(Text.of("id"))))
                .executor(new Quit())
                .build();
        CommandSpec setSpawnSpec = CommandSpec.builder()
                .arguments(GenericArguments.onlyOne(GenericArguments.integer(Text.of("id"))))
                .executor(new SetSpawn())
                .build();
        CommandSpec startSpec = CommandSpec.builder()
                .arguments(GenericArguments.onlyOne(GenericArguments.integer(Text.of("id"))))
                .executor(new Start())
                .build();
        CommandSpec statsSpec = CommandSpec.builder()
                .arguments(GenericArguments.optional(GenericArguments.none(),
                        GenericArguments.onlyOne(GenericArguments.integer(Text.of("id")))))
                .executor(new Stats())
                .build();
        CommandSpec helpSpec = CommandSpec.builder()
                .arguments(GenericArguments.onlyOne(GenericArguments.string(Text.of("command"))))
                .executor(new Help())
                .build();
        CommandSpec joinSpec = CommandSpec.builder()
                .arguments(GenericArguments.optional(GenericArguments.none(),
                        GenericArguments.onlyOne(GenericArguments.integer(Text.of("id")))))
                .executor(new Join())
                .build();
        CommandSpec listSpec = CommandSpec.builder()
                .arguments(GenericArguments.none())
                .executor(new List())
                .build();
        CommandSpec swSpec = CommandSpec.builder()
                .child(createSpec)
                .child(delSpec)
                .child(infoSpec)
                .child(quitSpec)
                .child(setSpawnSpec)
                .child(startSpec)
                .child(statsSpec)
                .child(helpSpec)
                .child(joinSpec)
                .child(listSpec)
                .executor(new SWCommand())
                .build();
        game.getCommandManager().register(this, swSpec, "sw");
    }

    public CommentedConfigurationNode rootNode(){
        return configurationNode;
    }
}
