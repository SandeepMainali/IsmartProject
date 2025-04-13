package Meeting_Management.System.Repository;

import Meeting_Management.System.Entity.Depart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartRepo extends JpaRepository<Depart,Integer> {
    public List<Depart> findAllById(long id);
    public boolean existsByDepartName(String departName);
}
