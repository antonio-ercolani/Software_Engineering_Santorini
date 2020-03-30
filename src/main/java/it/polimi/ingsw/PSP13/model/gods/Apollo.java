package it.polimi.ingsw.PSP13.model.gods;

import it.polimi.ingsw.PSP13.model.Match;
import it.polimi.ingsw.PSP13.model.Turn;
import it.polimi.ingsw.PSP13.model.board.Level;
import it.polimi.ingsw.PSP13.model.board.Map;
import it.polimi.ingsw.PSP13.model.exception.IllegalMoveException;
import it.polimi.ingsw.PSP13.model.player.Builder;
import it.polimi.ingsw.PSP13.model.player.Coords;
import it.polimi.ingsw.PSP13.model.player.Player;

public class Apollo extends Turn {

    public Apollo()
    {
    }

    /**
     *
     * @param apollo
     * @param coords
     * @return true if coords' cell is occupied by a player instead of a dome
     */
    private boolean apolloCheck(Builder apollo, Coords coords)
    {
        if(!match.isOccupied(coords))
            return true;
        else
        {
            if(match.getCell(coords).getLevel()!=Level.Dome)
                return true;
            else
                return false;
        }

    }

    /**
     * builder can move even if a cell is alredy occupied by another player's builder
     * @param builder builder that is currently moving
     * @param coords coordinates of the cell where the builder wants to move
     * @return
     * @throws IllegalArgumentException
     */
    @Override
    public boolean checkMove(Builder builder, Coords coords) throws IllegalArgumentException
    {
        if (!Map.isLegal(coords) || builder == null) {
            throw new IllegalArgumentException();
        } else {
            int diff = match.getCell(coords).getLevel().getHeight() - Turn.match.getHeight(builder.getCoords());
            if(Turn.match.getAdjacent(builder.getCoords()).contains(coords) && diff <= 1 && apolloCheck(builder, coords))
                return true;
            else
                return false;
        }

    }

    /**
     * the opponent builder is forced to swap position
     * @param builder builder that is currently moving
     * @param coords coordinates of the cell where the builder wants to move
     * @throws IllegalMoveException
     */
    @Override
    public void move(Builder builder, Coords coords) throws IllegalMoveException {
        if (checkMove(builder, coords))
        {
            if(apolloCheck(builder, coords))
            {
                try
                {
                    Builder opponent;
                    opponent = match.getBuilderByCoords(coords);
                    Coords old = builder.getCoords();

                    match.getPlayerByBuilder(opponent).getGod().force(opponent,old);
                }
                catch(IllegalArgumentException e)
                {

                }

            }

            builder.setCoords(coords);

        } else {
            throw new IllegalMoveException();
        }
    }

}