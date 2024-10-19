function MotelClient({ url, localisation, price, availability }) {


    return (
        <>
            <div className="produit">
                <img src={url} alt={`${localisation} image`} />
                <h2 className="localisation">Location: {localisation}</h2>
                <h3 className="price">price: {price} </h3>
                <h5 className="availability">availability: {availability}</h5>
            </div>
        </>
    )
}

export default MotelClient;
