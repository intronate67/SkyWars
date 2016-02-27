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

import org.spongepowered.api.world.Location;

import java.util.*;

public class Arena {

    private final int id;
    private final Map<Integer, Location> spawnLoc;
    private final List<UUID> players = new ArrayList<>();
    private final int arenaSize;
    private boolean inGame;

    public Arena(Location spawn, int id, Map<Integer, Location> spawnLoc, int arenaSize, boolean inGame){
        this.id = id;
        this.spawnLoc = spawnLoc;
        this.arenaSize = arenaSize;
        this.inGame = inGame;
    }

    public int getId(){
        return this.id;
    }

    public Map<Integer, Location> getSpawnLoc(){
        return this.spawnLoc;
    }

    public List<UUID> getPlayers(){
        return this.players;
    }

    public int getSize(){
        return this.getSize();
    }

    public boolean isInGame(){
        return inGame;
    }

    public void setInGame(boolean bool){
        this.inGame = bool;
    }
}
