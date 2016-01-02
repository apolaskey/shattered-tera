package com.solarwire.terra.game;

import com.solarwire.terra.sockets.GameSession;

import java.io.IOException;

/**
 * Game blocks prevent a standard command from occurring
 */
public abstract class GameStateBlock {

    public abstract void execute(GameSession session) throws IOException;

}
