import React, { useContext, useState, useEffect } from "react";
import { UserContext } from "../context/UserContext.jsx";
import axios from 'axios';
import './basket.css'

function Basket() {
    const { bagItems, setBagItems } = useContext(UserContext);
    const [quantities, setQuantities] = useState(Array(bagItems.length).fill(1));
    const [deliveries, setDeliveries] = useState([]);

    useEffect(() => {
        fetchDeliveries();
    }, []);

    const fetchDeliveries = async () => {
        try {
            const response = await axios.get('http://localhost:8080/delivery/false', {
                headers: {
                    'Cache-Control': 'no-cache',
                    'Pragma': 'no-cache',
                    'Expires': '0'
                }
            });
            if (Array.isArray(response.data)) {
                setDeliveries(response.data);
            } else {
                console.error('API response is not an array:', response.data);
            }
        } catch (error) {
            console.error('Error fetching Deliveries', error);
        }
    };

    const deleteProduct = (index) => {
        const updatedProducts = bagItems.filter((_, i) => i !== index);
        setBagItems(updatedProducts);
    };

    const handleQuantityChange = (index, value) => {
        const updatedQuantities = [...quantities];
        updatedQuantities[index] = value;
        setQuantities(updatedQuantities);
    };

    const handleOrder = (index) => {
        const orderedItem = bagItems[index];
        const quantitDem = quantities[index];
        const selectedDelivery = orderedItem.delivery;

        // Prepare order data
        const orderData = {
            quantitDem,
            prixTotal: orderedItem.priceUni * quantitDem,
            user: orderedItem.user,
            products: [orderedItem],
            deliveries: [selectedDelivery]
        };

        // POST request to place order
        axios.post('http://localhost:8080/order', orderData, {
            headers: {
                'Content-Type': 'application/json',
            }
        })
        .then(response => {
            if (response.status !== 200) {
                throw new Error('Failed to place order');
            }
            // Update delivery status after successful order
            return axios.put(`http://localhost:8080/delivery/${selectedDelivery.deliveryId}/true`);
        })
        .then(() => {
            // Remove ordered item from bagItems
            deleteProduct(index);
            console.log('Order placed successfully!');
        })
        .catch(error => {
            console.error('Error placing order:', error);
        });
    };

    return (
        <div className="bag-shop">
            <h2>Shopping Bag</h2>
            <ul className="produit">
                {bagItems.map((item, index) => (
                    <li key={index}>
                        <img src={item.url} alt={`${item.productName} image`} />
                        <div>
                            <p><strong>Product Name:</strong> {item.productName}</p>
                            <p><strong>Price:</strong> {item.priceUni} Dt</p>
                            <div className="input-box">
                                <input
                                    className='input-for'
                                    type="number"
                                    id="quantityInput"
                                    name="quantityInput"
                                    min="1"
                                    value={quantities[index]}
                                    onChange={(e) => handleQuantityChange(index, parseInt(e.target.value))}
                                    required
                                />
                                <div className='label-in'>Quantity Ordered</div>
                            </div>
                            <select
                                name="selectedDelivery"
                                className="select-options"
                                onChange={(e) => {
                                    const selectedDelivery = deliveries.find(delivery => delivery.deliveryName === e.target.value);
                                    const updatedBagItems = [...bagItems];
                                    updatedBagItems[index] = { ...bagItems[index], delivery: selectedDelivery };
                                    setBagItems(updatedBagItems);
                                }}
                            >
                                <option value="">Select a Delivery</option>
                                {deliveries.map(delivery => (
                                    <option key={delivery.deliveryId} value={delivery.deliveryName}>
                                        {delivery.deliveryName} with {delivery.cotisation} an{delivery.phone}
                                    </option>
                                ))}
                            </select> <br />
                            <button className="button" onClick={() => deleteProduct(index)}>✖️</button>
                            <button className="button" onClick={() => handleOrder(index)}>Order</button>
                        </div>
                    </li>
                ))}
            </ul>
        </div>
    );
}

export default Basket;
