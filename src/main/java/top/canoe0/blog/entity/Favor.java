package top.canoe0.blog.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import top.canoe0.blog.entity.user.Admin;
import top.canoe0.blog.entity.user.RegularUser;
import top.canoe0.blog.entity.user.User;

import javax.persistence.*;

@Data
@Entity
public class Favor {
    @GeneratedValue
    @Id
    private int favorId;

    private boolean flag;

    @ManyToOne(cascade = CascadeType.ALL)
    private Admin admin;

    @ManyToOne(cascade = CascadeType.ALL)
    private RegularUser regularUser;

    @ManyToOne(cascade = CascadeType.ALL)
    private Article article;

}
