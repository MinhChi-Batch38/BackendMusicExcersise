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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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

    @GetMapping("/get-songs")
    public Page<Song> pagination(@RequestParam(value = "keywords") String kw,@RequestParam (value = "page") int page, @RequestParam (value = "size") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
            Page<Song> songs;
            if (kw.isEmpty()) {
                songs = songRepository.findAll(pageable);
            } else {
                songs = songRepository.findAllByNameContaining(kw, pageable);
            }
            return songs;
        } catch (Exception err) {
            return null;
        }
    }

    @GetMapping("/search/{keyword}/{page}/{size}")
    public Page<Song> search(@PathVariable (value = "keyword") String kw, @PathVariable (value = "page") int page, @PathVariable (value = "size") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<Song> songs = songRepository.findAllByNameContaining(kw, pageable);
//        Page<Song> songs = songRepository.findAllByNameContainingOrSingerContaining(kw, pageable);

        return songs;
    }

    @GetMapping("/count-songs")
    public long countSongs() {
        return songRepository.count();
    }

    @PostMapping("/add-song")
    public ResponseEntity addSong(@RequestBody Song song) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        try {
            DateTimeFormatter formatter
                    = DateTimeFormatter.ofPattern(
                    "yyyy-MM-dd HH:mm:ss a");

            LocalDateTime time = LocalDateTime.now();
            Song newSong = new Song(song.getName(), song.getSinger(), song.getGenre(), song.getLink());
            newSong.setLastUpdate(time);

            Song addSong = songRepository.save(newSong);
            map.put("song", addSong);
            map.put("status", 201);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception err) {
            System.out.println(err);
            map.put("status", 404);
            map.put("message", "Add failed!");
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
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
