import HeaderIcon from "./HeadeIcon";
import { useEffect, useState } from "react";
import Cookies from "js-cookie";
import axios from "axios";
import Image from "next/image";
import logo from "pages/comp/pic/download.svg";
import jwt_decode from 'jwt-decode';
import {
  MapPinIcon,
  SearchIcon,
  Bars4Icon,
  MapIcon,
  BellAlertIcon,
  FilmIcon,
  CameraIcon,
  NewspaperIcon,
  ShareIcon,
  TrashIcon,
  StarIcon,
  Cog6ToothIcon,
  EyeIcon,
  ExclamationTriangleIcon,
  CalendarDaysIcon,
  BoltIcon,
  UserCircleIcon,
  UserPlusIcon,
  HomeIcon,
  ChatBubbleOvalLeftIcon,
  PlusIcon,
} from "@heroicons/react/24/outline";
import Link from "next/link";
import { _ErrorFilterSensitiveLog } from "@aws-sdk/client-s3";
import SearchBar from "./Searchbar";

function Header() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [user, setUser]=useState({})
  const token=Cookies.get("myCookieName")
    const email=Cookies.get("UserMail")
 
  ///email
  const decode=()=>{
    const token=Cookies.get("myCookieName")
  const decodedToken = jwt_decode(token);
  
  const sub = decodedToken.sub;
  const expirationDate = new Date();
            expirationDate.setDate(expirationDate.getDate() + 360);

            const cookieValue = sub;
            const cookieName = "UserMail";

            Cookies.set(cookieName, cookieValue, {
              expires: expirationDate,
            });
  return sub;
  ;
  }
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
  

  const pic=async()=>{
    
    const response=await axios.get(
      `http://localhost:8080/api/v1/demo-controller/userPic/${email}`,
      {
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`
        }
      });
      const data=response.data;
     const expirationDate = new Date();
     expirationDate.setDate(expirationDate.getDate() + 360);
    const cookieValue = decodeURIComponent(data);
    const cookieName = "UserPic";
       Cookies.set(cookieName, cookieValue, {
              expires: expirationDate,
            });
  }



  useEffect(() => {
    try{
    if(decode()!=null){
        setIsLoggedIn(true)
        if(isLoggedIn){ 
          getUser();
       
        }
      }}catch{
    }
  }, [isLoggedIn]);

  return (
    <>
    
      <div className="sticky top-0 z-50 bg-white flex items-center p-2 lg:px-5 shadow-md">
        <div className="flex items-center ml-2" style={{ position: "absolute", left: 0,top:9 }}>
          <Link href="/">
            <Image src={logo} width={80} height={80} alt="logo" />
          </Link>
        
        </div>
        <div className=" mx-auto w-5/12">
        <SearchBar></SearchBar>
        </div>
        

        {isLoggedIn ? (
          <>
            
            <Link href={`/comp/UserComponent?email=${email}`}>
            <img
            style={{ position: "absolute", right: 0,top:9 }}
                src={decodeURIComponent(user.profilePic)}
                className="rounded-full mx-3"
                width={40}
                height={40}
                
              />
            </Link>
            
          </>
        ) : (
          <>
          <Link href="../comp/auth/Register">
            <button className="bg-black text-white p-2 m-1 rounded-xl"> register</button>
          </Link>  
          <Link href="../comp/auth/Login">
            <button className="bg-black text-white p-2 m-1 rounded-xl">login</button>
          </Link>    
          </>
        )}
      </div>
    </>
  );
}
export default Header;
