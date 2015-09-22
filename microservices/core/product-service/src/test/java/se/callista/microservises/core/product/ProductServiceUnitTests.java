package se.callista.microservises.core.product;

import junit.framework.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import se.callista.microservises.core.product.service.ProductService;

/**
 * Interal Unit Tests against the inner Service class. Does not use
 * REST API!
 * Created by christian on 14.09.15.
 */
@Test
public class ProductServiceUnitTests {

    private ProductService productService;

    @BeforeClass
    public void Setup(){
        productService = new ProductService();
    }

    @Test
    public void TestId(){
        int actualId = productService.getProduct(42).getProductId();
        Assert.assertEquals(actualId,42);
    }

}
