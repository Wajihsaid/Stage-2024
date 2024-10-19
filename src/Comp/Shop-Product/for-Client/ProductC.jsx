

function ProductClient({ productId, url, productName, quantity, priceUni,addToBag }) {


    return (
        <>
            <div className="produit">
                <img src={url} alt={`${productName} image`} />
                <h2 className="productName">Product Name: {productName}</h2>
                <h3 className="Quantity">Quantity available: {quantity} </h3>
                <h5 className="priceU">Price by Unite: {priceUni} <b>Dt</b></h5>
                <button className="button" onClick={() => addToBag(productId)}>Add to the Bag</button>
            </div>
        </>
    )
}

export default ProductClient;
