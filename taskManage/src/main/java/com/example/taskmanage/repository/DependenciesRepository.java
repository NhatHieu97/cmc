package com.example.taskmanage.repository;

import com.example.taskmanage.model.Dependencies;
import com.example.taskmanage.model.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DependenciesRepository extends JpaRepository<Dependencies, Long> {
}