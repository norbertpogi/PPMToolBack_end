package io.learn.path.com.ppmtool.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.learn.path.com.ppmtool.domain.BackLog;

@Repository
public interface BackLogRepository extends CrudRepository<BackLog, Long>{
	

}
