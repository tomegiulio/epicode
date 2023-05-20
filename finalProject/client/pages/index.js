import Head from 'next/head'
import Header from './comp/Header'
import axios from "axios";
import Login from './comp/auth/Login'
import Logout from './comp/auth/Logout'
import Register from './comp/auth/Register'
import MapTest from './comp/MapTest'
import Link from "next/link";
import Cookies from "js-cookie";
import jwt_decode from 'jwt-decode';
import pic from "../pages/comp/pic/register.jpg"
import Image from "next/image";

import Profile from './comp/Profile'
import AvatarUpload from './comp/AvatarUpload'
import ContentLoad from './comp/ContentLoad'
import { useEffect, useState } from "react";
export default function Home() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [user, setUser]=useState({})
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

  
  useEffect(() => {
    try{ 
    if(decode()!=null){
        setIsLoggedIn(true)
       

        
      }}catch(error){
        console.log(error)
      }
  }, [isLoggedIn]);

  return (
    
    <>
     
      <Head>
        <title>capstone</title>
        
      </Head>
      {isLoggedIn ? (
          <>
      <div className='bg-gray-100 min-h-screen'>
      <Header></Header>
      <ContentLoad></ContentLoad>
      </div>
      </>):
      
      (<>
      <div className='bg-gray-100 min-h-screen'>
        <div className='h-20'></div>
       <div className=' mx-auto bg-white rounded-xl shadow-2xl p-2' style={{ maxWidth: 700 }}> 
        <h1 className='text-2xl font-black text-center'>Hi welcome in my capstone project</h1>
        <p className='text-lg font-semibold text-center'>Please for go ahead create an account or log in </p>
        <div className='mx-auto '>
        <Link href="../comp/auth/Login">
            <p className=" mt-2 bg-black text-white mx-auto text-center w-6/12 p-2 rounded-xl">login</p>
          </Link>
         
            
          <Link href="../comp/auth/Register">
          <p className=" mt-2 bg-black text-white mx-auto text-center w-6/12 p-2 rounded-xl">register</p>
          </Link>  
        </div>
         
        </div>
       
        </div>
      </>)}
      
      
      
   
    </>
    
  )
}
