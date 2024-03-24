package com.king.Bibliotheque.Services;

import com.king.Bibliotheque.Exceptions.RoleException;
import com.king.Bibliotheque.Exceptions.RoleNotFoundException;
import com.king.Bibliotheque.Models.Category;
import com.king.Bibliotheque.Repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void addCategory( Category category){
      if (categoryRepository.findByTitle(category.getTitle()) != null) {
        throw new RoleException("Title is already taken");
    }
    this.categoryRepository.save(category);
    }

    public List<Category> search(){
        return this.categoryRepository.findAll();
    }

    public Category getCategoryById(int id) {
        Optional<Category> optionalCategory = this.categoryRepository.findById(id);
        return optionalCategory.orElse(null);
    }

    public Category updateCategoryDetails(int id, Category updatedCategory) {
        Optional<Category> existingCategory = categoryRepository.findById(id);

        if (existingCategory.isPresent()) {
            Category category = existingCategory.get();

            // Update category details as needed
            category.setTitle(updatedCategory.getTitle());

            // Save the updated role to the database
            return categoryRepository.save(category);
        } else {
            throw new RoleNotFoundException("Category not found");
        }
    }
    public void deleteCategory(int id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            categoryRepository.deleteById(id);
        } else {
            throw new RoleNotFoundException("Category not found");
        }
    }
}
