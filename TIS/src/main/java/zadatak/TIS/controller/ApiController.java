package zadatak.TIS.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zadatak.TIS.model.PopularProductDTO;
import zadatak.TIS.model.Product;
import zadatak.TIS.service.ProductService;
import zadatak.TIS.service.ReviewService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
public class ApiController {

    private final ProductService productService;
    private final ReviewService reviewService;


    //@Autowired
    public ApiController(ProductService productService, ReviewService reviewService) {
        this.productService = productService;
        this.reviewService = reviewService;

    }


    // Dohvat svih proizvoda ili filtrirano - moguce koristenje i @Pathvariable
    @GetMapping("/getProducts")
    public ResponseEntity<List<Product>> getProducts(
            @RequestParam(required = false) String code,
            @RequestParam(required = false) String name
    ) {
        List<Product> products = productService.getProductsByCodeAndName(code, name);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }


    // Dohvat 3 najpopularnija proizvoda
    @GetMapping("/getPopular")
    public ResponseEntity<Map<String, List<PopularProductDTO>>> getTop3PopularProducts() {
        try {
            // selecta prosijecnu ocijenu za svaki proizvod, pa u sljedecem koraku usporeduje i sortira
            List<Object[]> popularProducts = reviewService.findTop3PopularProducts();

            // Sortiranje liste prema prosijeku, descending - Kovačević skripta lambde
            popularProducts.sort((a, b) -> Double.compare((Double) b[1], (Double) a[1]));

            // Dohvat samo 3 proizvoda
            List<PopularProductDTO> popularProductDTOs = popularProducts.stream()
                    .limit(3)
                    .map(result -> {
                        PopularProductDTO dto = new PopularProductDTO();
                        dto.setName((String) result[0]);
                        dto.setAverageRating((Double) result[1]);
                        return dto;
                    })
                    .collect(Collectors.toList());

            Map<String, List<PopularProductDTO>> response = new HashMap<>();
            response.put("popularProducts", popularProductDTOs);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/insertProduct")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product createdProduct = productService.createProduct(product);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    // za insert review-ova - nije implementirano do kraja (zadatak ne traži)
/*    @PostMapping("/reviews")
    public ResponseEntity<Review> addReview(@RequestBody Review review) {
        Review savedReview = reviewService.saveReview(review);
        return new ResponseEntity<>(savedReview, HttpStatus.CREATED);
    }*/

}
