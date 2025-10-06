    package com.kth.BO;

    import com.kth.UI.ItemInfo;
    import java.util.ArrayList;

    public class ItemHandler {

        public static ArrayList<ItemInfo> getUserShoppingCart(User user) {
            ArrayList<Item> items = user.getShoppingCart();
            ArrayList<ItemInfo> infos = new ArrayList<>();
            for (Item item : items) {
                infos.add(new ItemInfo(
                        item.getTitle(),
                        item.getGenre(),
                        item.getPrice()
                ));
            }
            return infos;
        }
    }
