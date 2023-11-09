package com.example.demo.DAO;

import com.example.demo.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    Optional<Category>findByCategoryName(String name);
    @Query(value = "select category_id from category where category_name=:name", nativeQuery = true)
    int findParentId(String name);
   Category findByCategoryId(Integer id);

    @Query(value = "select category_name from category where parent_id IS NULL",nativeQuery = true)
    List<String> findParentCategory();
    @Query(value = "select category_name from category where parent_id=:parentId",nativeQuery = true)
    List<String> findChildCategory(int parentId);


}
