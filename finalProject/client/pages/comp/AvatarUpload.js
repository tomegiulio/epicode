import React, { useState } from "react";
import Dropzone from "react-dropzone";
import AvatarEditor from "react-avatar-editor";
import aws from "aws-sdk";
import { v4 as uuidv4 } from "uuid";
import Cookies from "js-cookie";
import axios from "axios";
import PlusIcon from "@heroicons/react/24/outline";
//default pic
//https://cdn2.iconfinder.com/data/icons/instagram-outline/19/11-512.png
const AvatarUpload = () => {
  //bucket
  const S3_BUCKET_NAME = "ulioicebucketchallange";
  const REGION = "eu-central-1";
  const ACCESS_KEY_ID = "AKIAT4DFWYEXKCOIKSH4";
  const SECRET_ACCESS_KEY = "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";
  const s3 = new aws.S3({
    region: REGION,
    accessKeyId: ACCESS_KEY_ID,
    secretAccessKey: SECRET_ACCESS_KEY,
  });
  ////
  const email = Cookies.get("UserMail");
  const token=Cookies.get("myCookieName")

  ////  
  const [image, setImage] = useState(null);
  const [editor, setEditor] = useState(null);
  const [zoom, setZoom] = useState(1);
  const [showInput, setShowInput] = useState(true);
  const [showModal, setShowModal] = useState(true);

  const [showElement, setShowElement] = useState(true);

  const handleImageUpload = (acceptedFiles) => {
    setShowElement(false);
    const file = acceptedFiles[0];
    const reader = new FileReader();
    reader.onload = () => {
      setImage(reader.result);
      setShowInput(false);
    };
    reader.readAsDataURL(file);
  };

  const handleZoomChange = (event) => {
    setZoom(parseFloat(event.target.value));
  };

  const handleSave = async () => {
    
    if (editor) {
      
      const canvas = editor.getImageScaledToCanvas();
      const dataUrl = canvas.toDataURL();
  
      const buf = Buffer.from(dataUrl.replace(/^data:image\/\w+;base64,/, ''), 'base64');
      const fileName = `${Date.now()}_my-image-filename.jpg`;
      const params = {
        Bucket: S3_BUCKET_NAME,
        Key: fileName,
        Body: buf,
        ContentType: 'image/jpeg',
        ACL: 'public-read',
      };
     
      try {
        const response = await s3.upload(params).promise();
        console.log('Image uploaded:', response.Location);
        const imageUrl = response.Location;
  
        const headers = {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`,
          params: {
            photo: imageUrl
          }
        };
  
       
  
        const updatedUser = await axios.put(
            `http://localhost:8080/api/v1/demo-controller/user/${email}`,
            null,
            {
              headers,
              params: {
                photo: encodeURIComponent(imageUrl.toString())
              }
            }
          );
        console.log(updatedUser);   
        
  
      } catch (error) {
        console.error('Error uploading image:', error);
      }finally {
        handleCloseModal(); 
        location.href="/"
      }
      
    }
  };
  

  const handleCloseModal = () => {
    setShowModal(false);
    setShowInput(true);
    setImage(null);
  };
  

  const handleImageRemove =async()=>{
   
      
  try{ 
    const headers = {
      "Content-Type": "application/json",
      Authorization: `Bearer ${token}`,
      params: {
        photo: "https://cdn2.iconfinder.com/data/icons/instagram-outline/19/11-512.png"}, }   

      const updatedUser = await axios.put(
        `http://localhost:8080/api/v1/demo-controller/user/${email}`,
        null,
        {
          headers,
          params: {
            photo: encodeURIComponent("https://cdn2.iconfinder.com/data/icons/instagram-outline/19/11-512.png")
          }
        }
      );
    console.log(updatedUser);   
      }catch (error) {
        console.error('Error uploading image:', error);
      }finally {
        handleCloseModal(); 
       
        location.href="/"
        
      }

   
  }
 

  return (
    <>
      {showModal && (
        <div  className="fixed top-0 left-0 w-full h-full flex justify-center items-center bg-gray-500 bg-opacity-50 z-50">
          <div className="bg-white min-w-max rounded-lg shadow-lg p-2" >
            <div className="flex p-2 ">
            <p className="mx-auto font-bold">Edit your profile picture</p>
            <p className="bg-gray-200 font-bold text-lg px-2 rounded-full ml-2" onClick={()=> handleCloseModal() }>x</p>
            </div>
           
            
            <Dropzone onDrop={handleImageUpload}>
              {({ getRootProps, getInputProps }) => (
                <div {...getRootProps()}>
                  <input {...getInputProps()} />
                  {showInput && (
                   
                    <p  className="bg-blue-300 p-2 text-blue-500 rounded-xl">  
                    
                      Load your picture
                    </p>
                    
                  )}
                  {image && (
                    <AvatarEditor
                      ref={(ref) => setEditor(ref)}
                      image={image}
                      width={400}
                      height={400}
                      border={50}
                      borderRadius={300}
                      scale={zoom}
                      rotate={0}
                      className="sm:w-100 sm:h-100"
                    />
                  )}
                </div>
                
              )}
              
            </Dropzone>
            {showElement && <p className="bg-red-500  w-full m-auto p-2 text-white rounded-xl mt-3" onClick={handleImageRemove}>  
                      Rimuove your profile picture
                    </p>}
            {image && (
              <div>
                <label>Zoom:</label>
                <input
                  type="range"
                  min="1"
                  max="10"
                  step="0.1"
                  value={zoom}
                  onChange={handleZoomChange}
                  className="text-black"
                />
                <div>
                  <button onClick={handleSave} className="bg-black rounded-xl text-white rounded-xl p-2 ">Save picture</button>
                </div>
              </div>
            )}
          </div>
        </div>
      )}
    </>
  );
};

export default AvatarUpload;
