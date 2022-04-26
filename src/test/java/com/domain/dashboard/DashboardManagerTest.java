package com.domain.dashboard;

import com.domain.dto.Country;
import com.domain.dto.Game;
import com.domain.dto.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DashboardManagerTest {

    private GameManager gameManager;
    private DashboardSubscriber dm;

    @BeforeEach
    void beforeEach() {
        gameManager = new GameManager();
        dm = new DashboardSubscriber(gameManager);
    }

    @Test
    public void test1(){
        Game game1 = generate_score_5_5();
        Game game2 = generate_score_7_7();
        Game game3 = generate_score_1_1();
        Game game4 = generate_score_0_5();
        Game game5 = generate_score_10_10();
        Game game6 = generate_score_0_0();

        gameManager.update(game1);
        gameManager.update(game2);
        gameManager.update(game3);
        gameManager.update(game4);
        gameManager.update(game5);
        gameManager.update(game6);


        System.out.println(dm.getGames());
    }

    private Game generate_score_7_7 () {
        Game game = gameManager.start(new Team(Country.Poland), new Team(Country.Cyprus));

        game.getAwayTeam().addPoint();
        game.getAwayTeam().addPoint();
        game.getAwayTeam().addPoint();
        game.getAwayTeam().addPoint();
        game.getAwayTeam().addPoint();
        game.getAwayTeam().addPoint();

        game.getHomeTeam().addPoint();
        game.getHomeTeam().addPoint();
        game.getHomeTeam().addPoint();
        game.getHomeTeam().addPoint();
        game.getHomeTeam().addPoint();
        game.getHomeTeam().addPoint();
        game.getHomeTeam().addPoint();
        return game;
    }

    private Game generate_score_5_5 () {
        Game game = gameManager.start(new Team(Country.Germany), new Team(Country.France));

        game.getAwayTeam().addPoint();
        game.getAwayTeam().addPoint();
        game.getAwayTeam().addPoint();
        game.getAwayTeam().addPoint();
        game.getAwayTeam().addPoint();

        game.getHomeTeam().addPoint();
        game.getHomeTeam().addPoint();
        game.getHomeTeam().addPoint();
        game.getHomeTeam().addPoint();
        game.getHomeTeam().addPoint();
        return game;

    }

    private Game generate_score_1_1 () {
        Game game = gameManager.start(new Team(Country.Bahrain), new Team(Country.Venezuela));

        game.getAwayTeam().addPoint();
        game.getHomeTeam().addPoint();
        return game;

    }

    private Game generate_score_0_5 () {
        Game game = gameManager.start(new Team(Country.Haiti), new Team(Country.Nauru));

        game.getHomeTeam().addPoint();
        game.getHomeTeam().addPoint();
        game.getHomeTeam().addPoint();
        game.getHomeTeam().addPoint();
        game.getHomeTeam().addPoint();
        return game;

    }

    private Game generate_score_0_0 () {
        return gameManager.start(new Team(Country.Uruguay), new Team(Country.Malawi));

    }

    private Game generate_score_10_10 () {
        Game game = gameManager.start(new Team(Country.Moldova), new Team(Country.Namibia));

        game.getAwayTeam().addPoint();
        game.getAwayTeam().addPoint();
        game.getAwayTeam().addPoint();
        game.getAwayTeam().addPoint();
        game.getAwayTeam().addPoint();
        game.getAwayTeam().addPoint();
        game.getAwayTeam().addPoint();
        game.getAwayTeam().addPoint();
        game.getAwayTeam().addPoint();
        game.getAwayTeam().addPoint();

        game.getHomeTeam().addPoint();
        game.getHomeTeam().addPoint();
        game.getHomeTeam().addPoint();
        game.getHomeTeam().addPoint();
        game.getHomeTeam().addPoint();
        game.getHomeTeam().addPoint();
        game.getHomeTeam().addPoint();
        game.getHomeTeam().addPoint();
        game.getHomeTeam().addPoint();
        game.getHomeTeam().addPoint();
        return game;
    }

}