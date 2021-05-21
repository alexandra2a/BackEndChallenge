package com.bonitasoft.reactiveworkshop.api;

import java.util.List;

import com.bonitasoft.reactiveworkshop.domain.Artist;
import com.bonitasoft.reactiveworkshop.exception.NotFoundException;
import com.bonitasoft.reactiveworkshop.repository.ArtistRepository;
import com.bonitasoft.reactiveworkshop.repository.CommentRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArtistAPI {


    private ArtistRepository artistRepository;
    private CommentRepository commentRepository;

    public ArtistAPI(ArtistRepository artistRepository, CommentRepository commentRepository) {
        this.artistRepository = artistRepository;
        this.commentRepository = commentRepository;
    }

    @GetMapping("/artist/{id}")
    public Artist findById(@PathVariable String id) throws NotFoundException {
        return artistRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @GetMapping("/artist/{id}/comments")
    public Artist findCommentsById(@PathVariable String id) throws NotFoundException {
        Artist artist;        
    	artist = artistRepository.findById(id).orElseThrow(NotFoundException::new);
    	artist.setComments(commentRepository.findByUrl("/" + id + "/last10"));
        return artist;
    }

    @GetMapping("/artist/{id}/comments/stream")
    public Artist findStreamById(@PathVariable String id) throws NotFoundException {
        Artist artist;        
    	artist = artistRepository.findById(id).orElseThrow(NotFoundException::new);
    	artist.setComments(commentRepository.findByUrl("/" + id + "/stream"));
        return artist;
    }

    @GetMapping("/artists")
    public List<Artist> findAll() throws NotFoundException {
        return artistRepository.findAll();
    }

}
