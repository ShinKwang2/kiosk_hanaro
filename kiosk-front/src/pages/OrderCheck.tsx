import { useState } from 'react';
import OrderList from '../components/OrderList';
import Total from '../components/OrderTotal';
import { useNavigate } from 'react-router-dom';
import { useCartManager } from '../contexts/cart-context';

function OrderCheck() {
  const [orderItems, setOrderItems] = useState([
    { productName: '치즈 버거', price: 6000, quantity: 1 },
    { productName: '감자 튀김', price: 2000, quantity: 1 },
    { productName: '맥 플러리', price: 1000, quantity: 1 },
  ]);

  const { cart, removeProduct, addOneQuantity, deductOneQuantity, totalPrice } =
    useCartManager();

  const navigate = useNavigate();

  function handleQuantityChange(idx: number, newQuantity: number) {
    const updatedOrderItems = [...orderItems];
    updatedOrderItems[idx].quantity = newQuantity;
    setOrderItems(updatedOrderItems);
  }

  const deleteProduct = (productName: string) => {
    const newProducts = orderItems.filter(
      (item) => item.productName !== productName
    );
    setOrderItems(newProducts);
  };

  const totalQuantity = cart.reduce(
    (total, product) => total + product.quantity,
    0
  );

  // const totalPrice = orderItems.reduce(
  //   (total, item) => total + item.price * item.quantity,
  //   0
  // );

  function handlePay() {
    navigate('/pay');
  }
  return (
    <div className='m-10 bg-green-900 p-20 w-50'>
      <h1 className='font-bold text-lg text-center text-white mb-20'>
        주문을 확인하세요
      </h1>
      <div className='bg-white rounded-lg p-5 '>
        {cart.map((p) => (
          <OrderList key={p.id + p.optionName} {...p} />
        ))}
        <Total
          orderLists={cart}
          totalQuantity={totalQuantity}
          totalPrice={totalPrice}
        />
        <div className='flex justify-center mt-5'>
          <button className='py-2 px-8 rounded-lg bg-red-600 text-white mr-1'>
            추가주문
          </button>
          <button
            className='py-2 px-8 rounded-lg bg-green-800 text-white ml-1'
            onClick={handlePay}
          >
            결제하기
          </button>
        </div>
      </div>
    </div>
  );
}

export default OrderCheck;
