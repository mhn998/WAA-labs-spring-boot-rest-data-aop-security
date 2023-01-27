package com.example.waa_first_demo.repo.user;

import com.example.waa_first_demo.domain.Post;
import com.example.waa_first_demo.domain.User;
import com.example.waa_first_demo.domain.dao.UserEntity;
import com.example.waa_first_demo.util.Util;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository
@AllArgsConstructor
class UserRepoCrudImp implements UserRepo {

    // here for user we are completely decoupled from implementation, so I can make the class private-package not public
    EntityManager entityManager;

    private final RDBMSCrudSpringUserRepoImp rdbmsCrudSpringUserRepoImp; // to help me with data
    private final UserRepoSpringJPAImp userRepoSpringJPAImp; // to help me with pagination

    @Override
    public List<User> findAll() {
        return Util.mapToListOf(StreamSupport
                .stream(rdbmsCrudSpringUserRepoImp.findAll().spliterator()
                        , false).
                collect(Collectors.toList()), User.class);
    }

    @Override
    public Optional<User> findById(long id) {
        return Optional.of(Util.mapTo(rdbmsCrudSpringUserRepoImp.findById(id), User.class));
    }

    @Override
    public User save(User user) {
        UserEntity userEntity = Util.mapTo(user, UserEntity.class);
        return Util.mapTo(rdbmsCrudSpringUserRepoImp.save(userEntity), User.class);
    }

    @Override
    public void delete(long id) {
        rdbmsCrudSpringUserRepoImp.deleteById(id);
    }

    @Override
    public Optional<User> update(long id, User user) {
        UserEntity userById = rdbmsCrudSpringUserRepoImp.findById(id).orElseThrow();

        userById.setId(user.getId());
        userById.setName(user.getName());
        rdbmsCrudSpringUserRepoImp.save(userById);

        return Optional.of(user);

    }

    @Override
    public List<Post> findAllPostsByUserId(long id) {
        Optional<UserEntity> userById = rdbmsCrudSpringUserRepoImp.findById(id);

        AtomicReference<List<Post>> posts = new AtomicReference<>();
        userById.ifPresent((user) -> {
            posts.set(user.getPosts());
        });

        return posts.get();
    }

    @Override
    public List<User> findByPosts_SizeGreaterThan(long postsCountGreaterThan) {
        return Util.mapToListOf(rdbmsCrudSpringUserRepoImp.findByPosts_SizeGreaterThan(postsCountGreaterThan), User.class);
    }

    @Override
    public List<User> findByPostsTitle(String title) {
        return Util.mapToListOf(rdbmsCrudSpringUserRepoImp.findAllByPosts_TitleEquals(title), User.class);
    }

    @Override
    public Page<User> loadUsers(Pageable pageable) {
        return userRepoSpringJPAImp.findAll(pageable).map((p) -> Util.mapTo(p, User.class));
    }

    @Override
    public List<User> findHavingPostsGreaterThanOneBy(long size, String state) {
        return Util.mapToListOf(rdbmsCrudSpringUserRepoImp.findHavingPostsGreaterThanOneBy(size, state), User.class);
    }

    public List<Post> findAllPostsByUserOnCriteria(long id , String title, long postLength, String device) { // or wrap all these params in java object called PostRequestCriteria
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Post> cq = cb.createQuery(Post.class);
        Root<Post> root = cq.from(Post.class);
        List<Predicate> predicates = new ArrayList<>();

        Predicate user = cb.equal(root.get("user").get("id"), id);
        predicates.add(user);

        Predicate titleContains = cb.like(root.get("title"), "%" + title + "%");
        predicates.add(titleContains);

        Predicate specifiedPostLength = cb.greaterThanOrEqualTo(root.get("postCharactersLength"), postLength);
        predicates.add(specifiedPostLength);

        Predicate specifiedDevice = cb.equal(root.get("device"), device);
        predicates.add(specifiedDevice);

        cq.where(
                cb.and(predicates.toArray(new Predicate[0]))
        );

        TypedQuery<Post> query = entityManager.createQuery(cq);

        return query.getResultList();
    }

    @Override
    public User findByName(String name) {
        return Util.mapTo(rdbmsCrudSpringUserRepoImp.findByName(name), User.class);
    }

    @Override
    public User finByEmail(String email) {
        return Util.mapTo(rdbmsCrudSpringUserRepoImp.findByEmail(email), User.class);
    }

}
