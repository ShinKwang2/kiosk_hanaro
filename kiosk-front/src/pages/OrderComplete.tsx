import logo from '../static/img/logo2.png';

function OrderComplete() {
  const orderNumber = 2224;
  const point = 1200;

  return (
    <div className='flex justify-center items-center h-screen'>
      <div className='flex flex-col items-center'>
        <img src={logo} alt='logo' />
        <p className='mt-10'>
          고객님의 주문번호는
          <span className='font-bold text-red-500'>{orderNumber}</span>
          번입니다.
        </p>
        <p className='font-bold'>감사합니다.</p>
        <p className='mt-10'>
          회원님의 적립금은 <span className='font-bold'>{point}</span>원 입니다.
        </p>
      </div>
    </div>
  );
}

export default OrderComplete;
