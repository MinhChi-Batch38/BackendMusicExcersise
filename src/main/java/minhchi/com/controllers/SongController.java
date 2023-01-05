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
//                songs = songRepository.findAllByNameContaining(kw, pageable);
                  songs = songRepository.findAllByNameContainingOrSingerContaining(kw, pageable);
            }
            return songs;
        } catch (Exception err) {
            return null;
        }
    }

    @GetMapping("/check-song")
    public ResponseEntity checkSongs(@RequestParam (value = "name") String name, @RequestParam (value = "singer") String singer) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        try {
            Song check = songRepository.findSongByNameAndSinger(name, singer);
            if (check != null) {
                map.put("status", 404);
                map.put("message", "The song has already been existed!");
            } else {

                map.put("message", "OK");
                map.put("status", 200);
            }
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception ex) {
            System.out.println(ex);
            map.put("status", 500);
            map.put("message", "Can't check song! Please try again!");
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }
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
                map.put("message", "The song has been added!");
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception err) {
            System.out.println(err);
            map.put("status", 404);
            map.put("message", "Add failed!");
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/edit-song")
    public ResponseEntity editSong(@RequestBody Song song) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        try {
                DateTimeFormatter formatter
                        = DateTimeFormatter.ofPattern(
                        "yyyy-MM-dd HH:mm:ss a");

                LocalDateTime time = LocalDateTime.now();
                //Song newSong = new Song(song.getName(), song.getSinger(), song.getGenre(), song.getLink());
                song.setLastUpdate(time);

                Song editSong = songRepository.save(song);
                map.put("song", editSong);
                map.put("status", 200);
                map.put("message", "The song has been updated!");
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception err) {
            System.out.println(err);
            map.put("status", 404);
            map.put("message", "Edit failed!");
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
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
    public ResponseEntity deleteAllSong(@PathVariable(value = "ids") List<String> ids) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        boolean successDelete = true;
        String failedSongs = "";
        Song song = new Song();
        try {
            for (String id : ids) {
                song = songRepository.findSongById(Integer.parseInt(id));
                if (deleteSong(song) == "404") {
                    failedSongs += song.getName() + ".";
                    successDelete = false;
                }
            }
            if (!successDelete) {
                map.put("status", 404);
                map.put("message", "Can't delete songs: " + failedSongs);
            } else {
                map.put("status", 200);
                map.put("message", "The songs have been deleted!");
            }
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception err) {
            map.put("status", 500);
            map.put("message", "Delete failed!");
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }

    }
}
