import axios from "axios";
import aws from "aws-sdk";
import { useEffect, useState, useRef } from "react";
import Rating from "react-rating-stars-component";

import Cookies from "js-cookie";
import {
  PhotoIcon,
  PaperAirplaneIcon,
  TagIcon,
  MapPinIcon,
  MagnifyingGlassIcon,
  ArrowLeftIcon,
  NoSymbolIcon,
  FireIcon,
  EyeIcon,
  BoltIcon,
  ChevronDownIcon
} from "@heroicons/react/24/outline";
import Modal from "react-modal";

const S3_BUCKET_NAME = "ulioicebucketchallange";
const REGION = "eu-central-1";
const ACCESS_KEY_ID = "AKIAT4DFWYEXKCOIKSH4";
const SECRET_ACCESS_KEY = "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";

export default function CreatePostv2() {
  const [commentText, setCommentText] = useState("");
  const [selectedImage, setSelectedImage] = useState(null);
  const inputRef = useRef(null);
  const [url, setUrl] = useState(null);
  const [modalIsOpen, setModalIsOpen] = useState(false);
  const [modal2IsOpen, setModal2IsOpen] = useState(false);
  const [modal3IsOpen, setModal3IsOpen] = useState(false);
  const [modal4IsOpen, setModal4IsOpen] = useState(false);
  const [query, setQuery] = useState("");
  const [query2, setQuery2] = useState("");
  const [results, setResults] = useState({ crags: [], routes: [] });
  const [results2, setResults2] = useState({ routes: [] });
  const [cragEl, setCragEl] = useState({});
  const [routeEl, setRouteEl] = useState({});
  const [routeFinal, setRouteFinal] = useState({});
  const [x, setError] = useState("");
  const [user, setUser]=useState({})
 

  const [flash, setFlash] = useState(false);
  const [onsight, setOnsight] = useState(false);
  const [redpointed, setRedpointed] = useState(false);
  const [failed, setFailed] = useState(false);

  const [tryNumber, setTryNumber] = useState("");
  const [rating, setRating] = useState(0);

  
  const token = Cookies.get("myCookieName");
  const email = Cookies.get("UserMail");

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
      console.log(user)
     
  }

  const handleChange = (newRating) => {
    setRating(newRating);
  };

  const setStyleF = () => {
    closeModal4()
    setFlash(true);
    setOnsight(false);
    setRedpointed(false);
    setFailed(false);
  };
  const setStyleO = () => {
    closeModal4()
    setFlash(false);
    setOnsight(true);
    setRedpointed(false);
    setFailed(false);
  };

  const setStyleR = () => {
    closeModal4()
    setFlash(false);
    setOnsight(false);
    setRedpointed(true);
    setFailed(false);
  };

  const setStyleFail = () => {
    closeModal4()
    setFlash(false);
    setOnsight(false);
    setRedpointed(false);
    setFailed(true);
  };
 
  const saveRoute=()=>{
    if(rating == 0){
      setError("Please fill all the fields");
    }
    else if(flash==false&&onsight==false&&redpointed==false&&failed==false){
      setError("Please fill all the fields");
    }else if(failed==true&&tryNumber<1){
      setError("Please fill all the fields");
    }else if(redpointed==true&&tryNumber<1){
      setError("Please fill all the fields");
    }else{
      setError("")
      setRouteFinal(routeEl);
      closeModal3();
    }
  }

  const postPost = async () => {
    let cragId = 0;
    let routeId=0
    if (cragEl !== 0 && cragEl !== null) {
      cragId = cragEl.id;
    } if (routeFinal !== 0 && routeFinal !== null) {
      routeId = routeFinal.id;
    }
    if (commentText !== "" || selectedImage !== null) {
      try {
        if (selectedImage !== null) {
          const s3 = new aws.S3({
            region: REGION,
            accessKeyId: ACCESS_KEY_ID,
            secretAccessKey: SECRET_ACCESS_KEY,
          });
          const file = inputRef.current.files[0];
          const fileName = `${Date.now()}_${file.name}`;
          const fileReader = new FileReader();
          fileReader.readAsArrayBuffer(file);
          fileReader.onload = async () => {
            const params = {
              Bucket: S3_BUCKET_NAME,
              Key: fileName,
              Body: fileReader.result,
              ACL: "public-read",
            };
            const s3Response = await s3.upload(params).promise();
            const url = s3Response.Location;

            let PostRequest = {};
            PostRequest.cragId = cragId;
            PostRequest.routeId = routeId;
            PostRequest.star= rating;
            if(flash==true){
            PostRequest.style="flash"
            PostRequest.tryNum=1
            }if(onsight==true){
              PostRequest.style="onsight"
              PostRequest.tryNum=1
              }if(redpointed==true){
                PostRequest.style="redpointed"
                PostRequest.tryNum=tryNumber
                }if(failed==true){
                  PostRequest.style="failed"
                  PostRequest.tryNum=tryNumber
                  }
            if (commentText !== "") {
              PostRequest.text = commentText;
            }
            if (url !== null) {
              PostRequest.url = url;
            }
            console.log(PostRequest);
            const response = await axios.post(
              `http://localhost:8080/api/v1/demo-controller/newPost?userMail=${email}`,
              PostRequest,
              {
                headers: {
                  "Content-Type": "application/json",
                  Authorization: `Bearer ${token}`,
                },
              }
            );
            console.log(response);
            setCommentText("");
            setSelectedImage(null);
            setTimeout(function () {
              location.reload();
            }, 1000);
          };
        } else {
          const PostRequest = {};

          if (commentText !== "") {
            PostRequest.text = commentText;
            PostRequest.cragId = cragId;
            PostRequest.routeId = routeId;
            PostRequest.star= rating;
            if(flash==true){
            PostRequest.style = "flash"
            PostRequest.tryNum=1
            }if(onsight==true){
              PostRequest.style = "onsight"
              PostRequest.tryNum=1
              }if(redpointed==true){
                PostRequest.style = "redpointed"
                PostRequest.tryNum=tryNumber
                }if(failed==true){
                  PostRequest.style="failed"
                  PostRequest.tryNum=tryNumber
                  }
          }
          

          const response = await axios.post(
            `http://localhost:8080/api/v1/demo-controller/newPost?userMail=${email}`,
            PostRequest,
            {
              headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${token}`,
              },
            }
          );
          console.log(response);
          setCommentText("");
          setSelectedImage(null);
          setTimeout(function () {
            location.reload();
          }, 3000);
        }
      } catch (error) {
        console.error(error);
      }
    }
  };

  const insertCrag = () => {
    setModalIsOpen(true);
  };

  const insertRoutesx = () => {
    setModal2IsOpen(true);
  };

  const handleSearch = async () => {
    try {
      const cragsResponse = await axios.get(
        `http://localhost:8080/api/v1/demo-controller/getCrag/${query}`,
        {
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${token}`,
          },
        }
      );
      const cragsData = await cragsResponse.data;

      const routesResponse = await axios.get(
        `http://localhost:8080/api/v1/demo-controller/getRoute/${query}`,
        {
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${token}`,
          },
        }
      );
      const routesData = await routesResponse.data;
      setResults({ crags: cragsData, routes: routesData });
    } catch (error) {
      console.error(error);
    }
  };

  function closeModal() {
    setModalIsOpen(false);
    setQuery("");
  }
  function closeModal2() {
    setModal2IsOpen(false);
    setQuery2("");
  }
  function closeModal3() {
    setModal3IsOpen(false);
    setQuery("");
  }
  function closeModal4() {
    setModal4IsOpen(false);
    setQuery("");
  }
  const setCrag = (crag) => {
    setCragEl(crag);
    closeModal();
  };
  const setRoute = (route) => {
    setRouteEl(route);
    closeModal2();
    setModal3IsOpen(true);
  };

  const insertMedia = () => {
    inputRef.current.click();
  };

  const handleUploadImage = (event) => {
    const file = event.target.files[0];
    setSelectedImage(URL.createObjectURL(file));
  };

  useEffect(() => {
    getUser();
    if (query !== "" || query2 !== "") {
      handleSearch();
    }
  }, [query, query2]);
  return (
    <>
      <div className="bg-gray-100 ">
        <div
          className="mx-auto bg-white rounded-xl shadow-xl my-4"
          style={{ maxWidth: 800 }}
        >
          <div className="flex p-3 min-w-full">
            <img
              src={decodeURIComponent(user.profilePic)}
              className="rounded-full mx-2 m-2"
              width={40}
              height={40}
              loading="lazy"
            />
            <textarea
              name="commentText"
              value={commentText}
              onChange={(e) => {
                setCommentText(e.target.value);
              }}
              className="bg-gray-100 w-full rounded-lg placeholder:px-3 resize-none pr-2"
              placeholder="write a new post"
              type="textarea"
            />
          </div>

          <div className="block">
            {selectedImage && (
              <img
                src={selectedImage}
                alt="Selected"
                width={800}
                height={800}
              />
            )}
          </div>
          <div className="flex">
            <input
              ref={inputRef}
              id="upload-input"
              type="file"
              onChange={handleUploadImage}
              style={{ display: "none" }}
            />
            <div></div>

            <div className="flex w-full p-2">
  <div className=" flex">
    <button
      onClick={() => insertMedia()}
      className="text-yellow-500 w-auto flex mr-2 rounded-lg p-2"
      style={{ whiteSpace: 'nowrap' }}
    >
      <PhotoIcon className="mx-1 h-6"></PhotoIcon>
      media
    </button>
    <button
      onClick={() => insertCrag()}
      className="text-red-500 mr-2 w-auto mx-auto flex rounded-lg p-2"
      style={{ whiteSpace: 'nowrap' }}
    >
      <MapPinIcon className="h-5 mx-1"></MapPinIcon>
      {cragEl.name ? <p>{cragEl.name}</p> : <p>crag</p>}
    </button>
    <button
      onClick={() => insertRoutesx()}
      className="text-green-500 w-auto mx-auto flex rounded-lg p-2"
      style={{ whiteSpace: 'nowrap' }}
    >
      <TagIcon className="h-5  mx-1"></TagIcon>
      {routeFinal.name ? (
        <div className="flex">{routeFinal.name}</div>
      ) : (
        <p>ascend</p>
      )}
    </button>
  </div>


              <div className="w-6/12 mx-auto"></div>

              <div
                className="w-3/12 text-center mr-1"
                style={{ position: "relative" }}
              >
                <button
                  onClick={() => postPost()}
                  className="text-blue-500 flex rounded-lg p-2"
                  style={{ position: "absolute", right: 0 }}
                >
                  <PaperAirplaneIcon className="h-5 mt-1 mx-1 "></PaperAirplaneIcon>
                  post
                </button>
              </div>
            </div>
          </div>
        </div>

        <Modal
          isOpen={modalIsOpen}
          onRequestClose={closeModal}
          className="md:w-6/12 w-12/12 p-2 mx-auto mt-24 bg-white  drop-shadow-2xl outline-0 rounded-2xl"
        >
          <div className="flex mx-4">
            <div
              onClick={() => closeModal()}
              className="bg-gray-200 rounded-full p-2"
            >
              <ArrowLeftIcon className="h-5"></ArrowLeftIcon>
            </div>

            <div className="mx-auto font-black text-lg mt-1">
              <h1>Search the crag</h1>
            </div>
          </div>
          <hr className="my-2"></hr>

          <div className="p-2">
            <div className="flex rounded-full w-12/12 mx-auto p-2 bg-gray-100">
              <MagnifyingGlassIcon className="h-6 text-zinc-500"></MagnifyingGlassIcon>

              <input
                className="items-center ml-2 bg-transparent outline-none flex-shrink"
                type="text"
                placeholder="search here"
                value={query}
                onChange={(e) => setQuery(e.target.value)}
              />
            </div>
          </div>

          <div className="bg-white w-12/12 mx-auto pt-2 rounded-b-full">
            {results.crags.map((crag) => (
              <button
                className="flex w-full my-2"
                key={crag.id}
                onClick={() => setCrag(crag)}
              >
                <div className="flex w-full">
                  <div className=" mx-3 rounded-xl">
                    <div className=" max-h-fit rounded-lg px-2">
                      <MapPinIcon className="h-7 mt-2 text-red-500" />
                    </div>
                  </div>

                  <div className="flex-1">
                    <div className="flex items-center font-black">
                      <p className="font-bold text-md">{crag.name}</p>
                    </div>

                    <div className="flex items-center text-gray-500 text-sm">
                      <p>{crag.city}</p>
                      <p className="mx-1">&bull;</p>
                      <p>{crag.country}</p>
                    </div>
                  </div>
                </div>
              </button>
            ))}
          </div>
        </Modal>

        <Modal
          isOpen={modal2IsOpen}
          onRequestClose={closeModal2}
          className="md:w-6/12 w-12/12 p-2 mx-auto mt-24 bg-white  drop-shadow-2xl outline-0 rounded-2xl"
        >
          <div className="flex mx-4">
            <div
              onClick={() => closeModal2()}
              className="bg-gray-200 rounded-full p-2"
            >
              <ArrowLeftIcon className="h-5"></ArrowLeftIcon>
            </div>

            <div className="mx-auto font-black text-lg mt-1">
              <h1>Search the route</h1>
            </div>
          </div>
          <hr className="my-2"></hr>

          <div className="p-2">
            <div className="flex rounded-full w-12/12 mx-auto p-2 bg-gray-100">
              <MagnifyingGlassIcon className="h-6 text-zinc-500"></MagnifyingGlassIcon>

              <input
                className="items-center ml-2 bg-transparent outline-none flex-shrink"
                type="text"
                placeholder="search here"
                value={query}
                onChange={(e) => setQuery(e.target.value)}
              />
            </div>
          </div>

          <div className="bg-white w-12/12 mx-auto pt-2 rounded-b-full">
            {results.routes.map((route) => (
              <button
                className="flex w-full my-2"
                key={route.id}
                onClick={() => setRoute(route)}
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
                    </div>
                  </div>
                </div>
              </button>
            ))}
          </div>
        </Modal>
        <Modal
          isOpen={modal3IsOpen}
          onRequestClose={closeModal3}
          className="md:w-6/12 w-12/12 p-2 mx-auto mt-24 bg-white  drop-shadow-2xl outline-0 rounded-2xl"
        >
          <div className="flex mx-4">
            <div
              onClick={() => closeModal3()}
              className="bg-gray-200 rounded-full p-2"
            >
              <ArrowLeftIcon className="h-5"></ArrowLeftIcon>
            </div>

            <div className="mx-auto font-black text-xl mt-1">
              <h1>Ascend Info</h1>
            </div>
          </div>
          <hr className="mt-2"></hr>

          <div className="p-2">
            <div className="flex w-full">
              <div className=" mx-3 rounded-xl">
                <div className="max-h-fit rounded-lg px-2">
                  <TagIcon className="h-7 mt-2 text-green-500" />
                </div>
              </div>
              <div className="flex-1">
                <div className="flex items-center font-black">
                  <p className="font-bold text-md">{routeEl.name}</p>
                </div>
                <div className="flex items-center text-gray-500 text-sm">
                  <p>{routeEl.grade}</p>
                  <p className="mx-1">&bull;</p>
                  <p>{routeEl.length}m</p>
                </div>
                
                
               
              </div>

              <Rating
                count={5}
                size={24}
                activeColor="#ffd700"
                onChange={handleChange}
              />
            </div>
            <div className="flex my-8">
              <div className="w-6/12 flex">
                <button className="bg-gray-100 p-2 rounded-xl mx-4 flex border border-gray-300" onClick={() => setModal4IsOpen(!modal4IsOpen)}>
                {flash==false&&onsight==false&&redpointed==false&&failed==false?<p className="ml-1">select</p>:<></>}
                {flash==true?(<><BoltIcon className="h-5 mt-1 mx-1 text-yellow-500"></BoltIcon><p className="text-lg">Flash</p></>):<></>} 
                {onsight==true?(<><EyeIcon className="h-5 mt-1 mx-1 text-green-500"></EyeIcon><p className="text-lg">Onsight</p></>):<></>} 
                {redpointed==true?(<><FireIcon className="h-5 mt-1 mx-1 text-red-500"></FireIcon><p className="text-lg">RedPoint</p></>):<></>} 
                {failed==true?(<><NoSymbolIcon className="h-5 mt-1  mx-1 text-orange-900"></NoSymbolIcon><p className="text-lg">Failed</p></>):<></>} 
                <ChevronDownIcon className="h-3 mt-2 ml-1"></ChevronDownIcon>
                </button>
                
                   
                  
                {redpointed==true||failed==true?
                 <input
                 style={{ position: "absolute", right: 100 }}
                 name="try"
                 value={tryNumber}
                 onChange={(e) => setTryNumber(e.target.value)}
                 onInput={(e) => {
                   e.target.value = Math.max(1, Math.min(999, parseInt(e.target.value))) || "";
                 }}
                 type="number"
                 placeholder="try number"
                 className="bg-gray-100 border border-gray-300 rounded-lg w-4/12 py-3 pl-2"
               />
               
                :<></>}
                </div> 
                <button onClick={()=>saveRoute()} className="bg-gray-50 flex border border-gray-300 py-3 px-4 rounded-xl"  style={{ position: "absolute", right: 18 }}>
                  <p  className="font-bold mx-1">set</p>
                </button>
               
                
                
                 
                </div>
                <p className="ml-4 text-red-500" name="x"> 
                      {x}
                </p>
          </div>
        </Modal>
        <Modal
          isOpen={modal4IsOpen}
          onRequestClose={closeModal4}
          className="md:w-6/12 w-12/12 p-2 mx-auto mt-24 bg-white  drop-shadow-2xl outline-0 rounded-2xl"
        >
           <div className="flex mx-4">
            <div
              onClick={() => closeModal4()}
              className="bg-gray-200 rounded-full p-2"
            >
              <ArrowLeftIcon className="h-5"></ArrowLeftIcon>
            </div>

            <div className="mx-auto font-black text-lg mt-1">
              <h1>Ascend Style</h1>
            </div>
          </div>
          <hr className="mt-2"></hr>
                          <button  
                    class="text-lg rounded-lg w-full mt-2 "
                    
                  >
                          <div className=" flex py-2 hover:bg-gray-200  " onClick={() =>setStyleO() }>
                          <EyeIcon className="h-5 text-green-500  mt-1 ml-4 mr-1 "></EyeIcon>  Onsight
                          </div>
                          <div className=" flex py-2 hover:bg-gray-200  "  onClick={() =>setStyleF() } >
                           <BoltIcon className="h-5 text-yellow-500  mt-1   ml-4 mr-1 "></BoltIcon>Flash
                          </div>
                          <div className=" flex py-2 hover:bg-gray-200  "onClick={() =>setStyleR() }>
                            <FireIcon className="h-5 text-red-500  mt-1   ml-4 mr-1 "></FireIcon>Redpointed
                          </div>
                          <div className="flex py-2 hover:bg-gray-200   "onClick={() =>setStyleFail() }>
                            <NoSymbolIcon className="h-5 text-orange-900  mt-1   ml-4 mr-1 "></NoSymbolIcon>Failed
                          </div>
                        </button>
                  </Modal>
      </div>
    </>
  );
}
