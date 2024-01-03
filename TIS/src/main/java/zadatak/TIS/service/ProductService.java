package zadatak.TIS.service;

import org.springframework.stereotype.Service;
import zadatak.TIS.model.Product;
import zadatak.TIS.repository.ProductRepository;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final HnbService hnbService;

    public ProductService(ProductRepository productRepository, HnbService hnbService) {
        this.productRepository = productRepository;
        this.hnbService = hnbService;

    }



    public List<Product> getProductsByCodeAndName(String code, String name) {
        if (code != null && name != null) {
            // Dohvati podatke prema šifri i imenu
            return productRepository.findByCodeAndName(code, name);
        } else if (code != null) {
            throw new IllegalArgumentException("Missing code");
        } else if (name != null) {
            throw new IllegalArgumentException("Missing name");
        } else {
            // Dohvati sve
            return productRepository.findAll();
        }

    }

    public Product createProduct(Product product) {

        // Dohvati exchange rate za USD
        Double exchangeRate = hnbService.getExchangeRate("USD");

        if (exchangeRate != null) {
            // Izračunaj cijenu u USD
            double priceUsd = product.getPriceEUR() * exchangeRate;
            product.setPriceUSD(priceUsd);
        }
        // Spremi u bazu
        return productRepository.save(product);
    }






}
