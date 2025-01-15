package tn.esprit.gamepi.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.gamepi.entities.News;
import tn.esprit.gamepi.repositories.NewsRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class NewsService {

    @Autowired
    private NewsRepository newsRepository;

    public News createNews(News news) {
        return newsRepository.save(news);
    }

    public List<News> getAllNews() {
        return newsRepository.findAll();
    }

    public Optional<News> getNewsById(Long id) {
        return newsRepository.findById(id);
    }

    public News updateNews(Long id, News newsDetails) {
        News news = newsRepository.findById(id).orElseThrow(() -> new RuntimeException("News not found"));
        news.setHeadline(newsDetails.getHeadline());
        news.setContent(newsDetails.getContent());
        news.setDate(newsDetails.getDate());
        return newsRepository.save(news);
    }

    public void deleteNews(Long id) {
        newsRepository.deleteById(id);
    }
}
