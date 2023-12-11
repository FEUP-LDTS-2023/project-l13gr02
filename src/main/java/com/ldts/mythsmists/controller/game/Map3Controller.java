package com.ldts.mythsmists.controller.game;

import com.ldts.mythsmists.Game;
import com.ldts.mythsmists.gui.GUI;
import com.ldts.mythsmists.model.Elements.Orpheus;
import com.ldts.mythsmists.model.game.map.Map;
import com.ldts.mythsmists.model.menu.Menu;
import com.ldts.mythsmists.model.textsections.TextSection;
import com.ldts.mythsmists.states.Interlude2State;
import com.ldts.mythsmists.states.MenuState;
import com.ldts.mythsmists.states.BadEndingState;
import com.ldts.mythsmists.states.GoodEndingState;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.Objects;

public class Map3Controller extends GameController {

    private final OrpheusController orpheusController;
    private final EnemyController enemyController;

    public Map3Controller(Map map) {
        super(map);
        this.orpheusController = new OrpheusController(map);
        this.enemyController = new EnemyController(map);
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {
        Orpheus orpheus = getModel().getOrpheus();
        if (action == GUI.ACTION.QUIT || (orpheus != null && orpheus.getEnergy() == 0)) {
            game.setState(new MenuState(new Menu()));
        }
        else if (getModel().getOrpheus().get_isLookSpace() && orpheus.get_lookback()) {
            game.setState(new GoodEndingState(new TextSection()));
        }
        else if (!getModel().getOrpheus().get_isLookSpace() && orpheus.get_lookback()) {
            game.setState(new BadEndingState(new TextSection()));
        }
        else {
            orpheusController.step(game, action, time);
            enemyController.step(game, action, time);
        }
    }
}
