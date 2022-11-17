package minhchi.com.controllers;


//import com.google.api.client.util.DateTime;
import minhchi.com.models.Song;
import minhchi.com.repository.SongRepository;
//import minhchi.com.service.SongService;
//import minhchi.com.service.impl.SongServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@EnableJpaRepositories
@RequestMapping("/songs")
public class SongController {

    @Autowired
    private SongRepository songRepository;
    //SongService songService = new SongServiceImpl(songRepository);
    @GetMapping("/get-all")
    public List<Song> listAll() {
        List<Song> listSongs = songRepository.findAll();
        //model.addAttribute("listUsers", listUsers);
        return listSongs;
    }

    @PostMapping("/add-song")
    public String addSong(@RequestBody Song song) {
        try {
            DateTimeFormatter formatter
                    = DateTimeFormatter.ofPattern(
                    "yyyy-MM-dd HH:mm:ss a");

            LocalDateTime time = LocalDateTime.now();
            Song newSong = new Song(song.getName(), song.getSinger(), song.getGenre(), song.getLink());
            newSong.setLastUpdate(time);

            songRepository.save(newSong);
            return "201";
        } catch (Exception err) {
            System.out.println(err);
            return "404";
        }
    }
    @PutMapping("/edit-song")
    public String editSong(@RequestBody Song song) {
        try {
            DateTimeFormatter formatter
                    = DateTimeFormatter.ofPattern(
                    "yyyy-MM-dd HH:mm:ss a");

            LocalDateTime time = LocalDateTime.now();
            //Song newSong = new Song(song.getName(), song.getSinger(), song.getGenre(), song.getLink());
            song.setLastUpdate(time);

            songRepository.save(song);
            return "200";
        } catch (Exception err) {
            System.out.println(err);
            return "404";
        }
    }

    @DeleteMapping("/delete-song")
    public String deleteSong(@RequestBody Song song) {
        try {
            songRepository.delete(song);
            return "200";
        } catch (Exception err) {
            System.out.println(err);
            return "404";
        }
    }
}
