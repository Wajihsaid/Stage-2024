import React,{useState} from 'react';
import './SignIn.css'
import { Link } from 'react-router-dom';
 

function SginIN(){

    const [formData, setFormData] = useState({
        userName: '',
        address: '',
        email: '',
        password: '',
        repeatPassword: '',
        phone: ''
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({
            ...formData,
            [name]: value
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        if (formData.password !== formData.repeatPassword) {
            alert("Passwords do not match");
            return;
        }

        try {
            const response = await fetch('http://localhost:8080/user', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(formData),
            });

            if (response.ok) {
                const data = await response.json();
                alert("Account created successfully!");
            } else {
                alert("Failed to create account");
            }
        } catch (error) {
            console.error("Error:", error);
            alert("An error occurred");
        }
    };

    return(
        <>
    
            <div className="form-container">
                <form onSubmit={handleSubmit}>
                    <h1>Create Account</h1>
                    <div className='ALL'>
                        <div className='unput-box'>
                            <input name="userName" value={formData.userName} onChange={handleChange} className='input-for'  type="text" required />
                            <div className='label-in'>First Name</div>
                        </div>
                        <div className='unput-box'>
                            <input name='address' value={formData.address} onChange={handleChange} className='input-for' type="text" required/>
                            <div className='label-in'>Adress</div>
                        </div>
                        <div className='unput-box'>
                            <input name='email' value={FormData.email} onChange={handleChange} className='input-for' type="email" required />
                            <div className='label-in'>Enter your Email</div>
                        </div>
                        <div className="unput-box">
                            <input name='password' value={FormData.password} onChange={handleChange} className='input-for' type="password" required/>
                            <div className='label-in'>Enter your Password</div>
                        </div>
                        <div className="unput-box">
                            <input name='repeatPassword' value={FormData.repeatPassword} onChange={handleChange} className='input-for' type="password" required/>
                            <div className='label-in'>Repet your Password</div>
                        </div>
                        <div className="unput-box">
                            <input name='phone' value={FormData.phone} onChange={handleChange} className='input-for' type="tel" required/>
                            <div className='label-in'>Enter your Phone</div>
                        </div><hr className='ligne' />
                        <p>Already have Acount? <Link className='Log' to="/LogIn">LogIn</Link></p>
                        <button className='Submit'>Submit</button>
                        <br />
                        <br />
                    </div>
                </form>
            </div>

        </>
    )

}
export default SginIN
