package com.estore.ecommercespring.global;

import com.estore.ecommercespring.model.Product;

import java.util.ArrayList;
import java.util.List;

public class GlobalCart {
  public static List<Product> cart;
  static{
      cart = new ArrayList<>();
  }
}
