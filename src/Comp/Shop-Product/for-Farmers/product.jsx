import './product.css'
function Product({ url, productName, quantity, priceUni, onEdit, onDelete }){


    return(
        <>
            <div className="produit">
                <img src={url} alt={`${productName} image`} />
                <h2 className="productName">Product Name:{productName}</h2>
                <h3 className="Quantity">Quantity:{quantity} </h3>
                <h5 className="priceU">Price:{priceUni} Dt/kg</h5>
                <button className="button" onClick={onEdit}>on Edit</button>
                <button className="button" onClick={onDelete}>Delete</button>
            </div>
        </>
    )
}
export default Product
