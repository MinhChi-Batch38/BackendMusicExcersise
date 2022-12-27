package minhchi.com.repository;

import minhchi.com.models.Song;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song, Integer> {
    List<Song> findAll();

    Song findSongById(int id);

    Page<Song> findAllByNameContaining(String kw, Pageable pageable);

//    Page<Song> findAllByNameContainingOrSingerContaining(String kw, Pageable pageable);
    @Query(value = "Select * from songs where name like %?1% or singer like %?1%", nativeQuery = true)
    List<Song> findAllByNameContainingOrSingerContaining(String kw);
    //List<Song> findAllByGenre(String genre);
}
