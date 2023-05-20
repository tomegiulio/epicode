import { useEffect, useState,useRef} from "react";
import Cookies from "js-cookie";
import axios from "axios";
import { MapPinIcon, TagIcon } from "@heroicons/react/24/outline";
import Modal from "react-modal";
import Link from "next/link";

export default function SearchBar() {
  const token = Cookies.get("myCookieName");
  const [query, setQuery] = useState("");
  const [results, setResults] = useState({ crags: [], routes: [] });
  const inputRef = useRef(null);
  const [modalIsOpen, setModalIsOpen] = useState(false);

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

  function openCrag(cragId) {
    console.log("test " + cragId);
  }

  function closeModal() {
    setModalIsOpen(false);
    
  }

  useEffect(() => {
    if (query.length > 0) {
      handleSearch();
      setModalIsOpen(true);
      setTimeout(() => {
        if (inputRef.current) {
          inputRef.current.focus();
        }
      }, 100);
    } else {
      setModalIsOpen(false);
    }
  }, [query]);

  return (
    <>
      <div>
        <div className="  flex  p-2 bg-gray-100">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            fill="none"
            viewBox="0 0 24 24"
            strokeWidth={1.5}
            stroke="currentColor"
            className="w-6 h-6 text-zinc-500"
          >
            <path
              strokeLinecap="round"
              strokeLinejoin="round"
              d="M21 21l-5.197-5.197m0 0A7.5 7.5 0 105.196 5.196a7.5 7.5 0 0010.607 10.607z"
            />
          </svg>

          <input
          ref={inputRef}
  className=" ml-2 items-center bg-transparent outline-none flex-shrink"
  type="text"
  placeholder="search here"
  value={query}
  onChange={(e) => setQuery(e.target.value)}
  onFocus={() => console.log("Input field has focus")}
  
/>
        </div>
        <Modal
          isOpen={modalIsOpen}
          
          onRequestClose={closeModal}
          className="md:w-5/12 w-12/12 mx-auto mt-20 bg-gray-50  rounded-xl outline-0"
        >
          <div className="mt-3 ">
            {results.crags.map((crag) => (
              <Link href={`/comp/CragComponent?cragId=${crag.id}`}>
                <button className="p-2 ml-3 flex w-full my-2" key={crag.id}>
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
              </Link>
            ))}
          </div>
          {results.routes.map((route) => (
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
                  </div>
                </div>
              </div>
            </button>
            </Link>
          ))}
        </Modal>
      </div>
    </>
  );
}
