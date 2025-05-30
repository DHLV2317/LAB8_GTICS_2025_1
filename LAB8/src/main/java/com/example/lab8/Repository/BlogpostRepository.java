package com.example.lab8.Repository;

import com.example.lab8.Entity.Blogpost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogpostRepository extends JpaRepository<Blogpost, Long> {
}

