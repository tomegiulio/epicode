import { useState,useEffect } from "react";
import AvatarUpload from "./AvatarUpload";
import { useRouter } from "next/router";
import Cookies from "js-cookie";
import axios from "axios";
import { HeartIcon, EllipsisHorizontalIcon } from "@heroicons/react/24/solid";
import { ChatBubbleOvalLeftIcon, NoSymbolIcon,
  FireIcon,
  EyeIcon,
  BoltIcon,MapPinIcon } from "@heroicons/react/24/outline";
import Header from '../comp/Header'
import CreatePostv2 from "../comp/CreatePostv2";
import Link from "next/link";

function UserComponent() {
 
  const router = useRouter();
  const { email } = router.query;
  
  
  const myemail=Cookies.get("UserMail")
  const token=Cookies.get("myCookieName")
  
  const [user, setUser]=useState({})
  const [follower,setFollowers]=useState({})
  const [following,setFollowing]=useState({})
  const [posts, setPosts] = useState([]);
  const [likes, setLikes] = useState({});
  const [check, setCheck] = useState({});
  const [check2, setCheck2] = useState({});
  const [comment, setComment] = useState([]);
  const [comments, setComments] = useState({});
  const [showComments, setShowComments] = useState(false);
  const [activePostId, setActivePostId] = useState(null);
  const [commentText, setCommentText] = useState("");

  const [showUploadCard, setShowUploadCard] = useState(false);
  
  
  const toggleUploadCard = () => {
    setShowUploadCard((prevState) => !prevState);
  };
  
 
  const getFollowers=async()=>{
    const response =await axios.get(
      `http://localhost:8080/api/v1/demo-controller/${email}/followers`,
      {
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`
        }
      });
      setFollowers(response.data)
      
    
  }

  const getFollowing=async()=>{
    const response =await axios.get(
      `http://localhost:8080/api/v1/demo-controller/${email}/following`,
      {
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`
        }
      });
      setFollowing(response.data)
      
    
  }
  const getPost=async()=>{
    const response =await axios.get(
      `http://localhost:8080/api/v1/demo-controller/post/user/${email}`,
      {
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`
        }
      });
      setPosts(response.data)
      response.data.forEach((post) => {
        fetchLike(post.id);
        checkLike(post.id);
        getComment(post.id);
      });
    
  }
   ///aggiunge un like nel db
   const postLike = async (id) => {
    try {
      const response = await axios.post(
        `http://localhost:8080/api/v1/demo-controller/newPostLike?userEmail=${email}&postId=${id}`,
        null,
        {
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${token}`,
          },
        }
      );
      checkLike(id);
      fetchLike(id);
    } catch (error) {
      console.error(error);
    }
  };
  const deleteLike = async (postId) => {
    try {
      const response = await axios.delete(
        `http://localhost:8080/api/v1/demo-controller/like/user/${email}/post/${postId}`,
        {
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${token}`,
          },
        }
      );
      checkLike(postId);
      fetchLike(postId);
    } catch (error) {
      console.error(error);
    }
  };

  //ottiene numero di like nel db
  const fetchLike = async (postId) => {
    const response = await axios.get(
      `http://localhost:8080/api/v1/demo-controller/like/post/${postId}`,
      {
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`,
        },
      }
    );
    const like = response.data;
    setLikes((prevLikes) => ({ ...prevLikes, [postId]: like }));
  };
  ///controlla se l'utente ha aggiunto un like al post
  const checkLike = async (postId) => {
    const response = await axios.get(
      `http://localhost:8080/api/v1/demo-controller/like/user/${email}/post/${postId}`,
      {
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`,
        },
      }
    );
    const check = response.data;
    setCheck((prevCheck) => ({ ...prevCheck, [postId]: check }));
  };
  ///follow
  ///////////////////////////////////////////////================================================================
  const checkFollow = async () => {
    const response = await axios.get(
      `http://localhost:8080/api/v1/demo-controller/${myemail}/checkfollow/${email}`,
      {
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`,
        },
      }
    );
    if(response.data==false){
      setCheck2(false)
    }else{
      setCheck2(true)
    }
    
    
  };
  const follow = async () => {
    try{
      await axios.post(
        `http://localhost:8080/api/v1/demo-controller/follow/follower/${myemail}/following/${email}`,
        {}, 
        {
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${token}`,
          },
        }
      );
      
      checkFollow();
      getFollowers();
    } catch(error) {
      console.log(error)
    }
  };
  
  const unfollow = async () => {
    try{await axios.delete(
      `http://localhost:8080/api/v1/demo-controller/unfollow/follower/${myemail}/following/${email}`,
      {
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`,
        },
      }
    );
    checkFollow();
    getFollowers();}catch(error){
      console.log(error)
    }
  };



  ///////////////////////////////////////////////================================================================
  //////commenti-----------------------------------------------------------------------------
  ////crea un commento

  const postComment = async (postId) => {
    const headers = {
      "Content-Type": "application/json",
      Authorization: `Bearer ${token}`,
      params: {},
    };
    if (commentText != "") {
      const response = await axios.post(
        `http://localhost:8080/api/v1/demo-controller/newComment/post`,
        null,
        {
          headers,
          params: {
            userEmail: email,
            postId: postId,
            text: commentText,
          },
        }
      );
      console.log(response);
      setCommentText("");
      getComment(postId);
    }
  };
  //////fa la get dei commenti rispettivi al post
  const getComment = async (postId) => {
    const response = await axios.get(
      `http://localhost:8080/api/v1/demo-controller/comment/post/${postId}`,
      {
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`,
        },
      }
    );
    console.log(response.data);
    setComments((prevComments) => ({
      ...prevComments,
      [postId]: response.data,
    }));
    setComment(response.data);
  };

  const show = async (postId) => {
    if (activePostId === postId) {
      setShowComments(false);
      setActivePostId(null);
    } else {
      getComment(postId);
      setShowComments(true);
      setActivePostId(postId);
    }
  };

 
   useEffect(() => {
    const getUser = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/api/v1/demo-controller/userInfo/${email}`, {
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${token}`
          }
        });
        setUser(response.data);
      } catch (error) {
        console.log(error);
      }
    };
    if (email && token) {
      getUser();
      getFollowers();
      getFollowing();
      getPost();
      checkFollow();
    }
  }, [email, token]);

  if (!email) {
    return <div>Loading...</div>;
  }

  



  return (
    <>
     <div className='bg-gray-100 min-h-screen'>
    <Header></Header>
    <div className="container mx-auto  min-h-full w-12/12 min-w-max mt-3">
        <div className="flex flex-row min-w-max rounded-xl mx-auto shadow-2xl bg-white overflow-hidden"style={{ maxWidth: 800 }}>
          <div className="w-4/12 min-w-max h-full flex flex-col items-center justify-center">
            
          {myemail==user.email&&(
              <button>
                
              <img
              
                onClick={ toggleUploadCard}
                src={decodeURIComponent(user.profilePic)}
                className="rounded-full m-8"
                width={150}
                height={150}
                
              />
              {showUploadCard && (
                <AvatarUpload onClose={toggleUploadCard} />
              )}
            </button>
          )}
          {myemail!==user.email&&(
             <img
              
             
             src={decodeURIComponent(user.profilePic)}
             className="rounded-full m-8"
             width={150}
             height={150}
             
           />
          )}
            
          </div>
          
          
          <div className=" w-6/12 min-w-max mx-2 mt-5">
            <div className="mt-5">
              <button className="text-3xl flex lg:inline-flex mr-2">
               <p className="text-xxl font-black"> {user.firstname} {user.lastname}</p>
               <button>
                <EllipsisHorizontalIcon className="h-8 mt-1 flex inline-flex mx-2 mb-3" />
              </button>
              </button>
              {myemail!==user.email&&(
                <div>
                {check2==true? (
              <button className="bg-black text-white rounded-xl w-auto p-2 mr-2 mt-2"
              onClick={() => unfollow()}>
              unfollow
            </button>
            ) : (
              <button className="bg-black text-white rounded-xl w-auto p-2 mr-2 mt-2"
             
              onClick={() => follow()}>
              follow
            </button>
            )}
                <button className="bg-black text-white rounded-xl w-auto p-2 mr-2 mt-2">
                  message
                </button>
                </div>
              )}
              
            </div>
            <div className="mt-5 flex mr-2 min-w-max">
              
              <button className="text-lg md:text-xl font-semibold mr-2">follower: {follower.length}</button>
              <button className="text-lg md:text-xl font-semibold mx-2">following: {following.length}</button>
            </div>

            <div className="xl:block hidden my-2 overflow-wrap">
              <p className="overflow-wrap">
                
              </p>
            </div>

          </div>
        
        </div>
      </div>

      <div>
      {myemail==user.email&&(
                <div>
                  <CreatePostv2></CreatePostv2>
                </div>
              )}
      
      <div>
      
      {posts.map((post) => (
        <div
          className=" mx-auto bg-white rounded-xl shadow-xl my-4"
          key={post.id}
          style={{ maxWidth: 800 }}
        >
          <div className="flex pt-3 min-w-full">
            <Link href={`/comp/UserComponent?email=${post.user.email}`}>
              <img
                src={decodeURIComponent(post.user.profilePic)}
                className="rounded-full mx-2"
                width={50}
                height={50}
              />
            </Link>
            <div className="flex-grow">
              <p className="pt-1 mx-2 font-bold flex">
                <Link href={`/comp/UserComponent?email=${post.user.email}`}>
                  {post.user.firstname} {post.user.lastname}
                </Link>
                {post.crag && post.routes == null ? (
                  <Link href={`/comp/CragComponent?cragId=${post.crag.id}`}>
                  <div className="text-red-500 flex ">
                    
                    <MapPinIcon className="h-5  mx-1 "></MapPinIcon>
                    <p>{post.crag.name}</p>
                  </div>
                  </Link>
                ) : (
                  <></>
                )}

                {post.routes != null && (
                  <div className="flex">
                    {post.style == "flash" && (
                      <Link href={`/comp/RouteComponent?routeId=${post.routes.id}`}>
                      <div className="flex text-yellow-500">
                        <p className="  flex mx-1">
                          <BoltIcon className="h-4 mt-1"></BoltIcon>
                        </p>
                        <p className="font-bold">{post.routes.name}</p>
                      </div>
                      </Link>
                    )}
                    {post.style == "onsight" && (
                      <Link href={`/comp/RouteComponent?routeId=${post.routes.id}`}>
                      <div className="flex text-green-500">
                        <p className="  flex mx-1">
                          <EyeIcon className="h-4 mt-1"></EyeIcon>
                        </p>
                        <p className="font-bold">{post.routes.name}</p>
                      </div>
                      </Link>
                    )}
                    {post.style == "redpointed" && (
                      <Link href={`/comp/RouteComponent?routeId=${post.routes.id}`}>
                      <div className="flex text-red-500">
                        <p className="ml-1">{post.tryNumber}</p>
                        <p className="  flex mr-1">
                          <FireIcon className="h-4 mt-1"></FireIcon>
                        </p>

                        <p className="font-bold">{post.routes.name}</p>
                      </div>
                      </Link>
                    )}
                    {post.style == "failed" && (
                      <Link href={`/comp/RouteComponent?routeId=${post.routes.id}`}>
                      <div className="flex text-orange-900">
                        <p className="ml-1">{post.tryNumber}</p>
                        <p className="  flex mr-1">
                          <NoSymbolIcon className="h-4 mt-1"></NoSymbolIcon>
                        </p>

                        <p className="font-bold">{post.routes.name}</p>
                      </div>
                      </Link>
                    )}
                  </div>
                  
                )}
              </p>

              <p className="mx-2"> {post.createdAt}</p>
            </div>

            <div className="flex  mr-1 p-1 rounded-lg">
              <button className="flex w-full">
                <EllipsisHorizontalIcon className="h-6"></EllipsisHorizontalIcon>
              </button>
            </div>
          </div>

          <p
            className="block px-4 my-2 h-auto"
            style={{ wordWrap: "break-word" }}
          >
            {post.content}
          </p>

          <div style={{ maxWidth: 800 }}>
            {post.media != null && (
              <img
                className="m-auto"
                width={800}
                height={800}
                src={post.media}
              ></img>
            )}
            {post.media == null && <hr className="min-w-fit mx-auto"></hr>}
          </div>

          <div className="flex">
            {check[post.id] ? (
              <HeartIcon
                onClick={() => deleteLike(post.id)}
                className="h-8 text-red-500 m-2"
              ></HeartIcon>
            ) : (
              <HeartIcon
                onClick={() => postLike(post.id)}
                className="h-8 text-gray-300 m-2"
              >
                {" "}
                like
              </HeartIcon>
            )}
            <div className="flex p-2 w-full">
              <img
                src={decodeURIComponent(user.profilePic)}
                className="rounded-full mx-1 shadow-xl"
                width={35}
                height={35}
              />
              <input
                name="commentText"
                value={commentText}
                onChange={(e) => setCommentText(e.target.value)}
                className="bg-gray-100 rounded-lg w-full placeholder:p-3"
                placeholder="write a comment"
              ></input>
              <ChatBubbleOvalLeftIcon
                onClick={() => postComment(post.id)}
                className="h-5 m-2 "
              ></ChatBubbleOvalLeftIcon>
            </div>
          </div>

          <div className="flex">
            <p className="py-1 mx-3 flex">
              <p className="">
                {likes[post.id] ? likes[post.id].length : 0} like{" "}
              </p>
              <button onClick={() => show(post.id)}>
                <p className="ml-2">
                  {comments[post.id] ? comments[post.id].length : 0} comment
                </p>
              </button>
            </p>
          </div>
          {showComments ? (
            <div>
              {comment

                .filter((cmt) => cmt.post.id === post.id)

                .map((cmt) => (
                  <div className="mx-auto pb-3 flex" key={cmt.id}>
                    <div className="m-6 w-fit">
                      <Link
                        href={`/comp/UserComponent?email=${cmt.user.email}`}
                      >
                        <img
                          src={decodeURIComponent(cmt.user.profilePic)}
                          className="rounded-full mx-2 shadow-xl"
                          width={35}
                          height={35}
                        />
                      </Link>
                    </div>
                    <div className="rounded-xl shadow-xl bg-gray-100 p-4">
                      <div className="flex">
                        <p className="pt-1 mx-2 font-bold">
                          {cmt.user.firstname} {cmt.user.lastname}
                        </p>
                        <p className="pt-1 mx-2 ">{cmt.createdAt}</p>
                        {cmt.user.email === email && (
                          <EllipsisHorizontalIcon className="h-5 mt-1" />
                        )}
                      </div>
                      <h1 className="max-w"  style={{ wordWrap: "break-word",  maxWidth: 300 }}>{cmt.text}</h1>
                    </div>
                  </div>
                ))}
            </div>
          ) : (
            <></>
          )}
        </div>
      ))}
    </div>
    </div>
    </div>
    </>

      
      
  );
}

export default UserComponent;
