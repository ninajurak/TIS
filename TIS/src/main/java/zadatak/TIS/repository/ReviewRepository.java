package zadatak.TIS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import zadatak.TIS.model.Review;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("SELECT r.product.name, AVG(CAST(r.rating AS DOUBLE)) " +
            "FROM Review r " +
            "GROUP BY r.product.id, r.product.name " +
            "ORDER BY AVG(CAST(r.rating AS DOUBLE)) DESC")
    List<Object[]> findTop3PopularProducts();

}

