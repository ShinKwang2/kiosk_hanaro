import { useState } from 'react';
import KeyPad from '../components/KeyPad';
import logo from '../static/img/logo.png';
import ShowPoint from './ShowPoint';

function Point() {
  const [phoneNumber, setPhoneNumber] = useState('');
  const [showModal, setShowModal] = useState(false);
  const [showPoint, setShowPoint] = useState(false);
  const userPoint = 70;

  function handleModalClose() {
    setShowModal(false);
  }
  function handleModalOpen() {
    setShowModal(true);
  }
  function handleShowPoint() {
    setShowModal(false);
    setShowPoint(true);
  }

  function keyPadClick(value: number) {
    if (value === -1) {
      setPhoneNumber((prev) => prev.slice(0, -1));
    } else if (value === 12) {
      if (phoneNumber.length === 11) {
        handleModalOpen();
      }
    } else if (phoneNumber.length >= 11) {
      return;
    } else {
      setPhoneNumber((prev) => prev + (value === 11 ? 0 : String(value)));
    }
  }

  function formatPhoneNumber(value: string) {
    return value.replace(/(\d{3})(\d{4})(\d{4})/, '$1-$2-$3');
  }

  const keyPad = [];
  for (let i = 1; i <= 12; i++) {
    keyPad.push(
      <KeyPad value={i} onClick={keyPadClick} phoneNumber={phoneNumber} />
    );
  }

  return (
    <div className='m-10 bg-green-900 p-20 w-50'>
      <div className='flex justify-center'>
        <img src={logo} alt='logo' className='w-10 h-10' />
        <h1 className='font-bold ml-3 mt-2 text-white'>
          휴대폰 번호를 입력해주세요
        </h1>
      </div>
      <div className='flex flex-col justify-center items-center'>
        <input
          type='text'
          className='rounded-lg m-4 w-72 h-10 text-center'
          value={phoneNumber}
          readOnly
        />
        <div className='grid grid-cols-3 gap-1'>{keyPad}</div>
      </div>
      {showModal && (
        <div className='fixed top-0 left-0 right-0 bottom-0 bg-gray-800 bg-opacity-50 flex items-center justify-center'>
          <div className='bg-white p-8 rounded-lg relative'>
            <button
              className='absolute top-4 right-4'
              onClick={handleModalClose}
            >
              X
            </button>
            <p className='mt-3'>
              입력된 번호 {formatPhoneNumber(phoneNumber)} 이(가) 맞습니까?
            </p>
            <div className='flex justify-center mt-4'>
              <button
                className='w-20 h-10 py-2 px-4 rounded-lg shadow-md bg-green-500 text-white mr-2'
                onClick={handleShowPoint}
              >
                예
              </button>
              <button
                className='w-20 h-10 py-2 px-4 rounded-lg shadow-md bg-red-500 text-white'
                onClick={handleModalClose}
              >
                아니오
              </button>
            </div>
          </div>
        </div>
      )}
      {showPoint && <ShowPoint point={userPoint} />}
    </div>
  );
}

export default Point;
