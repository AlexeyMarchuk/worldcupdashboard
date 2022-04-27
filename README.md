World football cup library 
major release 1.0.0 🚀

--Provide managing of football matches.

Overview:
Library provides 2 classes to manipulate all data - **GameManager** and **DashboardSubscriber**
 
To start consuming games from gameManager need to create instance of DashboardSubscriber:
` GameManager gameManager  = new GameManager();
DashboardSubscriber dm = new DashboardSubscriber(gameManager);`

GameManager has next functions:
    - start game (initial score is 0:0)
   `start(Team a, Team b)`
    - finish game (games will be removed from manager and result also will be unavailable for the dashboard)
    `finish(Game game)`
    - update game (modify existing game)
    `update(Game game)`


--DashBoardSubscriber has next functions:
    - get games (return the map of active games)
    `getGames()`
    - get games with special order (first are games with the same total score)




