package com.solarwire.terra.sockets;

import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

/**
 * Entry point for an incoming Websocket connection just before it's passed into the rest of the system
 */
public class WebSocketListener extends WebSocketServlet {
    @Override
    public void configure(WebSocketServletFactory webSocketServletFactory) {
        // Where to start the connection at
        webSocketServletFactory.register(GameSocket.class);
    }
}
