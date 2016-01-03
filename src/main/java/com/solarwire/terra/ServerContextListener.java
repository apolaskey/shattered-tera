package com.solarwire.terra;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Main entry point into the application before we go full async
 */
public class ServerContextListener implements ServletContextListener {

    private static final Logger logger = LoggerFactory.getLogger(ServerContextListener.class);

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        logger.info("Server started!");
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

}
