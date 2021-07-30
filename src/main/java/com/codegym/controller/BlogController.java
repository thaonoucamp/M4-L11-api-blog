package com.codegym.controller;

import com.codegym.model.Blog;
import com.codegym.model.Category;
import com.codegym.service.BlogService;
import com.codegym.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private CategoryService categoryService;

    //    @ModelAttribute("categories")
//    public Iterable<Category> categories() {
//        return categoryService.findAll();
//    }
    @ModelAttribute("categories")
    public Iterable<Category> categories() {
        return categoryService.findAll();
    }

    //    @GetMapping("/home")
//    public ModelAndView showList(){
//        Iterable<Blog> blogList = blogService.findAll();
//        ModelAndView modelAndView = new ModelAndView("/blog/list");
//        modelAndView.addObject("products",blogList);
//        return modelAndView;
//    }
    @GetMapping("/list-blogs")
    public ModelAndView showList() {
        Iterable<Blog> blogs = blogService.findAll();
        ModelAndView modelAndView = new ModelAndView("/blog/list");
        modelAndView.addObject("blogs", blogs);
        return modelAndView;
    }

    //    @GetMapping("/home")
//    public ModelAndView showList(@PageableDefault(value = 5) Pageable pageable) {
//        Page<Blog> blogList = blogService.findAll(pageable);
//        ModelAndView modelAndView = new ModelAndView("/blog/list");
//        modelAndView.addObject("products", blogList);
//        return modelAndView;
//    }
    @GetMapping("/home")
    public ModelAndView home(@PageableDefault(value = 3) Pageable pageable) {
        Page<Blog> blogs = blogService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("/blog/list");
        modelAndView.addObject("blogs", blogs);
        return modelAndView;
    }

    //    @GetMapping("/sort")
//    public ModelAndView sortByDate(@PageableDefault(size = 5, sort = "date", direction = Sort.Direction.ASC) Pageable pageable) {
//        Page<Blog> blogList = blogService.findAllOrOrderByDate(pageable);
//        ModelAndView modelAndView = new ModelAndView("/blog/list");
//        modelAndView.addObject("products", blogList);
//        return modelAndView;
//    }
    @GetMapping("/sort")
    public ModelAndView sortByDate(@PageableDefault(value = 3, sort = "date", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Blog> blogs = blogService.findAllOrOrderByDate(pageable);
        ModelAndView modelAndView = new ModelAndView("/blog/list");
        modelAndView.addObject("blogs", blogs);
        return modelAndView;
    }

    //    @PostMapping("/search-title")
//    public ModelAndView listBlogs(@RequestParam("title") Optional<String> title, Pageable pageable) {
//        Page<Blog> products;
//        if (title.isPresent()) {
//            products = blogService.findAllByTitleContaining(title.get(), pageable);
//        } else {
//            products = blogService.findAll(pageable);
//        }
//        ModelAndView modelAndView = new ModelAndView("/blog/list");
//        modelAndView.addObject("products", products);
//        return modelAndView;
//    }
    @PostMapping("/search-title")
    public ModelAndView resultSearch(@RequestParam("title") Optional<String> title, Pageable pageable) {
        Page<Blog> blogs;
        if (title.isPresent()) {
            blogs = blogService.findAllByTitleContaining(title.get(), pageable);
        } else {
            blogs = blogService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/blog/list");
        modelAndView.addObject("products", blogs);
        return modelAndView;
    }

    //    @GetMapping("/create-blog")
//    public ModelAndView showCreateForm() {
//        ModelAndView modelAndView = new ModelAndView("/blog/create");
//        modelAndView.addObject("blog", new Blog());
//        return modelAndView;
//    }
    @GetMapping("/create-blog")
    public ModelAndView showFormBlog() {
        ModelAndView modelAndView = new ModelAndView("/blog/create");
        modelAndView.addObject("blog", new Blog());
        return modelAndView;
    }

//    @PostMapping("/create-blog")
//    public ModelAndView saveBlog(@ModelAttribute("blog") Blog blog) {
//        blogService.save(blog);
//        ModelAndView modelAndView = new ModelAndView("/blog/create");
//        modelAndView.addObject("blog", new Blog());
//        modelAndView.addObject("message", "New blog created successfully!");
//        return modelAndView;
//    }

    @PostMapping("/create-blog")
    public ModelAndView saveBlog(@ModelAttribute("blog") Blog blog) {
        blogService.save(blog);
        ModelAndView modelAndView = new ModelAndView("/blog/create");
        modelAndView.addObject("blog", new Blog());
        modelAndView.addObject("message", "New blog created successfully");
        return modelAndView;
    }

    //    @GetMapping("/view-blog/{id}")
//    public ModelAndView showViewForm(@PathVariable Long id) {
//        Optional<Blog> blog = blogService.findById(id);
//        if (blog.isPresent()) {
//            ModelAndView modelAndView = new ModelAndView("/blog/view");
//            modelAndView.addObject("blog", blog.get());
//            return modelAndView;
//        } else {
//            ModelAndView modelAndView = new ModelAndView("/error.404");
//            return modelAndView;
//        }
//    }
    @GetMapping("/edit-blog/{id}")
    public ModelAndView showFormView(@PathVariable Long id) {
        Optional<Blog> blog = blogService.findById(id);
        if (blog.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/blog/view");
            modelAndView.addObject("blog", blog.get());
            return modelAndView;
        } else {
            return new ModelAndView("/error.404");
        }
    }

    //    @GetMapping("/edit-blog/{id}")
//    public ModelAndView showEditForm(@PathVariable Long id) {
//        Optional<Blog> blog = blogService.findById(id);
//        if (blog.isPresent()) {
//            ModelAndView modelAndView = new ModelAndView("/blog/edit");
//            modelAndView.addObject("blog", blog.get());
//            return modelAndView;
//        } else {
//            ModelAndView modelAndView = new ModelAndView("/error.404");
//            return modelAndView;
//        }
//    }
    @GetMapping("edit-blog/{id}")
    public ModelAndView showFormEdit(@PathVariable Long id) {
        Optional<Blog> blog = blogService.findById(id);
        if (blog.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("blog/edit");
            modelAndView.addObject("blog", blog.get());
            return modelAndView;
        } else {
            return new ModelAndView("/error.404");
        }
    }

    //    @PostMapping("/edit-blog")
//    public ModelAndView updateCustomer(@ModelAttribute("blog") Blog blog) {
//        blogService.save(blog);
//        ModelAndView modelAndView = new ModelAndView("/blog/edit");
//        modelAndView.addObject("blog", blog);
//        modelAndView.addObject("message", "Blog updated successfully");
//        return modelAndView;
//    }
    @PutMapping("/edit-blog")
    public ModelAndView updateBlog(@ModelAttribute("blog") Blog blog) {
        blogService.save(blog);
        ModelAndView modelAndView = new ModelAndView("/blog/edit");
        modelAndView.addObject("blog", blog);
        modelAndView.addObject("message", "Blog updated successfully");
        return modelAndView;
    }

    //    @GetMapping("/delete-blog/{id}")
//    public ModelAndView showDeleteForm(@PathVariable Long id) {
//        Optional<Blog> blog = blogService.findById(id);
//        if (blog.isPresent()) {
//            ModelAndView modelAndView = new ModelAndView("/blog/delete");
//            modelAndView.addObject("blog", blog.get());
//            return modelAndView;
//        } else {
//            ModelAndView modelAndView = new ModelAndView("/error.404");
//            return modelAndView;
//        }
//    }
    @GetMapping("delete-blog/{id}")
    public ModelAndView showFormDelete(@PathVariable Long id) {
        Optional<Blog> blog = blogService.findById(id);
        if (blog.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/blog/delete");
            modelAndView.addObject("blog", blog);
            return modelAndView;
        } else {
            return new ModelAndView("/error.404");
        }
    }

    //
//    @DeleteMapping ("/delete-blog")
//    public String deleteCustomer(@ModelAttribute("blog") Blog blog) {
//        blogService.remove(blog.getId());
//        return "redirect:home";
//    }
    @DeleteMapping("/delete-blog")
    public String deleteBlog(@ModelAttribute("blog") Blog blog) {
        blogService.remove(blog.getId());
        return "redirect:/home";
    }

    //    @PostMapping("/view-category")
//    public ModelAndView viewCategory(@RequestParam("category") Long id) {
//        Optional<Category> categoryOptional = categoryService.findById(id);
//        if (!categoryOptional.isPresent()) {
//            return new ModelAndView("/error.404");
//        }
//        Iterable<Blog> products = blogService.findAllByCategory(categoryOptional.get());
//        ModelAndView modelAndView = new ModelAndView("/category/view");
//        modelAndView.addObject("category", categoryOptional.get());
//        modelAndView.addObject("products", products);
//        return modelAndView;
//    }
    @PostMapping("/view-category")
    public ModelAndView viewCategory(@RequestParam("category") Long id) {
        Optional<Category> category = categoryService.findById(id);
        if (!category.isPresent()) {
            return new ModelAndView("error.404");
        }
        Iterable<Blog> blogs = blogService.findAllByCategory(category.get());
        ModelAndView modelAndView = new ModelAndView("/category/view");
        modelAndView.addObject("category", category.get());
        modelAndView.addObject("blogs", blogs);
        return modelAndView;
    }
}