Shattered Terra
=======================

[![Join the chat at https://gitter.im/apolaskey/shattered-terra](https://badges.gitter.im/apolaskey/shattered-terra.svg)](https://gitter.im/apolaskey/shattered-terra?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

[![Build Status](https://travis-ci.org/apolaskey/shattered-terra.svg)](https://travis-ci.org/apolaskey/shattered-terra?branch=master)

Connect to [ws://glacial-meadow-4285.herokuapp.com/game] to play!

Table of Contents
-----------------
1. [Requirements](#requirements)
1. [Features](#features)
1. [Getting Started](#getting-started)
1. [Structure](#structure)

Requirements
------------

Java 8
Maven 3

Features
--------

Nothing at this time

Getting Started
---------------

Just clone the repo and:

```shell
$ mvn clean package                 # Install all the dependencies and build a .war file for deployment
  or
$ mvn clean jetty:run               # Launch in dev-mode
```

Structure
---------

The folder structure provided is only meant to serve as a guide, it is by no means prescriptive. It is something that has worked very well for me and my team, but use only what makes sense to you.

```
.
├── com.solarwire.terra      # Application namespace
│   ├── endpoints            # All REST API endpoints
│   ├── game                 # Anything to do with MUD gaming logic
│   │   ├── combat           # MUD Combat logic / commands
│   │   └── player           # Player logic / commands
│   ├── injection            # Guice DI modules
│   ├── persistence          # Database components
│   ├── sockets              # Connection handling and routing for WebSockets
│   ├── resources            # Configuration files for MUD / Ext. Libs
│   └── webapp               # Server Web UI files and tools
│       └── WEB-INF          # Application WAR config
└── test                     # Unit tests / Integration tests
```