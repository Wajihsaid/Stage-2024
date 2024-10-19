import { useState, createContext } from "react";
export const UserContext = createContext()



export const UserContextProvider = ({children}) =>{


    const [bagItems, setBagItems] = useState([]);

    const addToBag = (product) => {
        setBagItems(b=>[...b,product])
    };


    return <UserContext.Provider
    value={{
        bagItems,setBagItems,addToBag
    }}
    >{children}</UserContext.Provider>
     
}

