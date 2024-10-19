import React, { useState, useEffect } from 'react';
import axios from 'axios';
import Motel from './Motel.jsx'

function Booking(){

    const [formData, setFormData] = useState({
        imgUrl:'',
        localisation:'',
        price:'',
        availability:false
        
    });

    const [motels, setMotels] = useState([]);
    const [editingMotelId, setEditingMotelId] = useState(null);

    useEffect(() => {
        fetchMotels();
    }, []);

    const fetchMotels = async () => {
        try {
            const response = await axios.get(`http://localhost:8080/motel`);
            setMotels(response.data);
        }catch (error) {
            console.error("There was an error fetching the Motels!", error);
        }
    };

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({
            ...formData,
            [name]: value
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            if (editingMotelId) {
                await axios.put(`http://localhost:8080/motel/${editingMotelId}`, formData);
                setEditingMotelId(null);
            } else {
                await axios.post(`http://localhost:8080/motel/wajih`, formData);
            }
            setFormData({ imgUrl: '', localisation: '', price: '', availability: '' });
            fetchMotels();
        } catch (error) {
            console.error("There was an error creating/updating the motel!", error);
        }
    }

    const handleEdit = (motel) => {
        setEditingMotelId(motel.motelId);
        setFormData({
            // imgUrl: motel.imgUrl,
            localisation: motel.localisation,
            price: motel.price,
            availability: motel.availability
        });
    };

    const handleDelete = async (motelId) => {
        try {
            await axios.delete(`http://localhost:8080/motel/${motelId}`);
            fetchMotels();
        } catch (error) {
            console.error("There was an error deleting the motel!", error);
        }
    };

    return(
        <>
            <div className='body'>
                <div className="input-shop">
                    <form onSubmit={handleSubmit}>
                        <span className='input-box'>
                            
                            <input disabled={editingMotelId !== null} id='image-id' name="imgUrl" value={formData.imgUrl} onChange={handleChange} className='input-for'  type="url" required />
                            <div className='label'>motel Url Image</div>
                        </span>
                        <span className='input-box'>
                            <input id='localisation' name="localisation" value={formData.localisation} onChange={handleChange} className='input-for'  type="text" required />
                            <div className='label'>Location</div>
                        </span>
                        <span className='input-box'>
                            <input id='price' name="price" value={formData.price} onChange={handleChange} className='input-for'  type="number" required />
                            <div className='label'>Price</div>
                        </span>
                        <span className='input-box'>
                            <input id='availability' name="availability" value={formData.availability} onChange={handleChange}  className='input-for'  type="text" required />
                            <div className='label'>Availability</div>
                        </span>
                        <button className="button">{editingMotelId ? "Update Motel" : "Add Motel"}</button>
                    </form>
                </div>
                <div className="motel-booking">
                    {motels.map(motel => (
                        <Motel 
                            key={motel.motelId}
                            url={motel.imgUrl} 
                            localisation={motel.localisation}  
                            price={motel.price} 
                            availability={motel.availability.toString()}
                            onEdit={() => handleEdit(motel)}
                            onDelete={() => handleDelete(motel.motelId)}
                        />
                    ))}
                </div>
                
            </div>
        </>
    )
    
}
export default Booking
