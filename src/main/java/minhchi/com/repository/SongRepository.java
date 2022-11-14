package minhchi.com.repository;

import minhchi.com.models.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song, Integer> {
    List<Song> findAll();
    //List<Song> findAllByGenre(String genre);
}
