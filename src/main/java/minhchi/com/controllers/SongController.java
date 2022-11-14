package minhchi.com.controllers;


import minhchi.com.models.Song;
import minhchi.com.repository.SongRepository;
import minhchi.com.service.SongService;
import minhchi.com.service.impl.SongServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@EnableJpaRepositories
@RequestMapping("/songs")
public class SongController {

    @Autowired
    private SongRepository songRepository;
    SongService songService = new SongServiceImpl(songRepository);
    @GetMapping("/get-all")
    public List<Song> listAll() {
        List<Song> listSongs = songRepository.findAll();
        //model.addAttribute("listUsers", listUsers);
        return listSongs;
    }

    @PostMapping("/add-song")
    public String addSong(@RequestBody Song song) {
        try {
            Song newSong = new Song(song.getName(), song.getSinger(), song.getGenre(), song.getLink());
            songRepository.save(newSong);
            return "201";
        } catch (Exception err) {
            System.out.println(err);
            return "404";
        }
    }
}
