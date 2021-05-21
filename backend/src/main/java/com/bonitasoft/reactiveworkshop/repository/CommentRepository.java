package com.bonitasoft.reactiveworkshop.repository;

import com.bonitasoft.reactiveworkshop.domain.Comment;
import com.bonitasoft.reactiveworkshop.exception.NotFoundException;

import reactor.core.publisher.Flux;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.reactive.function.client.WebClient;

@Repository
public class CommentRepository {
	private String externalUrl = "http://localhost:3004/comments";

    private RestTemplate restTemplate;

    public CommentRepository(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
	public Comment[] findByUrl(String url) throws NotFoundException {
        ResponseEntity<Comment[]> responseEntity = restTemplate.getForEntity(externalUrl + url, Comment[].class);
        Comment[] comments = responseEntity.getBody();
        
        // todo: check exception after meeting about
        // MediaType contentType = responseEntity.getHeaders().getContentType();
        // HttpStatus statusCode = responseEntity.getStatusCode();
        
        return comments;
    }
	
	public void findStreamByUrl(String url) throws NotFoundException {
		final Flux<ServerSentEvent> stream = WebClient
	            .create("http://localhost:3004")
	            .get().uri("/comments/a5c59c0315098e6d67bb57610f7fb9b1/stream")
	            .retrieve()
	            .bodyToFlux(ServerSentEvent.class);

	    //stream.subscribe(sse -> log.info("Received: {}", sse));

	    //TimeUnit.MINUTES.sleep(10);
    }
	/*import org.springframework.http.codec.ServerSentEvent;
	import org.springframework.web.reactive.function.client.WebClient;

	public static void main(String[] args) throws InterruptedException {
	    final Flux<ServerSentEvent> stream = WebClient
	            .create("http://emojitrack-gostreamer.herokuapp.com")
	            .get().uri("/subscribe/eps")
	            .retrieve()
	            .bodyToFlux(ServerSentEvent.class);

	    stream.subscribe(sse -> log.info("Received: {}", sse));

	    TimeUnit.MINUTES.sleep(10);
	}*/
}
