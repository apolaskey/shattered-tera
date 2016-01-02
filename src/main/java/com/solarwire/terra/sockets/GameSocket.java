package com.solarwire.terra.sockets;

import com.solarwire.terra.game.GameState;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Game socket connection, where user commands are processed per-user.
 * Notes: Each incoming connection gets a copy of this, so 1000x connections == 1000x GameSocket instances
 * So it's safe to have a state here as each instance is unique to a connection
 */
@WebSocket
public class GameSocket {

    private static final Logger logger = LoggerFactory.getLogger(GameSocket.class);

    // Socket State below here
    private GameSession gameSession = new GameSession();


    @OnWebSocketConnect
    public void onConnect(Session session) {
        logger.info("Connecting: {}", session.getLocalAddress().toString());
        session.setIdleTimeout(1800000L);
        gameSession.setGameState(GameState.Authorization);
        gameSession.update(session, "");
    }

    @OnWebSocketClose
    public void onDisconnect(Session session, int closeCode, String reason) {
        logger.info("Disconnecting: {}", session.getLocalAddress().toString());
    }

    @OnWebSocketError
    public void onError(Session session, Throwable error) {
        logger.error("Critical error: {}", session.getLocalAddress().toString(), error);
    }

    @OnWebSocketMessage
    public void onMessage(Session session, String message) {
        logger.info("Message of bytes {} from {}", message.getBytes().length, session.getLocalAddress().toString());
        gameSession.update(session, message);
    }

}
