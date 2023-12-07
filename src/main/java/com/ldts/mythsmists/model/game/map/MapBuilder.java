package com.ldts.mythsmists.model.game.map;

import com.ldts.mythsmists.model.Elements.Enemy;
import com.ldts.mythsmists.model.Elements.Orpheus;
import com.ldts.mythsmists.model.Elements.Wall;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class MapBuilder {

    private final Random rng;
    private int width;
    private int height;

    private  int numberOfEnemys;

    public MapBuilder(){
    }

    public MapBuilder(int width,int height,int numberOfEnemys) {
        this.rng = new Random();

        this.width = width;
        this.height= height;
        this.numberOfEnemys=numberOfEnemys;
    }

    public Map createMap() {
        Map map = new Map(width, height);

        map.setOrpheus(createOrpheus());
        map.setWalls(createWalls());
        map.setEnemys(createEnemys());
        return map;
    }

    protected Orpheus createOrpheus(){
        return new Orpheus(width/2,height/2);
    }

    protected List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();

        for (int x = 0; x < width; x++) {
            walls.add(new Wall(x, 0));
            walls.add(new Wall(x, height - 1));
        }

        for (int y = 1; y < height - 1; y++) {
            walls.add(new Wall(0, y));
            walls.add(new Wall(width - 1, y));
        }

        return walls;
    }

    protected List<Enemy> createEnemys() {
        List<Enemy> enemys = new ArrayList<>();

        while (enemys.size() < numberOfEnemys)
            enemys.add(new Enemy(rng.nextInt(width - 2) + 1, rng.nextInt(height - 2) + 1));

        return enemys;
    }
}
