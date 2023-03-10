package com.example.waa_first_demo.controllers;


import com.example.waa_first_demo.domain.Address;
import com.example.waa_first_demo.domain.Comment;
import com.example.waa_first_demo.domain.Post;
import com.example.waa_first_demo.domain.User;
import com.example.waa_first_demo.domain.dto.AddressDTO;
import com.example.waa_first_demo.domain.dto.CommentDTO;
import com.example.waa_first_demo.domain.dto.PostDTO;
import com.example.waa_first_demo.domain.dto.UserDTO;
import com.example.waa_first_demo.service.comment.CommentService;
import com.example.waa_first_demo.service.post.PostService;
import com.example.waa_first_demo.service.user.UserService;
import com.example.waa_first_demo.util.Util;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@AllArgsConstructor
public class UserController {

    private UserService userService;
    private PostService postService;
    private CommentService commentService;


//    private RestTemplate restTemplate;


    // this is left without DTO for demo purposes!
    @GetMapping
    public List<User> findAll(){
        return userService
                .findAll();
    }

    @GetMapping("/{id}")
    public UserDTO findById(@PathVariable long id){
        User userById = userService
                .findById(id);

        System.out.println("userById = " + userById);

        return Util.mapTo(userById, UserDTO.class);
    }

    @PostMapping
    public UserDTO save(@RequestBody User user) {
        User savedUser = userService
                .save(user);
        return Util.mapTo(savedUser, UserDTO.class);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        userService
                .delete(id);
    }

    @PutMapping("/{id}")
    public UserDTO updateUser(@PathVariable int id, @RequestBody User user) {
        User userUpdated = userService
                .update(id, user).orElseThrow();
        return Util.mapTo(userUpdated, UserDTO.class);
    }



    // if you add @Request param to a method it is by default required=true, so if you don't supply it, it won't identify a mapper for the request, to overcome and provide choices of filtering add required false
    @GetMapping("filter")
    public List<UserDTO> findAllUserThatHaveMoreThanPosts(@RequestParam(defaultValue = "1") Long postsCountGreaterThan, @RequestParam(required = false) String state){
        // will filter on users having more than a post by default; if the state provided will filter on it as well
        List<User> usersByPostsCountGreaterThanOne;
        if(state != null) {
            usersByPostsCountGreaterThanOne = userService.findHavingPostsGreaterThanOneBy(postsCountGreaterThan, state);
        } else {
            usersByPostsCountGreaterThanOne = userService
                    .findByPosts_SizeGreaterThan(postsCountGreaterThan);
        }

        return Util.mapToListOf(usersByPostsCountGreaterThanOne, UserDTO.class);
    }

    @GetMapping("filterBy")
    public List<UserDTO> findHavingMoreThanOnePostBy(@RequestParam Long postsCountGreaterThan){
        List<User> usersByPostsCountGreaterThan = userService
                .findByPosts_SizeGreaterThan(postsCountGreaterThan);

        return Util.mapToListOf(usersByPostsCountGreaterThan, UserDTO.class);
    }

    @GetMapping("filterPostTitle")
    public List<UserDTO> findAllUsersWithPostTitle(@RequestParam String postTitle){
        List<User> usersByPostTitle = userService
                .findByPostsTitle(postTitle);
        return Util.mapToListOf(usersByPostTitle, UserDTO.class);
    }


    // posts section from users
    @GetMapping("/{userId}/posts")
    public List<PostDTO> findAllPostsByUser(@PathVariable long userId){
        List<Post> allPostsByUser = postService
                .findAllPostsByUser(userId);
        return Util.mapToListOf(allPostsByUser, PostDTO.class);
    }

    @GetMapping("/{userId}/posts/filter")
    public List<PostDTO> findAllByUserOnCriteria
            (@PathVariable long userId,
             @RequestParam String titleContains,
             @RequestParam Long postContentCharacterLength,
             @RequestParam String device){

        return Util.mapToListOf(userService.findAllPostsByUserOnCriteria(userId, titleContains, postContentCharacterLength, device), PostDTO.class);

    }

    @GetMapping("{userId}/posts/{postId}")
    public PostDTO findPostById(@PathVariable long userId,
                             @PathVariable long postId ){
//        User user = restTemplate.getForObject("http://localhost/api/v1/users/{userId}", User.class);

        Post postByUser = postService.findPostByUser(userId, postId);
        return Util.mapTo(postByUser, PostDTO.class );
    }


    // no longer needed after adding an endpoint in post controller which takes userId from the token
    @PostMapping("{userId}/posts")
    public PostDTO savePostToUser(@PathVariable long userId, @RequestBody Post post) {
        Post postSaved = postService.savePostToUser(userId, post);
        return Util.mapTo(postSaved, PostDTO.class);
    }

    @PutMapping("{userId}/posts")
    public PostDTO updatePostToUser(@PathVariable long userId, @RequestBody Post post) {
        Post postSaved = postService.savePostToUser(userId, post);
        return Util.mapTo(postSaved, PostDTO.class);
    }


    // comments section from users
    @PostMapping("{userId}/posts/{postId}/comments")
    public CommentDTO createComment(@RequestBody Comment comment , @PathVariable long userId, @PathVariable long postId) {
        Comment commentWithinPost = commentService.saveWithPost(userId, postId, comment);
        return Util
                .mapTo(commentWithinPost, CommentDTO.class);
    }


    @GetMapping("{userId}/posts/{postId}/comments")
    public List<CommentDTO> findAllCommentsOnPost(@PathVariable long userId, @PathVariable long postId) {
        List<Comment> allCommentsByUserAndPost = commentService.findAllCommentsByUserAndPost(userId, postId);
        return Util.mapToListOf(allCommentsByUserAndPost, CommentDTO.class);
    }


    @GetMapping("{userId}/posts/{postId}/comments/{commentId}")
    public CommentDTO findAllCommentsOnPost(@PathVariable long userId, @PathVariable long postId, @PathVariable long commentId) {
        Comment comment = commentService.findComment(userId, postId, commentId);
        return Util.mapTo(comment, CommentDTO.class);
    }

    // new localhost:8080/api/v1/users/search?pageSize=6&pageNo=0; pagination starts from 0
    @GetMapping("search")
    public Page<UserDTO> loadUsers(@RequestParam Integer pageNo, @RequestParam Integer pageSize) {
        return userService.loadAll(PageRequest.of(pageNo, pageSize)).map(user -> Util.mapTo(user, UserDTO.class));
    }


    // Address for user
    @PostMapping("{id}/address")
    AddressDTO createAddress (@PathVariable long id, @RequestBody Address address) {
        return Util.mapTo(userService.createAddress(id, address), AddressDTO.class);
    }

}



