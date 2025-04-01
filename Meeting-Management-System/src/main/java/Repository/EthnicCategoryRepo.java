package Repository;

import Entity.EthnicCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EthnicCategoryRepo extends JpaRepository<EthnicCategory,Integer> {
}
