import { Link } from "react-router-dom"
import './login.css'

function LogIn(){

    return(
        <>
            <div className="login">
                <form >
                <h1>LOGIN</h1>
                <div className="input-box">
                    <input className='input-for' type="text" name="UserName" id="user" required />
                    <div className='label-in'>Enter Your User Name</div>
                </div>
                <div className="input-box">
                    <input name='password' className='input-for' type="password" required/>
                    <div className='label-in'>Enter your Password</div>
                </div>
                
                <button className='Submit'>LOGIN</button>
                <p ><Link className="Log" to="#">forget your Password?</Link></p>
                </form>
            </div>
        </>
    )
}
export default LogIn
