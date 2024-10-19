import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './Shop.css'
import Product from './product.jsx'

function Shop({ selectedCategory }){

    const [formData, setFormData] = useState({
        imgUrl:'',
        productName:'',
        quantity:'',
        priceUni:''
    });

    const [products, setProducts] = useState([]);
    const [editingProductId, setEditingProductId] = useState(null);

    useEffect(() => {
        fetchProducts();
    }, []);

    const fetchProducts = async () => {
        try {
            const response = await axios.get(`http://localhost:8080/product`);
            setProducts(response.data);
        } catch (error) {
            console.error("There was an error fetching the products!", error);
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
        if (!selectedCategory) {
            alert("Please select a category first.");
            return;
        }
        try {
            if (editingProductId) {
                await axios.put(`http://localhost:8080/product/${editingProductId}`, formData);
                setEditingProductId(null);
            } else {
                await axios.post(`http://localhost:8080/product/wajih/${selectedCategory}`, formData,);
                
            }
            setFormData({ imgUrl: '', productName: '', quantity: '', priceUni: '' });
            fetchProducts(selectedCategory);
        } catch (error) {
            console.error("There was an error creating/updating the product!", error);
        }
    };

    const handleEdit = (product) => {
        setEditingProductId(product.productId);
        setFormData({
            imgUrl: product.imgUrl,
            productName: product.productName,
            quantity: product.quantity,
            priceUni: product.priceUni
        });
    };

    const handleDelete = async (productId) => {
        try {
            await axios.delete(`http://localhost:8080/product/${productId}`);
            fetchProducts();
        } catch (error) {
            console.error("There was an error deleting the product!", error);
        }
    };

    return (
        <>
            <div className='body'>
                <div className="input-shop">
                    <form onSubmit={handleSubmit}>
                        <span className='input-box'>
                            <input 
                                id='image-id' 
                                name="imgUrl" 
                                value={formData.imgUrl} 
                                onChange={handleChange} 
                                className='input-for' 
                                type="url" 
                                required 
                                disabled={editingProductId !== null} 
                            />
                            <div className='label'>Product Url Image</div>
                        </span>
                        <span className='input-box'>
                            <input 
                                id='pro-name' 
                                name="productName" 
                                value={formData.productName} 
                                onChange={handleChange} 
                                className='input-for' 
                                type="text" 
                                required 
                            />
                            <div className='label'>Product Name</div>
                        </span>
                        <span className='input-box'>
                            <input 
                                id='quantity' 
                                name="quantity" 
                                value={formData.quantity} 
                                onChange={handleChange} 
                                className='input-for' 
                                type="text" 
                                required 
                            />
                            <div className='label'>Quantity</div>
                        </span>
                        <span className='input-box'>
                            <input 
                                id='priceUni' 
                                name="priceUni" 
                                value={formData.priceUni} 
                                onChange={handleChange} 
                                className='input-for' 
                                type="number" 
                                required 
                            />
                            <div className='label'>Price Unit</div>
                        </span>
                        <button className="button">{editingProductId ? "Update Product" : "Add Product"}</button>
                    </form>
                </div>
                <div className="product-shop">
                    {products.map(product => (
                        <Product 
                            key={product.productId}
                            url={product.imgUrl} 
                            productName={product.productName} 
                            quantity={product.quantity} 
                            priceUni={product.priceUni} 
                            onEdit={() => handleEdit(product)}
                            onDelete={() => handleDelete(product.productId)}
                        />
                    ))}
                </div>
            </div>
        </>
    );
}

export default Shop;
