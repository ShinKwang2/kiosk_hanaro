import MenuTabList from './MenuTabList';
import ProductList from './ProductList';
import { MenuTitle } from '../../types/types';

const menuTitles: MenuTitle[] = [
  { menuName: '추천메뉴', tapIndex: 0, type: 'recommended' },
  { menuName: '버거&세트', tapIndex: 1, type: 'hamburger' },
  { menuName: '맥모닝', tapIndex: 2, type: 'mcmorning' },
  { menuName: '디저트', tapIndex: 3, type: 'dessert' },
  { menuName: '음료', tapIndex: 4, type: 'drink' },
];

const Menu = () => {
  return (
    <div className='bg-purple-500 p-3 h-3/5 flex'>
      <MenuTabList menuTitles={menuTitles} />
      <ProductList />
    </div>
  );
};

export default Menu;
