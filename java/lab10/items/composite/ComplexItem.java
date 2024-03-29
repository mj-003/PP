package items.composite;

import helpers.Point;
import helpers.BoundingBox;
import items.Item;
import items.primitive.Primitive;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ComplexItem extends Item {
    private final ArrayList<Primitive> children;


    /* ------------ Constructors ------------- */

    public ComplexItem()
    {
        super();
        this.children = new ArrayList<>();
        createBoundingBox();
    }

    public ComplexItem(Point position)
    {
        super(position);
        this.children = new ArrayList<>();
        createBoundingBox();
    }


    /* ------------ Getters, setters and other ------------- */

    public Item getLastChild()
    {
        if (children.isEmpty()) {
            return null;
        }
        return children.get(children.size() - 1);
    }

    public void addChild(Item child)
    {
        if (child instanceof Primitive)
            children.add((Primitive) child);
        createBoundingBox();
    }

    public List<Item> getChildren() {
        return children;
    }


    /* ------------ Abstract Methods ------------- */

    @Override
    public void translate(int dx, int dy)
    {
        for (Item child : children) {
            child.translate(dx, dy);
        }
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
        for (Item child : children) {
            if (child != null)
                child.draw(g);
        }

    }

    @Override
    public void createBoundingBox()
    {
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;

        for (Item child : children)
        {
            BoundingBox childBB = child.getBoundingBox();
            minX = Math.min(minX, childBB.topLeft().getX());
            minY = Math.min(minY, childBB.bottomLeft().getY());
            maxX = Math.max(maxX, childBB.bottomRight().getX());
            maxY = Math.max(maxY, childBB.topLeft().getY());
        }

        Point upperLeft = new Point(minX, maxY);
        Point upperRight = new Point(maxX, maxY);
        Point lowerLeft = new Point(minX, minY);
        Point lowerRight = new Point(maxX, minY);

        boundingBox = new BoundingBox(upperLeft, upperRight, lowerLeft, lowerRight);
    }

    @Override
    public boolean contains(Point point)
    {
        for (Item child : children)
        {
            if (child.contains(point)) {
                return true;
            }
        }
        return false;
    }
}
