import React,{useState,useEffect,useContext} from "react";
import './NavBar.css'
import {RiMenu3Line,RiCloseLine} from "react-icons/ri"
import logo from '../../assets/Farme4U.png'
import { Link } from "react-router-dom";
import { UserContext } from "../context/UserContext";


function NavBar(){
    const [toggleMenu,setToggleMenu]=useState(false)
    const { bagItems } = useContext(UserContext);
    const [count,setCount]=useState(0)
    useEffect(()=>{
        setCount(c=>(c=bagItems.length))
    })

    return(
        <>
            <nav className="ALL">
                <div className="Logo">
                    <Link to="/">
                        <img src={logo} alt="logo" />
                    </Link>
                </div>
                <div className="Links">
                    <ul>
                        <li><Link to="/Home">Home</Link></li>
                        <li><Link to="#">About Us</Link></li>
                        <li><Link to="/Shop">Shop</Link></li>
                        <li><Link to="/Booking">Booking </Link></li>
                    </ul>
                </div>
                <div className="Menu">
                    {toggleMenu ?(
                        <RiCloseLine color="#000" size={27} onClick={()=>setToggleMenu(false)}/>
                    ):(
                        <RiMenu3Line color="#000" size={27} onClick={()=>setToggleMenu(true)}/>
                    )}
                    {toggleMenu && (
                        <div className="Menu-container"> 
                            <div className="menu-links">
                                <ul>
                                    <li><Link to="/Basket">Basket{count}</Link></li> <br />
                                    <li><Link to="/LogIn">LogIn</Link></li>
                                </ul>
                            </div>
                        </div>
                    )}
                </div>
            </nav>
        </>
    )

}

export default NavBar
