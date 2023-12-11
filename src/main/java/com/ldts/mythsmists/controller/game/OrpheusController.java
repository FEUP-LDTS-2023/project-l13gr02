package com.ldts.mythsmists.controller.game;

import com.ldts.mythsmists.Game;
import com.ldts.mythsmists.gui.GUI;
import com.ldts.mythsmists.model.Elements.Dracma;
import com.ldts.mythsmists.model.Elements.Orpheus;
import com.ldts.mythsmists.model.Position;
import com.ldts.mythsmists.model.game.map.Map;
import com.ldts.mythsmists.model.game.map.MapLoader;
import com.ldts.mythsmists.model.textsections.TextSection;
import com.ldts.mythsmists.states.Act1State;
import com.ldts.mythsmists.states.Interlude1State;

import java.io.IOException;

public class OrpheusController extends GameController{

    public OrpheusController(Map map){super(map);}

    public void moveOrpheusLeft(){ moveOrpheus(getModel().getOrpheus().getPosition().getLeft());}
    public void moveOrpheusRight(){ moveOrpheus(getModel().getOrpheus().getPosition().getRight());}
    public void moveOrpheusUp(){ moveOrpheus(getModel().getOrpheus().getPosition().getUp());}

    public void moveOrpheusDown(){ moveOrpheus(getModel().getOrpheus().getPosition().getDown());}

    public void moveOrpheus(Position position) {
        if(getModel().isEmpty(position)){
            getModel().getOrpheus().setPosition(position);
            if(!(getModel().isEnemy(position))){
                getModel().getOrpheus().decreaseEnergy();
            }
            if(!(getModel().isDracma(position))){
                getModel().getOrpheus().increaseCount();
                getModel().getDracmas().removeIf(dracma -> dracma.getPosition().equals(position));
            }
            if(!(getModel().isCheckpoint(position))) {
                getModel().getOrpheus().setHasReachedCheckpoint();
            }
        }
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {
            if (action == GUI.ACTION.UP) {
                moveOrpheusUp();
                // Nota: ver isto. Era suposto o mapa dar reload com o resto do mapa que está no ficheiro 2.
                if (getModel().getOrpheus().getPosition().getY()<0) {
                    game.setState(new Act1State(new MapLoader(2).createMap()));
                }
            }
            if (action == GUI.ACTION.RIGHT) moveOrpheusRight();
            if (action == GUI.ACTION.DOWN) moveOrpheusDown();
            if (action == GUI.ACTION.LEFT) moveOrpheusLeft();
    }
}
