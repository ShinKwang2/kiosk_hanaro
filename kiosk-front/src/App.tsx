import Menu from './components/Menu';

function App() {
  return (
    <div className='bg-slate-200 w-3/4 m-4 mx-auto h-screen'>
      <div className='bg-red-500 h-1/5'>광고</div>
      {/* <div className='bg-purple-500 p-3 h-3/5 flex'> */}
      {/* <div className='´bg-green-400 w-1/4'>메뉴리스트</div> */}
      {/* <MenuTabList /> */}
      <Menu />
      {/* <div className='bg-gray-300 w-3/4'>메뉴 디테일</div> */}
      {/* </div> */}
      <div className='bg-yellow-400 h-1/5'>장바구니</div>
    </div>
  );
}

export default App;
