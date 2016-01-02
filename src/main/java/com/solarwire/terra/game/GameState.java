package com.solarwire.terra.game;

import com.solarwire.terra.game.combat.CombatState;
import com.solarwire.terra.game.player.AuthorizationState;
import com.solarwire.terra.game.player.IdleState;
import com.solarwire.terra.sockets.GameSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * State mapping for all the things
 */
public enum GameState {
    Authorization(new AuthorizationState()),
    Combat(new CombatState()),
    IdleState(new IdleState());

    private static final Logger logger = LoggerFactory.getLogger(GameState.class);

    private GameStateBlock activeState;

    GameState(GameStateBlock gameStateBlock) {
        this.activeState = gameStateBlock;
    }

    /**
     * Shouldn't be required but if someone needs to get the raw time this makes switching to it easier.
     * @param <T> Try and be polite and cast to the thing you think it is
     * @return Instance or Null if failed to cast
     */
    @SuppressWarnings("unchecked")
    public <T extends GameStateBlock> T getBlockAs() {
        if(activeState != null) {
            try {
                return (T) activeState;
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    public void blockForState(final GameSession gameSession) {
        try {
            this.activeState.execute(gameSession);
        } catch (IOException e) {
            logger.error("Error in state blocking, disconnecting user!", e);
            gameSession.disconnect();
        }
    }

}
