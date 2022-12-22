package minhchi.com.controllers;



//import com.google.api.client.util.DateTime;
import minhchi.com.models.Song;
import minhchi.com.repository.SongRepository;
//import minhchi.com.service.SongService;
//import minhchi.com.service.impl.SongServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import minhchi.com.service.FileService;
import minhchi.com.service.impl.FileServiceImpl;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
//@EnableJpaRepositories
@RequestMapping("/songs")
public class SongController {

    @Autowired
    private SongRepository songRepository;
    private FileService fileService = new FileServiceImpl();
    //SongService songService = new SongServiceImpl(songRepository);
    @GetMapping("/get-all")
    public List<Song> listAll() {
        List<Song> listSongs = songRepository.findAll(Sort.by("id").descending());
        //model.addAttribute("listUsers", listUsers);
        return listSongs;
    }

    @GetMapping("get-songs/{page}/{size}")
    public List<Song> pagination(@PathVariable (value = "page") int page, @PathVariable (value = "size") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<Song> songs = songRepository.findAll(pageable);
        return songs.getContent();
    }

    @GetMapping("/count-songs")
    public long countSongs() {
        return songRepository.count();
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
        String nameFile = song.getLink().replace("https://firebasestorage.googleapis.com/v0/b/music-d3f39.appspot.com/o/", "").replace("?alt=media", "");
        try {
            if (fileService.delete(nameFile)) {
                songRepository.delete(song);
                return "200";
            }
            return "404";
        } catch (Exception err) {
            System.out.println(err);
            return "404";
        }
    }
    @DeleteMapping("/delete-songs/{ids}")
    public String deleteAllSong(@PathVariable(value = "ids") List<String> ids) {
        boolean successDelete = true;
        String failedSongs = "";
        Song song = new Song();
        for (String id : ids) {
            song = songRepository.findSongById(Integer.parseInt(id));
            if (deleteSong(song) == "404") {
                failedSongs += song.getName() + ".";
                successDelete = false;
            }
        }
        if (!successDelete) {
            return failedSongs;
        }
        return "200";
    }
}
