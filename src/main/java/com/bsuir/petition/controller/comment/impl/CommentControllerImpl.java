package com.bsuir.petition.controller.comment.impl;

import com.bsuir.petition.bean.dto.comment.CommentListDTO;
import com.bsuir.petition.bean.dto.comment.ShortCommentDTO;
import com.bsuir.petition.bean.entity.Comment;
import com.bsuir.petition.controller.comment.CommentController;
import com.bsuir.petition.service.comment.CommentService;
import com.bsuir.petition.service.comment.exception.CommentNotFoundException;
import com.bsuir.petition.service.comment.exception.SuchCommentExistsException;
import com.bsuir.petition.service.exception.ErrorInputException;
import com.bsuir.petition.service.exception.ServerException;
import com.bsuir.petition.service.petition.exception.PetitionNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class CommentControllerImpl implements CommentController {

    private CommentService commentService;

    @Autowired
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    @Override
    public void addComment(@RequestBody ShortCommentDTO shortCommentDTO,
                           @PathVariable long id) throws ServerException, ErrorInputException, SuchCommentExistsException, PetitionNotFoundException {
        commentService.addComment(shortCommentDTO, id);
    }

    @Override
    public CommentListDTO getComments(@PathVariable long id) throws ServerException, PetitionNotFoundException {
        CommentListDTO commentListDTO;
        commentListDTO = commentService.getComments(id);
        return commentListDTO;
    }

    public List<Comment> getAllComments() {
        List<Comment> comments;
        comments = commentService.getAllComments();
        return comments;
    }

    @Override
    public ModelAndView downloadComments() {
        List<Comment> comments = getAllComments();
        return new ModelAndView("pdfBuilder", "comments", comments);
    }

    @Override
    public void deleteComment(@PathVariable long id) throws ServerException, CommentNotFoundException {
        commentService.deleteComment(id);
    }
}
