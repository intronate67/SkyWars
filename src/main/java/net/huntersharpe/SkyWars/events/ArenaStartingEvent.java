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
package net.huntersharpe.SkyWars.events;

import net.huntersharpe.SkyWars.arena.Arena;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Cancellable;
import org.spongepowered.api.event.cause.Cause;
import org.spongepowered.api.event.entity.living.humanoid.player.TargetPlayerEvent;

public class ArenaStartingEvent implements TargetPlayerEvent, Cancellable {

    private final Cause cause;
    private final Player firingPlayer;
    private boolean cancelled = false;
    private Arena a;

    public ArenaStartingEvent(Player firingPlayer, Cause cause, Arena a){
        this.firingPlayer = firingPlayer;
        this.cause = cause;
        this.a = a;
    }

    public Arena getArena(){
        return this.a;
    }

    @Override
    public boolean isCancelled(){
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancel){
        this.cancelled = cancel;
    }

    @Override
    public Cause getCause(){
        return this.getCause();
    }
    @Override
    public Player getTargetEntity(){
        return this.firingPlayer;
    }
}
