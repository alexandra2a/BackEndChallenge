package com.bonitasoft.reactiveworkshop.api;

import com.bonitasoft.reactiveworkshop.exception.NotFoundException;
import com.bonitasoft.reactiveworkshop.repository.CommentRepository;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentApi {

    private CommentRepository commentRepository;

    public CommentApi(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

	@RequestMapping(value="/comments/{id}/last10", method=RequestMethod.GET)
	public @ResponseBody Object[] findByIdLast10(@PathVariable String id) throws NotFoundException {
	    return commentRepository.findByUrl("/" + id + "/last10");
	}

	@RequestMapping(value="/comments/{id}/stream", method=RequestMethod.GET)
	public @ResponseBody Object[] findByIdStream(@PathVariable String id) throws NotFoundException {
	    return commentRepository.findByUrl("/" + id + "/stream");
	}

}
