package zeragan.perfit.hotspot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import zeragan.perfit.core.Node;

public class HotSpotList implements List<Node>
{

    private List<Node> nodes = new ArrayList<>();

    private Map<String, HotSpot> hotSpots = new HashMap<>();

    @Override
    public boolean add(Node e)
    {
        HotSpot hotSpot = hotSpots.get(e.getNodeName());
        if (hotSpot == null)
        {
            hotSpot = new HotSpot(e.getSourceId(), e.getThreadName(), e.getNodeName(), e.getTimeUnit());
            hotSpots.put(e.getNodeName(), hotSpot);
            nodes.add(hotSpot);
        }
        hotSpot.add(e);
        return true;
    }

    public boolean remove(Node node)
    {
        if (hotSpots.containsKey(node.getNodeName()))
        {
            hotSpots.remove(node.getNodeName());
            return nodes.remove(node);
        }
        return false;
    }

    @Override
    public boolean remove(Object o)
    {
        if (nodes.remove(o))
        {
            return hotSpots.remove(o) != null;
        }
        return false;
    }

    @Override
    public int size()
    {
        return nodes.size();
    }

    @Override
    public boolean isEmpty()
    {
        return nodes.isEmpty();
    }

    public boolean contains(Node o)
    {
        return hotSpots.containsKey(o.getNodeName());
    }

    @Override
    public boolean contains(Object o)
    {
        return nodes.contains(o);
    }

    @Override
    public Iterator<Node> iterator()
    {
        return nodes.iterator();
    }

    @Override
    public Object[] toArray()
    {
        return nodes.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a)
    {
        return nodes.toArray(a);
    }

    @Override
    public boolean containsAll(Collection<?> c)
    {
        return nodes.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends Node> c)
    {
        boolean add = false;
        for (Node node : c)
        {
            add |= add(node);
        }
        return add;
    }

    @Override
    public boolean addAll(int index, Collection<? extends Node> c)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> c)
    {
        boolean add = false;
        for (Object node : c)
        {
            add |= remove(node);
        }
        return add;
    }

    @Override
    public boolean retainAll(Collection<?> c)
    {
        boolean ret = false;
        for (Node node : this)
        {
            if (!c.contains(node))
            {
                ret |= remove(node);
            }
        }
        return ret;
    }

    @Override
    public void clear()
    {
        nodes.clear();
        hotSpots.clear();
    }

    @Override
    public Node get(int index)
    {
        return nodes.get(index);
    }

    @Override
    public Node set(int index, Node element)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, Node element)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Node remove(int index)
    {
        return nodes.remove(index);
    }

    @Override
    public int indexOf(Object o)
    {
        return nodes.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o)
    {
        return nodes.lastIndexOf(o);
    }

    @Override
    public ListIterator<Node> listIterator()
    {
        return nodes.listIterator();
    }

    @Override
    public ListIterator<Node> listIterator(int index)
    {
        return nodes.listIterator(index);
    }

    @Override
    public List<Node> subList(int fromIndex, int toIndex)
    {
        return nodes.subList(fromIndex, toIndex);
    }

}
