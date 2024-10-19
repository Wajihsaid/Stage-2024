
import facebook from '../../assets/facebook.png'
import XLog from '../../assets/twitter.png'
import instagram from '../../assets/instagram.png'
import './Footer.css'

function Footer(){
    return(
        <>
        <div className='bottom'>
            <div className='newsletter'>
                <h3>Subscibe To our Newsletter</h3>
                <p> The latest updates,articles and resources sent to your inbox weekly </p>
                <div className='enterarea'>
                    <input type="email" className='inpu-newsletter' required/>
                    <div className='label-input'>Enter Your Email</div>
                </div>
                <br />
                
                <button className='button'>→</button>
            </div>
            <hr />
            <footer>
                <span>
                    <a href="https://www.facebook.com/wajih.said01"><img src={facebook} alt="faceboook" /></a>
                </span>
                <span><a href="https://x.com/said_wajih"><img src={XLog} alt="X" /></a></span>
                <span><a href="https://www.instagram.com/said_wajih/"><img src={instagram} alt="insta" /></a></span> <hr />
                <p class="copyright">&copy;2024, Farm4U powered by WS</p>
            </footer>
        </div>
            
        </>
    )
}

export default Footer
