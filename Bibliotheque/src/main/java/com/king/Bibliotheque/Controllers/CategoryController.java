package com.king.Bibliotheque.Controllers;

import com.king.Bibliotheque.Models.Category;
import com.king.Bibliotheque.Services.CategoryService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "category")
public class CategoryController {
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

     @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public void addCategory(@RequestBody Category category){
        this.categoryService.addCategory(category);
    }
    @GetMapping(path = "/", produces = APPLICATION_JSON_VALUE)
    public List<Category> getCategory(){
        return this.categoryService.search();
    }

    @GetMapping(path = "{id}", produces = APPLICATION_JSON_VALUE)
    public Category getCategoryById(@PathVariable int id){
        return this.categoryService.getCategoryById(id);
    }

    @PutMapping(path = "{id}" ,consumes = APPLICATION_JSON_VALUE)
    public Category updateCategory(@PathVariable int id, @RequestBody Category updatedCategory) {
        return categoryService.updateCategoryDetails(id, updatedCategory);
    }

    @DeleteMapping(path = "{id}")
    public void deleteCategory(@PathVariable int id) {
        categoryService.deleteCategory(id);
    }

}
