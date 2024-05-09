import { useState } from 'react';
import { useNavigate } from 'react-router-dom';

function RecMenu() {
  const items = [
    { id: 1, product: '햄버거' },
    { id: 2, product: '콜라' },
    { id: 3, product: '아스크림' },
    { id: 4, product: '감튀' },
    { id: 5, product: '치즈' },
    { id: 6, product: '우유' },
    { id: 7, product: '사이다' },
    { id: 8, product: '제로' },
    { id: 9, product: '맥너겟' },
  ];
  const [showModal, setShowModal] = useState(false);
  const navigate = useNavigate(); // useNavigate 훅을 사용하여 navigate 함수를 가져옴

  function handleModalOpen() {
    setShowModal(true);
  }

  function handleModalClose() {
    setShowModal(false);
  }

  function handlePrevious() {
    history.back();
  }

  function handlePointPage() {
    navigate('/point');
  }

  function handleCheckOrder() {
    navigate('/checkOrder');
  }

  return (
    <div className='m-10 border-2 p-20 w-50 bg-green-900'>
      <h1 className='font-bold text-lg text-center mb-10 text-white'>
        함께 즐기면 더욱 좋습니다!
      </h1>
      <div className='flex justify-center'>
        <div className='grid grid-cols-3 grid-rows-3'>
          {items.map((item) => (
            <div
              key={item.id}
              className='border border-gray-400 p-4 m-2 shadow-md hover:shadow-xl'
            >
              main 메뉴 컴포넌트 <br />
              {item.product}
            </div>
          ))}
        </div>
      </div>
      <div className='flex justify-center mt-20'>
        <button
          className='py-2 px-8 rounded-lg shadow-xl font-bold mr-1 border-2 w-60 text-white hover:bg-white hover:text-gray-600'
          onClick={handlePrevious}
        >
          이전
        </button>
        <button
          className='py-2 px-8 rounded-lg shadow-xl font-bold ml-1 border-2 w-60 text-white hover:bg-white hover:text-gray-600'
          onClick={handleModalOpen}
        >
          선택안함
        </button>
      </div>
      {/* 모달 영역 */}
      {showModal && (
        <div className='fixed top-0 left-0 right-0 bottom-0 bg-gray-800 bg-opacity-50 flex items-center justify-center'>
          <div className='bg-white p-8 rounded-lg w-80 flex-col'>
            <button
              className='font-extrabold text-gray-500 ml-'
              onClick={handleModalClose}
            >
              X
            </button>
            <div>
              <p className='font-bold text-center'>
                포인트를 적립하시겠습니까?
              </p>
              <div className='flex justify-between mt-4'>
                <button
                  className='w-60 mr-1 py-2 px-4 rounded-lg shadow-md bg-gray-200 hover:bg-green-800 hover:text-white'
                  onClick={handlePointPage}
                >
                  예
                </button>
                <button
                  className='w-60 ml-1 py-2 px-4 rounded-lg shadow-md bg-gray-200 hover:bg-green-800 hover:text-white'
                  onClick={handleCheckOrder}
                >
                  건너뛰기
                </button>
              </div>
            </div>
          </div>
        </div>
      )}
    </div>
  );
}

export default RecMenu;
