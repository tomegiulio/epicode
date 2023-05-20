import Image from "next/image";
import { useState } from "react";
import axios from "axios";
import Cookies from "js-cookie";
import pic from "../pic/register.jpg";
import ReCAPTCHA from "react-google-recaptcha";

function Register() {
  const [firstname, setName] = useState("");
  const [lastname, setSurname] = useState("");
  const [email, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [password2, setPassword2] = useState("");
  const [x, setError] = useState("");
  const [recaptchaValue, setRecaptchaValue] = useState(null);

const onChangeRecaptcha = (value) => {
  setRecaptchaValue(value);
};


  const handleSubmit = async (event) => {
    event.preventDefault();
    if (firstname === "" || lastname === "") {
      setError("please fill all the fields");
    }else if(recaptchaValue === null) {
      setError("Please verify you are human");
    } 

    else {
      if (ValidateEmail() === true) {
        try {
          const emailAvailable = await checkEmail();
          if (emailAvailable) {
            if (validatePassword(password) === true) {
              if (password === password2) {
                const response = await axios.post(
                  "http://localhost:8080/api/v1/auth/register",
                  { firstname, lastname, email, password },
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

                setName("");
                setSurname("");
                setUsername("");
                setPassword("");
                setPassword2("");
                setError("");
                location.href = "/";

                return response;
              } else {
                setError("Passwords do not match");
              }
            } else {
              setError(
                "the password needs to have at least 8 characters, one uppercase letter, one number, and one special character"
              );
            }
          } else {
            setError("already used email");
          }
        } catch (error) {
          console.error(error);
        }
      } else {
        setError("invalid email");
      }
    }
  };

  function validatePassword(password) {
    const regex =
      /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*])[0-9a-zA-Z!@#$%^&*]{8,}$/;
    return regex.test(password);
  }

  function ValidateEmail() {
    if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(email)) {
      return true;
    }
    return false;
  }

  async function checkEmail() {
    try {
      const response = await axios.get(
        `http://localhost:8080/api/v1/auth/userCheck/${email}`,
        {
          headers: {
            "Content-Type": "application/json",
          },
        }
      );
      if (response.data === false) {
        return true;
      }
      return false;
    } catch (error) {
      console.error(error);
      return false;
    }
  }

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

          <div className="w-full lg:w-1/2 py-16 px-12 ">
            <h2 className="text-3xl mb-4">Register</h2>
            <p className="mb-4">
              Create your account. It’s free and only take a minute
            </p>
            <form>
              <div className="grid grid-cols-2 gap-5">
                <input
                  name="firstname"
                  value={firstname}
                  onChange={(e) => setName(e.target.value)}
                  type="text"
                  placeholder="Firstname"
                  className="border border-gray-400 py-1 px-2"
                />
                <input
                  name="lastname"
                  value={lastname}
                  onChange={(e) => setSurname(e.target.value)}
                  type="text"
                  placeholder="Surname"
                  className="border border-gray-400 py-1 px-2"
                />
              </div>
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
                <input
                  name="password2"
                  value={password2}
                  onChange={(e) => setPassword2(e.target.value)}
                  type="password"
                  placeholder="Confirm Password"
                  className="border border-gray-400 py-1 px-2 w-full"
                />
              </div>
              <div className="mt-5">
                <input type="checkbox" className="border border-gray-400" />
                <span className="px-2">
                  I accept the{" "}
                  <a href="#" className="text-black font-semibold">
                    Terms of Use
                  </a>{" "}
                  &{" "}
                  <a href="#" className="text-black font-semibold">
                    Privacy Policy
                  </a>
                </span>
              </div>
              <div className="mt-5">
               
                <ReCAPTCHA
                  sitekey="6LfPwmUlAAAAAKBhJqB_QONnbVb5OSGl8zoS0nkM"
                  onChange={onChangeRecaptcha}
                />
                

                <div name="x" className="my-2 text-red-600">
                  {x}
                </div>

                <button
                  onClick={handleSubmit}
                  className="w-full bg-black py-3 text-center text-white rounded-lg"
                >
                  Register Now
                </button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </>
  );
}

export default Register;
