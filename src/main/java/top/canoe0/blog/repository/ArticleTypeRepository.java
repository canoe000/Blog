package top.canoe0.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import top.canoe0.blog.entity.ArticleType;

import java.util.List;

//使用Mybatis框架时，使用的是mapper包，使用SB自带的框架时，用repository包
public interface ArticleTypeRepository extends JpaRepository<ArticleType, Integer> {
    ArticleType findArticleTypeByArticleTypeName(String articleTypeName);

    List<ArticleType> findArticleTypesByUserIdAndUserType(int userId, String userType);

    ArticleType findArticleTypeByArticleTypeId(int articleTypeId);

    ArticleType findArticleTypeByArticleId(int articleId);

    @Modifying
    @Transactional
    void deleteAllByUserIdAndUserType(int userId, String userType);

}
