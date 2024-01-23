package mysqlDemo.example.mysqlDemo.repo;

import mysqlDemo.example.mysqlDemo.Entities.AppData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MySQLRepository extends JpaRepository<AppData, Long> {
}
