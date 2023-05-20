import axios from "axios";
import Cookies from "js-cookie";

    const logout = async () => {
        const jwt = Cookies.get('myCookieName'); 
        console.log(jwt)
        const config = {
            headers: {
              'Authorization': `Bearer ${jwt}`,
              'Content-Type': 'text/plain'
            }
          };
        
        axios.get('http://localhost:8080/api/v1/auth/logout', config)
          .then(response => {
            console.log(response.data);
            Cookies.remove('myCookieName')
            Cookies.remove('UserMail')
            Cookies.remove('UserPic')
            location.href="/"
          })
          .catch(error => {
            console.error(error);
          });
      };


export default function Logout(){
    return(
        <>
                <div className="text-center mb-8">
                <button
                  onClick={logout}
                  className="w-auto px-2 bg-black py-3 text-center text-white rounded-lg"
                >
                  Logout
                </button>
                </div>
        </>
    )
}