package com.solarwire.terra.sockets;

import com.solarwire.terra.game.GameState;
import org.eclipse.jetty.websocket.api.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

/**
 * Wrapper around the Jetty Session, supports carrying of a game state for the connection
 */
public class GameSession {

    private static final Logger logger = LoggerFactory.getLogger(GameSession.class);

    // Raw Jetty Session
    private Session session;

    // State
    private Properties properties = new Properties();
    private GameState currentState, previousState;
    private String currentInput, previousInput;

    public void update(final Session session, final String currentInput) {
        this.session = session;
        previousInput = this.currentInput;
        this.currentInput = currentInput;

        // Finally execute state block
        currentState.blockForState(this);
    }

    public Properties properties() {
        return properties;
    }

    public GameState getGameState() {
        return currentState;
    }

    public void setGameState(final GameState gameState) {
        previousState = currentState;
        currentState = gameState;
    }

    public String getMessage() {
        return currentInput;
    }

    public String getPreviousMessage() {
        return previousInput;
    }

    public GameSession sendMessage(final String input) {
        try {
            session.getRemote().sendString(input);
        } catch (IOException e) {
            logger.error("Failed to send message to {}!", session.getLocalAddress().toString(), e);
        }
        return this;
    }

    public void disconnect() {
        try {
            session.disconnect();
        } catch (IOException e) {
            logger.warn("Failed to disconnect client {}, possible dangling connection.", session.getLocalAddress().toString());
        }
    }


}
