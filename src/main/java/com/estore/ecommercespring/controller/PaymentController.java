package com.estore.ecommercespring.controller;

import com.estore.ecommercespring.dto.Order;
import com.estore.ecommercespring.global.GlobalCart;
import com.estore.ecommercespring.service.PaypalService;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class PaymentController {
   private final PaypalService paypalService;
   private static final String SUCCESS_URL = "pay/success";
   private static final String CANCEL_URL = "pay/cancel";


   @GetMapping("/checkOut")
   public ModelAndView checkOutPage(HttpServletRequest request){
      ModelAndView model = new ModelAndView();
      HttpSession session = request.getSession();

      String userEmail = (String) session.getAttribute("U_email");
      if(userEmail == null){
         model.setViewName("redirect:/");
         return model;
      }

      model.setViewName("checkOut");
      model.addObject("cartCount", GlobalCart.cart.size());
      model.addObject("cartTotalAmount", GlobalCart.cart.stream().mapToDouble(product -> Double.parseDouble(product.getPrice())).sum());
      Order order = Order.builder().price(GlobalCart.cart.stream().mapToDouble(prod-> Double.parseDouble(prod.getPrice())).sum()).build();
      model.addObject("order", order);
      return model;
   }

   @PostMapping("/pay")
   public ModelAndView payment(@ModelAttribute("order") Order order, HttpServletRequest request) throws PayPalRESTException {
      ModelAndView model = new ModelAndView();
      HttpSession session = request.getSession();

      String userEmail = (String) session.getAttribute("U_email");
      if(userEmail == null){
         model.setViewName("redirect:/");
         return model;
      }


      Payment payment = paypalService.createPayment(
              order.getPrice(),
              order.getCurrency(),
              order.getMethod(),
              order.getIntent(),
              order.getDescription(),
              "http://localhost:8085/"+SUCCESS_URL,
              "http://localhost:8085/"+CANCEL_URL
      );
      for(Links link: payment.getLinks()){
         if(link.getRel().equals("approval_url")){
            model.setViewName("redirect:"+link.getHref());
            return model;
         }
      }
      model.setViewName("redirect:/checkOut");
   return model;
   }

   @GetMapping(value=CANCEL_URL)
   public String cancelPayment(){
      return "paymentCancel";
   }
   @GetMapping(value=SUCCESS_URL)
   public String successfulPayment(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId){
      try {
         Payment payment = paypalService.executePayment(paymentId, payerId);
         if(payment.getState().equals("approved")){
            return "paymentSuccess";
         }
      } catch (PayPalRESTException e) {
         throw new RuntimeException(e);
      }
      return "redirect:/checkOut";
   }



}
