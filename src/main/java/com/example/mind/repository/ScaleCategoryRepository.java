package com.example.mind.repository;

import com.example.mind.Entries.ScaleCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScaleCategoryRepository extends JpaRepository<ScaleCategory, String> {
    ScaleCategory findByCategoryName(String categoryName);
}
