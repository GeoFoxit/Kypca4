package geo.PurchaseService.repos;

import geo.PurchaseService.models.Purchase;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends CrudRepository<Purchase, Integer> {
    Purchase getById(Integer id);
}
