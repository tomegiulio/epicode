import { useState, useEffect } from "react";
import { useRouter } from "next/router";
import Cookies from "js-cookie";
import axios from "axios";
import CreatePostv2 from "../comp/CreatePostv2";
import Header from "../comp/Header";
import Link from "next/link";
import { MapIcon, MapPinIcon, TagIcon,ChatBubbleOvalLeftIcon, NoSymbolIcon,
  FireIcon,
  EyeIcon,
  BoltIcon } from "@heroicons/react/24/outline";
import { HeartIcon, EllipsisHorizontalIcon } from "@heroicons/react/24/solid";

export default function RouteComponent() {
  const router = useRouter();
  const data = router.query;
  const routeId = parseInt(data.routeId);

  const [route, setRoute] = useState([]);
  const [crag, setCrag] = useState([]);

  const [posts, setPosts] = useState([]);

  const email = Cookies.get("UserMail");
  const token = Cookies.get("myCookieName");
  const myemail = Cookies.get("UserMail");
  const pic = Cookies.get("UserPic");
  const [likes, setLikes] = useState({});
  const [check, setCheck] = useState({});
  const [comment, setComment] = useState([]);
  const [comments, setComments] = useState({});
  const [showComments, setShowComments] = useState(false);
  const [commentText, setCommentText] = useState();
  const [activePostId, setActivePostId] = useState(null);
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
 
  const fetchPost = async () => {
    const response = await axios.get(
      `http://localhost:8080/api/v1/demo-controller/post/routes/${routeId}`,
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

  useEffect(() => {
    getUser();
    const getRoute = async () => {
      try {
        const response = await axios.get(
          `http://localhost:8080/api/v1/demo-controller/getRouteById/${routeId}`,
          {
            headers: {
              "Content-Type": "application/json",
              Authorization: `Bearer ${token}`,
            },
          }
        );
        setRoute(response.data);
      } catch (error) {
        console.error(error);
      }
    };
    const getCrag = async () => {
      try {
        const response = await axios.get(
          `http://localhost:8080/api/v1/demo-controller/getRouteCragPic/${routeId}`,
          {
            headers: {
              "Content-Type": "application/json",
              Authorization: `Bearer ${token}`,
            },
          }
        );
        setCrag(response.data);
      } catch (error) {
        console.error(error);
      }
    };
    if (routeId && token) {
      getRoute();
      getCrag();
      fetchPost();
    }
  }, [routeId, token]);

  return (
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
                loading="lazy"
              />
            </button>
          </div>

          <div className=" w-6/12 min-w-max mx-2 mt-5">
            <div className="mt-5 flex">
              <button className="text-3xl flex inline-flex mr-2">
              <p className="text-xl md:text-3xl font-black">{route.name}</p>
              </button>
            </div>

            <div className="mt-2  mr-2 min-w-max">
              <div className="flex">
                <button className="text-lg md:text-xl mx-1 font-bold">
                  {route.grade}
                </button>
                <button className="text-lg md:text-xl mx-1 font-bold">
                  {route.length}m{" "}
                </button>
                <button className="text-lg md:text-xl mx-1 font-bold">
                  {route.quickdraw} quickdraw
                </button>
              </div>
              <Link href={`/comp/CragComponent?cragId=${crag.id}`}>
                <button className="flex w-full my-1 text-red-500">
                  <MapPinIcon className="h-5" />
                  <p className="font-bold text-md">{crag.name}</p>
                </button>
              </Link>
            </div>
          </div>
        </div>
      </div>
      <div>
         
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
                  <div className="text-red-500 flex ">
                    {" "}
                    <MapPinIcon className="h-5  mx-1 "></MapPinIcon>
                    <p>{post.crag.name}</p>
                  </div>
                ) : (
                  <></>
                )}

                {post.routes != null && (
                  <div className="flex">
                    {post.style == "flash" && (
                      <div className="flex text-yellow-500">
                        <p className="  flex mx-1">
                          <BoltIcon className="h-4 mt-1"></BoltIcon>
                        </p>
                        <p className="font-bold">{post.routes.name}</p>
                      </div>
                    )}
                    {post.style == "onsight" && (
                      <div className="flex text-green-500">
                        <p className="  flex mx-1">
                          <EyeIcon className="h-4 mt-1"></EyeIcon>
                        </p>
                        <p className="font-bold">{post.routes.name}</p>
                      </div>
                    )}
                    {post.style == "redpointed" && (
                      <div className="flex text-red-500">
                        <p className="ml-1">{post.tryNumber}</p>
                        <p className="  flex mr-1">
                          <FireIcon className="h-4 mt-1"></FireIcon>
                        </p>

                        <p className="font-bold">{post.routes.name}</p>
                      </div>
                    )}
                    {post.style == "failed" && (
                      <div className="flex text-orange-900">
                        <p className="ml-1">{post.tryNumber}</p>
                        <p className="  flex mr-1">
                          <NoSymbolIcon className="h-4 mt-1"></NoSymbolIcon>
                        </p>

                        <p className="font-bold">{post.routes.name}</p>
                      </div>
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
          
        </div>
    </div>
  );
}
