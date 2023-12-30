package zadatak.TIS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zadatak.TIS.model.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCodeAndName(String code, String name);

}
