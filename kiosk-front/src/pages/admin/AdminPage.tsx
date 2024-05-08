import { useState } from 'react';
import AdminMenu from '../../components/AdminMenu';
import MemberList from '../../components/MemberList';
import logo from '../../static/img/logo.png';

function AdminPage() {
  const [selectedMenu, setSelectedMenu] = useState<number>(0);

  return (
    <div className='m-10 p-20 w-50 flex flex-col items-center bg-green-900'>
      <div className='flex justify-center items-center'>
        <img src={logo} alt='logo' className='w-10 h-auto' />
        <h1 className='text-2xl font-bold ml-3 text-white'>관리자 화면</h1>
      </div>
      <div className='border-2 border-gray-500 bg-white p-10 rounded-2xl mt-10 w-full text-center shadow-lg'>
        <h1 className='text-xl font-bold mb-14'>회원 목록</h1>
        <div className='flex justify-center'>
          <div className='flex flex-col text-center'>
            <AdminMenu value='회원관리' onClick={() => setSelectedMenu(0)} />
            <AdminMenu value='상품관리' onClick={() => setSelectedMenu(1)} />
            <AdminMenu value='주문관리' onClick={() => setSelectedMenu(2)} />
          </div>
          <div className='flex flex-col items-end'>
            {selectedMenu === 0 && <MemberList />}
          </div>
        </div>
      </div>
    </div>
  );
}

export default AdminPage;
