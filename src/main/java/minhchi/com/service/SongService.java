package minhchi.com.service;

import minhchi.com.models.Song;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SongService {
    List<Song> getAllSong();
    String addSong(Song song);
}
