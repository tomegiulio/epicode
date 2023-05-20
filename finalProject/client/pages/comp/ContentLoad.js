import axios from "axios";
import { useEffect, useState } from "react";
import Cookies from "js-cookie";
import Rating from "react-rating-stars-component";
import {
  HeartIcon,
  EllipsisHorizontalIcon,
  TagIcon,
  EllipsisVerticalIcon,
} from "@heroicons/react/24/solid";
import {
  ChatBubbleOvalLeftIcon,
  MapPinIcon,
  NoSymbolIcon,
  FireIcon,
  EyeIcon,
  BoltIcon,
} from "@heroicons/react/24/outline";
import CreatePostv2 from "../comp/CreatePostv2";
import Link from "next/link";

export default function ContentLoad() {
  const [posts, setPosts] = useState([]);
  const [likes, setLikes] = useState({});

  const [check, setCheck] = useState({});
  const [comment, setComment] = useState([]);
  const [comments, setComments] = useState({});
  const [showComments, setShowComments] = useState(false);
  const [activePostId, setActivePostId] = useState(null);
  const [commentText, setCommentText] = useState();
  const [user, setUser]=useState({})

  const email = Cookies.get("UserMail");
  const token = Cookies.get("myCookieName");
  const pic = Cookies.get("UserPic");

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
      "http://localhost:8080/api/v1/demo-controller/getAll/post",
      {
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`,
        },
      }
    );
    setPosts(response.data);
    response.data.forEach((post) => {
      fetchLike(post.id);
      checkLike(post.id);
      getComment(post.id);
    });
  };
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
    fetchPost();
    getUser();
  }, []);

  return (
    <div>
      <CreatePostv2></CreatePostv2>
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
                      <p className="max-w mx-2"   style={{ wordWrap: "break-word",  maxWidth: 300 }}>{cmt.text}</p>
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
  );
}
