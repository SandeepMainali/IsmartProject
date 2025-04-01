package Repository;

import Entity.Depart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartRepo extends JpaRepository<Depart,Integer> {
}
