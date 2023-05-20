function HeaderIcon({Icon,active}){
    return(
       <div className="flex items-center cursor-pointer 
       md:px-10 sm:h-14 md:hover:bg-gray-100 rounded-xl 
       md:active:border-b-2 active:border-black group"> 
            <Icon className={`h-8 text-center sm-h-7 mx-auto text-zinc-500 group-hover:text-black
            ${active && "text-black"}`}/>
       </div>
    )
}
export default HeaderIcon   