import { useNavigate } from 'react-router-dom';
import payGif from '../static/gif/pay.gif';
import { useEffect } from 'react';
import { useCartManager } from '../contexts/cart-context';
import { ApiResponse, OrderResponse, Product } from '../types/types';

const makeRequest = (cart: Product[]) => {
  return cart.map((p) => ({ productId: p.id, quantity: p.quantity }));
};

function Pay() {
  const { totalPrice, totalQuantity } = useCartManager();
  const { cart } = useCartManager();
  const navigate = useNavigate();

  // useEffect(() => {
  //   const timer = setTimeout(() => {
  //     navigate('/complete');
  //   }, 4000);
  //   return () => clearTimeout(timer);
  // }, []);

  useEffect(() => {
    const controller = new AbortController();
    const { signal } = controller;
    (async function () {
      const res = await fetch(`http://localhost:8080/api/v1/orders`, {
        signal,
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
          orderProducts: makeRequest(cart),
        }),
      });
      const json = (await res.json()) as ApiResponse<OrderResponse>;
      if (json.code === 200) {
        const timer = setTimeout(() => {
          navigate(`/complete?id=${json.data.id}&point=${json.data.point}`);
        }, 3000);
      } else {
        alert('결제 실패');
        history.back();
      }
    })();

    return () => controller.abort();
  }, []);

  return (
    <div className='m-10 bg-green-900 p-20 w-50 flex flex-col items-center'>
      <h1 className='text-white font-bold mb-10'>주문을 확인하세요.</h1>
      <div className='bg-white w-96 font-bold text-center rounded-lg py-1'>
        총 수량: {totalQuantity}개
        <span className='text-red-500'> 총 가격: {totalPrice}원</span>
      </div>
      <div className='bg-white rounded-lg mt-5 w-96 flex-wrap text-center py-7'>
        <span className='font-bold'>카드를 화살표 방향</span>으로 투입구에
        <br />
        넣어주세요. 결제 오류 시,
        <span className='font-bold'> 카드를 긁어주세요.</span>
        <img className='w-50' src={payGif} alt='payGif' />
      </div>
    </div>
  );
}

export default Pay;
