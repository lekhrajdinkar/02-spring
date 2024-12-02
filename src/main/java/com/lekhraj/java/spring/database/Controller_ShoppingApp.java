package com.lekhraj.java.spring.database;

import com.lekhraj.java.spring.database.entities.AppOrder;
import com.lekhraj.java.spring.database.entities.AppOrderDetail;
import com.lekhraj.java.spring.database.entities.Customer;
import com.lekhraj.java.spring.database.entities.Product;
import com.lekhraj.java.spring.database.entities.Cart;
import com.lekhraj.java.spring.database.repo.AppOrderRepository;
import com.lekhraj.java.spring.database.repo.CartRepository;
import com.lekhraj.java.spring.database.repo.CustomerRepository;
import com.lekhraj.java.spring.database.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/shopping-app")
public class Controller_ShoppingApp
{
    @Autowired    private CustomerRepository customerRepository;
    @Autowired    private ProductRepository productRepository;
    @Autowired    private AppOrderRepository orderRepository;
    @Autowired    private CartRepository cartRepository;

    // ==== CUSTOMER ======
    @PostMapping("/add-customer")
    public String addCustomer(@RequestParam String name, @RequestParam String email, @RequestParam String address) {
        Customer customer = new Customer();
        customer.setName(name);  customer.setEmail(email); customer.setAddress(address);

        customerRepository.save(customer);

       //Cart cart = new Cart();  cart.setCustomer(customer); cartRepository.save(cart);

        return "Customer added successfully with empty cart";
    }

    @PostMapping("/view-customer")
    public List<Customer> viewCustomer() {  return customerRepository.findAll(); }

    // ==== PRODUCT ======
    @PostMapping("/add-product")
    public String addProduct(@RequestBody Product product) {
        productRepository.save(product);
        return "Product added successfully!";
    }

    @PostMapping("/view-product")
    public List<Product> viewProduct() { return productRepository.findAll();   }

    // ==== Place order ======

    @PostMapping("/place-order-directly")
    public String placeOrder(@RequestParam Long customerId, @RequestParam Long[] productIds, @RequestParam short quantity) {
        // Fetch the customer
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer == null) {
            return "Customer not found!";
        }

        // Create a new order for the customer
        AppOrder order = new AppOrder();
        order.setCustomer(customer);
        orderRepository.save(order);

        // Add order details
        List<AppOrderDetail> orderDetail_list = new ArrayList<>();
        for (Long productId : productIds) {
            Product product = productRepository.findById(productId).orElse(null);
            if (product != null) {
                AppOrderDetail orderDetail = new AppOrderDetail();
                orderDetail.setOrder(order);
                orderDetail.setProductName(product.getProductName());
                orderDetail.setQuantity(quantity);
                orderDetail.setPrice(product.getPrice());
                orderDetail_list.add(orderDetail);
            }
        }
        order.setOrderDetails(orderDetail_list);

        orderRepository.save(order);
        return "Order placed successfully!";
    }

    /*@PostMapping("/add-product-to-cart")
    public String addToCart(@RequestParam Long customerId, @RequestParam Long productId, @RequestParam int quantity)
    {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer == null) {            return "Customer not found!";        }

        Product product = productRepository.findById(productId).orElse(null);
        if (product == null) {            return "Product not found!";        }

        // Fetch or create cart for the customer
        Cart cart = cartRepository.findByCustomer(customer).orElse(new Cart());
        cart.setCustomer(customer);
        cart.setQuantity(quantity);
        List temp_products = cart.getProducts(); temp_products.add(product);
        cart.setProducts(temp_products);

        cartRepository.save(cart);
        return "Product added to cart!";
    }*/
}
