package top.canoe0.blog.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
public class ArticleType {
    @Id
    @GeneratedValue
    private int articleTypeId;

    @NotBlank
    @Length(min = 2, max = 10)
    private String articleTypeName;

    public ArticleType() {
    }

    @ManyToOne(cascade = CascadeType.ALL)
    private Article article;
}
