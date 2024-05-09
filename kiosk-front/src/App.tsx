import CartList from './components/menu/CartList';
import Menu from './components/menu/Menu';
import { SelectedMenuProvider } from './contexts/selectedMenu-context';

function App() {
  return (
    <div className='bg-slate-200 w-3/4 m-4 mx-auto h-screen min-w-full'>
      <div className='bg-red-500 h-1/5'>광고</div>
      {/* <div className='bg-purple-500 p-3 h-3/5 flex'> */}
      {/* <div className='´bg-green-400 w-1/4'>메뉴리스트</div> */}
      {/* <MenuTabList /> */}
      <SelectedMenuProvider>
        <Menu />
      </SelectedMenuProvider>
      {/* <div className='bg-gray-300 w-3/4'>메뉴 디테일</div> */}
      {/* </div> */}
      <div className='bg-yellow-400 h-1/5'>
        <CartList />
      </div>
    </div>
  );
}

export default App;
