import React, { useState, useEffect, useContext } from "react";
import axios from "axios";
import ProductClient from "./ProductC.jsx";
import { UserContext } from "../../context/UserContext.jsx";
import './ShopC.css';

function ShopClient() {
    const [products, setProducts] = useState([]);
    const [selectedCategory, setSelectedCategory] = useState("");
    const [categories, setCategories] = useState([]);
    const { addToBag, } = useContext(UserContext);

    useEffect(() => {
        fetchCategories();
    }, []);

    useEffect(() => {
        fetchProducts();
    }, [selectedCategory]);

    const fetchCategories = async () => {
        try {
            const response = await axios.get('http://localhost:8080/category');
            if (Array.isArray(response.data)) {
                setCategories(response.data);
            } else {
                console.error('API response is not an array:', response.data);
            }
        } catch (error) {
            console.error('Error fetching categories', error);
        }
    };

    const fetchProducts = async () => {
        try {
            let url = 'http://localhost:8080/product';
            if (selectedCategory) {
                url += `/${encodeURIComponent(selectedCategory)}`;
            }
            const response = await axios.get(url);
            setProducts(response.data);
        } catch (error) {
            console.error("Error fetching products:", error);
        }
    };

    const handleCategorySelect = (event) => {
        setSelectedCategory(event.target.value);
    };


    const handleAddToBag = (product) => {
        
        addToBag(product);
        
    };

    return (
        <>
        <div className="all">
            <select
                name="selectedCategory"
                className="select-options"
                value={selectedCategory}
                onChange={handleCategorySelect}
            >
                <option value="">Select a category</option>
                {categories.map(category => (
                    <option key={category.categoryId} value={category.categoryName}>
                        {category.categoryName}
                    </option>
                ))}
            </select>
            <div className="product-shop">
                {products.map((product) => (
                    <ProductClient
                        key={product.productId}
                        productId={product.productId}
                        url={product.url}
                        productName={product.productName}
                        quantity={product.quantity}
                        priceUni={product.priceUni}
                        addToBag={() => handleAddToBag(product)}
                    />
                ))}
            </div>
        </div>
        </>
    );
}

export default ShopClient;
