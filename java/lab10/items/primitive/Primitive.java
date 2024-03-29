package items.primitive;

import helpers.Point;
import items.Item;

public abstract class Primitive extends Item {
    public Primitive() { super(); }
    public Primitive(Point position) {
        super(position);
    }
}
