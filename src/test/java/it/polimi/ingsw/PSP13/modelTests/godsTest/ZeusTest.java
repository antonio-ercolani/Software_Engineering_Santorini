package it.polimi.ingsw.PSP13.modelTests.godsTest;

import it.polimi.ingsw.PSP13.model.Match;
import it.polimi.ingsw.PSP13.model.Turn;
import it.polimi.ingsw.PSP13.model.board.Level;
import it.polimi.ingsw.PSP13.model.exception.IllegalBuildException;
import it.polimi.ingsw.PSP13.model.gods.Ares;
import it.polimi.ingsw.PSP13.model.gods.Zeus;
import it.polimi.ingsw.PSP13.model.player.Builder;
import it.polimi.ingsw.PSP13.model.player.Color;
import it.polimi.ingsw.PSP13.model.player.Coords;
import it.polimi.ingsw.PSP13.model.player.Player;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class ZeusTest {

    private static Match match;
    private static Player player, opponentPlayer;
    private static Builder builder1, builder2, opponentsbuilder1, opponentsbuilder2;
    private static Turn turn,zeus;

    @BeforeClass
    public static void init()
    {
        match = new Match();
        match.start();
        player = new Player(Color.Blue, 21, "Mario");
        opponentPlayer = new Player(Color.Brown, 21, "Diego");

        match.addPlayer(player);
        match.addPlayer(opponentPlayer);

        builder1 = new Builder(player);
        builder2 = new Builder(player);
        player.setBuilders(new Builder[]{builder1, builder2});
        turn = new Turn(match);
        zeus = new Zeus();
        player.setGod(zeus);

        opponentsbuilder1 = new Builder(opponentPlayer);
        opponentsbuilder2 = new Builder(opponentPlayer);
        opponentPlayer.setBuilders(new Builder[]{opponentsbuilder1, opponentsbuilder2});
        opponentPlayer.setGod(new Turn());

        player.getBuilders()[0].setCoords(new Coords(3, 0));
        player.getBuilders()[1].setCoords(new Coords(3, 1));

        opponentPlayer.getBuilders()[0].setCoords(new Coords(0, 0));
        opponentPlayer.getBuilders()[1].setCoords(new Coords(0, 1));


        match.setCell(new Coords(3, 0), Level.Floor);
        match.setCell(new Coords(3, 2), Level.Floor);
        match.setCell(new Coords(1, 2), Level.Base);
        match.setCell(new Coords(2, 2), Level.Medium);
        match.setCell(new Coords(1, 3), Level.Top);
        match.setCell(new Coords(3, 3), Level.Dome);
        match.setCell(new Coords(1, 2), Level.Base);
    }

    @Test
    public void checkBuildTrue()
    {
        boolean result;
        match.setCell(new Coords(3, 0), Level.Floor);
        result = zeus.checkBuild(builder1,new Coords(3,0));
        assertTrue(result);

        result = zeus.checkBuild(builder1,new Coords(2,0));
        assertTrue(result);

    }

    @Test
    public void checkBuildFalse()
    {
        boolean result;
        match.setCell(new Coords(3, 0), Level.Top);
        result = zeus.checkBuild(builder1,new Coords(3,0));
        assertFalse(result);

    }

}