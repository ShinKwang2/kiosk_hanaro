import { useEffect, useState } from 'react';
import logo from '../static/img/logo.png';
import set from '../static/img/set.png';
import takeout from '../static/img/takeout.jpg';
import { useNavigate } from 'react-router-dom';

function CheckTakeOut() {
  const [selectedCheck, setSelectedCheck] = useState<string | null>(null);
  const [selectedLanguage, setSelectedLanguage] = useState<string | null>(null);
  const navigate = useNavigate();

  useEffect(() => {
    if (selectedCheck !== null && selectedLanguage !== null) {
      navigate('/recMenu');
    }
  }, [selectedCheck, selectedLanguage, navigate]);

  const handleCheckClick = (checkId: string | null) => {
    setSelectedCheck(checkId);
  };

  const handleLanguageClick = (languageId: string | null) => {
    setSelectedLanguage(languageId);
  };

  return (
    <div className='m-10 bg-green-900 p-20 w-50 flex flex-col items-center'>
      <div className='flex items-center justify-center'>
        <img src={logo} alt='logo' />
        <p className='text-white font-bold ml-10'>
          식사하실 장소를 선택해주세요
        </p>
      </div>
      <div className='flex text-center justify-center items-center my-3'>
        <div
          id='check1'
          className={`flex flex-col items-center ${selectedCheck === 'check1' ? 'bg-gray-300' : ''}`}
          onClick={() => handleCheckClick('check1')}
          onKeyDown={() => handleCheckClick('check1')} // 키보드 이벤트 처리 추가
          role='button'
          tabIndex={0}
        >
          <div className='bg-white rounded-xl w-60 h-80 m-3 justify-center flex flex-col items-center'>
            <img src={set} alt='holl' className='h-40 w-50' />
            <p className='text-red-500 font-bold'>매장에서 식사</p>
          </div>
        </div>
        <div
          id='check2'
          className={`flex flex-col items-center ${selectedCheck === 'check2' ? 'bg-gray-300' : ''}`}
          onClick={() => handleCheckClick('check2')}
          onKeyDown={() => handleCheckClick('check2')} // 키보드 이벤트 처리
          role='button'
          tabIndex={0}
        >
          <div className='bg-white rounded-xl w-60 h-80 m-3 justify-center flex flex-col items-center'>
            <img src={takeout} alt='takeoutImg' className='h-40 w-40' />
            <p className='text-red-500 font-bold'>테이크 아웃</p>
          </div>
        </div>
      </div>
      <p className='text-white font-bold'>Please select your language</p>
      <div className='flex text-center font-bold'>
        <div
          id='ko'
          className={`flex justify-center items-center bg-white rounded-xl w-60 h-12 m-3 ${selectedLanguage === 'ko' ? 'bg-gray-300' : ''}`}
          onClick={() => handleLanguageClick('ko')}
          onKeyDown={() => handleLanguageClick('ko')} // 키보드 이벤트 처리
          role='button'
          tabIndex={0}
        >
          한국어
        </div>
        <div
          id='en'
          className={`flex justify-center items-center bg-white rounded-xl w-60 h-12 m-3 ${selectedLanguage === 'en' ? 'bg-gray-300' : ''}`}
          onClick={() => handleLanguageClick('en')}
          onKeyDown={() => handleLanguageClick('en')} // 키보드 이벤트 처리 추가
          role='button'
          tabIndex={0}
        >
          English
        </div>
      </div>
    </div>
  );
}

export default CheckTakeOut;
