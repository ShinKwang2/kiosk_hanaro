interface OrderListProps {
  productName: string;
  price: number;
  quantity: number;
  onQuantityChange: (newQuantity: number) => void;
  onClickDelete: (productName: string) => void;
}

function OrderList({
  productName,
  price,
  quantity,
  onQuantityChange,
  onClickDelete,
}: OrderListProps) {
  function increaceBtn() {
    onQuantityChange(quantity + 1);
  }

  function decreaceBtn() {
    if (quantity > 1) {
      onQuantityChange(quantity - 1);
    }
  }

  return (
    <div className='flex justify-between mt-2'>
      <div className='py-2 px-4'>
        {productName} - {price}
      </div>
      <span className='ml-5 flex'>
        <button
          onClick={decreaceBtn}
          className='shadow-lg py-2 px-4 rounded-lg font-bold'
        >
          -
        </button>
        <p className='py-2 px-4'>수량 : {quantity}</p>
        <button
          onClick={increaceBtn}
          className='shadow-lg py-2 px-4 rounded-lg font-bold'
        >
          +
        </button>
        <button
          className='shadow-xl py-2 px-4 ml-4 rounded-lg bg-red-600 text-white'
          onClick={() => onClickDelete(productName)}
        >
          삭제
        </button>
      </span>
    </div>
  );
}

export default OrderList;
