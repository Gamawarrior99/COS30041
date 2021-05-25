/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ed.sfsb;

import com.sun.xml.rpc.processor.modeler.j2ee.xml.string;
import dto.CartItem;
import java.util.ArrayList;
import javax.ejb.Stateful;

/**
 *
 * @author gamal
 */
@Stateful
public class ShopCartBean implements ShopCartBeanRemote {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    private ArrayList<CartItem> cart;

    public ShopCartBean() {
        cart = new ArrayList<CartItem>();
    }

    private boolean add(CartItem cartItem) {
        boolean result = false;
        try {
            result = cart.add(cartItem);
        } catch (Exception ex) {
        }
        return result;
    }

    private boolean delete(CartItem cartItem) {
        boolean result = false;
        try {
            result = cart.remove(cartItem);
        } catch (Exception ex) {
        }
        return result;
    }

    @Override
    public boolean addCartItem(CartItem cartItem) {
        for (CartItem cartitem : this.getCart()) {
            if (cartitem.getItemId().equals(cartItem.getItemId())) {
                cartitem.setQuantity(cartitem.getQuantity() + cartItem.getQuantity());
                return true;
            }
        }
        return this.add(cartItem);
    }

    @Override
    public boolean deleteCartItem(String itemId) {
        for (CartItem cartitem : this.getCart()) {
            if (cartitem.getItemId().equals(itemId)) {
                return this.delete(cartitem);
            }
        }
        return false;
    }

    @Override
    public boolean updateCartItem(CartItem cartItem) {
        try {
            for (CartItem cartitem : this.getCart()) {
                if (cartitem.getItemId().equals(cartItem.getItemId())) {
                    cartitem.setItemId(cartItem.getItemId());
                    cartitem.setDescription(cartItem.getDescription());
                    cartitem.setUnitPrice(cartItem.getUnitPrice());
                    cartitem.setQuantity(cartItem.getQuantity());
                    return true;
                }
            }
        } catch (Exception ex) {
        }
        return false;
    }

    @Override
    public ArrayList<CartItem> getCart() {
        return cart;
    }

    public void remove() {
        cart = null;
    }
}
