package items.composite;

import helpers.Point;
import helpers.BoundingBox;
import items.Item;

import java.awt.*;

public class TextItem extends Item {
    private String text;

    /* ------------ Constructors ------------- */

    public TextItem()
    {
        super();
        this.text = "";
        createBoundingBox();
    }

    public TextItem(Point position, String text)
    {
        super(position);
        this.text = text;
        createBoundingBox();
    }


    /* ------------ Getters and Setters ------------- */

    public String getText() {
        return text;
    }
    public void setText(String text) { this.text = text; }


    /* ------------ Abstract Methods ------------- */
    @Override
    public void translate(int dx, int dy)
    {
        position.setX(position.getX() + dx);
        position.setY(position.getY() + dy);
        createBoundingBox();
    }

    @Override
    public void draw(Graphics g)
    {
        if (selected)
        {
            createBoundingBox();
            boundingBox.draw(g);
        }

        g.setColor(Color.BLACK);
        g.drawString(text, position.getX(), position.getY());
    }

    @Override
    public void createBoundingBox ()
    {
        int approxCharWidth = 12;
        int approxCharHeight = 20;
        int margin = 5;

        int textWidth = text.length() * approxCharWidth;

        int x = position.getX() - 2 * margin;
        int y = position.getY() + margin;

        Point topLeft = new Point(x, y);
        Point topRight = new Point(x + textWidth, y);
        Point bottomLeft = new Point(x, y - approxCharHeight);
        Point bottomRight = new Point(x + textWidth, y - approxCharHeight);

        boundingBox = new BoundingBox(topLeft, topRight, bottomLeft, bottomRight);
    }

    @Override
    public boolean contains(Point point) {
        return boundingBox.contains(point);
    }
}
