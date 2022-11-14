package minhchi.com.service.impl;

import minhchi.com.models.Song;
import minhchi.com.repository.SongRepository;
import minhchi.com.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

import java.util.List;

@EnableJpaRepositories
@Service
public class SongServiceImpl implements SongService {
    @Autowired
    private SongRepository repository;
    @Autowired
    public SongServiceImpl(SongRepository repository) {
        this.repository = repository;
    }
    @Override
    public List<Song> getAllSong() {
        return repository.findAll();
    }

    @Override
    public String addSong(Song song) {
        try {
            repository.save(song);
            return "201";
        } catch (Exception err) {
            System.out.println(err);
            return "404";
        }
    }
}
