package top.canoe0.blog.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import top.canoe0.blog.entity.user.Admin;
import top.canoe0.blog.entity.user.RegularUser;
import top.canoe0.blog.entity.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Article {
    @Id
    @GeneratedValue
    private int articleId;

    private int userId;

    private String userType;


    private String articleTitle;

    @Lob
    private String articleContent;

    private LocalDateTime releaseTime;
    private LocalDateTime lastModifyTime;


}
