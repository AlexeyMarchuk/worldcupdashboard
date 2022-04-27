package com.domain.dashboard;

import com.domain.dto.Country;
import com.domain.dto.Game;
import com.domain.dto.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

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
    public void test1() {
        Game germany_france = generate_score_5_5();
        Game poland_cyprus = generate_score_7_7();
        Game bahrain_venezuela = generate_score_1_1();
        Game haiti_nauru = generate_score_0_5();
        Game moldova_namibia = generate_score_10_10();
        Game uruguay_malawi = generate_score_0_0();

        gameManager.update(germany_france);
        gameManager.update(poland_cyprus);
        gameManager.update(bahrain_venezuela);
        gameManager.update(haiti_nauru);
        gameManager.update(moldova_namibia);
        gameManager.update(uruguay_malawi);

        List<Game> sortFirstTime = dm.getSortedGames();
        assertThat(sortFirstTime.get(0).getHomeTeam().getName()).isEqualTo(moldova_namibia.getHomeTeam().getName());
        assertThat(sortFirstTime.get(0).getAwayTeam().getName()).isEqualTo(moldova_namibia.getAwayTeam().getName());

        assertThat(sortFirstTime.get(1).getHomeTeam().getName()).isEqualTo(poland_cyprus.getHomeTeam().getName());
        assertThat(sortFirstTime.get(1).getAwayTeam().getName()).isEqualTo(poland_cyprus.getAwayTeam().getName());

        assertThat(sortFirstTime.get(2).getHomeTeam().getName()).isEqualTo(germany_france.getHomeTeam().getName());
        assertThat(sortFirstTime.get(2).getAwayTeam().getName()).isEqualTo(germany_france.getAwayTeam().getName());

        assertThat(sortFirstTime.get(3).getHomeTeam().getName()).isEqualTo(bahrain_venezuela.getHomeTeam().getName());
        assertThat(sortFirstTime.get(3).getAwayTeam().getName()).isEqualTo(bahrain_venezuela.getAwayTeam().getName());

        assertThat(sortFirstTime.get(4).getHomeTeam().getName()).isEqualTo(uruguay_malawi.getHomeTeam().getName());
        assertThat(sortFirstTime.get(4).getAwayTeam().getName()).isEqualTo(uruguay_malawi.getAwayTeam().getName());

        uruguay_malawi.getAwayTeam().addPoint();
        uruguay_malawi.getAwayTeam().addPoint();
        uruguay_malawi.getHomeTeam().addPoint();
        uruguay_malawi.getHomeTeam().addPoint();

        gameManager.update(uruguay_malawi);

        List<Game> sortSecondTime = dm.getSortedGames();
        assertThat(sortSecondTime.get(3).getHomeTeam().getName()).isEqualTo(uruguay_malawi.getHomeTeam().getName());
        assertThat(sortSecondTime.get(3).getAwayTeam().getName()).isEqualTo(uruguay_malawi.getAwayTeam().getName());
    }

    private Game generate_score_7_7() {
        Game game = gameManager.start(new Team(Country.Poland), new Team(Country.Cyprus));

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
        return game;
    }

    private Game generate_score_5_5() {
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

    private Game generate_score_1_1() {
        Game game = gameManager.start(new Team(Country.Bahrain), new Team(Country.Venezuela));

        game.getAwayTeam().addPoint();
        game.getHomeTeam().addPoint();
        return game;

    }

    private Game generate_score_0_5() {
        Game game = gameManager.start(new Team(Country.Haiti), new Team(Country.Nauru));

        game.getHomeTeam().addPoint();
        game.getHomeTeam().addPoint();
        game.getHomeTeam().addPoint();
        game.getHomeTeam().addPoint();
        game.getHomeTeam().addPoint();
        return game;

    }

    private Game generate_score_0_0() {
        return gameManager.start(new Team(Country.Uruguay), new Team(Country.Malawi));

    }

    private Game generate_score_10_10() {
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