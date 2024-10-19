import React, { useState, createContext } from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import NavBar from "./Comp/Navbar/NavBar.jsx";
import Footer from "./Comp/Footer/Footer.jsx";
import Home from './Comp/Home/Home.jsx';
import SignIn from "./Comp/Sign-In/Sign-in.jsx";
import Category from "./Comp/Shop-Product/for_Farmers/Category.jsx";
import Shop from "./Comp/Shop-Product/for_Farmers/Shop.jsx"
import ShopClient from "./Comp/Shop-Product/for_CLient/ShopC.jsx";
import Basket from "./Comp/Basket/Basket.jsx";
import Booking from "./Comp/booking/for-Farmers/Booking.jsx";
import BookingClinet from "./Comp/booking/for-CLients/BookingC.jsx";
import LogIn from "./Comp/login/LogIn.jsx"
import { UserContextProvider } from "./Comp/context/UserContext.jsx";



function App() {
    const [selectedCategory, setSelectedCategory] = useState(null);

    return (
        <UserContextProvider>
            <Router>
                <div className="App">
                    <NavBar />
                    <div className="content">
                        <Routes>
                            <Route path="/Home" element={<Home />} />
                            <Route path="/Create-Account" element={<SignIn />} />
                            {/* <Route path="/Shop" element={<Category setSelectedCategory={setSelectedCategory}/>}/> */}
                            {/* <Route path="/Shop/Product" element={<Shop selectedCategory={selectedCategory}/>}/> */}
                            <Route path="/Shop" element={<ShopClient />} />
                            <Route path="/Basket" element={<Basket />} />
                            {/* <Route path="/Booking" element={<Booking/>}/> */}
                            <Route path="/Booking" element={<BookingClinet/>}/>
                            <Route path="/LogIn" element={<LogIn/>}/>
                        </Routes>
                    </div>
                    <Footer />
                </div>
            </Router>
        </UserContextProvider>
    );
}

export default App;
