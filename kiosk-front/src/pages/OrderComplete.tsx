import { useLocation, useNavigate, useParams } from 'react-router-dom';
import logo from '../static/gif/logo.gif';
import { useEffect, useState } from 'react';
import { useCartManager } from '../contexts/cart-context';

function OrderComplete() {
  const location = useLocation();
  const searchParams = new URLSearchParams(location.search);
  const id = searchParams.get('id');
  const point = searchParams.get('point');

  const { resetCart } = useCartManager();

  // const orderNumber = id;
  // const point = point;
  const navigate = useNavigate();
  const [countdown, setCountdown] = useState(5);

  useEffect(() => {
    resetCart();
    const timer = setInterval(() => {
      setCountdown((prevCountdown) => prevCountdown - 1);
    }, 1000);

    if (countdown === 0) {
      clearInterval(timer);
      navigate('/');
    }

    return () => clearInterval(timer);
  }, [countdown, navigate]);

  const handleClick = () => {
    navigate('/');
  };

  function handleKeyDown(event: React.KeyboardEvent<HTMLDivElement>) {
    if (event.key === 'Enter' || event.key === ' ') {
      handleClick();
    }
  }

  return (
    <div
      className='flex justify-center items-center h-screen'
      role='button'
      tabIndex={0}
      onKeyDown={handleKeyDown}
      onClick={handleClick}
    >
      <div className='flex flex-col items-center'>
        <img className='w-56 h-56 shadow-xl' src={logo} alt='logo' />
        <p className='mt-10'>
          고객님의 주문번호는
          <span className='font-bold text-red-500'>{id}</span>
          번입니다.
        </p>
        <p className='font-bold'>감사합니다.</p>
        <p className='mt-10'>
          회원님의 적립금은 <span className='font-bold'>{point}</span>원 입니다.
        </p>
        <p className='mt-10'>
          {countdown} 초 후에 첫 화면으로 자동으로 이동됩니다.
        </p>
      </div>
    </div>
  );
}

export default OrderComplete;
