package com.ldts.mythsmists.gui;

import com.ldts.mythsmists.model.Position;

import java.io.IOException;

public interface GUI {
    void drawText(Position position, String text, String color);

    void clear();

    void refresh() throws IOException;

    void close() throws IOException;

    enum ACTION {UP, DOWN, LEFT, RIGHT, QUIT, SELECT, NONE};
    ACTION getNextAction() throws IOException;
}