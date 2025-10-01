package com.kth.BO;

import com.kth.UI.ItemInfo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class ItemHandler {
    public static Collection<ItemInfo> getItems(String s) {
        Collection c = Item.searchItems(s);
        ArrayList<ItemInfo> items = new ArrayList<ItemInfo>();
        for (Iterator itr = c.iterator(); itr.hasNext();) {
            Item item = (Item) itr.next();
            items.add(new ItemInfo(item.getTitle(), item.getGenre(), item.getPrice()));
        }
        return items;
    }

}
