package com.bsuir.petition.service.comment.util.impl;

import com.bsuir.petition.bean.dto.comment.CommentDTO;
import com.bsuir.petition.bean.dto.comment.ShortCommentDTO;
import com.bsuir.petition.bean.entity.Comment;
import com.bsuir.petition.bean.entity.Petition;
import com.bsuir.petition.bean.entity.User;
import com.bsuir.petition.dao.CommentDao;
import com.bsuir.petition.dao.PetitionDao;
import com.bsuir.petition.dao.UserDao;
import com.bsuir.petition.security.TokenAuthentication;
import com.bsuir.petition.service.comment.util.ExchangerComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class ExchangerCommentImpl implements ExchangerComment {

    private PetitionDao petitionDao;
    private UserDao userDao;

    @Autowired
    public void setPetitionDao(PetitionDao petitionDao) { this.petitionDao = petitionDao; }

    @Autowired
    public void setUserDao(UserDao userDao) { this.userDao = userDao; }

    @Override
    public Comment getComment(ShortCommentDTO shortCommentDTO, long id) {
        Comment comment = new Comment();
        comment.setText(shortCommentDTO.getText());
        Petition petition = petitionDao.getPetition(id);
        comment.setPetition(petition);
        TokenAuthentication tokenAuthentication;
        tokenAuthentication = (TokenAuthentication) SecurityContextHolder.getContext().getAuthentication();
        User user = userDao.getUserById((Long)tokenAuthentication.getDetails());
        comment.setUser(user);
        return comment;
    }
}
