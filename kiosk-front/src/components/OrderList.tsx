import { useCartManager } from '../contexts/cart-context';
import { Product } from '../types/types';

interface OrderListProps {
  product: Product;
}

function OrderList(props: Product) {
  const { id, koreanName, englishName, price, quantity, optionName } = props;

  const { addOneQuantity, deductOneQuantity, removeProduct } = useCartManager();

  return (
    <div className='flex justify-between mt-2'>
      <div className='py-2 px-4'>
        {`${koreanName} ${optionName}`} X {price}
      </div>
      <span className='ml-5 flex'>
        <button
          onClick={() => deductOneQuantity(id, optionName)}
          className='shadow-lg py-2 px-4 rounded-lg font-bold'
        >
          -
        </button>
        <p className='py-2 px-4'>수량 : {quantity}</p>
        <button
          onClick={() => addOneQuantity(id, optionName)}
          className='shadow-lg py-2 px-4 rounded-lg font-bold'
        >
          +
        </button>
        <button
          className='shadow-xl py-2 px-4 ml-4 rounded-lg bg-red-600 text-white'
          onClick={() => removeProduct(id, optionName)}
        >
          삭제
        </button>
      </span>
    </div>
  );
}

export default OrderList;
