import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import './Category.css';

const CategorySelect = ({ setSelectedCategory }) => {
    const [categories, setCategories] = useState([]);
    const [selectedCategory, setLocalSelectedCategory] = useState('');
    const [categoryName, setCategoryName] = useState('');
    const navigate = useNavigate();

    useEffect(() => {
        fetchCategories();
    }, []);

    const fetchCategories = async () => {
        try {
            const response = await axios.get('http://localhost:8080/category', {
                headers: {
                    'Cache-Control': 'no-cache',
                    'Pragma': 'no-cache',
                    'Expires': '0'
                }
            });
            if (Array.isArray(response.data)) {
                setCategories(response.data);
            } else {
                console.error('API response is not an array:', response.data);
            }
        } catch (error) {
            console.error('Error fetching categories', error);
        }
    };

    const handleSelectChange = (event) => {
        try{
            const value = event.target.value;
            setLocalSelectedCategory(value);
            setSelectedCategory(value);
            if (value === 'Fruits') {
                navigate('/Shop/Product?category=Fruits');
            } else if (value === 'Vegetables') {
                navigate('/Shop/Product?category=Vegetables');
            }else if (value==="Animal\'s product"){
                navigate('/Shop/Product?category=Animals')
            }
        }
        catch (error) {
            console.error('Error categories', error);
            alert("enter adequat category")
        }
    };

    const handleNewCategoryChange = (event) => {
        setCategoryName(event.target.value);
    };

    const handleAddCategory = async (event) => {
        event.preventDefault();
        try {
            await axios.post('http://localhost:8080/category', { categoryName });
            setCategoryName('');
            fetchCategories();
        } catch (error) {
            console.error('Error adding category', error);
        }
    };

    return (
        <>
        <div className='formc'>
            <select
                name="selectedCategory"
                className="select-options"
                value={selectedCategory}
                onChange={handleSelectChange}
            >
                <option value="">Select a category</option>
                {categories.map(category => (
                    <option key={category.categoryId} value={category.categoryName}>
                        {category.categoryName}
                    </option>
                ))}
            </select>
            <form onSubmit={handleAddCategory}>
                <div unput-box>
                    <input
                        type="text"
                        value={categoryName}
                        onChange={handleNewCategoryChange}
                        className='input-for'
                        required
                    />
                    <div className='label-in'>New category name</div> <br /> <br /> <br /> <br />
                </div>


                <button className='button' type="submit">Add Category</button>
            </form>
        </div>
        </>
    );
};

export default CategorySelect;
