import CartList from '../components/menu/CartList';
import Menu from '../components/menu/Menu';
import { SelectedMenuProvider } from '../contexts/selectedMenu-context';

const MainPage = () => {
  return (
    <div className='bg-slate-200 w-3/4 m-4 mx-auto h-screen min-w-full'>
      <div className='bg-red-500 h-1/5'>광고</div>
      <SelectedMenuProvider>
        <Menu />
      </SelectedMenuProvider>
      <div className='bg-yellow-400 h-1/5'>
        <CartList />
      </div>
    </div>
  );
};

export default MainPage;
