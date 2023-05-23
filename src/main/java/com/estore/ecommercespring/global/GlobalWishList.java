package com.estore.ecommercespring.global;

import com.estore.ecommercespring.model.Product;

import java.util.ArrayList;
import java.util.List;

public class GlobalWishList {
    public static List<Product> wishList;

    static{
        wishList =new ArrayList<>();
    }
}
