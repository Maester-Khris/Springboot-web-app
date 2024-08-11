package nk.projects.webapp.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import nk.projects.webapp.models.Task;

@Repository
public interface TaskRepository extends CrudRepository<Task, Integer> {
    Optional<Task> findByTasktitle(String title);
}
