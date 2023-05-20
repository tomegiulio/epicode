import { useState,useEffect } from "react";
import axios from "axios";
import HeaderIcon from "./HeadeIcon";
import AvatarUpload from "./AvatarUpload";
import Cookies from "js-cookie";
import { HeartIcon, EllipsisHorizontalIcon } from "@heroicons/react/24/solid";
import { ChatBubbleOvalLeftIcon } from "@heroicons/react/24/outline";



export default function Profile() {
  const [showUploadCard, setShowUploadCard] = useState(false);
  const [user, setUser]=useState({})
  const [follower,setFollowers]=useState({})
  const [following,setFollowing]=useState({})
  const [posts, setPosts] = useState([]);
  const [likes, setLikes] = useState({});
  const [check, setCheck] = useState({});
  const [comment, setComment] = useState([]);
  const [comments, setComments] = useState({});
  const [showComments, setShowComments] = useState(false);
  const [activePostId, setActivePostId] = useState(null);
  const [commentText, setCommentText] = useState("");

  const userPic=Cookies.get("UserPic");
  const token=Cookies.get("myCookieName")
  const email=Cookies.get("UserMail")
  

  const toggleUploadCard = () => {
    setShowUploadCard((prevState) => !prevState);
  };
  
  const getUser=async()=>{

    const response = await axios.get(
      `http://localhost:8080/api/v1/demo-controller/userInfo/${email}`,
      {
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`
        }
      });
      
      
      setUser(response.data)
     
  }
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
    getUser();
    getFollowers();
    getFollowing();
    getPost();
  }, []);


  return (
    <>
      <div className="container mx-auto h-72 min-h-full w-12/12 min-w-max mt-3">
        <div className="flex flex-row min-w-max rounded-xl mx-auto shadow-2xl bg-white overflow-hidden" style={{ maxWidth: 800 }}>
          <div className="w-4/12 min-w-max h-full flex flex-col items-center justify-center">
            <button>
              <img
                src={userPic}
                className="rounded-full m-8"
                width={150}
                height={150}
                onMouseDown={toggleUploadCard}
              />
              {showUploadCard && (
                <AvatarUpload onClose={toggleUploadCard} />
              )}
            </button>
          </div>
          
          
          <div className=" w-6/12 min-w-max mx-2 mt-5">
            <div className="mt-5">
              <button className="text-3xl flex lg:inline-flex mr-2">
               <p> {user.firstname} {user.lastname}</p>
              </button>
              {email!==user.email&&(
                <div>
                  <button className="bg-black text-white rounded-xl w-auto p-2 mr-2 ">
                  follow
                </button>
                <button className="bg-black text-white rounded-xl w-auto p-2 mr-2">
                  message
                </button>
                </div>
              )}
            
              <button>
                <EllipsisHorizontalIcon className="h-8 flex inline-flex mr-2" />
              </button>
            </div>
            <div className="mt-5 flex mr-2 min-w-max">
              
              <button className="text-lg md:text-xl mr-2">Follower: {follower.length}</button>
              <button className="text-lg md:text-xl mx-2">Following: {following.length}</button>
            </div>

            <div className="xl:block hidden my-2 overflow-wrap">
              <p className="overflow-wrap">
                
              </p>
            </div>

          </div>
        
        </div>
      </div>
      <div>
      <CreatePost></CreatePost>
      {posts.map((post) => (
        <div
          className=" mx-auto bg-white rounded-xl shadow-xl my-4"
          key={post.id}
          style={{ maxWidth: 800 }}
        >
          <div className="flex pt-3 min-w-full">
            <img
              loading="lazy"
              src={decodeURIComponent(post.user.profilePic)}
              className="rounded-full mx-2"
              width={50}
              height={50}
            />
            <div>
              <p className="pt-1 mx-2 font-bold">
                {post.user.firstname} {post.user.lastname}
              </p>
              <p className="mx-2"> {post.createdAt}</p>
            </div>
          </div>
          <p className="block px-4 my-2 h-auto" style={{ wordWrap: 'break-word' }}>{post.content}</p>

          <div style={{maxWidth: 800 }}>
            {post.media!=null&&(
                <img
                className="m-auto"
                width={800}
                height={800}
                src={post.media}
              ></img>
            )}
            {post.media==null&&(
               
               <hr className="min-w-fit mx-auto" ></hr>
               
            )}
          
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
                src={decodeURIComponent(userPic)}
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
                      <img
                        src={decodeURIComponent(cmt.user.profilePic)}
                        className="rounded-full mx-2 shadow-xl"
                        width={35}
                        height={35}
                       
                      />
                       
                    </div>
                    <div className="rounded-xl shadow-xl bg-gray-100 p-4">
                      <div className="flex">
                        <p className="pt-1 mx-2 font-bold" >
                         
                          {cmt.user.firstname} {cmt.user.lastname}
                        </p>
                        <p className="pt-1 mx-2 ">{cmt.createdAt}</p>
                        {cmt.user.email === email && (
                          <EllipsisHorizontalIcon className="h-5 mt-1" />
                        )}
                      </div>
                      <h1 className="max-w">{cmt.text}</h1>
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
    </>
  );
}
