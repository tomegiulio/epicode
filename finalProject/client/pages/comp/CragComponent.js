import { useState, useEffect } from "react";
import { useRouter } from "next/router";
import Cookies from "js-cookie";
import axios from "axios";
import { HeartIcon, EllipsisHorizontalIcon } from "@heroicons/react/24/solid";
import Link from "next/link";
import {
  ChatBubbleOvalLeftIcon,
  MapIcon,
  MapPinIcon,
  NewspaperIcon,
  PhotoIcon,
  TagIcon,
  NoSymbolIcon,
  FireIcon,
  EyeIcon,
  BoltIcon,
} from "@heroicons/react/24/outline";
import Header from "../comp/Header";
import CreatePostv2 from "../comp/CreatePostv2";
import MapTest from "./MapTest";
function CragComponent() {
  const router = useRouter();
  const data = router.query;
  const cragId = parseInt(data.cragId);

  const token = Cookies.get("myCookieName");
  const myemail = Cookies.get("UserMail");
  const pic = Cookies.get("UserPic");

  const [crag, setCrag] = useState({});
  const [cragLikes, setCragLikes] = useState({});
  const [cragCheck, setCragCheck] = useState({});
  const [routes, setRoutes] = useState([]);
  const [posts, setPosts] = useState([]);
  const [pics, setPics] = useState([]);

  const [likes, setLikes] = useState({});
  const [check, setCheck] = useState({});
  const [comment, setComment] = useState([]);
  const [comments, setComments] = useState({});
  const [showComments, setShowComments] = useState(false);
  const [commentText, setCommentText] = useState();
  const [activePostId, setActivePostId] = useState(null);

  const [showRoutes, setShowRoutes] = useState(false);
  const [showMap, setShowMap] = useState(false);
  const [showPost, setShowPost] = useState(true);
  const [showPics, setShowPics] = useState(false);

  const email = Cookies.get("UserMail");
  

  ///bottoni sezioni
  const buttonRoutes = () => {
    setShowRoutes(true);
    setShowMap(false);
    setShowPost(false);
    setShowPics(false);
  };
  const buttonMap = () => {
    setShowRoutes(false);
    setShowMap(true);
    setShowPost(false);
    setShowPics(false);
  };
  const buttonPost = () => {
    setShowRoutes(false);
    setShowMap(false);
    setShowPost(true);
    setShowPics(false);
  };
  const buttonPics = () => {
    setShowRoutes(false);
    setShowMap(false);
    setShowPost(false);
    setShowPics(true);
  };
  const [user, setUser]=useState({});
  const getUser=async()=>{
    const email = Cookies.get("UserMail");
    const token=Cookies.get("myCookieName")
    const response = await axios.get(
      `http://localhost:8080/api/v1/demo-controller/userInfo/${email}`,
      {
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`
        }
      });
      
      
      setUser(response.data)
      console.log(user)
     
  }

  ////funzioni per i like crag
  ///aggiunge un like nel db
  const postLike = async () => {
    try {
      const response = await axios.post(
        `http://localhost:8080/api/v1/demo-controller/newCragLike?userEmail=${myemail}&cragId=${cragId}`,
        null,
        {
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${token}`,
          },
        }
      );
      checkLike();
      fetchLike();
    } catch (error) {
      console.error(error);
    }
  };
  const deleteLike = async () => {
    try {
      const response = await axios.delete(
        `http://localhost:8080/api/v1/demo-controller/like/user/${myemail}/crag/${cragId}`,
        {
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${token}`,
          },
        }
      );
      checkLike();
      fetchLike();
    } catch (error) {
      console.error(error);
    }
  };

  //ottiene numero di like nel db
  const fetchLike = async () => {
    const response = await axios.get(
      `http://localhost:8080/api/v1/demo-controller/like/crag/${cragId}`,
      {
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`,
        },
      }
    );
    const cragLikes = response.data;
    setCragLikes((prevCragLikes) => ({
      ...prevCragLikes,
      [cragId]: cragLikes,
    }));
  };
  ///controlla se l'utente ha aggiunto un like al post
  const checkLike = async () => {
    const response = await axios.get(
      `http://localhost:8080/api/v1/demo-controller/like/user/${myemail}/crag/${cragId}`,
      {
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`,
        },
      }
    );
    const cragCheck = response.data;
    setCragCheck((prevCragCheck) => ({
      ...prevCragCheck,
      [cragId]: cragCheck,
    }));
  };
  ///////get delle vie
  const getRoutes = async () => {
    try {
      const response = await axios.get(
        `http://localhost:8080/api/v1/demo-controller/getRouteByCragId/${cragId}`,
        {
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${token}`,
          },
        }
      );
      setRoutes(response.data);
    } catch (error) {
      console.error(error);
    }
  };
  ////get dei post falesia
  const fetchPost = async () => {
    const response = await axios.get(
      `http://localhost:8080/api/v1/demo-controller/post/crag/${cragId}`,
      {
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`,
        },
      }
    );
    setPosts(response.data);
    response.data.forEach((post) => {
      fetchLike2(post.id);
      checkLike2(post.id);
      getComment(post.id);
    });
  };
  const postLike2 = async (id) => {
    try {
      const response = await axios.post(
        `http://localhost:8080/api/v1/demo-controller/newPostLike?userEmail=${myemail}&postId=${id}`,
        null,
        {
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${token}`,
          },
        }
      );
      checkLike2(id);
      fetchLike2(id);
    } catch (error) {
      console.error(error);
    }
  };
  const deleteLike2 = async (postId) => {
    try {
      const response = await axios.delete(
        `http://localhost:8080/api/v1/demo-controller/like/user/${myemail}/post/${postId}`,
        {
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${token}`,
          },
        }
      );
      checkLike2(postId);
      fetchLike2(postId);
    } catch (error) {
      console.error(error);
    }
  };

  //ottiene numero di like nel db
  const fetchLike2 = async (postId) => {
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
  const checkLike2 = async (postId) => {
    const response = await axios.get(
      `http://localhost:8080/api/v1/demo-controller/like/user/${myemail}/post/${postId}`,
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
            userEmail: myemail,
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
  const getPic = async () => {
    const response = await axios.get(
      `http://localhost:8080/api/v1/demo-controller/pic/crag/${cragId}`,
      {
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`,
        },
      }
    );
    setPics(response.data);
  };

  ///fetch della falesia
  useEffect(() => {
    getUser();
    const getCrag = async () => {
      try {
        const response = await axios.get(
          `http://localhost:8080/api/v1/demo-controller/getCragById/${cragId}`,
          {
            headers: {
              "Content-Type": "application/json",
              Authorization: `Bearer ${token}`,
            },
          }
        );
        setCrag(response.data);
        fetchLike();
        checkLike();
      } catch (error) {
        console.error(error);
      }
    };

    if (cragId && token) {
      getCrag();
      getRoutes();
      fetchPost();
      getPic();
    }
  }, [cragId, token]);

  return (
    <>
      <div className="bg-gray-100 min-h-screen">
        <Header></Header>
        <div className="container mx-auto  min-h-full w-12/12 min-w-max mt-3">
          <div
            className="flex flex-row min-w-max rounded-xl mx-auto shadow-2xl bg-white overflow-hidden"
            style={{ maxWidth: 800 }}
          >
            <div className="w-4/12 min-w-max h-full flex flex-col items-center justify-center">
              <button>
                <img
                  src={decodeURIComponent(crag.pic)}
                  className="rounded-full m-8"
                  width={150}
                  height={150}
                />
              </button>
            </div>

            <div className=" w-6/12 min-w-max mx-2 mt-5">
              <div className="mt-5 flex">
                <button className="text-3xl flex inline-flex mr-2">
                  <p className="text-xxl font-black"> {crag.name}</p>
                </button>

                {cragCheck[cragId] ? (
                  <HeartIcon
                    onClick={() => deleteLike()}
                    className="h-8 text-red-500 m-1"
                  ></HeartIcon>
                ) : (
                  <HeartIcon
                    onClick={() => postLike()}
                    className="h-8 text-gray-300 m-1"
                  ></HeartIcon>
                )}
                <p className="mt-2">
                  {cragLikes[cragId] ? cragLikes[cragId].length : 0}
                </p>
              </div>
              <div className="mt-5 flex mr-2 min-w-max">
                <button className="text-lg md:text-xl mr-1">
                  Routes: {routes.length}
                </button>
                <button className="text-lg md:text-xl mx-1">Post: {posts.length}</button>
              </div>

              <div className="xl:block hidden my-2 overflow-wrap">
                <p className="overflow-wrap"></p>
              </div>
            </div>
          </div>
        </div>

        <div
          className="container mx-auto shadow-xl min-h-full w-12/12 min-w-max mt-3 bg-white rounded-lg flex text-center"
          style={{ maxWidth: 800 }}
        >
          <button
            onClick={() => buttonRoutes()}
            className="w-4/12 border-gray-100 border-r-2 p-2 "
          >
            <TagIcon className="h-6 m-auto" />
          </button>
          <button
            onClick={() => buttonMap()}
            className="w-4/12 border-gray-100 border-r-2 p-2"
          >
            <MapIcon className="h-6 m-auto" />
          </button>
          <button
            onClick={() => buttonPost()}
            className="w-4/12 border-gray-100 border-r-2 p-2"
          >
            <NewspaperIcon className="h-6 m-auto" />
          </button>
          <button onClick={() => buttonPics()} className="w-4/12  p-2">
            <PhotoIcon className="h-6 m-auto" />
          </button>
        </div>

        <div>
          {showPost ? (
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
                {post.crag&&post.routes == null ? (
                  <div className="text-red-500 flex ">
                    
                    <MapPinIcon className="h-5  mx-1 "></MapPinIcon>
                    <p>{post.crag.name}</p>
                  </div>
                ) : (
                  <></>
                )}
                 
  {post.routes != null && (
    <div className="flex">
      {post.style=="flash"&&(
        <Link href={`/comp/RouteComponent?routeId=${post.routes.id}`}>
        <div className="flex text-yellow-500">
         
         <p className="  flex mx-1"><BoltIcon className="h-4 mt-1"></BoltIcon></p>
         <p className="font-bold">{post.routes.name}</p>
        </div>
        </Link>
      )}
        {post.style=="onsight"&&(
          <Link href={`/comp/RouteComponent?routeId=${post.routes.id}`}>
        <div className="flex text-green-500">
         
         <p className="  flex mx-1"><EyeIcon className="h-4 mt-1"></EyeIcon></p>
         <p className="font-bold">{post.routes.name}</p>
        </div>
        </Link>
      )}
        {post.style=="redpointed"&&(
          <Link href={`/comp/RouteComponent?routeId=${post.routes.id}`}>
        <div className="flex text-red-500">
          <p className="ml-1">{post.tryNumber}</p>
         <p className="  flex mr-1"><FireIcon className="h-4 mt-1"></FireIcon></p>
         
         <p className="font-bold">{post.routes.name}</p>
        </div>
        </Link>
      )}
       {post.style=="failed"&&(
        <Link href={`/comp/RouteComponent?routeId=${post.routes.id}`}>
        <div className="flex text-orange-900">
          <p className="ml-1">{post.tryNumber}</p>
         <p className="  flex mr-1"><NoSymbolIcon className="h-4 mt-1"></NoSymbolIcon></p>
         
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
                onClick={() => deleteLike2(post.id)}
                className="h-8 text-red-500 m-2"
              ></HeartIcon>
            ) : (
              <HeartIcon
                onClick={() => postLike2(post.id)}
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
                      <h1 className="max-w" style={{ wordWrap: "break-word",  maxWidth: 300 }}>{cmt.text}</h1>
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
          ) : (
            <></>
          )}
        </div>

        <div>
          {showPics ? (
            <>
              <div className="flex flex-wrap mx-auto mt-2" style={{ maxWidth: 800 }}>
                {pics.map((pic) => (
                  <img
                    src={decodeURIComponent(pic)}
                    className="mx-auto p-1 "
                    width={400}
                    height={400}
                  />
                ))}
              </div>
            </>
          ) : (
            <></>
          )}
        </div>
        <div>
        {showMap ? (
            <>
              <div className="flex flex-wrap mx-auto mt-2" style={{ maxWidth: 800 }}>
               <MapTest  latProp={crag.lat} lngProp={crag.lng}></MapTest>
              </div>
            </>
          ) : (
            <></>
          )}
        </div>
        <div>
        {showRoutes ? (
            <>
               <div style={{ maxWidth: 800 }} className="mx-auto mt-2 bg-white rounded-xl shadow-xl
              ">
              {routes

              
                .map((route) => (
                  <div className="mx-auto flex" key={route.id}>
                      <Link href={`/comp/RouteComponent?routeId=${route.id}`}>
            <button
              className="flex w-full my-2"
              key={route.id}
              
            >
              <div className="flex w-full">
                <div className=" mx-3 rounded-xl">
                  <div className="max-h-fit rounded-lg px-2">
                    <TagIcon className="h-7 mt-2 text-green-500" />
                  </div>
                </div>
                <div className="flex-1">
                  <div className="flex items-center font-black">
                    <p className="font-bold text-md">{route.name}</p>
                  </div>
                  <div className="flex items-center text-gray-500 text-sm">
                    <p>{route.grade}</p>
                    <p className="mx-1">&bull;</p>
                    <p>{route.length}m</p>
                    <p className="mx-1">&bull;</p>
                    <p>{route.quickdraw}spit</p>
                  </div>
                 
                </div>
              </div>
            </button>
            </Link> 
                  </div>





                ))}
                
                </div>
                

            </>
          ) : (
            <></>
          )}
        </div>
      </div>
    </>
  );
}
export default CragComponent;
