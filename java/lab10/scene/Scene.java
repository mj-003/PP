package scene;

import helpers.Singleton;
import items.composite.ComplexItem;
import items.Item;
import items.primitive.shapes.Shape;
import items.primitive.shapes.Triangle;
import scene.helpers.ItemFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class Scene extends JPanel {
    private final List<Item> items = new ArrayList<>();
    private Point startPoint;
    private Item currentItem;
    private int clickCount = 0;
    private helpers.Point p1;
    private helpers.Point p2;
    ItemFactory itemFactory = new ItemFactory();


    public Scene(MainFrame mainFrame)
    {

        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(800, 600));

        mainFrame.complexModeCheckbox.addActionListener(e ->
        {
            if (mainFrame.complexModeCheckbox.isSelected()) {
                currentItem = new ComplexItem(new helpers.Point(0, 0));
                items.add(currentItem);
            }
            else currentItem = null;
        });

        MouseAdapter mouseHandler = new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent e)
            {
                /* ------------ Creating shapes ------------- */

                handleShapeCreating(e);

                /* ------------ Selecting shapes ------------- */

                if (mainFrame.getCurrentMode() == MainFrame.Mode.SELECT)
                {
                    for (Item shape : items)
                    {
                        if (shape.contains(new helpers.Point(e.getX(), e.getY()))) {
                            currentItem = shape;
                            startPoint = e.getPoint();
                            break;
                        }
                    }
                }
            }

            @Override
            public void mouseDragged(MouseEvent e)
            {

                /* ------------ Moving shapes ------------- */

                if (mainFrame.getCurrentMode() == MainFrame.Mode.SELECT && currentItem != null)
                {
                    int dx = e.getX() - startPoint.x;
                    int dy = e.getY() - startPoint.y;

                    currentItem.translate(dx, dy);
                    startPoint = e.getPoint();
                    repaint();

                    currentItem.setSelected(false);
                    repaint();
                }

                /* ------------ Items with parameters ------------- */

                else
                {
                    if (currentItem instanceof ComplexItem)
                        handleShape(e, ((ComplexItem) currentItem).getLastChild());
                    else
                        handleShape(e, currentItem);
                }
                repaint();
            }

            @Override
            public void mouseClicked(MouseEvent e)
            {
                /* ------------ Creating triangle ------------- */

                if (mainFrame.getCurrentMode() == MainFrame.Mode.TRIANGLE) {
                    handleTriangleClick(e.getX(), e.getY());
                }

                /* ------------ Filling shape ------------- */

                 else if (mainFrame.getCurrentMode() == MainFrame.Mode.FILL)
                    fillSelectedShape(e);

                /* ------------ Selecting shape ------------- */

                 else if (mainFrame.getCurrentMode() == MainFrame.Mode.SELECT)
                    selectShape(e);
            }



            /* ------------ Helper methods ------------- */

            private void handleShape(MouseEvent e, Item shape)
            {
                int dx = Math.abs(startPoint.x - e.getX());
                int dy = Math.abs(startPoint.y - e.getY());
                helpers.Point point = new helpers.Point(e.getX(), e.getY());

                itemFactory.updateShape(mainFrame.getCurrentMode(), point, shape, dx, dy);
            }

            private void selectShape(MouseEvent e) {
                for (Item shape : items) {
                    if (shape != null && shape.contains(new helpers.Point(e.getX(), e.getY()))) {
                        shape.setSelected(true);
                        repaint();
                        break;
                    }
                }
            }

            private void fillSelectedShape(MouseEvent e) {
                for (Item shape : items) {
                    if (shape instanceof Shape && shape.contains(new helpers.Point(e.getX(), e.getY()))) {
                        ((Shape) shape).setFilled(true);
                        ((Shape) shape).setColor(mainFrame.getChosenFillColor());
                        repaint();
                        break;
                    }
                }
            }

            private void handleShapeCreating(MouseEvent e) {
                startPoint = e.getPoint();

                if (!mainFrame.isComplexMode()) {
                    currentItem = null;
                }

                MainFrame.Mode shapeType = mainFrame.getCurrentMode();

                Item newItem = itemFactory.createShape(shapeType, new helpers.Point(e.getX(), e.getY()));
                repaint();

                if (newItem != null)
                {
                    if (newItem instanceof Singleton) {
                        ((Singleton) newItem).removeItem(items);
                    }

                    if (currentItem instanceof ComplexItem) {
                        ((ComplexItem) currentItem).addChild(newItem);
                    }

                    else {
                        currentItem = newItem;
                        items.add(currentItem);
                    }
                }
                repaint();
            }
        };


        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseHandler);
    }


    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        for (Item shape : items)
        {
            if (shape != null) {
                shape.draw(g);
            }
        }
    }

    public void clear()
    {
        items.clear();
        currentItem = null;
        repaint();
    }

    private void handleTriangleClick(int x, int y)
    {
        clickCount++;

        if (clickCount == 1) {
            p1 = new helpers.Point(x, y);
        }

        else if (clickCount == 2) {
            p2 = new helpers.Point(x, y);
        }

        else if (clickCount == 3)
        {
            helpers.Point p3 = new helpers.Point(x, y);
            clickCount = 0;
            boolean fillShape = false;
            Item triangle = new Triangle(p1, fillShape, p1, p2, p3);

            if (currentItem instanceof ComplexItem) {
                ((ComplexItem) currentItem).addChild(triangle);
            } else {
                currentItem = triangle;
                items.add(currentItem);
            }
            repaint();
        }
    }
}
