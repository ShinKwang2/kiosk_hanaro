import { Link } from 'react-router-dom';
import start from '../static/img/randing.png';

function Randing() {
  return (
    <div className='m-10 p-20 w-50 flex flex-col items-center relative'>
      <Link to='/checkTakeout'>
        <img src={start} alt='start img' />
      </Link>
      <p className='text-white font-bold absolute bottom-28 pl-20'>
        주문하시려면 터치하세요!
      </p>
    </div>
  );
}

export default Randing;
