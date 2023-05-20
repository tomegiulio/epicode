package com.alibou.security.demo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibou.security.demo.dao.repository.CommentRepository;
import com.alibou.security.demo.dao.repository.CragRepository;
import com.alibou.security.demo.dao.repository.FollowRepository;
import com.alibou.security.demo.dao.repository.LikeRepository;
import com.alibou.security.demo.dao.repository.PostRepository;
import com.alibou.security.demo.dao.repository.RoutesRepository;
import com.alibou.security.demo.dao.service.FollowService;
import com.alibou.security.demo.dao.service.PostService;
import com.alibou.security.demo.model.Comment;
import com.alibou.security.demo.model.Crag;
import com.alibou.security.demo.model.Follow;
import com.alibou.security.demo.model.Likee;
import com.alibou.security.demo.model.Post;
import com.alibou.security.demo.model.Routes;
import com.alibou.security.user.User;
import com.alibou.security.user.UserRepository;

import jakarta.transaction.Transactional;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/demo-controller")
public class DemoController {

	@Autowired
	UserRepository ur;
	@Autowired
	LikeRepository postLikeRepository;
	@Autowired
	PostRepository pr;
	@Autowired
	PostService ps;
	@Autowired
	CommentRepository commentRepository;
	
	@Autowired
	CragRepository cragRepository;
	@Autowired
	RoutesRepository routesRepository;
	 @Autowired
	FollowService followService;
	 @Autowired
	 FollowRepository followRepository;
	 
	@GetMapping
	public ResponseEntity<String> sayHello() {

		return ResponseEntity.ok("Hello from secured endpoint");
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////
	///ricerca controller//////////////////////////////////////////////////////////////////////////////////////////////////// 
	@Transactional
	@GetMapping("/getCrag/{name}")
	public List<Crag> getCrag(@PathVariable(required = true) String name) {
	    String searchName = "%" + name.toLowerCase() + "%";
	    List<Crag> r = cragRepository.findByNameContainingIgnoreCase(searchName);
	    return r;
	}
	@Transactional
	@GetMapping("/getRoute/{name}")
	public List<Routes> getRoute(@PathVariable(required = true) String name) {
	    String searchName = "%" + name.toLowerCase() + "%";
	    List<Routes> r = routesRepository.findByNameContainingIgnoreCase(searchName);
	    return r;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////
	///// user controller////////////////////////////////////////////////////////////////////////////////////////////////////
	// get della foto profilo
	@GetMapping("/userPic/{email}")
	public String getUserPic(@PathVariable(required = true) String email) {
		Optional<User> u = ur.findByEmail(email);
		if (u.isPresent()) {
			User u1 = u.get();
			String x = u1.getProfilePic();
			return x;
		} else {
			return null;
		}
	}

	/// modifica della foto profilo
	@CrossOrigin
	@PutMapping("/user/{email}")
	public User updateUserPic(@PathVariable(required = true) String email, @RequestParam String photo) {
		Optional<User> u = ur.findByEmail(email);
		if (u.isPresent()) {
			User u1 = u.get();
			u1.setProfilePic(photo);
			ur.save(u1);
			return u1;
		} else {
			return null;
		}
	}
	/////da creare una response personalizzata senza password
	// get delle info utente
	@GetMapping("/userInfo/{email}")
	public User getUser(@PathVariable(required = true) String email) {
		Optional<User> u = ur.findByEmail(email);
		if (u.isPresent()) {
			User u1 = u.get();
			return u1;
		} else {
			return null;
		}
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////
	////////crag controller////////////////////////////////////////////////////////////////////////////////////////////////////
	@PostMapping("/newCrag")
	public ResponseEntity<Crag> createCrag(@RequestBody Crag crag) {
			cragRepository.save(crag);
			return ResponseEntity.ok(crag);
	
	}
	@Transactional
	@GetMapping("/getCragById/{cragId}")
	public Crag getCragById(@PathVariable(required = true)int cragId) {
		 
		Optional<Crag> c=cragRepository.findById(cragId);
		if(c.isPresent()) {
			Crag cf=c.get();
			return cf;
		}else {
		return null;
		}

	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////
	///routes controller////////////////////////////////////////////////////////////////////////////////////////////////////
	@PostMapping("/newRoutes")
	public ResponseEntity<Routes> createRoutes(@RequestBody Routes routes) {
		routesRepository.save(routes);
			return ResponseEntity.ok(routes);
	
	}
	@GetMapping("/getRouteByCragId/{cragId}")
	public List<Routes> getRouteByCragId(@PathVariable(required = true)int cragId) {
		List<Routes> r=routesRepository.findByCragId(cragId);
		
		return r;
		}
	@GetMapping("/getRouteById/{routeId}")
	public Routes getRouteById(@PathVariable(required = true)int routeId) {
		Optional<Routes> r=routesRepository.findById(routeId);
		if(r.isPresent()) {
			Routes rf=r.get();
			return rf;
		}else {
			return null;
		}
		
		
		}
	@GetMapping("/getRouteCragPic/{routeId}")
	public Crag getRouteCragPic(@PathVariable(required = true)int routeId) {
		Optional<Routes> r=routesRepository.findById(routeId);
		if(r.isPresent()) {
			Routes rf=r.get();
			Optional<Crag> c=cragRepository.findById(rf.getCragId());
			if(c.isPresent()) {
				Crag cf=c.get();
				return cf;
			}
		}
		return null;
		
		
		}
	

	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////
	///post controller////////////////////////////////////////////////////////////////////////////////////////////////////
	@Transactional
	@PostMapping("/newPost")
	public ResponseEntity<Post> createPost(@RequestBody PostRequest PostRequest, @RequestParam String userMail) {
	    Optional<User> u = ur.findByEmail(userMail);
	    Optional<Crag> c = cragRepository.findById(PostRequest.getCragId());
	    Optional<Routes> r = routesRepository.findById(PostRequest.getRouteId());
	    if (u.isPresent()&&c.isPresent()&&r.isEmpty()) {
	        User uf = u.get();
	        Crag cf=c.get();
	        Post p = Post.builder()
	                .media(PostRequest.getUrl())
	                .content(PostRequest.getText())
	                .user(uf)
	                .crag(cf)
	                .createdAt(LocalDateTime.now())
	                .build();
	       

	        pr.save(p);
	        return ResponseEntity.ok(p);
	    }else if (u.isPresent()&&r.isPresent()) {
	        User uf = u.get();
	        Routes rf=r.get();
	        Optional<Crag> c2=cragRepository.findById(rf.getCragId());
	        Crag cf=c2.get();
	        Post p = Post.builder()
	        		.routes(rf)
	        		.valutation(PostRequest.getStar())
	        		.tryNumber(PostRequest.getTryNum())
	        		.style(PostRequest.getStyle())
	                .media(PostRequest.getUrl())
	                .content(PostRequest.getText())
	                .user(uf)
	                .crag(cf)
	                .createdAt(LocalDateTime.now())
	                .build();
	        pr.save(p);
	        return ResponseEntity.ok(p);
	        }else if(u.isPresent()) {
	    	 User uf = u.get();
		        
		        Post p = Post.builder()
		                .media(PostRequest.getUrl())
		                .content(PostRequest.getText())
		                .user(uf)
		              
		                .createdAt(LocalDateTime.now())
		                .build();

		        pr.save(p);
		        return ResponseEntity.ok(p);
	    }
		return null;
	}

	 @GetMapping("/getAll/post")
	    public List<Post> getAllPosts() {
	        return ps.findAll();
	    }
	 
	 @Transactional
	 @GetMapping("/post/user/{userEmail}")
	 public List<Post> getUserPost(@PathVariable String userEmail) {
		Optional<User> x=ur.findByEmail(userEmail);
		if(x.isPresent()) {
			User uf=x.get();
			List<Post> userPost=pr.findByUserId(uf.getId());
			return userPost;
		}
		return null;
		 
	 }
	
	 @Transactional
	 @GetMapping("/post/crag/{cragId}")
	 public List<Post> findByCragId(@PathVariable int cragId) {
		
			List<Post> userPost=pr.findByCragId(cragId);
			return userPost;
		
		 
	 }
	 @Transactional
	 @GetMapping("/post/routes/{routesId}")
	 public List<Post> findByRoutesId(@PathVariable int routesId) {
		
			List<Post> userPost=pr.findByRoutesId(routesId);
			return userPost;
		
		 
	 }
	 @Transactional
	 @GetMapping("/pic/crag/{cragId}")
	 public Set<String> findPicByCragId(@PathVariable int cragId) {
		 	Set<String> pics=new HashSet<>();
			List<Post> userPost=pr.findByCragId(cragId);
			for(Post x:userPost) {
					if(!(x.getMedia()==null)) {
						String y=x.getMedia();
						pics.add(y);
					}
					
			}
			return pics;
		
		 
	 }
	
	 
	 ////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////
	//////// like controller////////////////////////////////////////////////////////////////////////////////////////////////////
	@PostMapping("/newPostLike")
	public ResponseEntity<Likee> createPostLike(@RequestParam String userEmail, @RequestParam int postId) {
		Optional<User> u = ur.findByEmail(userEmail);
		Optional<Post> p = pr.findById(postId);
		if (u.isPresent() && p.isPresent()) {
			User uf = u.get();
			Post pf = p.get();
			Likee postLike = Likee.builder().isLiked(true).user(uf).post(pf).build();
			postLikeRepository.save(postLike);
			return ResponseEntity.ok(postLike);
		}
		return null;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////
	///crag like////////////////////////////////////////////////////////////////////////////////////////////////////
	@PostMapping("/newCragLike")
	public ResponseEntity<Likee> createCragLike(@RequestParam String userEmail, @RequestParam int cragId) {
		Optional<User> u = ur.findByEmail(userEmail);
		Optional<Crag> c = cragRepository.findById(cragId);
		if (u.isPresent() && c.isPresent()) {
			User uf = u.get();
			Crag cf = c.get();
			Likee postLike = Likee.builder().isLiked(true).user(uf).crag(cf).build();
			postLikeRepository.save(postLike);
			return ResponseEntity.ok(postLike);
		}
		return null;
	}
	@Transactional
	@GetMapping("/like/user/{userEmail}/crag/{cragId}")
	public boolean checkCragLike(@PathVariable String userEmail,@PathVariable int cragId) {
		boolean checker=false;
		Optional<User> u=ur.findByEmail(userEmail);
		
		if(u.isPresent()) {
			User uf=u.get();
			int x=uf.getId();
			Optional<Likee> postLikes= postLikeRepository.findByCragIdAndUserId(cragId,  x);
		if(postLikes.isPresent()) {
			checker= true;
		}
		}
		return checker;
	}
	
	@Transactional
	@DeleteMapping("/like/user/{userEmail}/crag/{cragId}")
	public void deleteCragLike(@PathVariable String userEmail,@PathVariable int cragId) {
		
		Optional<User> u=ur.findByEmail(userEmail);
		if(u.isPresent()) {
			User uf=u.get();
			int x=uf.getId();
			 Optional<Likee> postLikes= postLikeRepository.findByCragIdAndUserId(cragId,  x);
		if(postLikes.isPresent()) {
			Likee like=postLikes.get();
			postLikeRepository.delete(like);
		}
		}
	}
	
	@Transactional
	@GetMapping("/like/crag/{cragId}")
	public ResponseEntity<List<Likee>> getLikesByCragId(@PathVariable int cragId) {
		List<Likee> postLikes = postLikeRepository.findByCragId(cragId);
		return ResponseEntity.ok(postLikes);
	}

	@Transactional
	@GetMapping("/like/post/{postId}")
	public ResponseEntity<List<Likee>> getLikesByPostId(@PathVariable int postId) {
		List<Likee> postLikes = postLikeRepository.findByPostId(postId);
		return ResponseEntity.ok(postLikes);
	}
	
	@Transactional
	@GetMapping("/like/user/{userId}")
	public ResponseEntity<List<Likee>> getLikesByUserId(@PathVariable int userId) {
		List<Likee> postLikes = postLikeRepository.findByUserId(userId);
		return ResponseEntity.ok(postLikes);
	}
	@Transactional
	@GetMapping("/like/user/{userEmail}/post/{postId}")
	public boolean checkPostLike(@PathVariable String userEmail,@PathVariable int postId) {
		boolean checker=false;
		Optional<User> u=ur.findByEmail(userEmail);
		if(u.isPresent()) {
			User uf=u.get();
			int x=uf.getId();
		Optional<Likee> postLikes = postLikeRepository.findByPostIdAndUserId(postId,x);
		if(postLikes.isPresent()) {
			checker= true;
		}
		}
		return checker;
	}
	
	@Transactional
	@DeleteMapping("/like/user/{userEmail}/post/{postId}")
	public void deletePostLike(@PathVariable String userEmail,@PathVariable int postId) {
		Optional<User> u=ur.findByEmail(userEmail);
		if(u.isPresent()) {
			User uf=u.get();
			int x=uf.getId();
		Optional<Likee> postLikes = postLikeRepository.findByPostIdAndUserId(postId,x);
		if(postLikes.isPresent()) {
			Likee like=postLikes.get();
			postLikeRepository.delete(like);
		}
		}
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////
	//////// comment controller////////////////////////////////////////////////////////////////////////////////////////////////////
	@PostMapping("/newComment/post")
	public ResponseEntity<Comment> createPostComment(@RequestParam String userEmail, @RequestParam int postId,@RequestParam String text) {
		Optional<User> u = ur.findByEmail(userEmail);
		Optional<Post> p = pr.findById(postId);
		if (u.isPresent() && p.isPresent()) {
			User uf = u.get();
			Post pf = p.get();
			Comment c = Comment.builder()
					.text(text)
					.createdAt(LocalDate.now())
					.build();
			c.setUser(uf);
			c.setPost(pf);
					
				
			commentRepository.save(c);
			return ResponseEntity.ok(c);
		}
		return null;

	}
	
	@Transactional
	@GetMapping("/comment/post/{postId}")
	public List<Comment> getCommentsByPostId(@PathVariable int postId) {
		List<Comment> comments = commentRepository.findByPostId(postId);
		return comments;
	}
	
	@GetMapping("/comment/user/{userId}")
	public ResponseEntity<List<Comment>> getCommentsByUserId(@PathVariable int userId) {
		List<Comment> comments = commentRepository.findByUserId(userId);
		return ResponseEntity.ok(comments);
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////follow controller/////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////
	@PostMapping("follow/follower/{followerEmail}/following/{followingEmail}")
	public ResponseEntity<String> followUser(@PathVariable String followerEmail, @PathVariable String followingEmail) {
	   Optional<User> follower = ur.findByEmail(followerEmail);
	   Optional<User> following = ur.findByEmail(followingEmail);
	   if (follower.isPresent() && following.isPresent()) {
	       User followerf = follower.get();
	       User followingf = following.get();
	       followService.follow(followerf.getId(), followingf.getId());
	       return ResponseEntity.ok().build();
	   } else {
	       return null;
	   }
	}

	@DeleteMapping("unfollow/follower/{followerEmail}/following/{followingEmail}")
	public ResponseEntity<String> unfollowUser(@PathVariable String followerEmail, @PathVariable String followingEmail) {
	    Optional<User> follower = ur.findByEmail(followerEmail);
	    Optional<User> following = ur.findByEmail(followingEmail);
	    if (follower.isPresent() && following.isPresent()) {
	        User followerf = follower.get();
	        User followingf = following.get();
	        followService.unfollow(followerf.getId(), followingf.getId());
	        return ResponseEntity.ok().build();
	    } else {
	        return null;
	    }
	}


	    @GetMapping("/{userEmail}/followers")
	    public ResponseEntity<List<User>> getFollowers(@PathVariable String userEmail) {
	    	Optional<User> x=ur.findByEmail(userEmail);
	    	if(x.isPresent()) {
	    		User uf=x.get();
	    		List<User> followers = followService.getFollowers(uf.getId());
	    		return ResponseEntity.ok(followers);
	    	}else {
	    		return null;
	    	}
	        
	        
	    }
	    @GetMapping("/{followerEmail}/checkfollow/{followingEmail}")
	    public boolean checkFollow(@PathVariable String followerEmail, @PathVariable String followingEmail) {
	    	boolean check=false;
	    	 Optional<User> follower=ur.findByEmail(followerEmail);
			   Optional<User> following=ur.findByEmail(followingEmail);
			   if(follower.isPresent()&&following.isPresent()) {
				   User followerf=follower.get();
				   User followingf=following.get();
				   Optional<Follow> x= followRepository.findByFollowingIdAndFollowerId(followingf.getId(),followerf.getId());
				   if(x.isPresent()) {
					   check=true;
					   }}
				  return check;
	        
	        
	    }

	    @GetMapping("/{userEmail}/following")
	    public ResponseEntity<List<User>> getFollowing(@PathVariable String userEmail) {
	    	Optional<User> x=ur.findByEmail(userEmail);
	    	if(x.isPresent()) {
	    		User uf=x.get();
	        List<User> following = followService.getFollowing(uf.getId());
	        return ResponseEntity.ok(following);}else {
	        	return null;
	        }
	    }
}
