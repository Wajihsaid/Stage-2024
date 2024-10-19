import { Link } from 'react-router-dom'
import './Home.css'

function Home(){


    return(
    <>
        <div className="All">
            <div className="mainwest">
                <div className='container'>
                    <p className="quotes">“From a problem we face a solution is always born”</p>
                    <h1>Farm4U</h1>
                    <h5>Connect with Farmers and buy fresh products directly</h5>
                    <button className='button'><Link to="/Create-Account">Crate an account</Link></button> <br />
                    <div >
                        <Link className='existant-acount' to="/LogIn">Already have an account ?</Link>
                    </div>
                </div>
            </div>

        </div>
    </>    
    )
}
export default Home
