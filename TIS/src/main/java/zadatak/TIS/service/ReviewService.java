package zadatak.TIS.service;


import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zadatak.TIS.model.Review;
import zadatak.TIS.repository.ReviewRepository;

import java.util.List;

@Service
public class ReviewService {


    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

/*    @Transactional
    public Review saveReview(Review review) {
       return reviewRepository.save(review);

    }*/

    @Transactional
    public List<Object[]> findTop3PopularProducts() {
        return reviewRepository.findTop3PopularProducts();
    }

}
