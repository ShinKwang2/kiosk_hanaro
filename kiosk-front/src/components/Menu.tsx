import { useState } from 'react';
import MenuTabList from './MenuTabList';
import ProductList from './ProductList';
import { MenuTitle } from '../types/types';

const menuTitles: MenuTitle[] = [
  { menuName: '추천메뉴', isSelected: true, tapIndex: 0 },
  { menuName: '버거&세트', isSelected: false, tapIndex: 1 },
  { menuName: '맥모닝', isSelected: false, tapIndex: 2 },
  { menuName: '디저트', isSelected: false, tapIndex: 3 },
  { menuName: '음료', isSelected: false, tapIndex: 4 },
];

const Menu = () => {
  const [selected, setSelected] = useState<string>(menuTitles[0].menuName);

  const onClickMenu = (menuTitle: string) => {
    setSelected(menuTitle);
  };

  return (
    <div className='bg-purple-500 p-3 h-3/5 flex'>
      <MenuTabList menuTitles={menuTitles} onClickMenu={onClickMenu} />
      <ProductList menuTitle={selected} />
    </div>
  );
};

export default Menu;
