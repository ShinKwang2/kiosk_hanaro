import { useNavigate } from 'react-router-dom';
import payImg from '../static/img/payImg.png';
import { useEffect } from 'react';

function Pay() {
  const totalCount = 4;
  const totalPrice = 27700;
  const navigate = useNavigate();

  useEffect(() => {
    const timer = setTimeout(() => {
      navigate('/complete');
    }, 3000);
    return () => clearTimeout(timer);
  }, [navigate]);

  return (
    <div className='m-10 bg-green-900 p-20 w-50 flex flex-col items-center'>
      <h1 className='text-white font-bold mb-10'>주문을 확인하세요.</h1>
      <div className='bg-white w-96 font-bold text-center rounded-lg py-1'>
        총 수량: {totalCount}개
        <span className='text-red-500'> 총 가격: {totalPrice}원</span>
      </div>
      <div className='bg-white rounded-lg mt-5 w-96 flex-wrap text-center py-7'>
        <span className='font-bold'>카드를 화살표 방향</span>으로 투입구에
        <br />
        넣어주세요. 결제 오류 시,
        <span className='font-bold'> 카드를 긁어주세요.</span>
        <img src={payImg} alt='payImg' />
      </div>
    </div>
  );
}

export default Pay;
