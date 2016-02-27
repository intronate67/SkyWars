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
package net.huntersharpe.SkyWars.arena;

import com.flowpowered.math.vector.Vector3d;
import net.huntersharpe.SkyWars.MessageHandler;
import net.huntersharpe.SkyWars.events.ArenaStartingEvent;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.cause.Cause;
import org.spongepowered.api.item.inventory.Inventory;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.extent.Extent;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.*;

public class ArenaManager {

    private static ArenaManager arenaManager;

    private final Map<UUID, Map<Integer, Location>> locs = new HashMap<>();
    private final Map<UUID, Inventory> inv = new HashMap<>();
    private final Map<UUID, Location> prevLoc = new HashMap<>();

    public List<Integer> arenaIds = new ArrayList<>();

    private final List<Arena> arenas = new ArrayList<>();

    public ArenaManager() {}

    public static ArenaManager getManager(){
        if(arenaManager == null)
            arenaManager = new ArenaManager();
        return arenaManager;
    }

    public Arena getArena(int i){
        for(Arena a : this.arenas){
            if(a.getId() == i)
                return a;
        }
        return null;
    }

    public void addPlayer(Player p, int i, Location spawn){
        Arena a = this.getArena(i);
        if(a==null){
            p.sendMessage(Text.of(TextColors.RED, "Invalid Arena!"));
            return;
        }
        if(this.isInGame(p)){
            p.sendMessage(Text.of(TextColors.RED, "Cannot join more then one game."));
            return;
        }
        if(a.isInGame()==true){
            p.sendMessage(Text.of(TextColors.RED, "Arena is in game!"));
        }
        a.getPlayers().add(p.getUniqueId());
        inv.put(p.getUniqueId(), p.getInventory());
        p.getInventory().clear();
        int size = a.getSize();
        int spawnId = 0;
        Random rn = new Random();
        for(int iE=0;iE<1;iE++){
            spawnId = rn.nextInt(size) + 1;
        }
        Location spawnLoc = a.getSpawnLoc().get(spawnId);
        Map<Integer, Location> pSpawn = new HashMap<>();
        pSpawn.put(spawnId, spawnLoc);
        locs.put(p.getUniqueId(), pSpawn);
        prevLoc.put(p.getUniqueId(), p.getLocation());
        p.getLocation().setPosition(spawnLoc.getPosition());
        if(a.getPlayers().size() >= size){
            a.setInGame(true);
            ArenaStartingEvent event = new ArenaStartingEvent(p, Cause.of(p), a);
            Sponge.getEventManager().post(event);
        }
    }

    public void removePlayer(Player p, int i){
        Arena a = this.getArena(i);
        for(Arena arena : this.arenas){
            if(arena.getPlayers().contains(p.getUniqueId()))
                a = arena;
        }

        if(a == null){
            p.sendMessage(Text.of(TextColors.RED, "Invaid Arena."));
            return;
        }
        a.getPlayers().remove(p.getUniqueId());
        p.getInventory().clear();
        //TODO: Set their inventory back to normal.
        p.getLocation().setPosition(prevLoc.get(p.getUniqueId()).getPosition());
        locs.remove(p.getUniqueId());
        prevLoc.remove(p.getUniqueId());
        p.getHealthData().maxHealth();
        p.sendMessage(Text.of(TextColors.GREEN, "You have successfully left the arena!"));
    }

    public Arena createArena(Location l, int size, String choice, Player p){
        int id = 1;
        while(arenaIds.contains(id)){
            id++;
        }
        if(!choice.equals("default") || !choice.equals("custom")){
            MessageHandler.getManager().arenaFailed(p);
            return null;
        }
        File newWorldDir = new File(Sponge.getGame().getSavesDirectory() + "/arena" + id);
        //Default world/arena shizz starts here
        Path defaultWorld = new File("src/main/resources/worlds/DefaultMap/").toPath();
        if(choice.equals("default")){
            ///sw create default
            if(size == 8){
                Vector3d vec = new Vector3d(19628, 150, 27722);
                //TODO: Handle with getting location directly from vector with extent.
                Location loc = Sponge.getServer().getWorld("arena" + id).get().getLocation(vec);
                Map<Integer, Location> firstSpawn = new HashMap<>();
                firstSpawn.put(0, loc);
                Arena a = new Arena(l, id, firstSpawn, 8, false);
                try{
                    Files.copy(newWorldDir.toPath(), defaultWorld, StandardCopyOption.REPLACE_EXISTING);
                }catch(IOException e){
                    e.printStackTrace();
                    return null;
                }
                this.arenas.add(a);
                //TODO: Create from worlds folder.
                MessageHandler.getManager().arenaCreated(p, id);
                return a;
            }
            MessageHandler.getManager().arenaFailed(p);
            return null;
        }
        //Default world/arena shiz ends here.
        //Custom world/arena starts here.
        ///sw create custom
        if(size == 8){
            Map<Integer, Location> firstSpawn = new HashMap<>();
            Location loc = p.getLocation();
            firstSpawn.put(0, loc);
            Arena a = new Arena(l, id, firstSpawn, 8, false);
            this.arenas.add(a);
            //TODO: Create from worlds folder.
            MessageHandler.getManager().arenaCreated(p, id);
            return a;
        }
        ///sw create custom <size>
        Map<Integer, Location> firstSpawn = new HashMap<>();
        Location loc = p.getLocation();
        firstSpawn.put(0, loc);
        Arena a = new Arena(l, id, firstSpawn, size, false);
        this.arenas.add(a);
        //TODO: Create from worlds folder.
        MessageHandler.getManager().arenaCreated(p, id);
        return a;

    }

    public void deleteArena(int i){
        Arena a = this.getArena(i);
        //TODO: remove from config.
        this.arenas.remove(a);
    }

    public boolean isInGame(Player p) {
        for (Arena a : this.arenas) {
            if (a.getPlayers().contains(p.getUniqueId()))
                return true;
        }
        return false;
    }


}
