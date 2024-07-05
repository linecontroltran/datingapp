package datingapp.datingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import datingapp.datingapp.entity.DataEntity;

public interface DataRepository extends JpaRepository<DataEntity, Long> {
}
