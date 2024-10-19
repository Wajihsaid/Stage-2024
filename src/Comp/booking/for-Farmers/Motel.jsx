import './motel.css'

function Motel({ url, localisation, availability, price, onEdit, onDelete }){


    return(
        <>
            <div className="motel">
                <img src={url} alt={`${localisation} image`} />
                <h2 className="localisation">Location:{localisation}</h2>
                <h3 className="availability">availability:{availability} </h3>
                <h5 className="price">Price:{price} Dt</h5>
                <button className="button" onClick={onEdit}>on Edit</button>
                <button className="button" onClick={onDelete}>Delete</button>
            </div>
        </>
    )
}
export default Motel
