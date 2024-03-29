package scene.helpers;

import helpers.Point;
import items.Item;
import items.composite.TextItem;
import items.primitive.Segment;
import items.primitive.shapes.Circle;
import items.primitive.shapes.Rect;
import items.primitive.shapes.Spiral;
import scene.MainFrame;

import javax.swing.*;

public class ItemFactory {
    public Item createShape(MainFrame.Mode type, Point start)
    {
        return switch (type) {
            case CIRCLE -> createCircle(start);
            case RECTANGLE -> createRectangle(start);
            case SPIRAL -> createSpiral(start);
            case SEGMENT -> createSegment(start);
            case TEXT -> handleTextCreation(start);
            default -> null;
        };
    }

    public void updateShape(MainFrame.Mode type, Point start, Item item, Object... params)
    {
        switch (type) {
            case CIRCLE -> updateCircle(item, params);
            case RECTANGLE -> updateRectangle(item, params);
            case SPIRAL -> updateSpiral(item, params);
            case SEGMENT -> updateSegment(item, start);
        }
    }


    /* ------------ Creating default items ------------- */

    private Item createSegment(Point start) { return new Segment(new helpers.Point(start.getX(), start.getY()), new helpers.Point(start.getX(), start.getY()));}
    private Circle createCircle(Point start) { return new Circle(new helpers.Point(start.getX(), start.getY()), false, 0);}
    private Rect createRectangle(Point start) { return new Rect(new helpers.Point(start.getX(), start.getY()), false, 0, 0);}
    private Spiral createSpiral(Point start) { return new Spiral(new helpers.Point(start.getX(), start.getY()), false, 0, 0, 0); }
    private Item handleTextCreation(Point start)
    {
        String text = JOptionPane.showInputDialog("Enter text");
        if (text == null) return null;
        return new TextItem(new helpers.Point(start.getX(), start.getY()), text);
    }


    /* ------------ Updating items ------------- */

    private void updateCircle(Item item, Object... params)
    {
        if (params.length >= 1 && params[0] instanceof Integer) {
            int radius = (Integer) params[0];
            ((Circle) item).setRadius(radius);
        } else {
            throw new IllegalArgumentException("Invalid parameters for updating a circle");
        }
    }

    private void updateRectangle(Item item, Object... params)
    {
        if (params.length >= 2 && params[0] instanceof Integer && params[1] instanceof Integer) {
            int dx = (Integer) params[0];
            int dy = (Integer) params[1];
            ((Rect) item).setWidth(dx);
            ((Rect) item).setHeight(dy);
        } else {
            throw new IllegalArgumentException("Invalid parameters for updating a rectangle");
        }
    }

    private void updateSpiral(Item item, Object... params)
    {
    if (params.length >= 2 && params[0] instanceof Integer && params[1] instanceof Integer) {
                int dx = (Integer) params[0];
                int dy = (Integer) params[1];
                ((Spiral) item).setStartRadius(dx);
                ((Spiral) item).setEndRadius(dy);
                ((Spiral) item).setNumTurns(10);
            } else {
                throw new IllegalArgumentException("Invalid parameters for updating a spiral");
            }
    }
    private void updateSegment(Item item, Point start)
    {
        ((Segment) item).setEndPoint(start);
    }
}

