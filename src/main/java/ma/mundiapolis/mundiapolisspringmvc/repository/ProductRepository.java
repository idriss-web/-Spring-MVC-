package ma.mundiapolis.mundiapolisspringmvc.repository;

import ma.mundiapolis.mundiapolisspringmvc.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long>
{

}