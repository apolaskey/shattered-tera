package com.solarwire.terra.game.player;

import com.solarwire.terra.game.GameStateBlock;
import com.solarwire.terra.sockets.GameSession;

import java.io.IOException;

/**
 * Idle state, accepts any commands and processes them
 */
public class IdleState extends GameStateBlock {

    @Override
    public void execute(GameSession session) throws IOException {
        session.sendMessage("In idle state!");
    }
}
