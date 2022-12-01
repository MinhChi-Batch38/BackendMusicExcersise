package minhchi.com.repository;

import minhchi.com.models.Song;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SongRepository extends JpaRepository<Song, Integer> {
    List<Song> findAll();

    Song findSongById(int id);
    //List<Song> findAllByGenre(String genre);
}
