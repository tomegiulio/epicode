import { useState } from "react";
import Image from "next/image";
import axios from "axios";
import Cookies from "js-cookie";
import pic from "../pic/register.jpg";
import ReCAPTCHA from "react-google-recaptcha";

function Login() {
  const [email, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [x, setError] = useState("");
    const [recaptchaValue, setRecaptchaValue] = useState(null);

const onChangeRecaptcha = (value) => {
  setRecaptchaValue(value);
};


  const handleSubmit = async (event) => {
    event.preventDefault();
     if(recaptchaValue === null) {
      setError("Please verify you are human");
    } 
    else if (password == "" || email == "") {
      setError("please fill all the fields");
    } else {
      try {
        const response = await axios.post(
          "http://localhost:8080/api/v1/auth/authenticate",
          { email, password },
          {
            headers: {
              "Content-Type": "application/json",
            },
          }
        );
        const data = response.data;
  
        const expirationDate = new Date();
        expirationDate.setDate(expirationDate.getDate() + 360);
  
        const cookieValue = data.token;
        const cookieName = "myCookieName";
  
        Cookies.set(cookieName, cookieValue, {
          expires: expirationDate,
        });
        setPassword("");
        setUsername("");
        setError("");
        location.href="/"
  
      } catch (error) {
        console.error(error);
        setError("wrong email or password");
      }
    
     
    }
  };
  
  
  return (
    <>
      <div className="container mx-auto my-16">
        <div className="flex flex-col lg:flex-row  w-10/12 xl:w-8/12  bg-gray-100 rounded-xl mx-auto shadow-2xl overflow-hidden">
          <div className="w-full h-full hidden lg:inline-flex lg:w-1/2 flex flex-col items-center justify-center">
            <Image
              className="object-cover object-fill"
              height="auto"
              width="auto"
              src={pic}
              alt="logo"
            />
          </div>

          <div className="w-full lg:w-1/2 py-16 px-12">
            <h2 className="text-3xl mb-4">Login</h2>
            
            
              
              <div className="mt-5">
                <input
                  name="email"
                  value={email}
                  onChange={(e) => setUsername(e.target.value)}
                  type="text"
                  placeholder="Email"
                  className="border border-gray-400 py-1 px-2 w-full"
                />
              </div>
              <div className="mt-5">
                <input
                  name="password"
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                  type="password"
                  placeholder="Password"
                  className="border border-gray-400 py-1 px-2 w-full"
                />
              </div>
               <div className="mt-5">
               <ReCAPTCHA
                  sitekey="6LfPwmUlAAAAAKBhJqB_QONnbVb5OSGl8zoS0nkM"
                  onChange={onChangeRecaptcha}
                />
               </div>
              

              <div name="x" className="my-2 text-red-600">{x}</div>
                
                <button
                  onClick={handleSubmit}
                  className="w-full bg-black py-3 text-center text-white rounded-lg"
                >
                  Login Now
                </button>
              
              </div>
            
          </div>
        </div>
      
    </>
  );
}
export default Login;
