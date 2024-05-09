import { useNavigate } from 'react-router-dom';
import { useCartManager } from '../../contexts/cart-context';

const concatName = (koreanName: string, optionName: string) => {
  return koreanName + ' ' + optionName;
};

const isEmptyArray = (arr: any[]): boolean => arr.length === 0;

const CartList = () => {
  const {
    cart,
    addOneQuantity,
    deductOneQuantity,
    removeProduct,
    totalPrice,
    resetCart,
  } = useCartManager();

  const navigate = useNavigate();

  return (
    <>
      <ul className='bg-teal-400 w-auto h-fitm-3 min-h-20'>
        <span className='text-xl font-semibold'>장바구니</span>
        {cart.map(({ id, koreanName, optionName, quantity, price }) => {
          return (
            <p
              key={id + optionName}
              className='flex place-items-center gap-3 mx-3'
            >
              {`${concatName(koreanName, optionName)} X ${quantity} = ${price * quantity}`}
              <span className='ml-5 inline-flex'>
                <button
                  onClick={() => deductOneQuantity(id, optionName)}
                  className='shadow-lg py-2 px-4 h-8 m-auto rounded-lg font-bold bg-white'
                >
                  -
                </button>
                <p className='py-2 px-4'>수량 : {quantity}</p>
                <button
                  onClick={() => addOneQuantity(id, optionName)}
                  className='shadow-lg py-2 px-4 h-8 rounded-lg font-bold bg-white m-auto'
                >
                  +
                </button>
              </span>
              <button
                onClick={() => removeProduct(id, optionName)}
                className='bg-red-400 shadow-lg py-1 px-4 h-8 rounded-lg font-bold'
              >
                X
              </button>
            </p>
          );
        })}
      </ul>
      <div className='bg-yellow-200 flex justify-center'>
        <button
          className='bg-blue-400 rounded-xl p-4 m-3 min-w-20'
          onClick={() => {
            navigate('/recmenu');
          }}
        >
          주문하기
        </button>
        <button
          className='bg-red-400 rounded-xl p-4 m-3 min-w-20'
          onClick={resetCart}
        >
          비우기
        </button>

        <span className='content-evenly'>
          {isEmptyArray(cart) ? '' : `합계: ${totalPrice}`}
        </span>
      </div>
    </>
  );
};
export default CartList;
