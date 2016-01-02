package com.solarwire.terra.game.player;

import com.google.common.base.Strings;
import com.solarwire.terra.game.GameState;
import com.solarwire.terra.game.GameStateBlock;
import com.solarwire.terra.sockets.GameSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AuthorizationState extends GameStateBlock {
    private static final Logger logger = LoggerFactory.getLogger(AuthorizationState.class);

    private boolean promptedForUser = false, promptedForPass = false;

    public void execute(GameSession session) {
        String user = session.properties().getProperty(PlayerEntity.authUser);
        String pass = session.properties().getProperty(PlayerEntity.authPass);

        if(!promptedForUser) {
            session.sendMessage("What is your name?");
            promptedForUser = true;
        } else if(Strings.isNullOrEmpty(user)) {
            user = session.getMessage();
            session.properties().setProperty(PlayerEntity.authUser, user);
        }

        if(!Strings.isNullOrEmpty(user)) {
            if (!promptedForPass) {
                session.sendMessage("What is your secret?");
                promptedForPass = true;
            } else if(Strings.isNullOrEmpty(pass)) {
                pass = session.getMessage();
                session.properties().setProperty(PlayerEntity.authPass, pass);

                // Do login stuff then switch
                session.setGameState(GameState.IdleState);
            }
        }
    }

    /**
     * Checks to see if a username is new or has dirty words
     * - Messages will be sent in the case of failed validation indicating why
     * @param user {@link String} Users name
     * @return {@link Boolean} True if all is good, false otherwise
     */
    private boolean validateUserName(GameSession session, String user) {
        return true;
    }

}
