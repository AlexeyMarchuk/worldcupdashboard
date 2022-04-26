package com.domain.dashboard;

import com.domain.dto.Country;
import com.domain.dto.Game;
import com.domain.dto.Team;
import com.exceptions.InvalidTeamException;
import com.exceptions.TeamAlreadyPlayingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GameManagerTest {

    private GameManager gameManager;
    private DashboardSubscriber dm;

    @BeforeEach
    void beforeEach() {
        gameManager = new GameManager();
        dm = new DashboardSubscriber(gameManager);
    }

    @Test
    void match_with_update_successful() {
        Game albania_ukraine = gameManager.start(new Team(Country.Albania), new Team(Country.Ukraine));

        albania_ukraine.getAwayTeam().addPoint();
        gameManager.update(albania_ukraine);

        assertThat(dm.getGames().size()).isEqualTo(1);
        assertThat(dm.getGames().get(albania_ukraine.getId()).getHomeTeam().getScore()).isEqualTo(0);
        assertThat(dm.getGames().get(albania_ukraine.getId()).getAwayTeam().getScore()).isEqualTo(1);

        albania_ukraine.getAwayTeam().addPoint();
        gameManager.update(albania_ukraine);

        assertThat(dm.getGames().size()).isEqualTo(1);
        assertThat(dm.getGames().get(albania_ukraine.getId()).getHomeTeam().getScore()).isEqualTo(0);
        assertThat(dm.getGames().get(albania_ukraine.getId()).getAwayTeam().getScore()).isEqualTo(2);

        albania_ukraine.getHomeTeam().addPoint();
        gameManager.update(albania_ukraine);

        assertThat(dm.getGames().size()).isEqualTo(1);
        assertThat(dm.getGames().get(albania_ukraine.getId()).getHomeTeam().getScore()).isEqualTo(1);
        assertThat(dm.getGames().get(albania_ukraine.getId()).getAwayTeam().getScore()).isEqualTo(2);
    }

    @Test
    void match_without_update() {
        Game poland_germany = gameManager.start(new Team(Country.Poland), new Team(Country.Germany));
        poland_germany.getHomeTeam().addPoint();

        assertThat(dm.getGames().size()).isEqualTo(1);
        assertThat(dm.getGames().get(poland_germany.getId()).getHomeTeam().getScore()).isEqualTo(0);
        assertThat(dm.getGames().get(poland_germany.getId()).getAwayTeam().getScore()).isEqualTo(0);

        gameManager.update(poland_germany);
        assertThat(dm.getGames().get(poland_germany.getId()).getHomeTeam().getScore()).isEqualTo(1);
        assertThat(dm.getGames().get(poland_germany.getId()).getAwayTeam().getScore()).isEqualTo(0);
    }

    @Test
    void add_already_playing_country_failed() {
        gameManager.start(new Team(Country.Poland), new Team(Country.Germany));
        Game bahrain_ukraine = gameManager.start(new Team(Country.Bahrain), new Team(Country.Ukraine));

        assertThat(dm.getGames().size()).isEqualTo(2);

        gameManager.finish(bahrain_ukraine);
        assertThat(dm.getGames().size()).isEqualTo(1);

        TeamAlreadyPlayingException thrownAlreadyPlaying = assertThrows(
                TeamAlreadyPlayingException.class,
                () -> gameManager.start(new Team(Country.Poland), new Team(Country.Germany)),
                "Expected start() to throw, but it didn't"
        );

        assertTrue(thrownAlreadyPlaying.getMessage().contains(TeamAlreadyPlayingException.MESSAGE));

    }


    @Test
    void incorrect_team_name() {
        InvalidTeamException thrownNullName = assertThrows(
                InvalidTeamException.class,
                () -> gameManager.start(new Team(Country.Poland), null),
                "Expected start() to throw, but it didn't"
        );

        InvalidTeamException thrownSameNames = assertThrows(
                InvalidTeamException.class,
                () -> gameManager.start(new Team(Country.Poland), new Team(Country.Poland)),
                "Expected start() to throw, but it didn't"
        );

        assertTrue(thrownNullName.getMessage().contains(InvalidTeamException.MESSAGE));
        assertTrue(thrownSameNames.getMessage().contains(InvalidTeamException.MESSAGE));
    }

}