package tn.esprit.gamepi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.gamepi.entities.News;
import tn.esprit.gamepi.services.NewsService;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @PostMapping("/add")
    public News createNews(@RequestBody News news) {
        return newsService.createNews(news);
    }

    @GetMapping("/all")
    public List<News> getAllNews() {
        return newsService.getAllNews();
    }

    @GetMapping("/{id}")
    public Optional<News> getNewsById(@PathVariable Long id) {
        return newsService.getNewsById(id);
    }

    @PutMapping("/update/{id}")
    public News updateNews(@PathVariable Long id, @RequestBody News news) {
        return newsService.updateNews(id, news);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteNews(@PathVariable Long id) {
        newsService.deleteNews(id);
    }
}
