package it.polimi.ingsw.PSP13.network.client_dispatching.behavior;

import it.polimi.ingsw.PSP13.network.client_dispatching.MessageCV;
import it.polimi.ingsw.PSP13.view.Input;

public class ClientDisconnectionBehavior extends ClientEffectBehavior {

    @Override
    public void behavior(MessageCV messageCV) {
        input.disconnectionMessage();
        System.exit(0);
    }

    public ClientDisconnectionBehavior(Input input)
    {
        super(input);
    }
}