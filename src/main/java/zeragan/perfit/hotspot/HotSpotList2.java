package zeragan.perfit.hotspot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import zeragan.perfit.core.Node;

public class HotSpotList2 extends ArrayList<HotSpot>
{

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    private Map<String, HotSpot> hotSpots = new HashMap<>();

    public void register(Node e)
    {
        HotSpot hotSpot = hotSpots.get(e.getNodeName());
        if (hotSpot == null)
        {
            hotSpot = new HotSpot(e.getSourceId(), e.getThreadName(), e.getNodeName(), e.getTimeUnit());
            hotSpots.put(e.getNodeName(), hotSpot);
            add(hotSpot);
        }
        hotSpot.add(e);
    }

    @Override
    public boolean add(HotSpot e)
    {
        HotSpot hotSpot = hotSpots.get(e.getNodeName());
        if (hotSpot == null)
        {
            hotSpots.put(e.getNodeName(), e);
            return super.add(e);
        }
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends HotSpot> c)
    {
        return super.addAll(c);
    }

    @Override
    public void add(int index, HotSpot element)
    {
        super.add(index, element);
    }

    @Override
    public boolean addAll(int index, Collection<? extends HotSpot> c)
    {
        return super.addAll(index, c);
    }

    @Override
    public boolean remove(Object o)
    {
        return super.remove(o);
    }

    @Override
    public HotSpot set(int index, HotSpot element)
    {
        return super.set(index, element);
    }

    @Override
    public HotSpot remove(int index)
    {
        return super.remove(index);
    }

    @Override
    public boolean removeAll(Collection<?> c)
    {
        return super.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c)
    {
        return super.retainAll(c);
    }

    @Override
    public void clear()
    {
        super.clear();
    }

}
