package tn.esprit.devops_project.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.devops_project.entities.Product;
import tn.esprit.devops_project.entities.ProductCategory;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.repositories.ProductRepository;
import tn.esprit.devops_project.repositories.StockRepository;
import tn.esprit.devops_project.services.ProductServiceImpl;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;

public class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private StockRepository stockRepository;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddProduct() {
        Long stockId = 1L;
        Stock stock = new Stock();
        Product product = new Product();
        when(stockRepository.findById(stockId)).thenReturn(java.util.Optional.of(stock));
        when(productRepository.save(product)).thenReturn(product);
        Product addedProduct = productService.addProduct(product, stockId);
        assertEquals(stock, product.getStock()); // Check if product is associated with the correct stock
        assertEquals(product, addedProduct);
    }

    @Test
    void testRetrieveProduct() {
        Long productId = 1L;
        Product product = new Product();
        when(productRepository.findById(productId)).thenReturn(java.util.Optional.of(product));
        Product retrievedProduct = productService.retrieveProduct(productId);
        assertEquals(product, retrievedProduct);
    }

    @Test
    void testRetrieveAllProduct() {
        List<Product> products = new ArrayList<>();
        // Add some test products to the list
        products.add(new Product());
        when(productRepository.findAll()).thenReturn(products);
        List<Product> retrievedProducts = productService.retreiveAllProduct();
        assertEquals(1, retrievedProducts.size()); // Check if the list contains one product
    }

    @Test
    void testRetrieveProductByCategory() {
        ProductCategory category = ProductCategory.ELECTRONICS;
        List<Product> products = new ArrayList<>();
        // Add some test products to the list
        products.add(new Product());
        when(productRepository.findByCategory(category)).thenReturn(products);
        List<Product> retrievedProducts = productService.retrieveProductByCategory(category);
        assertEquals(1, retrievedProducts.size()); // Check if the list contains one product
    }

    @Test
    void testDeleteProduct() {
        Long productId = 1L;
        doNothing().when(productRepository).deleteById(productId);
        productService.deleteProduct(productId);
        verify(productRepository, times(1)).deleteById(productId);
    }

    @Test
    void testRetreiveProductStock() {
        Long stockId = 1L;
        List<Product> products = new ArrayList<>();
        // Add some test products to the list
        products.add(new Product());
        when(productRepository.findByStockIdStock(stockId)).thenReturn(products);
        List<Product> retrievedProducts = productService.retreiveProductStock(stockId);
        assertEquals(1, retrievedProducts.size()); // Check if the list contains one product
    }
}
